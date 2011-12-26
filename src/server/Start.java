package server;

/**
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.IOException;

public class Start {
	/**
	 * Starts the chatserver
	 * @param args console params
	 */
	public static void main(String[] args){
		try {
			Server.start(1337);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
