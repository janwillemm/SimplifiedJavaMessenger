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
		
		if(panel.getPartnerId() < 0) {
			this.addTab(panel.getPartner(), panel);
		}
		else {
			this.addTab("#" + panel.getPartnerId() + " - " + panel.getPartner(), panel);
		}
	}
	
	public void removeTabByUserId(int id){
		ConversationPanel cp = this.getTabByUserId(id);

		this.remove(cp);
		this.panels.remove(cp);
	}
	
	public ConversationPanel getTabByUserId(int id){
		for(ConversationPanel cp : this.panels){
			if(cp.getPartnerId() == id) return cp;
		}
		return null;
	}
}
