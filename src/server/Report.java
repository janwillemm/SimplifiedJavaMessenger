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
		
		Server.getClients().size();
		this.clients = Server.getClients();
	}
	
	public String toString(){
		String res = "Server report from: " + now.toString() + "\r\n number of clients: " + this.clients.size() ;
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
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				try {
					Server.stop();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
				}
			}
		}
	}
	
	public static void stop(){
		shouldRun = false;
	}
	
	

}
