package server;

import shared.RemoteMessageList;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer implements RemoteMessageList {
    private ArrayList<String> messageList;

    public RMIServer() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);
    }

    public void start(){

    }

    @Override
    public void addMessage(String message) throws RemoteException {
        messageList.add(message);
        System.out.println(message);
    }
}
