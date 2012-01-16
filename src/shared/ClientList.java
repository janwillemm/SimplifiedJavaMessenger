package shared;

import java.io.*;
import java.util.HashMap;

import lombok.*;

@Data
public class ClientList implements Serializable{
	private static final long serialVersionUID = 42939343464891357L;
	private HashMap<Integer, String> clients;
	
	public ClientList() {
		this.clients = new HashMap<Integer, String>();
	}
	
	public void add(int clientId, String client) {
		clients.put(clientId, client);
	}
}
