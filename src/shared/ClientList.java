package shared;

/**
 * Holds a list of currently online clients (id + name)
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;
import java.util.HashMap;

import lombok.*;

@Data
public class ClientList implements Serializable{
	private static final long serialVersionUID = 42939343464891357L;
	private HashMap<Integer, String> clients;
	
	/**
	 * Constructor
	 */
	public ClientList() {
		this.clients = new HashMap<Integer, String>();
	}
	
	/**
	 * Adds a specified client to the list
	 * @param clientId id of client
	 * @param client name of client
	 */
	public void add(int clientId, String client) {
		clients.put(clientId, client);
	}
}
