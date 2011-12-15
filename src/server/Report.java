/**
 * 	Practicum IN1205P,	Report.java
 *	Auteur jan-willemmanenschijn,		Studienummer 4148509	
 *	Datum Dec 15, 2011
 */
package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author jan-willemmanenschijn
 *
 */
public class Report implements Runnable{
	
	private final Date now = new Date();
	private ArrayList<Client> clients;
	private static boolean shouldRun = true;
	
	public Report(){
		this.clients = Server.getClients();
	}
	
	public String toString(){
		String res = "-- Server report from: " + now.toString() + "\r\n    number of clients: " + this.clients.size() + "\r\n    connected clients: ";
		for(Client c : this.clients){
			res += c.getName() + ", ";
		}
		return res;
	}

	public void run() {
		while(shouldRun){
			Report report = new Report();
			System.out.println(report);
			try {
				Thread.sleep(10000);
			} 
			catch (InterruptedException e) {
				System.out.println(e.getMessage());
				try {
					Server.stop();
				} catch (IOException e1) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	public static void stop(){
		shouldRun = false;
	}
}
