package client;

import shared.RemoteMessageList;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RMIClient {
    private RemoteMessageList list;

    public RMIClient() throws RemoteException {
        UnicastRemoteObject.exportObject((Remote) this,0);
    }

    public void send(String text) throws RemoteException {
            list.addMessage(text);
    }

    public void startClient() throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry("localhost",1089);
        list = (RemoteMessageList) registry.lookup("MessageList");
    }
}
