package uppercasebroadcast.server;

import uppercasebroadcast.shared.UpperCaseClient;
import uppercasebroadcast.shared.UpperCaseServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RMIServerImpl implements UpperCaseServer {

    private List<UpperCaseClient> clientsForBroadcast;

    public RMIServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);
    }

    @Override
    public String toUpperCase(String str, UpperCaseClient client) {
        String result = str.toUpperCase();

        try{
            Thread.sleep(1000);
        } catch(InterruptedException ignored){
            //
        }

        updateClients(result, client);

        return result;
    }

    private void updateClients(String result, UpperCaseClient dontBroadcastToMe){
        for (UpperCaseClient client : clientsForBroadcast){
            if(client.equals(dontBroadcastToMe)) continue;

           try {
               client.update(result);
           } catch (RemoteException e){
               e.printStackTrace();
           }
        }
    }

    @Override
    public void registerClient(UpperCaseClient clientToRegister) {
        clientsForBroadcast.add(clientToRegister);
    }
}
