package EXERCISE6;

import java.net.*;
import java.io.*;

public class SERVER {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(6789)) {
            // Step 2: create the socket and wait for connection from client
            Socket con = ss.accept();
            DataInputStream in = new DataInputStream(con.getInputStream());
            DataOutputStream out = new DataOutputStream(con.getOutputStream());

            // Step 3: Implement the function of the client
            // Finding even numbers in range [a, b] sent by the client
            // Sending even numbers back to the client
            int a = in.readInt(); // Read the lower bound of the range
            int b = in.readInt(); // Read the upper bound of the range
            
            for (int i = a; i <= b; i++) {
                if (i % 2 == 0) {
                    out.writeInt(i); // Send even number back to client
                    out.flush(); // Flush the output stream
                }
            }

            // Step 4: Close socket and ServerSocket
            con.close();
            ss.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
