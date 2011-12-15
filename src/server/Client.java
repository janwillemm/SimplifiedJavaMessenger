/**
 * 	Practicum IN1205P,	Client.java
 *	Auteur jan-willemmanenschijn,		Studienummer 4148509	
 *	Datum Dec 15, 2011
 */
package server;

import java.io.*;
import java.net.*;

/**
 * @author jan-willemmanenschijn
 *
 */
public class Client implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private String name;
	
	public Client(Socket socket) throws IOException{
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
