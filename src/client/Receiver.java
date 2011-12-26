package client;

import java.io.*;
import java.net.Socket;

import shared.Message;

public class Receiver implements Runnable{

	ObjectInputStream in;
	
	public Receiver(Socket socket) throws IOException{
		this.in = new ObjectInputStream(socket.getInputStream());
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Object received;
				received = this.in.readObject();
			
				System.out.println(received);
				
				if(received instanceof Message) {
					Message msg = (Message) received;
					System.out.println(msg.getContent());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e){
				e.printStackTrace();
			}
		}
		
	}

	
}
