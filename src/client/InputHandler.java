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
	
	public InputHandler(Sender sender){
		this.sender = sender;
	}

	@Override
	public void run() {
		InputStreamReader converter = new InputStreamReader(System.in);
		BufferedReader in = new BufferedReader(converter);
		while(true){
			try {
				String input = in.readLine();
				
				if(input.substring(0, 1).equals("/")){
					String cmnd = input.split(" ")[0];
					if(cmnd.toUpperCase().equals("/TO")){
						this.destId = Integer.parseInt(input.split(" ")[1]);
					}else{
						Command cmd = new Command(cmnd.replace("/", "").toUpperCase(), input.replace(cmnd, "").split(" "), 0);
						this.sender.sendObject(cmd);
					}
				}else{
					Message message = new Message(input, new Date(), null, 0, this.destId);
					this.sender.sendObject(message);
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			} catch (NumberFormatException e){
				System.out.println("Voer wel een getal in!");
			}
		}
		
	}

}
