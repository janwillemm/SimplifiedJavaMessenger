package server;

/**
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
	
	public static void start(int portNumber) throws IOException, InterruptedException{
		if(server == null){
			clients = new ArrayList<Client>();
			server = new ServerSocket(portNumber);
			System.out.println("* Server up and running!");
			
			Server.portNumber = portNumber;
			startReporting();
			System.out.println("* Reporter up and running!");
			
			listenForClients();
		}
	}
	
	public static void stop() throws IOException{
		Report.stop();
		server.close();
	}
		
	private static void startReporting() throws InterruptedException{
		reportThread = new Thread(new Report());
		reportThread.start();
	}
	
	public static void addClient(Client cl) {
		if(!clients.contains(cl)) {
			clients.add(cl);
		}
	}
	
	public static void removeClient(Client cl) {
		if(clients.contains(cl)) {
			clients.remove(cl);
		}
	}
	
	public static Client findClient(int clientId) {
		for(Client c : clients) {
			if(String.valueOf(c.getClientId()).equals(clientId)) {
				return c;
			}
		}
		return null;
	}
	
	public static void listenForClients() throws IOException, InterruptedException{
		int clientId = 0;
		
		while(true){
			System.out.println("* Waiting for a client to connect...");
		
			Socket socket = getServer().accept();
			Client client = new Client(socket, clientId);
			
			addClient(client);

			System.out.println("* Client #" + clientId + " connected!");
			clientId++;
		}
	}
}
