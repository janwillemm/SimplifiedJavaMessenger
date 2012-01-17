package server;

/**
 * Holds a connection with a client, and a little bit information about that client
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.net.*;
import java.util.Date;

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
	
	/**
	 * Constructor
	 * @param socket connection with client
	 * @param clientId id of client
	 * @throws IOException
	 */
	public Client(Socket socket, int clientId) throws IOException{
		this.socket = socket;
		this.clientId = clientId;
		
		this.in = new Thread(new Receiver(this));
		this.out = new Sender(this.socket);
		
		in.start();
		
		this.out.sendObject(new Message("Goedendag. U heeft verbinding met SimplifiedJavaMessenger Server v0.1b.", new Date(), "Server", -1, this.clientId));
	}
	
	/**
	 * Closes the socket with the client and removes it from the client list in Server.
	 */
	public void disconnect(){
		try {
			this.socket.close();
		}
		catch (IOException e) {
			System.out.println("* Closing of socket of client " + this.clientId + " failed.");
		}
		Server.removeClient(this);
		System.out.println("* Client disconnected.");
	}
	
	/**
	 * Handles an incoming message by sending it to the requested partner
	 * @param msg message
	 * @throws IOException
	 */
	public void receivedMessage(Message msg) throws IOException {
		if(msg != null) {
			// Find target client
			Client target = Server.findClient(msg.getTo());
			
			// If exists, send message
			if(target != null) {
				target.getOut().sendObject(new Message(msg.getContent(), new Date(), msg.getFrom(), msg.getFromId(), msg.getTo()));
			}
			
			// Else, return error message to this client 
			else {
				this.out.sendObject(new Message("Deze persoon is momenteel niet online...", new Date(), "Server", -1, this.clientId));
			}
		}
	}
	
	/**
	 * Sends a list of online users to this client
	 * @throws IOException
	 */
	public void sendOnlineUsers() throws IOException {
		ClientList clients = new ClientList();
		
		for(Client cl : Server.getClients()) {
			clients.add(cl.getClientId(), cl.getName());
		}
		
		this.out.sendObject(clients);
	}
	
	/**
	 * Handles a name update for this client
	 * @param newName new user name of client
	 */
	public void updateName(String newName) {
		// If valid name was entered
		if(newName != null && !newName.equals("")) {
			try {
				// If it was the first time, send a welcome message and update
				if(this.name == null) {
					this.name = newName;
					this.out.sendObject(new Message("Welkom, " + this.name + "!", new Date(), "Server", -1, this.clientId));
					sendHelp();
				}
				// Else, notify user that the change was successful
				else {
					this.name = newName;
					this.out.sendObject(new Message("Voortaan heet u " + this.name + ".", new Date(), "Server", -1, this.clientId));
				}
			}
			catch (IOException e) {
				System.out.println("* Name change of client " + this.clientId + " failed.");
			}
		}
		Server.pushClientsList();
	}
	
	/**
	 * Sends an overview of supported commands to this client
	 * @throws IOException
	 */
	public void sendHelp() throws IOException {
		String helpText = "De volgende commando's zijn beschikbaar:\n";
		helpText += "* /NAME <naam>: Uw nickname aanpassen;\n";
		helpText += "* /PART: Een conversatie sluiten.\n";
		helpText += "* /HELP: Dit overzicht.\n";
		
		this.out.sendObject(new Message(helpText, new Date(), "Server", -1, this.clientId));
	}

	/**
	 * Sends a warning that a chat partner has closed the chat
	 * @param partnerId id of chat partner
	 * @throws IOException
	 */
	public void sendLeft(int partnerId) throws IOException {
		String returnText = "Je chatpartner heeft het gesprek gesloten.";
		
		Server.findClient(partnerId).getOut().sendObject(new Message(returnText, new Date(), "Server", -1, partnerId));
	}

	/**
	 * Handles an incoming Object
	 * @param object the incoming object
	 */
	@Override
	public void objectReceived(Object object) {
		// If it is a Message
		if(object instanceof Message) {
			Message msg = (Message) object;
			msg.setFrom(this.name);
			msg.setFromId(this.clientId);
			
			// Hand it over to this.receivedMessage
			try {
				this.receivedMessage(msg);
			} catch (IOException e) {
				System.out.println("* Mishandled incoming message from client " + this.clientId + ".");
			}
		}
		
		// If it is a Command
		if(object instanceof Command) {
			Command cmd = (Command) object;
			try {
				// Determine which command was used, and hand it over to the matching method
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
				System.out.println("* Mishandled incoming command from client " + this.clientId + ".");
			}
		}
	}	
}