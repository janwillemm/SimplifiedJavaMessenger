package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import shared.Message;
import shared.Sender;

public class InputHandler implements Runnable{
	
	Sender sender;
	
	public InputHandler(Sender sender){
		this.sender = sender;
	}

	@Override
	public void run() {
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		while(true){
			try {
				String test = in.readLine();
				Message message = new Message(test, new Date(), "Jan-Willem", "Rick");
				
				this.sender.sendObject(message);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
	}

}
