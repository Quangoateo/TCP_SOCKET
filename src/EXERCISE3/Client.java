package EXERCISE3;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 6789);
            DataInputStream Network_in = new DataInputStream(s.getInputStream());
            DataOutputStream Network_out = new DataOutputStream(s.getOutputStream());
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input two integers:");
            int data1 = keyboard.nextInt();
            int data2 = keyboard.nextInt();
            Network_out.writeInt(data1);
            Network_out.writeInt(data2);
            int Sum = Network_in.readInt();
            System.out.println("Data from Server:" + Sum);
            s.close();
        } catch (IOException e) {
        }
    }
}
