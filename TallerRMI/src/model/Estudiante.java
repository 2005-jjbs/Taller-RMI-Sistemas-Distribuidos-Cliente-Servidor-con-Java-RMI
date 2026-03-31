
// Representar un estudiante con nombre, apellido, grupo de trabajo y dos notas.
// Esta clase es serializable para poder enviarla por RMI si fuera necesario.
package model;


import java.io.Serializable;

public class Estudiante implements Serializable {

    // Campos del estudiante
    private String nombre;
    private String apellido;
    private String grupoTrabajo;
    private float quiz1;
    private float taller1;

    // Constructor: inicializa los datos básicos del estudiante
    public Estudiante(String nombre, String apellido, String grupoTrabajo, float quiz1, float taller1) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.grupoTrabajo = grupoTrabajo;
        this.quiz1 = quiz1;
        this.taller1 = taller1;

    }

    // Devuelve el nombre del estudiante
    public String getNombre() {
        return nombre;
    }

    // Devuelve el apellido del estudiante
    public String getApellido() {
        return apellido;
    }

    // Devuelve el grupo de trabajo al que pertenece el estudiante
    public String getGrupoTrabajo() {
        return grupoTrabajo;
    }

    // Devuelve la nota del quiz1
    public float quiz1() {
        return quiz1;
    }

    // Devuelve la nota del taller1
    public float taller1() {
        return taller1;
    }

    // Calcula y devuelve el promedio simple entre quiz1 y taller1
    public float getPromedio() {
        return (quiz1 + taller1) / 2;
    }
}
