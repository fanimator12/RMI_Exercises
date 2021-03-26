package uppercasereturn.server;

import uppercasereturn.shared.UpperCaseServer;
import uppercasereturn.shared.UpperCaseClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RMIServerImpl implements UpperCaseServer {
    public RMIServerImpl() throws RemoteException {
        UnicastRemoteObject.exportObject(this,0);
    }

    @Override
    public void toUpperCase(String str, UpperCaseClient client) {
        String result = str.toUpperCase();

        try{
            Thread.sleep(1000);
        } catch(InterruptedException ignored){
            //
        }

        try {
            client.uppercaseResult(result);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
