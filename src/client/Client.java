package client;

import gui.MainFrame;
import gui.WelcomeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextField;

import lombok.Getter;

import shared.Command;

public class Client implements ActionListener{
	private static Client cl = null;
	@Getter private MainFrame mainFrame;
	private WelcomeFrame wf;
	private ServerConnection serverConn;
	
	public Client(){
		this.wf = new WelcomeFrame("This is SimplifiedJavaMessenger 0.01b. Thanks for using it!", this);
		this.wf.setVisible(true);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.wf.startButton){
			if(!this.wf.nameTextField.getText().equals("")){
				this.startConnection(this.wf.nameTextField.getText());
				this.wf.setVisible(false);
			}
		}
		else {
			this.serverConn.openChatWithUser(this.mainFrame.getClients().getSelectedUserId(), this.mainFrame.getClients().getSelectedUser());
		}
	}

	private void startConnection(String name) {
		this.mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		
		this.serverConn = new ServerConnection();
		this.serverConn.createSocket();
		try {
			this.serverConn.sender.sendObject(new Command("NAME", new String[]{name}, -1));
			this.serverConn.sender.sendObject(new Command("LIST",new String[]{}, -1));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Client.getInstance();
	}
	
	public static Client getInstance() {
		if(cl == null) {
			cl = new Client();
		}
		return cl;
	}
}
