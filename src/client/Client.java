package client;

import gui.MainFrame;
import gui.WelcomeFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JTextField;

import shared.Command;

public class Client implements ActionListener{

	private WelcomeFrame wf;
	
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
		
	}

	private void startConnection(String name) {
		MainFrame mf = MainFrame.getInstance();
		mf.setVisible(true);
		
		Command cmd = new Command("NAME", new String[]{name}, -1);
		
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.createSocket(cmd);
		try {
			serverConnection.sender.sendObject(new Command("LIST",new String[]{}, 0));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Client client = new Client();
	}
	
}
