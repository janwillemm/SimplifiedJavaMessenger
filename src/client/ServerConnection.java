package client;

import gui.MainFrame;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lombok.Getter;

import shared.Command;
import shared.DataHandler;
import shared.Message;
import shared.Receiver;
import shared.Sender;

public class ServerConnection implements DataHandler{

	@Getter Socket socket;
	Sender sender;
	Thread send;
	Thread receive;
	int id = -1;
	ArrayList<ChatConnection> chats;
	
	
	public void createSocket(){
			chats = new ArrayList<ChatConnection>();
			try {
				//JW 145.53.129.85
				//RickW 86.83.37.53
				this.socket = new Socket("localhost", 1337);
				this.sender = new Sender(socket);
				this.send = new Thread(new InputHandler(this.sender));
				this.receive = new Thread(new Receiver(this));
				
				this.send.start();
				this.receive.start();				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}

	@Override
	public void objectReceived(Object object) {
		if(object instanceof Message){
			Message msg = (Message) object;
			
			if(this.id == -1)
				this.id = msg.getTo();
			
			this.openChatWithUser(msg.getFromId(), msg.getFrom()).receivedMessage(msg);
		}
		else if(object instanceof HashMap){
			Client.getInstance().getMainFrame().addClients((HashMap<Integer, String>)object);
		}
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
	public ChatConnection openChatWithUser(int userId, String user) {
		boolean partnerFound = false;
		ChatConnection chatConn = null;
		
		for(ChatConnection chat : chats){
			if(userId == chat.partnerId){
				partnerFound = true;
				chatConn = chat;
			}
		}
		
		if(!partnerFound) {
			chatConn = new ChatConnection(user, userId, this.sender);
			this.chats.add(chatConn);
		}
		
		return chatConn;
	}
}
