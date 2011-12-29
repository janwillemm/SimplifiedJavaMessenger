package gui;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JFrame;

import shared.Message;

public class TestTabsPanePanel extends JFrame{

	public static void main(String[] args) {
		new TestTabsPanePanel();
	}
	
	public TestTabsPanePanel(){
		setLayout(new BorderLayout());
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
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
		
		this.add(tp);
		
		this.setVisible(true);
		
		tp.addNewPanel(cp);
		
		tp.removeTabByUserId(1);
		
		
	}
}
