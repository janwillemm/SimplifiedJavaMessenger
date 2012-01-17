package client;

/**
 * Runs the chat client. Use this one to run the program.
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.awt.Color;

import gui.ConversationPanel;
import gui.MainFrame;

import javax.swing.UIManager;

public class Start{
	/**
	 * @param args commandline parameters
	 */
	public static void main(String[] args) {
		// Get look and feel from current UIManager (i.e. Windows/Apple/KDE/Gnome/etc.)
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Get the instance of Client (and therefore, the Client will be initialized) 
		Client client = Client.getInstance();
		
		// Create the mainFrame
		client.setMainFrame(new MainFrame());
		client.getMainFrame().setVisible(true);
		
		// Require a login, by adding a ConversationPanel prompting to enter a user name
		ConversationPanel cp = new ConversationPanel(-1, "Inloggen", client);
		client.setCp(cp);
		cp.addMessage("Welkom! Vul een naam in en druk op enter om verbinding te maken met de server.", new Color(230, 0, 0));
		
		client.getMainFrame().getTabs().addNewPanel(client.getCp());
	}
}
