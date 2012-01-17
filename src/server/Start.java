package server;

/**
 * Runs the chat server. Use this one to start the server.
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.IOException;

public class Start {
	/**
	 * @param args commandline parameters
	 */
	public static void main(String[] args){
		try {
			Server.start(1337);
		} 
		catch (IOException e) {
			System.out.println("Failed to start the server...");
		}
		catch (InterruptedException e) {
			System.out.println("Failed to start the server...");
		}
	}
}
