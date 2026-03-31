// Mantener en memoria una lista de estudiantes de ejemplo
// y proporcionar métodos de búsqueda por nombre y apellido.
package server;

import model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class BaseDatosEstudiantes {

    // Lista en memoria con estudiantes de ejemplo
    private List<Estudiante> estudiantes;

    // Constructor: inicializa la lista con datos de prueba
    public BaseDatosEstudiantes() {

        estudiantes = new ArrayList<>();

        estudiantes.add(new Estudiante("Maria", "Perez", "GRT1", 4.5F, 4));
        estudiantes.add(new Estudiante("José", "Montealegre", "GRT1", 4.25F, 4));
        estudiantes.add(new Estudiante("Juan", "Sanchez", "GRT2", 5, 4.5F));
        estudiantes.add(new Estudiante("Mariana", "Tellez", "GRT2", 5, 4.5F));
        estudiantes.add(new Estudiante("Miguel", "Castiblanco", "GRT3", 5, 4.4F));
        estudiantes.add(new Estudiante("Thamara", "Ospina", "GRT3", 5, 4.4F));
        estudiantes.add(new Estudiante("Pedro", "Berrizbeitia", "GRT4", 4.75F, 0));
        estudiantes.add(new Estudiante("Samira", "Morales", "GRT4", 4.9F, 3));
        estudiantes.add(new Estudiante("Thomas", "Sarmiento", "GRT5", 4.5F, 4));
        estudiantes.add(new Estudiante("Lucía", "Montenegro", "GRT5", 0, 4));
        estudiantes.add(new Estudiante("Juan", "Madrigal", "GRT5", 4.5F, 4));
        estudiantes.add(new Estudiante("Nicolas", "Morales", "GRT6", 5, 2.8F));
        estudiantes.add(new Estudiante("Daniela", "Bohorquez", "GRT7", 4.8F, 2.3F));
        estudiantes.add(new Estudiante("Mariana", "Diaz", "GRT7", 4.8F, 2.3F));
        estudiantes.add(new Estudiante("Alejandro", "Parrado", "GRT8", 4.9F, 4.3F));
        estudiantes.add(new Estudiante("Silvestre", "Vargas", "GRT8", 4.9F, 4.3F));
        estudiantes.add(new Estudiante("Juliana", "Araque", "GRT9", 5, 0));
        estudiantes.add(new Estudiante("Juan Ignacio", "Quintero", "GRT9", 0, 3));
        estudiantes.add(new Estudiante("Mónica", "Jimenez", "GRT9", 5, 3));
    }

    // Busca estudiantes por nombre (no distingue mayúsculas/minúsculas)
    // Ahora devuelve todas las coincidencias en una lista.
    public List<Estudiante> buscarPorNombre(String nombre) {

        List<Estudiante> coincidencias = new ArrayList<>();

        for (Estudiante e : estudiantes) {
            if (e.getNombre().equalsIgnoreCase(nombre)) {
                coincidencias.add(e);
            }
        }

        return coincidencias;
    }

    // Busca estudiantes por apellido (no distingue mayúsculas/minúsculas)
    // Ahora devuelve todas las coincidencias en una lista.
    public List<Estudiante> buscarPorApellido(String apellido) {

        List<Estudiante> coincidencias = new ArrayList<>();

        for (Estudiante e : estudiantes) {
            if (e.getApellido().equalsIgnoreCase(apellido)) {
                coincidencias.add(e);
            }
        }

        return coincidencias;
    }

}