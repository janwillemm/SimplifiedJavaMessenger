/**
 * 	Practicum IN1205P,	Client.java
 *	Auteur jan-willemmanenschijn,		Studienummer 4148509	
 *	Datum Dec 15, 2011
 */
package server;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import lombok.*;

/**
 * @author jan-willemmanenschijn
 *
 */
public class Client implements Runnable {

	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	@Getter private String name;
	
	public Client(Socket socket) throws IOException{
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i<100; i++)
		out.println("hi");
		while(true){
			try {
				String text = in.readLine();
				out.println(text);
				System.out.println(text);
				Scanner sc = new Scanner(System.in);
				out.println(sc.nextLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
