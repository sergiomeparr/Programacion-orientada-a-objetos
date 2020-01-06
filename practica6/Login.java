
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JFrame;

public class Login extends Panel implements ActionListener, Runnable, Serializable {

    //Constantes globales
    private final int puerto = 5000;
    private final String host = "localhost";
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    private Socket cliente;
    //Variables GUI
    Button conecta, conecta1;
    TextField tf;

    public Login() {
        Panel p1, p2;
        p1 = new Panel();
        p2 = new Panel();
        p1.setLayout(new GridLayout(3, 3));
        p2.setLayout(new BorderLayout());

        conecta = new Button("Conecta");
        conecta.addActionListener(this);
        conecta1 = new Button("Envia");
        conecta1.addActionListener(this);
        tf = new TextField(15);
        p2.add(conecta, BorderLayout.NORTH);
        p2.add(conecta1, BorderLayout.CENTER);
        p2.add(tf, BorderLayout.SOUTH);
        add(p2);
    }

    public static void main(String args[]) {
        JFrame f = new JFrame("Login");
        f.setTitle("Cliente");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add("Center", new Login());
        f.setSize(250, 250);
        f.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Button btn = (Button) e.getSource();
        if (btn == conecta) {
            conectar();
        } else {
            if (!e.getActionCommand().equals("Conecta")) {

                //Crear objeto tipo UseSession
                try {
                    UserSession c = new UserSession();
                    c.setUser(tf.getText());
                    c.setLoginTime(new Date());
                    writer.writeObject(c);
                } catch (IOException ex) {
                    System.out.println("Error: " + ex.toString());
                }
            }
        }
    }

    void conectar() {

        try {
            //Conectarse al servidor
            cliente = new Socket("localhost", puerto);
            //Obtener flujos
            reader = new ObjectInputStream(cliente.getInputStream());
            writer = new ObjectOutputStream(cliente.getOutputStream());
        } catch (IOException ioe) {
            System.out.println("Fallo creacion Socket" + ioe);
        }

        //Crea un hilo
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run() {

        try {
            //Leer el mensaje del servidor
            Object object = reader.readObject();
            ArrayList<UserSession> us = (ArrayList<UserSession>) object;

            //Imprimir mensaje del servidor
            System.out.println("\n-------------------------- LISTA DE CLIENTES --------------------------");
            for (int i = 0; i < us.size(); i++) {
                System.out.println("USUARIO: " + us.get(i).getUser() + "\t" + "FECHA: " + us.get(i).getLoginTime());
            }

            //Cerrar conexion
            cliente.close();

            System.out.println("Conexion con el servidor esta cerrada? " + cliente.isClosed());
            System.out.println("-----------------------------------------------------------------------");

        } catch (IOException e) {
            System.out.println("IO ex" + e);
        } catch (ClassNotFoundException ex) {
            System.out.println("Class no found" + ex);
        }
    }
}
