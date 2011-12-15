/**
 * 	Practicum IN1205P,	Server.java
 *	Auteur jan-willemmanenschijn,		Studienummer 4148509	
 *	Datum Dec 15, 2011
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * @author jan-willemmanenschijn
 *
 */
public class Server {
	private static ServerSocket server = null;
	private static Thread reportThread;
	private static ArrayList<Client> clients;
	
	public static void start(int portNumber) throws IOException, InterruptedException{
		if(server == null){
			clients = new ArrayList<Client>();
			server = new ServerSocket(portNumber);
			startReporting();
		}
	}
	
	public static void stop() throws IOException{
		Report.stop();
		server.close();
	}
	
	public static ServerSocket get(){
		return server;
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
	
	public static Client findClient(String name) {
		for(Client c : clients) {
			if(c.getName().equals(name)) {
				return c;
			}
		}
		return null;
	}
		
	public static ArrayList<Client> getClients(){
		return clients;
	}
}
