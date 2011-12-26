package client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	Socket socket;
	Thread send;
	Thread receive;
	
	public void createSocket(){
		
			try {
				//JW 145.53.129.85
				socket = new Socket("86.83.37.53", 1337);
				send = new Thread(new Sender(socket));
				receive = new Thread(new Receiver(socket));
				
				send.start();
				receive.start();
				
				
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
}
