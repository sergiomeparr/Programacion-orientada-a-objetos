import java.rmi.*;
import java.util.Date;


public interface RemoteDate extends java.rmi.Remote {

    public String getRemoteDate(String a) throws java.rmi.RemoteException;
    public final static String LOOKUPNAME = "RemoteDate";
}