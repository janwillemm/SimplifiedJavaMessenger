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
	
	public static void start(int portNumber) throws IOException, InterruptedException{
		if(server == null){
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
	}
	
	public static ArrayList<Client> getClients(){
		
		ArrayList<Client> clients = new ArrayList<Client>();
		
		return clients;
	}
}
