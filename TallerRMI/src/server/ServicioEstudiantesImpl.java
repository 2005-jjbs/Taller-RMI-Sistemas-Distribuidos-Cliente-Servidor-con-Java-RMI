// Implementación del servicio remoto ServicioEstudiantes.
// Esta clase extiende UnicastRemoteObject para exportarse como objeto RMI y
// delega la búsqueda de datos en BaseDatosEstudiantes en memoria.
package server;


import remote.ServicioEstudiantes;
import model.Estudiante;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServicioEstudiantesImpl extends UnicastRemoteObject implements ServicioEstudiantes {

    // Base de datos en memoria con estudiantes de ejemplo
    private BaseDatosEstudiantes baseDatos;

    // Constructor: crea la base de datos en memoria y exporta el objeto remoto
    protected ServicioEstudiantesImpl() throws RemoteException {
        super(5000);
        baseDatos = new BaseDatosEstudiantes();
    }

    @Override
    public double obtenerPromedio(String nombre) throws RemoteException {

        // Normaliza la entrada: evita nulls y espacios en blanco
        if (nombre == null) {
            return -1;
        }

        String clave = nombre.trim();

        if (clave.isEmpty()) {
            return -1;
        }

        // Busca coincidencias tanto por nombre como por apellido
        List<Estudiante> coincidencias = buscarEstudiantesPorNombre(clave);

        if (coincidencias.size() == 1) {
            return coincidencias.get(0).getPromedio();
        } else if (coincidencias.size() > 1) {
            // Indica ambigüedad: hay varias personas con ese nombre/apellido
            return -2;
        }

        return -1; // no encontrado
    }

    @Override
    public String obtenerGrupo(String apellido) throws RemoteException {

        if (apellido == null) {
            return "Estudiante no encontrado";
        }

        String clave = apellido.trim();

        if (clave.isEmpty()) {
            return "Estudiante no encontrado";
        }

        List<Estudiante> coincidencias = baseDatos.buscarPorApellido(clave);

        if (coincidencias.size() == 1) {
            return coincidencias.get(0).getGrupoTrabajo();
        } else if (coincidencias.size() > 1) {
            return "Varios estudiantes encontrados con ese apellido, especifique nombre.";
        }

        return "Estudiante no encontrado";
    }

    @Override
    public List<String> obtenerEvaluaciones() throws RemoteException {

        // Devuelve la lista de evaluaciones que el servicio reconoce
        return Arrays.asList("Quiz1", "Taller1");

    }

    @Override
    public List<Estudiante> buscarEstudiantesPorNombre(String nombre) throws RemoteException {
        List<Estudiante> resultado = new ArrayList<>();

        if (nombre == null) {
            return resultado;
        }

        String clave = nombre.trim();

        if (clave.isEmpty()) {
            return resultado;
        }

        // Buscar por nombre
        List<Estudiante> porNombre = baseDatos.buscarPorNombre(clave);
        if (porNombre != null && !porNombre.isEmpty()) {
            resultado.addAll(porNombre);
        }

        // Buscar por apellido y agregar sin duplicados
        List<Estudiante> porApellido = baseDatos.buscarPorApellido(clave);
        if (porApellido != null && !porApellido.isEmpty()) {
            for (Estudiante e : porApellido) {
                boolean ya = false;
                for (Estudiante r : resultado) {
                    if (r.getNombre().equalsIgnoreCase(e.getNombre()) && r.getApellido().equalsIgnoreCase(e.getApellido())) {
                        ya = true;
                        break;
                    }
                }
                if (!ya) {
                    resultado.add(e);
                }
            }
        }

        return resultado;
    }
}
