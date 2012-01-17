package server;

/**
 * Shows a report containing all online users, every 10 seconds
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class Report implements Runnable{
	
	private final Date now = new Date();
	private ArrayList<Client> clients;
	private static boolean shouldRun = true;
	
	/**
	 * Constructor.
	 */
	public Report(){
		this.clients = Server.getClients();
	}
	
	/**
	 * @return the report
	 */
	public String toString(){
		String res = "-- Server report from: " + now.toString() + "\r\n    number of clients: " + this.clients.size() + "\r\n    connected clients: ";
		for(Client c : this.clients){
			res += c.getName() + ", ";
		}
		return res;
	}

	/**
	 * Since this is a Thread, there must be a run...
	 * This one prints the report every 10 seconds.
	 */
	public void run() {
		while(shouldRun){
			Report report = new Report();
			System.out.println(report);
			
			try {
				// Wait 10 seconds
				Thread.sleep(10000);
			}
			// In case waiting fails, stop report server
			catch (InterruptedException e) {
				System.out.println("* Sleeping of report server failed");
				try {
					Server.stop();
				} catch (IOException e1) {
					System.out.println("* Stopping of report server failed");
				}
			}
		}
	}
	
	/**
	 * To be able to stop this Thread, this one stops the while-loop in this.run()
	 */
	public static void stop(){
		shouldRun = false;
	}
}
