package j4.lesson10ex;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileSendClient {
    public static void main(String[] args) throws IOException {
        // Create a socket and connect to remote server
        //Socket socket = new Socket(addr, EchoServer.PORT);
        //InetAddress addr = InetAddress.getByName("localhost");

        try (Socket socket = new Socket("localhost", EchoServer.PORT)) {
            System.out.println("Client is connecting to the Server ---");
            System.out.println("Type in console; Type END for closing connection");

            // Get the input stream
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            // Get the output stream
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);

            System.out.print("Send File: ");
            Scanner input = new Scanner(System.in);
            String fileName = input.nextLine();

            Scanner file = new Scanner(new File(fileName));
            while (file.hasNextLine()) {
                String line = file.nextLine();
                out.println(line);
                String str = in.readLine();
                System.out.println("Server=>Client: " + str);
            }
            out.println("END");

            System.out.println("Client is closed ---");
        }
    }
}
