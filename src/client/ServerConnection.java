package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

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
	
	public void createSocket(Command cmd){
		
			try {
				//JW 145.53.129.85
				this.socket = new Socket("86.83.37.53", 1337);
				this.sender = new Sender(socket);
				this.send = new Thread(new InputHandler(this.sender, this.id));
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
			System.out.println("*** "+msg.getFrom()+" zegt: "+msg.getContent());
		}
		
	}

	@Override
	public void disconnect() {
		// TODO Auto-generated method stub
		
	}
	
}
