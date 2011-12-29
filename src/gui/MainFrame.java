package gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame{

	public static MainFrame mf = null;
	public TabsPanePanel tabs;
	public ClientsPane clients;
	
	public MainFrame(){
		super("SIMPLIFIEDJAVAMESSENGER");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		MainFrame.setDefaultLookAndFeelDecorated(true);
		this.setLayout(new BorderLayout());
		this.add(this.tabs);
		this.add(this.clients, "WEST");
		
	}
	
	public static MainFrame getInstance(){
		if(mf == null){
			mf = new MainFrame();
		}
		return mf;
	}
	
	
}
