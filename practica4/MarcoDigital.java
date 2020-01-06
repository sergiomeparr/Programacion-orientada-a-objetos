import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

public class MarcoDigital extends JFrame implements ActionListener,Runnable{
    Thread PrinterThread;
    int SleepTime;
    ImageIcon imagen[] = new ImageIcon[10];
    Panel p;
    JLabel jlimg;
    Integer tiempo[] = {100,500,1000,1500,2000};
    JComboBox asignacion;
               
    public MarcoDigital(){
        setTitle("Marco Digital");
        SleepTime = tiempo[0];
        p = new Panel();
        imagen[0]=new ImageIcon(getClass().getResource("conejo.jpg"));
        imagen[1]=new ImageIcon(getClass().getResource("nieve.jpg"));
        imagen[2]=new ImageIcon(getClass().getResource("noche.jpg"));
        imagen[3]=new ImageIcon(getClass().getResource("paidaje3.jpg"));
        imagen[4]=new ImageIcon(getClass().getResource("paisaje2.jpg"));
        imagen[5]=new ImageIcon(getClass().getResource("paisaje.jpg"));
        imagen[6]=new ImageIcon(getClass().getResource("paisaje6.jpg"));
        imagen[7]=new ImageIcon(getClass().getResource("paisaje4.jpg"));
        imagen[8]=new ImageIcon(getClass().getResource("paisaje5.jpg"));
        imagen[9]=new ImageIcon(getClass().getResource("rayo.jpg"));
        for(int i=0;i<10;i++){
             jlimg=new JLabel(imagen[i], JLabel.CENTER);    
        }
        asignacion = new JComboBox(tiempo);
        asignacion.setBounds(170,320,100,30);
        asignacion.setSelectedIndex(0);
        asignacion.addActionListener(this);
        this.add(asignacion);
        p.add(jlimg);
        Container content = getContentPane();
    	content.add(p, BorderLayout.CENTER);
        PrinterThread = new Thread(this);
        PrinterThread.start(); 
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); 
        setSize(435, 400);
    	setVisible(true);
    }
        
    public void run(){
        int j=0;
        do{
            for(int i=0;i<10;i++){
                try {
                    Thread.sleep(SleepTime);
                } catch (InterruptedException ex) {
                    return;
                }
                jlimg.setIcon(imagen[i]);
                p.add(jlimg);
            }
        }while(j<10); 
    }
   
    public static void main(String args[]){
        new MarcoDigital();
    }

    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()== asignacion){
            JComboBox d = (JComboBox)ae.getSource();
            Integer a = (Integer)d.getSelectedIndex();
            switch(a){
                case 0:
                    SleepTime=tiempo[0];  
                    break;
                case 1:
                    SleepTime=tiempo[1];
                    break;
                case 2:
                    SleepTime=tiempo[2];  
                    break;
                case 3:
                    SleepTime=tiempo[3];  
                    break;
                case 4:   
                    SleepTime=tiempo[4];    
                    break;    
            }
        }
    }
}

