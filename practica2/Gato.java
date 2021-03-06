import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;
//
public class Gato extends Applet implements ActionListener {
	JButton boton[];
	int v, h;				// Se divide la imagen V x H celdas
        int iw, ih;				// Ancho y Alto de la imagen
        int cw, ch;				// Ancho y Alto de una celda
        int movidas;			// Numero de movidas
	int last;				// Ultima celda (negra)
	int numCeldas;			// Numero de celdas
	boolean solved;		// Resuelto ?	
	Image[] Celda;			// Arreglo de imagenes
	int[] Orden;			// Orden de las imagenes
	int lastPos;			// Posicion del cuadro vacio
	Image Imagen;	
	JPanel p1, p2;
	JLabel jl;
        public Gato(){
		jl=new JLabel("0");
		init(); start();
		Frame f=new Frame("Gato");
		f.add("Center", this);
		f.add("South", jl);
                f.setSize(500, 500);
		f.setVisible(true);
        }
	public void init(){
                //last = 0;
		//lastPos = 0;
		solved = false;
		Imagen = getToolkit().getImage("auto.jpg");
                MediaTracker t = new MediaTracker(this);
		try {
		t.addImage(Imagen, 0);
		t.waitForID(0); 
                } catch (Exception e) {};
		iw = Imagen.getWidth(null);
		ih = Imagen.getHeight(null);
		v=4;
   		h=4;
		cw = iw / h;
		ch = ih / v;            
                numCeldas = v*h;
		last = numCeldas - 1;
		System.out.println("iw= "+iw+"ih= "+ih+" last= "+last);
		lastPos = last;
		Celda = new Image[numCeldas];
		Orden = new int[numCeldas];       
		Celda = new Image[numCeldas];
		Orden = new int[numCeldas];
		CropImageFilter f;
		FilteredImageSource fis;
		setLayout(new GridLayout(v,h));
		boton=new JButton[numCeldas];			
		for (int y=0; y < v; y++){
			for (int x=0; x < h; x++){
				f = new CropImageFilter(cw*x, ch*y, cw, ch);
				fis = new FilteredImageSource(Imagen.getSource(), f);
				int i = y*h+x;
				Orden[i] = i;
				Celda[i] = createImage(fis);
			}
		}
                shuffle();
                for (int i=0; i < numCeldas; i++){
			add(boton[i]=new JButton(
                                    new ImageIcon(Celda[Orden[i]])));
			boton[i].addActionListener(this);
		}
		dibuja();
	}
	public void actionPerformed(ActionEvent e){
		JButton btn=(JButton)e.getSource();
                int pos=0;
                for (int i=0; i < numCeldas; i++)
			if(btn==boton[i])
				pos=i;		        			  
		if ( (pos != lastPos) && (solved==false) && (pos<=last)){
			System.out.println("pos= "+pos);
			// Izquierda 
			if (((pos%h)!=0) && (Orden[pos-1]==last)){
				Orden[pos-1]=Orden[pos];
				Orden[pos]=last;
				lastPos = pos;
				movidas++;
				dibuja();
				//repaint();
			}
			// Arriba
			else if (((pos-h)>=0) && (Orden[pos-h]==last)){
				Orden[pos-h]=Orden[pos];
				Orden[pos]=last;
				lastPos = pos;
				movidas++;
				dibuja();
				//repaint();
			}
			// Derecha
			else if (((pos%h)!=h) && (Orden[pos+1]==last)){
				Orden[pos+1]=Orden[pos];
				Orden[pos]=last;
				lastPos = pos;
				movidas++;
				//repaint();
				dibuja();
			}
			// Abajo
			else if (((pos+h)<=last) && (Orden[pos+h]==last)){
				Orden[pos+h]=Orden[pos];
				Orden[pos]=last;
				lastPos = pos;
				movidas++;
				//repaint();
				dibuja();
			}
			//movStr = "Movidas: " + movidas;
		}
	}
	private void dibuja(){
		int score = Puntaje();
		jl.setText(""+score);
		if ((score-1)==last){
			jl.setText("Ganaste");
		}
		for (int i=0; i < numCeldas; i++)
			if ( Orden[i] != last) 
				boton[i].setIcon(new ImageIcon(Celda[Orden[i]])) ;
                        else
				boton[i].setIcon(new ImageIcon("negro1.jpg"));
	}
	public static void main(String args[]){  new Gato();	}
	public int Puntaje(){
		int score=0;
		for(int i=0; i < numCeldas; i++)
			if (Orden[i]==i) {score++;}
		return score;
	}
	private void shuffle(){
		for (int i=0; i < numCeldas*2; i++){
			int si = (int) (Math.random() * numCeldas);
			int di = (int) (Math.random() * numCeldas);
			int tmp = Orden[si];
			Orden[si] = Orden[di];
			Orden[di] = tmp;
		}
	}
}
