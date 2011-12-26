package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import shared.Command;
import shared.Message;
import shared.Sender;

public class InputHandler implements Runnable{
	
	Sender sender;
	int id;
	int destId;
	
	public InputHandler(Sender sender, int id){
		this.sender = sender;
		this.id = id;
	}

	@Override
	public void run() {
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		while(true){
			try {
				String input = in.readLine();
				
				if(input.substring(0, 1).equals("/")){
					Command cmd = new Command(input.split(" ")[0].replace("/", "").toUpperCase(), input.replace(input.split(" ")[0], "").split(" "), this.id);
					this.sender.sendObject(cmd);
				}else{
					Message message = new Message(input, new Date(), this.id, this.destId);
					this.sender.sendObject(message);
				}
				
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		}
		
	}

}
