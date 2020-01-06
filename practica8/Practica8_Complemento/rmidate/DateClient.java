import java.rmi.*;
import java.util.*;
//java -Djava.security.policy=no.policy DateClient
public class DateClient {
  protected static RemoteDate netConn = null;

  public static void main(String args[]) {
        
    System.setSecurityManager(new RMISecurityManager());

    try {
	boolean i;
	i=true;
	while(i){
		Scanner lector = new Scanner(System.in);
		String cad = lector.nextLine(); 
		netConn = (RemoteDate)Naming.lookup(RemoteDate.LOOKUPNAME);
		String a = netConn.getRemoteDate(cad.toString());
		System.out.println("RemoteClient: " +a.toString( )+"\n");
		System.out.println("\n");
		if(a.equals("BYE")){
		System.out.println("*************** BYE!!!!!!! **************\n");
		i = false;
		}
	}	
    } 
    catch (Exception e) {
      System.out.println("Exception in main: " + e);
      e.printStackTrace( );
    }

  }

}
