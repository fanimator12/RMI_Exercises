package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws RemoteException, NotBoundException {
        RMIClient client = new RMIClient();
        client.startClient();

        Scanner in = new Scanner(System.in);
        while(true){
            System.out.println("Input> ");
            String line = in.nextLine();

            try {
                client.send(line);
            } catch (Exception e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
