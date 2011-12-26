package client;

import java.io.*;
import java.net.Socket;
import java.util.Date;

import shared.Message;

public class Sender implements Runnable{

	ObjectOutputStream out;
	
	public Sender(Socket socket) throws IOException{
		this.out = new ObjectOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		while(true){
			try {
				String test = in.readLine();
				Message message = new Message(test, new Date(), "Jan-Willem", "Rick");
				
				this.out.writeObject(message);
				this.out.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
	}
}
