package gui;

/**
 * Holds a ClientList
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

import lombok.Getter;
import shared.ClientList;
import client.Client;

@SuppressWarnings({"serial", "rawtypes", "unchecked"})
public class ClientsPane extends JPanel {
	@Getter private ClientList clients = null;
	
	private DefaultListModel clientListModel = new DefaultListModel();
	private JList clientList;
		
	private JButton open = new JButton("Open gesprek");
	
	/**
	 * Constructor, to create the pane
	 */
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
		
		this.open.addActionListener(Client.getInstance());	
	}
	
	/**
	 * Handles the given list as an update, by using it to refresh the contents of this pane
	 * @param clients list of currently online clients
	 */
	public void update(ClientList clients) {
		this.clients = clients;
		
		this.clientListModel.removeAllElements();
		
		for(int i : clients.getClients().keySet()) {
			this.clientListModel.addElement("#" + i + ": " + clients.getClients().get(i));
		}
	}
	
	/**
	 * @return the ID of the selected user name
	 */
	public int getSelectedUserId() {
		if(this.clientList.getSelectedValue() != null) {
			String[] clientIdArr = ((String) this.clientList.getSelectedValue()).split(":");
			String clientId = clientIdArr[0].substring(1);
			return Integer.parseInt(clientId);
		}
		return -1;
	}

	/**
	 * @return the selected user name
	 */
	public String getSelectedUser() {
		if(this.getSelectedUserId() > -1) {
			return this.clients.getClients().get(this.getSelectedUserId());
		}
		return null;
	}
}
