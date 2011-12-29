package gui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class TabsPanePanel extends JTabbedPane{
	
	private ArrayList<ConversationPanel> panels;
	
	public TabsPanePanel(){
		super();
		this.panels = new ArrayList<ConversationPanel>();
		
	}
	
	public void addNewPanel(ConversationPanel panel){
		this.panels.add(panel);
		this.addTab("#" + panel.getPartnerId() + " - " + panel.getPartner(), panel);
		//this.setMnemonicAt(this.getTabCount(), KeyEvent.VK_1);
	}
	
	public void removeTabByUserId(int id){
		ConversationPanel cp = this.getTabByUserId(id);
		
		this.remove(cp);
		this.panels.remove(cp);
		//this.
	}
	
	public ConversationPanel getTabByUserId(int id){
		for(ConversationPanel cp : this.panels){
			if(cp.getPartnerId() == id) return cp;
		}
		return null;
	}
}
