package client;

/**
 * Holds all server communications
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import gui.ConversationPanel;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import lombok.Getter;
import shared.ClientList;
import shared.DataHandler;
import shared.Message;
import shared.Receiver;
import shared.Sender;

public class ServerConnection implements DataHandler{
	private final String serverIP = "localhost";
	private final int serverPort = 1337;
	
	@Getter private Socket socket;
	@Getter private Sender sender;
	private Thread receive;
	private int id = -1;
	@Getter private ArrayList<ChatConnection> chats;
	
	/**
	 * Creates a socket for the connection with the server
	 */
	public void createSocket(){
		chats = new ArrayList<ChatConnection>();
		try {
			this.socket = new Socket(this.serverIP, this.serverPort);
			this.sender = new Sender(socket);
			this.receive = new Thread(new Receiver(this));
			this.receive.start();				
		} catch (UnknownHostException e) {
			Client.getInstance().getMainFrame().showError("De gespecificeerde server is niet gevonden...");
		} catch (IOException e) {
			Client.getInstance().getMainFrame().showError("Er is iets misgegaan bij het verbinden met de server...");
		}
	}

	/**
	 * Handles an incoming Object, used when the server sends an Object to the client
	 * @param object the incoming Object
	 */
	@Override
	public void objectReceived(Object object) {
		// If a Message is coming in
		if(object instanceof Message){
			Message msg = (Message) object;
			
			// Remember the ID of this client
			if(this.id == -1)
				this.id = msg.getTo();
			
			// Make sure that the Message is displayed on the right tab
			this.openChatWithUser(msg.getFromId(), msg.getFrom()).receivedMessage(msg);
			
			// If the Message came from the server, display it in both Server tab and current tab
			if(msg.getFromId() == -1) {
				ConversationPanel current = (ConversationPanel) Client.getInstance().getMainFrame().getTabs().getSelectedComponent();
				if(current.getPartnerId() != -1) {
					current.addExternalMessage(msg);
				}
			}
		}
		// Else If a ClientList is coming in
		else if(object instanceof ClientList){
			Client.getInstance().getMainFrame().addClients((ClientList) object);
		}
		
	}

	/**
	 * Opens a ChatConnection with the specified user
	 * @param userId partner id
	 * @param user partner name
	 * @return the created or found ChatConnection
	 */
	public ChatConnection openChatWithUser(int userId, String user) {
		boolean partnerFound = false;
		ChatConnection chatConn = null;
		
		// Check if the ChatConnection already exists
		for(ChatConnection chat : chats){
			if(userId == chat.getPartnerId()){
				partnerFound = true;
				chatConn = chat;
			}
		}
		
		// If not exists, create a new ChatConnection
		if(!partnerFound) {
			chatConn = new ChatConnection(user, userId, this.sender);
			this.chats.add(chatConn);
		}
		
		return chatConn;
	}
	
	/**
	 * Required due to interface DataHandler, but not implemented.
	 */
	@Override
	public void disconnect() {
		
	}
}
