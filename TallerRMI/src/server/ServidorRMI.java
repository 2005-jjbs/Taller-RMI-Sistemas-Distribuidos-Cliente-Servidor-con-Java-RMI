// Clase con el método main que levanta el servidor RMI,
// crea el registro en el puerto 1099 y publica (rebind) la implementación
// del servicio remoto bajo el nombre "ServicioEstudiantes".
package server;

import remote.ServicioEstudiantes;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServidorRMI {

    public static void main(String[] args) {

        try {


            System.setProperty("java.rmi.server.hostname", "10.43.100.115");
            ServicioEstudiantes servicio = new ServicioEstudiantesImpl();

            // Crea un registro RMI en el puerto 1099 (puede lanzar RemoteException)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Publica el servicio en el registro para que los clientes puedan buscarlo
            registry.rebind("ServicioEstudiantes", servicio);

            System.out.println("Servidor RMI listo...");

        } catch (Exception e) {

            // Manejo simple de errores: imprime la traza (útil en desarrollo)
            e.printStackTrace();

        }

    }
}