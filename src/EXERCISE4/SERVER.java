package EXERCISE4;

/**
 *
 *
 * TCP Based Echo Service One echo service is defined as a connection based
 * application on TCP. A server listens for TCP connections on TCP port 7. Once
 * a connection is established any data received is sent back. This continues
 * until the calling user terminates the connection.
 */
import java.io.*;
import java.net.*;

public class SERVER {

    public static void main(String[] args) {
        int portNumber = 7;

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Echo server is listening on port " + portNumber + "...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Connected to client " + clientSocket.getInetAddress());
                new Thread(new EchoClientHandler(clientSocket)).start();
            }

        } catch (IOException e) {
            System.out.println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
            System.out.println(e.getMessage());
        }
    }
}

class EchoClientHandler implements Runnable {

    private Socket clientSocket;

    public EchoClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
     public void run() {
        try (
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
        ) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("Received: " + inputLine);
                out.println(inputLine);
            }
        } catch (IOException e) {
            System.out.println("Exception caught when trying to read or write to client");
            System.out.println(e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("Exception caught when trying to close client socket");
                System.out.println(e.getMessage());
            }
        }
    }
}
