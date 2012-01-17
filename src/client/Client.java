package client;

/**
 * Main class for client
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import gui.ConversationPanel;
import gui.MainFrame;
import shared.Command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import lombok.Getter;
import lombok.Setter;

public class Client implements KeyListener, ActionListener{
	private static Client cl = null;
	@Getter @Setter private MainFrame mainFrame;
	@Getter private ServerConnection serverConn;
	@Getter @Setter private ConversationPanel cp;
	
	/**
	 * Empty constructor
	 */
	public Client(){
	}

	/**
	 * Handles request to open a new chat (when button was clicked)
	 * @param e triggered event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.serverConn.openChatWithUser(this.mainFrame.getClients().getSelectedUserId(), this.mainFrame.getClients().getSelectedUser());
	}

	/**
	 * Opens a ServerConnection and sends the initial commands
	 * @param name
	 */
	private void startConnection(String name) {
		this.serverConn = new ServerConnection();
		this.serverConn.createSocket();
		try {
			this.serverConn.getSender().sendObject(new Command("NAME", new String[]{name}, -1));
			this.serverConn.getSender().sendObject(new Command("LIST",new String[]{}, -1));
		} catch (IOException e) {
			this.mainFrame.showError("Er is iets misgegaan bij de initialisatie...");
		}
	}
	
	/**
	 * @return the instance of Client
	 */
	public static Client getInstance() {
		if(cl == null) {
			cl = new Client();
		}
		return cl;
	}

	/**
	 * Handles initial request (containing the desired user name) and runs startConnection()
	 * @param arg0 triggered event
	 */
	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			this.mainFrame.getTabs().remove(this.cp);
			this.startConnection(this.cp.getInput());
			
		}
	}
	
	/**
	 * Required due to interface KeyListener, but not implemented.
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	/**
	 * Required due to interface KeyListener, but not implemented.
	 */
	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
}
