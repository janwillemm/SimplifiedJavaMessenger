package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.net.*;
import lombok.*;

@EqualsAndHashCode
public class Client {
	@Getter private Socket socket;
	@Getter private Thread in;
	@Getter private Sender out;
	@Getter private int clientId;
	@Getter private String name;
	@Getter @Setter private Client partner = null;
	
	public Client(Socket socket, int clientId) throws IOException{
		this.socket = socket;
		this.clientId = clientId;
		
		this.in = new Thread(new Receiver(this));
		this.out = new Sender(this.socket);
		
		in.start();
		
		this.out.sendMessage("Goedendag. U heeft verbinding met SimplifiedJavaMessenger Server v.0.01b.");
	}
	
	public void disconnect() throws IOException {
		this.socket.close();
		Server.removeClient(this);
		System.out.println("* Client disconnected.");
	}
	
	public void receivedMessage(String whatComesIn) throws IOException {
		if(whatComesIn != null) {
			System.out.println(whatComesIn);
			this.out.sendMessage(whatComesIn);
		}
	}	
}