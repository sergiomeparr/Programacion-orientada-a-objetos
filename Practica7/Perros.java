import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Perros extends javax.swing.JFrame implements ActionListener {

    JLabel Nombre, Edad, Raza, Genero;
    JTextField campo1, campo2, campo3, campo4;
    JButton conectar, insertar;
    Connection conexion = null;
    public Perros() {
        initComponents();
        setTitle("Base de Datos(Access) en java");
        Nombre = new JLabel("Nombre:");
        Nombre.setBounds(20,20,50,50);
        add(Nombre);
        
        Raza = new JLabel("Raza:");
        Raza.setBounds(35,60,50,50);
        this.add(Raza);
        
        Edad = new JLabel("Edad:");
        Edad.setBounds(35,100,50,50);
        this.add(Edad);
        
        Genero = new JLabel("Genero:");
        Genero.setBounds(22,140,50,50);
        add(Genero);
        
        campo1 = new JTextField(" ");
        campo1.setBounds(80,20,150,40);
        add(campo1);
        
        campo2 = new JTextField(" ");
        campo2.setBounds(80,60,150,40);
        add(campo2);
        
        campo3 = new JTextField(" ");
        campo3.setBounds(80,100,150,40);
        add(campo3);
        
        campo4 = new JTextField(" ");
        campo4.setBounds(80,140,150,40);
        add(campo4);
        
        conectar = new JButton("Conectar");
        conectar.setBounds(250,40,100,50);
        add(conectar);
        conectar.addActionListener(this);
        
        insertar = new JButton("Insertar");
        insertar.setBounds(250,120,100,50);
        add(insertar);
        insertar.addActionListener(this);
        
        setSize(400,250);
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) { new Perros();}
    
    public void actionPerformed(ActionEvent ae) {
        String url = "jdbc:ucanaccess://C:/base/Base.mdb";
        Connection conexion;        
        String driver = "net.ucanaccess.jdbc.UcanaccessDriver"; 
        JButton btn = (JButton)ae.getSource();
        if(btn==conectar){
            try{
                Class.forName(driver);
                conexion=DriverManager.getConnection(url);
                JOptionPane.showMessageDialog(null, "Conexion establecida");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "El error es "+ex);
            }
        }
        else if(btn==insertar){
            try{
                conexion=DriverManager.getConnection(url);
                Statement s = conexion.createStatement();
                String access = "INSERT INTO Perros(Nombre,Raza,Edad,Genero)" 
                        + " values('" +this.campo1.getText()+"',"
                        + "'" +this.campo2.getText()+"',"
                        + "'" +this.campo3.getText()+"',"
                        + "'" +this.campo4.getText()+"')";
                s.executeUpdate(access);
                s.close();
                conexion.close();
                JOptionPane.showMessageDialog(null, "Insercion exitosa");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "El error es "+ex);               
            }
        }
    }
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

