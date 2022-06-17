package j4.lesson10ex;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket; 

public class EchoServer  { 

	public static final int PORT = 9999; 

	public static void main(String[] args) throws IOException  {
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
					if (str.equalsIgnoreCase("END")) {
						out.println("BYE");
						break;
					}
					out.println(str.toUpperCase());
				}
			} finally {
				System.out.println("Server has closed ...");
			}
		}
	}
}
