package client;

import gui.ConversationPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;

import lombok.Data;
import shared.Command;
import shared.Message;
import shared.Sender;

@Data
public class ChatConnection implements KeyListener {

	int partnerId;
	String partnerName;
	Sender sender;
	
	ConversationPanel cp;
	
	
	public ChatConnection(String name, int id, Sender sender) {
		this.partnerId = id;
		this.partnerName = name;
		this.sender = sender;
		
		this.cp = new ConversationPanel(id, name, this);
		
		Client.getInstance().getMainFrame().getTabs().addNewPanel(cp);
		Client.getInstance().getMainFrame().getTabs().setSelectedComponent(cp);
	}

	public void receivedMessage(Message msg) {
		this.cp.addExternalMessage(msg);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
	
			try {
				String input = this.cp.getInput();
				
				if(input.substring(0, 1).equals("/")){
					String cmnd = input.split(" ")[0];
					
					Command cmd = new Command(cmnd.replace("/", "").toUpperCase(), input.replace(cmnd+ " ", "").split(" "), -1);
					this.sender.sendObject(cmd);
				}
				else{
					Message message = new Message(input, new Date(), null, 0, this.partnerId);
					this.sender.sendObject(message);
					this.cp.addOwnMessage(message);
					this.cp.setInput("");
				}
	
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				
			} catch (NumberFormatException e1){
				System.out.println("Voer wel een getal in!");
			}
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
