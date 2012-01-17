package gui;

/**
 * Holds all tabs
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.util.ArrayList;
import javax.swing.*;

@SuppressWarnings("serial")
public class TabsPanePanel extends JTabbedPane {
	private ArrayList<ConversationPanel> panels;
	
	/**
	 * Constructor
	 */
	public TabsPanePanel(){
		super();
		this.panels = new ArrayList<ConversationPanel>();
	}
	
	/**
	 * Adds a new ConversationPanel to the pane
	 * @param panel the ConversationPanel that should be added
	 */
	public void addNewPanel(ConversationPanel panel){
		this.panels.add(panel);
		
		if(panel.getPartnerId() < 0) {
			this.addTab(panel.getPartner(), panel);
		}
		else {
			this.addTab("#" + panel.getPartnerId() + " - " + panel.getPartner(), panel);
		}
	}
	
	/**
	 * Removes a ConversationPanel by a given user id
	 * @param id user id
	 */
	public void removeTabByUserId(int id){
		ConversationPanel cp = this.getTabByUserId(id);

		this.remove(cp);
		this.panels.remove(cp);
	}
	
	/**
	 * @param id user id
	 * @return ConversationPanel matching the given user id
	 */
	public ConversationPanel getTabByUserId(int id){
		for(ConversationPanel cp : this.panels){
			if(cp.getPartnerId() == id) return cp;
		}
		return null;
	}
}
