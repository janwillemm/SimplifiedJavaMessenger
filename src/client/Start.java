package client;

import gui.ConversationPanel;
import gui.MainFrame;

import javax.swing.UIManager;

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
		
		client.setCp(new ConversationPanel(-1, "Vul je naam in", client));
		
		client.getMainFrame().getTabs().addNewPanel(client.getCp());
	}
}
