package gui;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class WelcomeFrame extends JFrame{

	public JTextField nameTextField;
	public JButton startButton;
	
	public WelcomeFrame(String message, ActionListener al){
		this.setBounds(500,500,300,150);
		this.setLayout(new GridLayout(4,1));
		this.add(new JLabel(message));
		this.add(new JLabel("Please fill in your name"));
		this.nameTextField = new JTextField(15);
		this.add(this.nameTextField);
		this.startButton = new JButton("Start!");
		this.startButton.addActionListener(al);
		this.add(this.startButton);
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		WelcomeFrame wf = new WelcomeFrame("Geen status", null);
		wf.setVisible(true);

	}

}
