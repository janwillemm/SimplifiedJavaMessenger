package gui;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import lombok.Getter;
import shared.ClientList;


public class MainFrame extends JFrame{
	@Getter private TabsPanePanel tabs;
	@Getter private ClientsPane clients = null;
	
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
	
	public void addClients(ClientList clients){
		this.clients.update(clients);
	}
	
	public void askForName(){
		
	}
}
