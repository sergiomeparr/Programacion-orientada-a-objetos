
import java.io.*;
import java.net.*;
import java.util.*;

public class VerySimpleChatServer {

    private ArrayList<UserSession> users;   // Lista que guarda todos los uaurios conecatdos (Historial)

    public static void main(String[] args) {
        new VerySimpleChatServer().go();
    }

    public void go() {

        users = new ArrayList<>();  //Inicializar el historial

        try {

            //Crea un nuevo SocketServidor (Crea el servidor)
            ServerSocket serverSock = new ServerSocket(5000);
			System.out.println("\n --------------- INFORMACION SERVIDOR -----------------");
			System.out.println("HOST: \t" + serverSock.getLocalSocketAddress().toString());
			System.out.println("IP: \t" + serverSock.getLocalPort());
			System.out.println("---------------------------------------------------------");
			
            while (true) {

                //Servidor en espera de un nuevo cliente
                Socket clientSocket = serverSock.accept(); //Cliente conectado
				//usuario			//es el metodo para escuchar clientes
				System.out.println("xx NUEVO CLIENTE CONECTADO, ESPERANDO TRANSACCION XX");

                //Crear un hilo nuevo para el cliente que se acaba de conectar
                Thread t = new Thread(new ClientHandler(clientSocket));
				//hilo 						clase 		objeto 
                t.start(); // t.start =  public void run
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }

    public class ClientHandler implements Runnable {
									// interfaz que permite correr hilos 
        private ObjectOutputStream writer;   //Canal para escribirle al cliente
        private ObjectInputStream reader;   //Canal para leer mensajes del cliente
        private Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        public void run() {
            try {
				
                //Obtener canales de escritura y lectura
                writer = new ObjectOutputStream(clientSocket.getOutputStream());
                reader = new ObjectInputStream(clientSocket.getInputStream());

                //Leer el mensaje que mando el cliente
                UserSession obj = (UserSession) reader.readObject();

                //Imprimir información en el servidor
				System.out.println("\n--------------------------------------------- NUEVA CONEXION -------------------------------------");
                System.out.println("Nuevo cliente conectado: " + clientSocket.getInetAddress());
				
                System.out.println("Nombre de usuario: " + obj.getUser() + " \t HORA CONEXION: " + obj.getLoginTime());
                users.add(obj); //Agrega el cliente que se acaba de conectar al Historial
                writer.writeObject(users); //Escribe el historial en el canal de salida hacia el cliente

                //Cerrar conexión con el cliente
                clientSocket.close();
                
                if(clientSocket.isClosed()){
                    System.out.println("Conexion con el cliente ha terminado satisfactoriamente");
                }
                System.out.println("--------------------------------------------------------------------------------------------------\n");

            } catch (IOException | ClassNotFoundException ex) {
                System.out.println("Error 2: " + ex.toString());
            }
        } 
    } 
}
