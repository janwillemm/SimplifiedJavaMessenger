package client;

import java.awt.Color;
import java.util.Date;

import gui.ConversationPanel;
import gui.MainFrame;

import javax.swing.UIManager;

import shared.Message;

public class Start{

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		Client client = Client.getInstance();
		
		client.setMainFrame(new MainFrame());
		client.getMainFrame().setVisible(true);
		
		ConversationPanel cp = new ConversationPanel(-1, "Inloggen", client);
		client.setCp(cp);
		
		cp.addMessage("Welkom! Vul een naam in en druk op enter om verbinding te maken met de server.", new Color(230, 0, 0));
		
		client.getMainFrame().getTabs().addNewPanel(client.getCp());
	}
}
