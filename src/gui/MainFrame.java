package gui;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;

import shared.Message;

public class MainFrame extends JFrame{

	public static MainFrame mf = null;
	public TabsPanePanel tabs;
	public ClientsPane clients = null;
	
	public MainFrame(){
		super("SIMPLIFIEDJAVAMESSENGER");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setDefaultLookAndFeelDecorated(true);
		this.setLayout(new BorderLayout());
		
		this.tabs = addTabs();
		this.clients = new ClientsPane();
		this.add(this.clients, BorderLayout.EAST);
		this.add(this.tabs, BorderLayout.CENTER);
		
		
		this.setBounds(100,100,550,500);
		
	}
	
	public static MainFrame getInstance(){
		if(mf == null){
			mf = new MainFrame();
		}
		return mf;
	}
	
	public void addClients(HashMap<Integer, String> clients){
		this.clients.update(clients);
	}
	
	public TabsPanePanel addTabs(){
		ConversationPanel cp1 = new ConversationPanel(0, "JW");
		
		
		cp1.addExternalMessage(new Message("Hallo daar",new Date(),"JW",1,0));
		cp1.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp1.addOwnMessage("Waarom dan?");
		cp1.addExternalMessage(new Message("Omdat jij het doet!",new Date(),"Server",-1,0));
		cp1.addOwnMessage(":-(");
		
		ConversationPanel cp2 = new ConversationPanel(1, "JW");
		
		cp2.addExternalMessage(new Message("niks daar",new Date(),"JW",1,0));
		cp2.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp2.addOwnMessage("Waarom dan?");
		cp2.addExternalMessage(new Message("Omdat jij het doet!",new Date(),"Server",-1,0));
		cp2.addOwnMessage(":-(");
		
		ConversationPanel cp = new ConversationPanel(2, "JW");
		
		cp.addExternalMessage(new Message("Hallo daar",new Date(),"JW",1,0));
		cp.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp.addOwnMessage("Waarom dan?");
		cp.addExternalMessage(new Message("Omdat jij het doet!",new Date(),"Server",-1,0));
		cp.addOwnMessage(":-(");
		
		TabsPanePanel tp = new TabsPanePanel();
		
		tp.addNewPanel(cp1);
		tp.addNewPanel(cp2);
		return tp;
	}
	
	
}
