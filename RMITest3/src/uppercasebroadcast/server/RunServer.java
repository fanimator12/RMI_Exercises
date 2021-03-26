package uppercasebroadcast.server;

import uppercasebroadcast.shared.UpperCaseServer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RunServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        UpperCaseServer server = new RMIServerImpl();
        Registry registry = LocateRegistry.createRegistry(1089);
        registry.bind("Server", server);
        System.out.println("Server started");
    }
}
