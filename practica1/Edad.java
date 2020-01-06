import java.awt.*;
import java.applet.*;
import java.awt.event.*;
public class Edad extends Applet implements ActionListener{
	Button b;
	TextField t;
	Label res;
	public void init(){
		b=new Button("Año de Nacimiento ");
		t=new TextField(4);
		res=new Label("Edad:          ");
		add(b);add(t);add(res);
		b.addActionListener(this);
		t.addActionListener(this);
		}
	public void actionPerformed(ActionEvent e){
		int c,l;
		String s=t.getText();
		c=Integer.parseInt(s);
		l=2014-c;
		res.setText("Edad:"+l);
		}
}
