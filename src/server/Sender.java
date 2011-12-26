package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender {

	PrintWriter out;
	
	public Sender(Socket socket) throws IOException {
		this.out = new PrintWriter(socket.getOutputStream(), true);
	}
	
	public void sendMessage(String str) {
		this.out.println(str);
	}
}
