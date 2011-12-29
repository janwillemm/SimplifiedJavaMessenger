package gui;

import java.awt.BorderLayout;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JFrame;

import shared.Message;

public class MainFrame extends JFrame{

	public static MainFrame mf = null;
	public TabsPanePanel tabs;
	public ClientsPane clients;
	
	public MainFrame(){
		super("SIMPLIFIEDJAVAMESSENGER");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setDefaultLookAndFeelDecorated(true);
		this.setLayout(new BorderLayout());
		
		this.tabs = addTabs();
		this.clients = addClients();
		
		this.add(this.tabs, BorderLayout.CENTER);
		
		this.add(this.clients, BorderLayout.EAST);
		
	}
	
	public static MainFrame getInstance(){
		if(mf == null){
			mf = new MainFrame();
		}
		return mf;
	}
	
	public static void main(String[] args) {
		MainFrame mf = MainFrame.getInstance();
		mf.setBounds(100,100,550,500);
		mf.setVisible(true);
	}
	
	public ClientsPane addClients(){
		ClientsPane cp = new ClientsPane();
		
		HashMap<Integer, String> clients = new HashMap<Integer, String>();
		
		clients.put(0,"JW");
		clients.put(1,"Rick");
		clients.put(2,"Jens");
		clients.put(3,"Klaas");
		clients.put(5,"Jan");
		
		clients.put(7,"JW");
		clients.put(6,"Rick");
		clients.put(9,"Jens");
		clients.put(67,"Klaas");
		clients.put(54,"Jan");
		
		clients.put(567,"JW");
		clients.put(65,"Rick");
		clients.put(53,"Jens");
		clients.put(59,"Klaas");
		clients.put(36,"Jan");
		
		clients.put(56,"JW");
		clients.put(57,"Rick");
		clients.put(58,"Jens");
		clients.put(60,"Klaas");
		clients.put(61,"Jan");
		
		cp.update(clients);
		return cp;
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
