package gui;

import java.awt.BorderLayout;
import java.util.HashMap;

import javax.swing.JFrame;

import lombok.Getter;


public class MainFrame extends JFrame{
	@Getter private TabsPanePanel tabs;
	@Getter private ClientsPane clients = null;
	
	public MainFrame(){
		super("SIMPLIFIEDJAVAMESSENGER");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setDefaultLookAndFeelDecorated(true);
		this.setLayout(new BorderLayout());
		
		this.tabs = new TabsPanePanel();
		this.clients = new ClientsPane();
		this.add(this.clients, BorderLayout.EAST);
		this.add(this.tabs, BorderLayout.CENTER);
		
		
		this.setBounds(100,100,550,500);	
	}
	
	public void addClients(HashMap<Integer, String> clients){
		this.clients.update(clients);
	}
}
