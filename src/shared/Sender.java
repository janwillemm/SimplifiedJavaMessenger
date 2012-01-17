package shared;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.net.Socket;

import lombok.Synchronized;

public class Sender {

	ObjectOutputStream out;
	
	/**
	 * Constructor
	 * @param socket connection
	 * @throws IOException
	 */
	public Sender(Socket socket) throws IOException {
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}
	
	/**
	 * Sends an object to the output stream, which is connected to the socket
	 * @param object the Object that should be sent 
	 * @throws IOException
	 */
	@Synchronized
	public void sendObject(Object object) throws IOException {
		this.out.writeObject(object);
		this.out.flush();
	}
}
