package EXERCISE5;

import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {
        //step1 " : connect to server
        try (Socket s = new Socket("localhost", 2243);) {
            //create datastream.
            DataInputStream Network_in = new DataInputStream(s.getInputStream());
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream());
            //implement the function of client.
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String messageToServer;
            String messageFromServer;
            //client side can chat with a user at server side one after another
            while (true) {
                //client inputs string
                System.out.print("Client:");
                messageToServer = userInput.readLine();
                //send string back to server;
                Network_out.writeUTF(messageToServer);
                Network_out.flush();
                //if client types exit ,break the loop
                if (messageToServer.equalsIgnoreCase("exit")) {
                    System.out.println("Connection closed by client");
                    break;
                }
            //Server displat the received string
            messageFromServer = Network_in.readUTF();
            System.out.println("Server " + messageFromServer);
                
            }
            //user at client side input a string and send string to server, server will display it on the screen
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
