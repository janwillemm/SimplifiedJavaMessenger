package gui;

/**
 * Holds the mainFrame
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import lombok.Getter;
import shared.ClientList;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	@Getter private TabsPanePanel tabs;
	@Getter private ClientsPane clients = null;
	
	/**
	 * Constructor
	 */
	public MainFrame(){
		super("Simplified Java Messenger");
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setDefaultLookAndFeelDecorated(true);
		this.setLayout(new BorderLayout());
		
		this.tabs = new TabsPanePanel();
		this.clients = new ClientsPane();
		this.add(this.clients, BorderLayout.EAST);
		this.add(this.tabs, BorderLayout.CENTER);
		
		this.setBounds(100,100,550,500);	
	}
	
	/**
	 * Sends request to update the clientsPane
	 * @param clients the new client list
	 */
	public void addClients(ClientList clients){
		this.clients.update(clients);
	}
	
	/**
	 * Pops out a messageDialog containing the given error
	 * @param text error message
	 */
	public void showError(String text) {
		JOptionPane.showMessageDialog(null, text, "Fout", JOptionPane.ERROR_MESSAGE);
	}
}
