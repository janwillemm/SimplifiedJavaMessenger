package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;

import shared.Message;

public class Receiver implements Runnable {

	Client client;
	ObjectInputStream in;
	
	public Receiver(Client client) throws IOException {
		this.client = client;
		this.in = new ObjectInputStream(client.getSocket().getInputStream());
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
					this.client.receivedMessage(msg.getContent());
				}
			}
				
			catch (IOException e) {
				try {
					e.printStackTrace();
					client.disconnect();
					break;
				} catch (IOException e1) {
					e1.printStackTrace();
					break;
				}
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
		}
	}

}
