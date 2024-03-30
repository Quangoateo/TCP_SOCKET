package Exercise2;

import java.io.*;
import java.net.*;
import java.util.*;

public class Client {
    public static void main(String[] args)
    {
        try(  Socket ss = new Socket("localhost",1235)){

            DataInputStream Network_in = new DataInputStream(ss.getInputStream());
            DataOutputStream Network_out = new DataOutputStream(ss.getOutputStream());
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Please input two integers:");

            int data1 = keyboard.nextInt();
            int data2 = keyboard.nextInt();

            Network_out.writeInt(data1);
            Network_out.writeInt(data2);
            
            int LCM = Network_in.readInt();
            System.out.println("LCM from Server:" + LCM);
            ss.close();
        }catch(IOException e)
        {
          e.printStackTrace();
        }
    }
}
