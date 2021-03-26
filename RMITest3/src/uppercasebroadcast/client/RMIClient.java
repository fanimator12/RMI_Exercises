package uppercasebroadcast.client;

import uppercasebroadcast.shared.UpperCaseClient;
import uppercasebroadcast.shared.UpperCaseServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient implements UpperCaseClient {

    private UpperCaseServer server;

    public RMIClient() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);
    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1089);
        server = (UpperCaseServer) registry.lookup("Server");
    }

    public String toUpperCase(String str) {
        String result = null;
        try {
            result = server.toUpperCase(str, this);
            return result;
        } catch (RemoteException e) {
            e.printStackTrace();
            throw new RuntimeException("Could not contact server");
        }
    }

    @Override
    public void update(String result) {
        System.out.println("Broadcasted> " + result);
    }
}
