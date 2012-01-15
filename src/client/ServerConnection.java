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
	
	
	public void createSocket(Command cmd){
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
				this.sender.sendObject(cmd);
				
				
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
			if(this.id == -1)this.id = msg.getTo();
			
			int fromId = msg.getFromId();
			boolean partnerFound = false;
			for(ChatConnection chat : chats){
				if(fromId == chat.partnerId){
					chat.receivedMessage(msg);
					partnerFound = true;
				}
			}
			if(!partnerFound){
				ChatConnection chat = new ChatConnection(msg.getFrom(), msg.getFromId());
				this.addChat(chat);
				chat.receivedMessage(msg);
			}
			
			System.out.println(msg);
		}
		else if(object instanceof HashMap){
			MainFrame.getInstance().addClients((HashMap<Integer, String>)object);
		}
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
	private void addChat(ChatConnection chat){
		
		this.chats.add(chat);
		
		
	}
	
}
