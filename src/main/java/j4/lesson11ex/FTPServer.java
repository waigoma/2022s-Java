package j4.lesson11ex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

public class FTPServer {
    static final int PORT = 34567;
    static int clientCount = 0;

    public static void main(String[] args) {
        ServerSocket sListenSkt = null;
        Socket sClientSkt = null;
        BufferedReader c2s = null;
        PrintWriter s2c = null;

        try {
            sListenSkt = new ServerSocket(PORT);
            System.out.println("Waiting for connection ...");
            while (true) {
                sClientSkt = sListenSkt.accept();
                clientCount++;
                String clientInfo = sClientSkt.getRemoteSocketAddress().toString();
                System.out.println("Client#" + clientCount + " Info: " + clientInfo);

                c2s = new BufferedReader(new InputStreamReader(sClientSkt.getInputStream()));
                s2c = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sClientSkt.getOutputStream())), true);
                while (true) {
                    String clientCommand = c2s.readLine();
                    System.out.println("ClientCommand: "+clientCommand);
                    if (clientCommand.equalsIgnoreCase("Connect")) {
                        s2c.println("S>C: Connected");
                        String clientMsg = c2s.readLine();
                        System.out.println(clientMsg);
                        String str = "S>C: Welcome Client#" + clientCount + sClientSkt.getRemoteSocketAddress();
                        s2c.println(str);
                        System.out.println(str);
                    } else if (clientCommand.equalsIgnoreCase("Close")) {
                        System.out.println("Disconnected from Client#" + clientCount + "\n");
                        s2c.close();
                        c2s.close();
                        sClientSkt.close();
                        break;
                    } else if (clientCommand.equalsIgnoreCase("UpFile")) {
                        String fileName = c2s.readLine();
                        System.out.println("<<: " + fileName);
                        while (true) {
                            String line = c2s.readLine();
                            System.out.println("<<: " + line);
                            if (line.equalsIgnoreCase("EOF")) {
                                s2c.println("S>C: EOF");
                                break;
                            }
                        }
                    } else if (clientCommand.equalsIgnoreCase("DwFile")) {
                        String fileName = c2s.readLine();
                        String[] fileInfo = fileName.split(",");

                        System.out.println("To download " + fileInfo[0]);

                        File file = new File(fileInfo[1]);

                        Scanner scanner = new Scanner(file);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            s2c.println(">>: " + line);
                        }
                        s2c.println("EOF");
                        scanner.close();
                    } else if (clientCommand.equalsIgnoreCase("Text")) {
                        System.out.println(c2s.readLine());
                    }
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("Server has closed ...");
        }
    }
}
