package EXERCISE5;
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket (2243)) {
            System.out.println("Server started. Waiting for a client...");
            // Waiting for a client connection
            Socket con = ss.accept();
            System.out.println("Client connected.");
            // Input and output streams for communication with client
            DataInputStream in = new DataInputStream(con.getInputStream());
            DataOutputStream out = new DataOutputStream(con.getOutputStream());
            // Server allows user input and sends it back to client until "bye" is received
            BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));
            String messageToClient;
           String messageFromClient;

            // Loop for continuous communication
            while (true) {
                // Server waits for client's message
                messageFromClient = in.readUTF();
                System.out.println("Client: " + messageFromClient);

                // If client types "bye", break the loop
                if (messageFromClient.equalsIgnoreCase("bye")) {
                    System.out.println("Connection closed by client.");
                    break;
                }

                // Server inputs a string
                System.out.print("Server: ");
                messageToClient = serverInput.readLine();

                // Send string to client
                out.writeUTF(messageToClient);
                out.flush();
            }

            // Close the socket and server socket
            con.close();
            ss.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
