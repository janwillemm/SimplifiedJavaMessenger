package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WelcomeFrame extends JFrame implements KeyListener{

	public JTextField nameTextField;
	public JButton startButton;
	
	public WelcomeFrame(String message, ActionListener al){
		this.setBounds(500,500,300,150);
		this.setLayout(new GridLayout(4,1));
		this.add(new JLabel(message));
		this.add(new JLabel("Please fill in your name"));
		this.nameTextField = new JTextField(15);
		this.nameTextField.addKeyListener(this);
		this.add(this.nameTextField);
		this.startButton = new JButton("Start!");
		this.startButton.addActionListener(al);
		this.add(this.startButton);
	}
	
	
	/**
	 * @param args
	 */


	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if(arg0.getKeyCode() == KeyEvent.VK_ENTER) {
			this.startButton.doClick();
		}
	}


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
