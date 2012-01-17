package client;

/**
 * Holds the ConversationPanel and interaction with chat partner.
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import gui.ConversationPanel;
import shared.Command;
import shared.Message;
import shared.Sender;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Date;

import lombok.Data;

@Data
public class ChatConnection implements KeyListener {
	private int partnerId;
	private String partnerName;
	private Sender sender;
	private ConversationPanel cp;
	
	/**
	 * Constructor
	 * @param name Name of partner
	 * @param id ID of partner
	 * @param sender Output stream (via shared.Sender)
	 */
	public ChatConnection(String name, int id, Sender sender) {
		this.partnerId = id;
		this.partnerName = name;
		this.sender = sender;
		
		this.cp = new ConversationPanel(id, name, this);
		
		Client.getInstance().getMainFrame().getTabs().addNewPanel(cp);
		Client.getInstance().getMainFrame().getTabs().setSelectedComponent(cp);
	}

	/**
	 * Handles incoming messages
	 * @param msg message
	 */
	public void receivedMessage(Message msg) {
		this.cp.addExternalMessage(msg);
	}
	
	/**
	 * Handles send requests
	 * @param arg0 triggered event
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			try {
				String input = this.cp.getInput();
				
				// If a command was entered 
				if(input.substring(0, 1).equals("/")){
					String cmnd = input.split(" ")[0];
					
					Command cmd = null;
					
					// If user wants to close a conversation, handle request also local (by closing the tab and removing it from the ConversationPanel list)
					if(cmnd.replace("/", "").toUpperCase().equals("PART") && this.partnerId > -1) {
						Client.getInstance().getMainFrame().getTabs().removeTabByUserId(this.partnerId);
						cmd = new Command("PART", new String[]{(this.partnerId+"")}, -1);
						Client.getInstance().getServerConn().getChats().remove(this);
					}
					// Else, only created a Command object
					else {
						cmd = new Command(cmnd.replace("/", "").toUpperCase(), input.replace(cmnd+ " ", "").split(" "), -1);
					}
					
					// Send Command and clean the input box
					this.sender.sendObject(cmd);
					this.cp.setInput("");
				}
				// Else (a message was entered)
				else{
					Message message = new Message(input, new Date(), null, 0, this.partnerId);
					
					// Send Message, display locally and reset the input box
					this.sender.sendObject(message);
					this.cp.addOwnMessage(message);
					this.cp.setInput("");
				}
			} catch (IOException e1) {
				Client.getInstance().getMainFrame().showError("Er is iets misgegaan bij het verzenden van je opdracht...");
			}
		}
	}
	
	/**
	 * Required due to interface KeyListener, but not implemented.
	 */
	@Override
	public void keyReleased(KeyEvent e) {

	}
	
	/**
	 * Required due to interface KeyListener, but not implemented.
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
