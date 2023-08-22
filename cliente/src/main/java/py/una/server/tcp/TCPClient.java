package py.una.server.tcp;


import java.io.*;
import java.net.*;

public class TCPClient {

    public static void main(String[] args) throws IOException {

        Socket unSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            unSocket = new Socket("localhost", 4444);
            // enviamos nosotros
            out = new PrintWriter(unSocket.getOutputStream(), true);

            //viene del servidor
            in = new BufferedReader(new InputStreamReader(unSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Host desconocido");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error de I/O en la conexion al host");
            System.exit(1);
        }

        
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String fromServer;
        //String fromUser;
        String nombre;
        String apellido;
        int cedula;

        while ((fromServer = in.readLine()) != null) {
            //System.out.println("Servidor: " + fromServer);
            System.out.println("Servidor: " + fromServer);
            if (fromServer.equals("Bye")) {
                break;
            }
            System.out.println("Ingrese su nombre: ");
            //fromUser = stdIn.readLine();
            nombre = stdIn.readLine();
            
            System.out.println("Ingrese su apellido: ");
            apellido = stdIn.readLine();
            
            System.out.println("Ingrese su cedula: ");
            cedula = Integer.parseInt(stdIn.readLine());

            if (nombre != null && apellido != null && cedula != 0) {
                System.out.println("Bievenido usuario con Nombre: " + nombre + " Apellido: " + apellido + " Cedula: " + cedula);

                //escribimos al servidor
                out.println(nombre);
                //out.println(apellido);
                //out.println(cedula);
            }
           
            
        }

        out.close();
        in.close();
        stdIn.close();
        unSocket.close();
    }
}
