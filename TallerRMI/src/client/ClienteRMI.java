// Cliente RMI que consulta al servidor remoto ServicioEstudiantes
// para obtener promedios, grupos y la lista de evaluaciones.
package client;


import remote.ServicioEstudiantes;
import model.Estudiante;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class ClienteRMI {

    public static void main(String[] args) {

        try {

            // Obtiene el registro RMI en ip y puerto donde el servidor está publicado
            Registry registry = LocateRegistry.getRegistry("10.43.100.115", 1099);

            // Busca el objeto remoto con el nombre "ServicioEstudiantes"
            ServicioEstudiantes servicio = (ServicioEstudiantes) registry.lookup("ServicioEstudiantes");

            Scanner sc = new Scanner(System.in);
            
            do {
                // Menú simple por consola
                System.out.println("Bienvenido al cliente RMI de estudiantes");
                System.out.println("1. Consultar promedio");
                System.out.println("2. Consultar grupo");
                System.out.println("3. Ver evaluaciones");
                System.out.println("0. Salir");

                int opcion = sc.nextInt();
                sc.nextLine();

                switch (opcion) {

                    case 0:

                        System.out.println("Saliendo...");
                        sc.close();
                        return;

                    case 1:

                        // Pide nombre y solicita al servicio remoto la lista de coincidencias
                        System.out.print("Ingrese nombre o apellido del estudiante: ");
                        String nombre = sc.nextLine();

                        List<Estudiante> coincidencias = servicio.buscarEstudiantesPorNombre(nombre);

                        if (coincidencias == null || coincidencias.isEmpty()) {
                            System.out.println("No se encontró ningún estudiante con ese nombre o apellido.");
                        } else if (coincidencias.size() == 1) {
                            double promedio = coincidencias.get(0).getPromedio();
                            System.out.println("Promedio: " + promedio);
                        } else {
                            System.out.println("Hay varios estudiantes con ese nombre/apellido:");
                            for (int i = 0; i < coincidencias.size(); i++) {
                                Estudiante e = coincidencias.get(i);
                                System.out.println((i + 1) + ". " + e.getNombre() + " " + e.getApellido());
                            }
                            System.out.print("Seleccione el número correspondiente al estudiante: ");
                            int seleccion = -1;
                            try {
                                seleccion = Integer.parseInt(sc.nextLine());
                            } catch (NumberFormatException ex) {
                                System.out.println("Selección inválida.");
                                break;
                            }

                            if (seleccion < 1 || seleccion > coincidencias.size()) {
                                System.out.println("Selección fuera de rango.");
                                break;
                            }

                            Estudiante elegido = coincidencias.get(seleccion - 1);
                            System.out.println("Promedio: " + elegido.getPromedio());
                        }

                        break;

                    case 2:

                        // Pide apellido y solicita el grupo al servicio remoto
                        System.out.print("Ingrese apellido del estudiante: ");
                        String apellido = sc.nextLine();

                        String grupo = servicio.obtenerGrupo(apellido);

                        System.out.println("Grupo: " + grupo);

                        break;

                    case 3:

                        // Solicita la lista de evaluaciones al servicio remoto
                        List<String> evaluaciones = servicio.obtenerEvaluaciones();

                        System.out.println("Evaluaciones:");

                        for (String e : evaluaciones) {
                            System.out.println(e);
                        }

                        break;

                    default:

                        System.out.println("Opción inválida");

                }
            }
            while (true);}
        catch (Exception e) {
            
            // Manejo simple de errores: imprime la traza (útil en desarrollo)
            e.printStackTrace();
        }
    }
}
