package EXERCISE3;
import java.io.*;
import java.net.*;
import java.util.*;
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(6789);
            Socket con = ss.accept();
            DataInputStream in = new DataInputStream(con.getInputStream());
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            int number1 = in.readInt();
            int number2 = in.readInt();
            int Sum = number1 + number2;
            out.writeInt(Sum);
            con.close();
            ss.close();
        } catch (Exception e) {
        }
    }
}
