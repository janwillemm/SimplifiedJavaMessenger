package client;

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
		
		Client.getInstance();
	}
}
