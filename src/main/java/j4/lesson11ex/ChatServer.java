package j4.lesson11ex;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    public static final int PORT = 45678;
    private static ServerSocket ss;

    private static int clientCount = 0;
    private static final ArrayList<Client> clients = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ss = new ServerSocket(PORT);
            System.out.println("Waiting for connection ...");
            while (true) {
                Socket sc = ss.accept();
                BufferedReader br = new BufferedReader(new InputStreamReader(sc.getInputStream()));

                String clientInfo = sc.getRemoteSocketAddress().toString();
                String clientName = br.readLine();

                System.out.println("Client" + ++clientCount + " Info: " + clientInfo);
                System.out.println("Client#" + clientCount + "=> ++" + clientName + "++ joined...");

                Client ct = new Client(sc, clientName, clientCount);
                clients.add(ct);
                ct.start();

                broadcastMessage("++" + clientName + "++ joined...");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void broadcastMessage(String str) throws IOException {
        for (Client c : clients) {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(c.getSocket().getOutputStream())), true);
            pw.println(str);
        }
    }

    public static void removeClient(Client c) {
        clients.remove(c);
    }
}

class Client extends Thread {
    private final Socket sc;
    private final String clientName;
    private final int clientId;
    private BufferedReader br;
    private PrintWriter pw;

    public Client(Socket s, String name, int id) {
        sc = s;
        clientName = name;
        clientId = id;
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sc.getOutputStream())), true);
        } catch(Exception e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String clientMsg = br.readLine();
                if (clientMsg == null || clientMsg.equals("")) {
                    ChatServer.broadcastMessage("==" + clientName + "== left...");
                    System.out.println("Client#" + clientId + "=> ==" + clientName + "== left...");
                    throw new Exception("Client disconnected");
                }
                String DisplayMsg = clientName + "=> " + clientMsg;
                System.out.println(DisplayMsg);
                ChatServer.broadcastMessage(DisplayMsg);
            } catch(Exception e) {
                try {
                    br.close();
                    pw.close();
                    sc.close();
                    System.out.println("Bye");
                    ChatServer.removeClient(this);
                    break;
                } catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public Socket getSocket() {
        return sc;
    }
}