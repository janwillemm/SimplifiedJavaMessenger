package client;

import gui.*;

import java.io.IOException;
import java.net.Socket;

import shared.DataHandler;
import shared.Message;
import shared.Receiver;
import shared.Sender;

public class ChatConnection implements DataHandler{

	int partnerId;
	String partnerName;
	ConversationPanel cp;
	
	
	public ChatConnection(String name, int id) {
		
		this.cp = new ConversationPanel(id, name);
		
		MainFrame.getInstance().tabs.addNewPanel(cp);
		
		
	}

	@Override
	public void objectReceived(Object object) {
		
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Socket getSocket() {
		// TODO Auto-generated method stub
		return null;
	}

	public void receivedMessage(Message msg) {
		//miss nog wat doen
		this.cp.addExternalMessage(msg);
		
	}

}
