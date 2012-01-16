package client;

import gui.ConversationPanel;
import gui.MainFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextField;

import lombok.Getter;
import lombok.Setter;

import shared.Command;

public class Client implements KeyListener, ActionListener{
	private static Client cl = null;
	@Getter @Setter private MainFrame mainFrame;
	@Getter private ServerConnection serverConn;
	@Getter @Setter private ConversationPanel cp;
	
	public Client(){
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.serverConn.openChatWithUser(this.mainFrame.getClients().getSelectedUserId(), this.mainFrame.getClients().getSelectedUser());
	}

	private void startConnection(String name) {
		
		this.serverConn = new ServerConnection();
		this.serverConn.createSocket();
		try {
			this.serverConn.getSender().sendObject(new Command("NAME", new String[]{name}, -1));
			this.serverConn.getSender().sendObject(new Command("LIST",new String[]{}, -1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Client getInstance() {
		if(cl == null) {
			cl = new Client();
		}
		return cl;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			this.mainFrame.getTabs().remove(this.cp);
			this.startConnection(this.cp.getInput());
			
		}
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
