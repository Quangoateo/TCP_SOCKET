package EXERCISE6;

//import java.io.DataInputStream;
import java.net.Socket;
import java.io.*;
import java.util.*;

public class CLIENT {

    public static void main(String[] args) {
        //STEP 1: connect to server 
        try (Socket s = new Socket("localhost", 6789)) {

            //STEP 2: create new datastream
            DataInputStream Network_in = new DataInputStream(s.getInputStream());
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream());
            //STEP 3: Execute the function of the client :
            //Sending a and b integeers to server
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please enter 2 integers :");
            int data1 = keyboard.nextInt();
            int data2 = keyboard.nextInt();

            //Sending both integers to server
            Network_out.writeInt(data1);
            Network_out.writeInt(data2);
            Network_out.flush(); // Flush the output stream to ensure data is sent
            //Receive the even numbers from the server :
            System.out.println("Even numbers received from server");
            while (true) {
                try {
                    int evenNumber = Network_in.readInt();  // Read an even number from the server
                    System.out.println(evenNumber);// Print the even number

                } catch (EOFException e) {
                    //end of the stream reached
                    break;
                }
            }
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
