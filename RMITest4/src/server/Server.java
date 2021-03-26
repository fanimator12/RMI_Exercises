package server;


import shared.RemoteMessageList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject {
    protected Server() throws RemoteException {
        super();
    }

    private static void startRegistry() throws RemoteException {
        try{
            Registry reg = LocateRegistry.createRegistry(1099);
            System.out.println("Registry started...");
        } catch (ExportException e) {
            System.out.println("Registry already started?\n" + e.getMessage());
        }
    }

    public static void main(String[] args) throws RemoteException, MalformedURLException {
        startRegistry();
        RemoteMessageList list = new RMIServer();
        Registry registry = LocateRegistry.createRegistry(1089);
        Naming.rebind("MessageList", list);
        UnicastRemoteObject.exportObject(list, 0);
        System.out.println("Server started");
    }
}
