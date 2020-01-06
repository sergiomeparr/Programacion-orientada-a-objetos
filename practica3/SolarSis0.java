import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.picking.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.event.*;
import javax.swing.*;
import java.util.*;


public class SolarSis0 extends MouseAdapter implements ActionListener 
{
        private PickCanvas pickCanvas;
        private JComboBox jcb;
        private JTextArea jta;

        public SolarSis0(){

	             JFrame frame = new JFrame("Planetario");
               GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();
               Canvas3D canvas = new Canvas3D(config);
               canvas.setSize(1500, 700);
               SimpleUniverse universe = new SimpleUniverse(canvas);
               BranchGroup group = new BranchGroup();

               
               Appearance appsol = new Appearance();
               Appearance appmercurio = new Appearance();
               Appearance appvenus = new Appearance();
               Appearance appearth = new Appearance();
               Appearance appmarte = new Appearance();
               Appearance appjupiter = new Appearance();


               TextureLoader tex = new TextureLoader("TIERRA.JPG", null);
               appearth.setTexture(tex.getTexture());
               tex = new TextureLoader("SOL.JPG", null);
               appsol.setTexture(tex.getTexture());
               TextureLoader textura = new TextureLoader("MERCURIO.JPG",null);
               appmercurio.setTexture(textura.getTexture());
               TextureLoader textura_venus = new TextureLoader("VENUS.JPG",null);
               appvenus.setTexture(textura_venus.getTexture());
               TextureLoader textura_marte = new TextureLoader("MARTE.JPG",null);
               appmarte.setTexture(textura_marte.getTexture());
               TextureLoader textura_jupiter = new TextureLoader("JUPITER.JPG",null);
               appjupiter.setTexture(textura_jupiter.getTexture());
                                        
  
               Sphere sol = new Sphere(0.15f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appsol);
               sol.setUserData("Sol");
               Sphere mercurio = new Sphere(0.025f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appmercurio);
               mercurio.setUserData("Mercurio");
               Sphere venus = new Sphere(0.04f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appvenus);
               venus.setUserData("Venus");
               Sphere earth = new Sphere(0.045f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appearth);
               earth.setUserData("Tierra");
               Sphere marte = new Sphere(0.030f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appmarte);
               marte.setUserData("Marte");
               Sphere jupiter = new Sphere(0.085f, Primitive.GENERATE_NORMALS | Primitive.GENERATE_TEXTURE_COORDS, 32, appjupiter);
               jupiter.setUserData("Jupiter");
               

               TransformGroup solRotXformGroup = rotate(sol, new Alpha(-1, 1250));
               TransformGroup mercurioRotXformGroup = rotate(mercurio, new Alpha(-1, 1250));
               TransformGroup venusRotXformGroup = rotate(venus, new Alpha(-1, 1250));
               TransformGroup earthRotXformGroup = rotate(earth, new Alpha(-1, 1250));
               TransformGroup marteRotXformGroup = rotate(marte, new Alpha(-1, 1250));
               TransformGroup jupiterRotXformGroup = rotate(jupiter, new Alpha(-1, 1250));

                              
               TransformGroup mercurioTransXformGroup = translate(mercurioRotXformGroup, new Vector3f(0.0f, 0.0f, 0.225f));
               TransformGroup mercurioRotGroupXformGroup = rotate(mercurioTransXformGroup, new Alpha(-1, 2000));
               TransformGroup venusTransXformGroup = translate(venusRotXformGroup, new Vector3f(0.0f, 0.0f, 0.300f));
               TransformGroup venusRotGroupXformGroup = rotate(venusTransXformGroup, new Alpha(-1, 3000));
               TransformGroup earthTransXformGroup = translate(earthRotXformGroup, new Vector3f(0.0f, 0.0f, 0.400f));
               TransformGroup earthRotGroupXformGroup = rotate(earthTransXformGroup, new Alpha(-1, 3500));
               TransformGroup marteTransXformGroup = translate(marteRotXformGroup, new Vector3f(0.0f, 0.0f, 0.500f));
               TransformGroup marteRotGroupXformGroup = rotate(marteTransXformGroup, new Alpha(-1, 4000));
               TransformGroup jupiterTransXformGroup = translate(jupiterRotXformGroup, new Vector3f(0.0f, 0.0f, 0.650f));
               TransformGroup jupiterRotGroupXformGroup = rotate(jupiterTransXformGroup, new Alpha(-1, 5000));
               

               group.addChild(solRotXformGroup);
               group.addChild(mercurioRotGroupXformGroup);
               group.addChild(venusRotGroupXformGroup);
               group.addChild(earthRotGroupXformGroup);
               group.addChild(marteRotGroupXformGroup);
               group.addChild(jupiterRotGroupXformGroup);
               
               universe.getViewingPlatform().setNominalViewingTransform();
               universe.addBranchGraph(group);
               frame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent winEvent) {
                            System.exit(0);
                    }
                    });
               JPanel jp=new JPanel();
               frame.setLayout(new BorderLayout());
               Vector nomPlanet=new Vector();
               


               nomPlanet.addElement("Sol");
               nomPlanet.addElement("Mercurio");
               nomPlanet.addElement("Venus");
               nomPlanet.addElement("Tierra");
               nomPlanet.addElement("Marte");
               nomPlanet.addElement("Jupiter");
               

               jcb=new JComboBox(nomPlanet);
               jcb.addActionListener(this);
               jta=new JTextArea("informacion: ",5,20);
               jp.add(jcb);
               jp.add(jta);
               frame.add("Center",canvas);
               frame.add("South", jp);
               pickCanvas = new PickCanvas(canvas, group);
               pickCanvas.setMode(PickCanvas.BOUNDS);
               canvas.addMouseListener(this);
               frame.pack();
               frame.setVisible(true);
        }

        public static void main(String[] args) { new SolarSis0();}

        TransformGroup rotate(Node node, Alpha alpha) {
                TransformGroup xformGroup = new TransformGroup();
                xformGroup.setCapability(
                TransformGroup.ALLOW_TRANSFORM_WRITE);
                RotationInterpolator interpolator =
                new RotationInterpolator(alpha, xformGroup);
                interpolator.setSchedulingBounds(new BoundingSphere(
                new Point3d(0.0, 0.0, 0.0), 1.0));
                xformGroup.addChild(interpolator);
                xformGroup.addChild(node);
                return xformGroup;
        }
        TransformGroup translate(Node node, Vector3f vector) {
                Transform3D transform3D = new Transform3D();
                transform3D.setTranslation(vector);
                TransformGroup transformGroup =
                new TransformGroup();
                transformGroup.setTransform(transform3D);
                transformGroup.addChild(node);
                return transformGroup;
        }
        /*public void mouseClicked(MouseEvent e){
                pickCanvas.setShapeLocation(e);
                PickResult result = pickCanvas.pickClosest();
                String sol = new String("El Sol es una estrella de tipo-G de la secuencia principal y clase de luminosidad V que se encuentra en el centro del sistema solar y constituye la mayor fuente de radiación electromagnética de este sistema planetario");
                if (result == null) {
                System.out.println("Nothing picked");
                } else {
                        Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);
                        Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D);
                        if (p != null) {
                        System.out.println("UserData1: "+p.getUserData() );
                        System.out.println(p.getClass().getName());
                        jta.setText(""+p.getUserData());
                } else if (s != null) {
          	        System.out.println("UserData2: "+s.getUserData() );
                        System.out.println(s.getClass().getName());
                        jta.setText(s.getClass().getName());
                } else{
                        System.out.println("null");
                }
        }
        
        public void actionPerformed(ActionEvent e){
          String nombre=(String)jcb.getSelectedItem();
                jta.append(nombre);
        }*/
}
