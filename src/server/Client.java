package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.net.*;
import java.util.Date;

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
	@Getter private int clientId;
	@Getter private String name;
	
	public Client(Socket socket, int clientId) throws IOException{
		this.socket = socket;
		this.clientId = clientId;
		
		this.in = new Thread(new Receiver(this));
		this.out = new Sender(this.socket);
		
		in.start();
		
		this.out.sendObject(new Message("Goedendag. U heeft verbinding met SimplifiedJavaMessenger Server v.0.05b.", new Date(), -1, this.clientId));
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
			this.out.sendObject(new Message(whatComesIn, new Date(), -1, this.clientId));
		}
	}
	
	public void sendOnlineUsers() throws IOException {
		String content = "Aantal online gebruikers: " + Server.getClients().size() + "\n";
		for(Client cl : Server.getClients()) {
			content += "#" + cl.getClientId() + ": " + cl.getName() + "\n";
		}
		content = content.substring(0, Server.getClients().size()-2);
		
		this.out.sendObject(new Message(content, new Date(), -1, this.clientId));
	}
	
	public void updateName(String newName) {
		if(newName != null && !newName.equals("")) {
			try {	
				if(this.name == null) {
					this.name = newName;
					this.out.sendObject(new Message("Welkom, " + this.name + "!", new Date(), -1, this.clientId));
					sendHelp();
				}
				else {
					this.name = newName;
					this.out.sendObject(new Message("Voortaan heet u " + this.name + ".", new Date(), -1, this.clientId));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendHelp() throws IOException {
		String helpText = "De volgende commando's zijn beschikbaar:";
		helpText += "* NAME <naam>: Uw nickname aanpassen;";
		helpText += "* LIST: Een lijst met online clients;";
		helpText += "* HELP: Dit overzicht.";
		
		this.out.sendObject(new Message(helpText, new Date(), -1, this.clientId));
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
		
		if(object instanceof Command) {
			Command cmd = (Command) object;
			try {
				if(cmd.getCommand().equals("NAME")) {
					updateName(cmd.getParameters());
				}
				else if(cmd.getCommand().equals("LIST")) {
					sendOnlineUsers();
				}
				else if(cmd.getCommand().equals("HELP")) {
					sendHelp();
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}