package shared;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Sender {

	ObjectOutputStream out;
	
	public Sender(Socket socket) throws IOException {
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	public void sendObject(Object object) throws IOException {
		this.out.writeObject(object);
		this.out.flush();
	}
}
