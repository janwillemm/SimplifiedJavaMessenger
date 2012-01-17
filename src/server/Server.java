package server;

/**
 * Main class for server
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import lombok.*;

public class Server {
	@Getter private static int portNumber;
	@Getter private static ServerSocket server = null;
	private static Thread reportThread;
	@Getter private static ArrayList<Client> clients;
	
	/**
	 * Starts the server on the given port number	
	 * @param portNumber port number
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void start(int portNumber) throws IOException, InterruptedException{
		// Check if it isn't started yet
		if(server == null){
			Server.portNumber = portNumber;
			Server.clients = new ArrayList<Client>();
			
			// Create the server socket
			Server.server = new ServerSocket(portNumber);
			System.out.println("* Server up and running!");
			
			// Start report server			
			startReporting();
			System.out.println("* Reporter up and running!");
			
			// Start listener
			listenForClients();
		}
	}
	
	/**
	 * Stops the server
	 * @throws IOException
	 */
	public static void stop() throws IOException{
		Report.stop();
		server.close();
	}
	
	/**
	 * Starts the report server Thread
	 * @throws InterruptedException
	 */
	private static void startReporting() throws InterruptedException{
		reportThread = new Thread(new Report());
		reportThread.start();
	}
	
	/**
	 * Adds a client to the list of clients
	 * @param cl client that should be added
	 */
	public static void addClient(Client cl) {
		if(!clients.contains(cl)) {
			clients.add(cl);
		}
		
		// Also fire an update, so that every client has the new client in its client list
		pushClientsList();
	}
	
	/**
	 * Removes a client from the list of clients
	 * @param cl client that should be removed
	 */
	public static void removeClient(Client cl) {
		if(clients.contains(cl)) {
			clients.remove(cl);
		}
		
		// Also fire an update, so that no client has this client in its client list anymore
		pushClientsList();
	}
	
	/**
	 * Sends the list of currently online clients to every Client
	 */
	public static void pushClientsList(){
		try {
			for(Client c : clients){
				c.sendOnlineUsers();
			}
		} catch (IOException e) {
			System.out.println("* Failed to send the ClientList to all the clients");
		}
	}
	
	/**
	 * Finds a client in the list of clients by the specified client id
	 * @param clientId id of client that should be found 
	 * @return the found Client, or null if no Client was found 
	 */
	public static Client findClient(int clientId) {
		for(Client c : clients) {
			if(c.getClientId() == clientId) {
				return c;
			}
		}
		return null;
	}
	
	/**
	 * Listens for new clients to connect
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void listenForClients() throws IOException, InterruptedException{
		int clientId = 0;
		
		while(true){
			System.out.println("* Waiting for a client to connect...");
		
			// If a client connects, the statement below will return a socket containing the client connection.
			// While nobody connects, the script will wait at this point.
			Socket socket = getServer().accept();
			
			// If there is a connection, create a Client and add it to the list of currently connected clients
			Client client = new Client(socket, clientId);
			addClient(client);

			System.out.println("* Client #" + clientId + " connected!");
			clientId++;
		}
	}
}
