package gui;

import java.awt.*;
import java.util.HashMap;

import javax.swing.*;

public class TestClientsPane extends JFrame {
	public static void main(String[] args) {
		new TestClientsPane();
	}
	
	public TestClientsPane() {
		setLayout(new BorderLayout());
		setBounds(100, 100, 200, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		ClientsPane cp = new ClientsPane();
		
		HashMap<Integer, String> clients = new HashMap<Integer, String>();
		
		clients.put(0,"JW");
		clients.put(1,"Rick");
		clients.put(2,"Jens");
		clients.put(3,"Klaas");
		clients.put(5,"Jan");
		
		clients.put(7,"JW");
		clients.put(6,"Rick");
		clients.put(9,"Jens");
		clients.put(67,"Klaas");
		clients.put(54,"Jan");
		
		clients.put(567,"JW");
		clients.put(65,"Rick");
		clients.put(53,"Jens");
		clients.put(59,"Klaas");
		clients.put(36,"Jan");
		
		clients.put(56,"JW");
		clients.put(57,"Rick");
		clients.put(58,"Jens");
		clients.put(60,"Klaas");
		clients.put(61,"Jan");
		
		add(cp);
		
		cp.update(clients);
		
		setVisible(true);
	}

}
