import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.util.*;


public class RemoteDateImpl extends UnicastRemoteObject implements RemoteDate {
	static String preguntas []= { "COMO TE LLAMAS","DONDE VIVES","DE DONDE ERES","COMO ESTAS","TE GUSTA LA CAFE","SOY BONITA","SOY GUAPO","QUE COMIDA TE GUSTA","QUE TE GUSTA HACER","CUANTO VALES","QUIEN ES TU PADRE","QUIEN ES TU MADRE","TE AMO","ERES HOMBRE","ERES MUJER","TE GUSTO"};
	static String respuestas []= {"MI NOMBRE ES TAMAGOCHI","EN LA COMPU","DE COREA","BIEN","NO,LA ODIO","SI","NO ERES HORRIBLE","TACO TACO TACO","VER PELICULAS","UN BILLON","NO TENGO PADRE","NO TENGO MADRE","TE ODIO","NO","NO","SI MUCHO"};
	

  public RemoteDateImpl() throws RemoteException {
    super();
  }

    
public String getRemoteDate(String a) throws RemoteException {
	
	String result = "";
	for(int i = 0; i < preguntas.length; ++i) {
		if(preguntas[i].equals(a)) {
			result = respuestas[i];
			break;
		}
	} 
	return result;
}

  public static void main(String args[]) {

    try {
      RemoteDateImpl im = new RemoteDateImpl( );
      Naming.rebind(RemoteDate.LOOKUPNAME, im);
	 System.out.println("DateServer ready.");
    }
     catch (RemoteException re) {
      System.out.println("Exception in RemoteImpl.main: " + re);
      System.exit(1);
    }
    catch (MalformedURLException e) {
      System.out.println("MalformedURLException in RemoteImpl.main: " + e);
     System.exit(1);
    }

  }

}
