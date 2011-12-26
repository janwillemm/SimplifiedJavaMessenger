package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.net.*;

import shared.DataHandler;
import shared.Message;
import shared.Receiver;
import shared.Sender;
import lombok.*;

@EqualsAndHashCode
public class Client implements DataHandler{
	@Getter private Socket socket;
	@Getter private Thread in;
	@Getter private Sender out;
	@Getter private String name;
	@Getter @Setter private Client partner = null;
	
	public Client(Socket socket) throws IOException{
		this.socket = socket;
		
		this.in = new Thread(new Receiver(this));
		this.out = new Sender(this.socket);
		
		in.start();
		
		this.out.sendObject(new Message("Goedendag. U heeft verbinding met SimplifiedJavaMessenger Server v.0.01b.", null, null, null));
	}
	
	public void disconnect(){
		try {
			this.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Server.removeClient(this);
		System.out.println("* Client disconnected.");
	}
	
	public void receivedMessage(String whatComesIn) throws IOException {
		if(whatComesIn != null) {
			System.out.println(whatComesIn);
			this.out.sendObject(new Message(whatComesIn, null, null, null));
		}
	}

	@Override
	public void objectReceived(Object object) {
		
		if(object instanceof Message) {
			Message msg = (Message) object;
			try {
				this.receivedMessage(msg.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
}