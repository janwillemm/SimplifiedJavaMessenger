package gui;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.*;

@SuppressWarnings("serial")
public class TabsPanePanel extends JTabbedPane{
	
	private ArrayList<ConverstationPanel> panels;
	
	public TabsPanePanel(){
		super();
		this.panels = new ArrayList<ConverstationPanel>();
		
	}
	
	public void addTabWithUser(ConverstationPanel panel){
		this.panels.add(panel);
		this.addTab("#" + panel.PId() + " - " + panel.P(), panel);
		//this.setMnemonicAt(this.getTabCount(), KeyEvent.VK_1);
	}
	
	public void removeTabByUserId(int id){
		ConverstationPanel cp = this.getTabByUserId(id);
		
		this.remove(cp);
		this.panels.remove(cp);
		//this.
	}
	
	public ConverstationPanel getTabByUserId(int id){
		for(ConverstationPanel cp : this.panels){
			if(cp.getId().equals(id+"")) return cp;
		}
		return null;
	}
}
