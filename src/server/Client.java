package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.HashMap;

import shared.ClientList;
import shared.Command;
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
		
		this.out.sendObject(new Message("Goedendag. U heeft verbinding met SimplifiedJavaMessenger Server v0.1b.", new Date(), "Server", -1, this.clientId));
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
	
	public void receivedMessage(Message msg) throws IOException {
		if(msg != null) {
			System.out.println(msg.toString());
			
			Client target = Server.findClient(msg.getTo());
			if(target != null) {
				target.getOut().sendObject(new Message(msg.getContent(), new Date(), msg.getFrom(), msg.getFromId(), msg.getTo()));
			}
			else {
				this.out.sendObject(new Message("Deze persoon is momenteel niet online...", new Date(), "Server", -1, this.clientId));
			}
		}
	}
	
	public void sendOnlineUsers() throws IOException {
		ClientList clients = new ClientList();
		
		for(Client cl : Server.getClients()) {
			clients.add(cl.getClientId(), cl.getName());
		}
		
		this.out.sendObject(clients);
	}
	
	public void updateName(String newName) {
		if(newName != null && !newName.equals("")) {
			try {	
				if(this.name == null) {
					this.name = newName;
					this.out.sendObject(new Message("Welkom, " + this.name + "!", new Date(), "Server", -1, this.clientId));
					sendHelp();
				}
				else {
					this.name = newName;
					this.out.sendObject(new Message("Voortaan heet u " + this.name + ".", new Date(), "Server", -1, this.clientId));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Server.pushClientsList();
	}
	
	public void sendHelp() throws IOException {
		String helpText = "De volgende commando's zijn beschikbaar:\n";
		helpText += "* /NAME <naam>: Uw nickname aanpassen;\n";
		helpText += "* /PART: Een conversatie sluiten.\n";
		helpText += "* /HELP: Dit overzicht.\n";
		
		this.out.sendObject(new Message(helpText, new Date(), "Server", -1, this.clientId));
	}

	public void sendLeft(int partnerId) throws IOException {
		String returnText = "Je chatpartner heeft het gesprek gesloten.";
		
		Server.findClient(partnerId).getOut().sendObject(new Message(returnText, new Date(), "Server", -1, partnerId));
	}

	@Override
	public void objectReceived(Object object) {
		
		if(object instanceof Message) {
			Message msg = (Message) object;
			msg.setFrom(this.name);
			msg.setFromId(this.clientId);
			
			try {
				this.receivedMessage(msg);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(object instanceof Command) {
			Command cmd = (Command) object;
			try {
				if(cmd.getCommand().equals("NAME")) {
					updateName(cmd.getParameters()[0]);
				}
				else if(cmd.getCommand().equals("HELP")) {
					sendHelp();
				}
				else if(cmd.getCommand().equals("PART")) {
					sendLeft(Integer.parseInt(cmd.getParameters()[0]));
				}
			}
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	
}