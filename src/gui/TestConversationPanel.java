package gui;

import java.awt.BorderLayout;
import java.util.Date;

import javax.swing.JFrame;

import shared.Message;

public class TestConversationPanel extends JFrame {
	public static void main(String[] args) {
		new TestConversationPanel();
	}
	
	public TestConversationPanel() {
		setLayout(new BorderLayout());
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ConversationPanel cp = new ConversationPanel(0, "JW");
		
		add(cp);
		
		cp.addExternalMessage(new Message("Hallo daar",new Date(),"JW",1,0));
		cp.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp.addOwnMessage("Waarom dan?");
		cp.addExternalMessage(new Message("Omdat jij het doet! Kijk nu bijvoorbeeld eens naar deze veel te lange regel; word je daar gelukkig van?",new Date(),"Server",-1,0));
		cp.addOwnMessage(":-(");
		
		cp.addExternalMessage(new Message("Hallo daar",new Date(),"JW",1,0));
		cp.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp.addOwnMessage("Waarom dan?");
		cp.addExternalMessage(new Message("Omdat jij het doet!",new Date(),"Server",-1,0));
		cp.addOwnMessage(":-(");
		
		cp.addExternalMessage(new Message("Hallo daar",new Date(),"JW",1,0));
		cp.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp.addOwnMessage("Waarom dan?");
		cp.addExternalMessage(new Message("Omdat jij het doet!",new Date(),"Server",-1,0));
		cp.addOwnMessage(":-(");
		
		cp.addExternalMessage(new Message("Hallo daar",new Date(),"JW",1,0));
		cp.addExternalMessage(new Message("Dit gaat mis!",new Date(),"Server",-1,0));
		cp.addOwnMessage("Waarom dan?");
		cp.addExternalMessage(new Message("Omdat jij het doet!",new Date(),"Server",-1,0));
		cp.addOwnMessage(":-(");
		
		setVisible(true);
	}
}
