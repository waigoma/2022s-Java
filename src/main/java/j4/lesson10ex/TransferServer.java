package j4.lesson10ex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TransferServer {
    public static final int PORT = 19999;

    public static void main(String[] args) throws IOException {
        try (ServerSocket listen_sock = new ServerSocket(PORT)) {
            System.out.println("Server runs and waits for connection ...");
            // Block until a connection occurs:
            try (Socket socket = listen_sock.accept()) {
                // Connection accepted:
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //Output is automatically flushed by PrintWriter:
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

                //echo back and forth
                while (true) {
                    String str = in.readLine();
                    if (str.equalsIgnoreCase("Eoc")) {
                        System.out.println("Received: Eoc");
                        out.println("BYE");
                        break;
                    }
                    out.println(str.toUpperCase());
                }
            } catch (Exception e) {
                listen_sock.close();
                main(args);
            } finally {
                System.out.println("Server has closed ...");
            }
        }
    }
}
