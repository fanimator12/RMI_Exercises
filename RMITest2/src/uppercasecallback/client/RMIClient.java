package uppercasecallback.client;

import uppercasecallback.shared.UpperCaseServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient implements UpperCaseServer {

    private UpperCaseServer server;

    public RMIClient(){

    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1099);
        server = (UpperCaseServer) registry.lookup("Server");
    }

    @Override
    public String toUpperCase(String str) {
        String result = null;
        try {
            result = server.toUpperCase(str);
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
        return result;
    }
}
