package gui;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import lombok.*;

public class ClientsPane extends JPanel {
	@Getter private HashMap<Integer, String> clients = null;
	
	private DefaultListModel clientListModel = new DefaultListModel();
	private JList clientList;
		
	private JButton open = new JButton("Open gesprek");
	
	public ClientsPane() {
		setLayout(new BorderLayout(2,2));
		
		this.clientList = new JList(clientListModel);
		this.clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		this.clientList.setBackground(Color.BLACK);
		this.clientList.setForeground(Color.LIGHT_GRAY);
		this.clientList.setFont(new Font("Consolas", 0, 12));
		this.clientList.setSelectionBackground(Color.GRAY);
		this.clientList.setSelectionForeground(Color.BLACK);
		
		JScrollPane scrollPane = new JScrollPane(clientList, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(this.open, BorderLayout.SOUTH);
		
		//TODO point to actionHandler!
		this.clientList.addListSelectionListener(null);	
	}
	
	public void update(HashMap<Integer, String> clients) {
		this.clients = clients;
		
		this.clientListModel.removeAllElements();
		
		for(int i : clients.keySet()) {
			this.clientListModel.addElement("#" + i + ": " + clients.get(i));
		}
	}
	
	public int getSelectedUserId() {
		if(this.clientList.getSelectedValue() != null) {
			String[] clientIdArr = ((String) this.clientList.getSelectedValue()).split(":");
			String clientId = clientIdArr[0].substring(1);
			return Integer.parseInt(clientId);
		}
		return -1;
	}

	public String getSelectedUser() {
		if(this.getSelectedUserId() > -1) {
			return this.clients.get(this.getSelectedUserId());
		}
		return null;
	}
}
