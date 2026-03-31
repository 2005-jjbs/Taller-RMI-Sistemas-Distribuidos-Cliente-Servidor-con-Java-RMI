// Interfaz RMI que define los métodos que el servidor remoto
// ServicioEstudiantes expondrá a los clientes.
// Cada método declara RemoteException porque puede fallar la comunicación remota.
package remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import model.Estudiante;

public interface ServicioEstudiantes extends Remote {

    // Devuelve el promedio del estudiante identificado por su nombre.
    // Lanza RemoteException en caso de fallo de comunicación remota.
    double obtenerPromedio(String nombre) throws RemoteException;

    // Devuelve el grupo de trabajo del estudiante identificado por su apellido.
    // Lanza RemoteException en caso de fallo de comunicación remota.
    String obtenerGrupo(String apellido) throws RemoteException;

    // Devuelve la lista de evaluaciones disponibles (por ejemplo: "Quiz1", "Taller1").
    // Lanza RemoteException en caso de fallo de comunicación remota.
    List<String> obtenerEvaluaciones() throws RemoteException;

    // Busca estudiantes por nombre y devuelve todas las coincidencias.
    List<Estudiante> buscarEstudiantesPorNombre(String nombre) throws RemoteException;

}