/**
 * 	Practicum IN1205P,	Client.java
 *	Auteur jan-willemmanenschijn,		Studienummer 4148509	
 *	Datum Dec 15, 2011
 */
package server;

import java.io.*;
import java.net.*;
import lombok.*;

/**
 * @author jan-willemmanenschijn
 *
 */

@EqualsAndHashCode
public class Client implements Runnable {
	private Socket socket;
	@Getter private BufferedReader in;
	@Getter private PrintWriter out;
	@Getter private String name;
	@Getter @Setter private Client partner = null;
	
	public Client(Socket socket) throws IOException{
		this.socket = socket;
		this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
		this.out = new PrintWriter(this.socket.getOutputStream(), true);
	}

	public void chooseNick() throws IOException {
		out.print("Please enter a nickname: ");
		out.flush();
		
		this.name = in.readLine();
		
		if(Server.findClient(this.name) != null) {
			out.println("\nSorry, that one's already in use...");
			chooseNick();
		}
	}
	
	public void findPartner() throws IOException {
		out.print("Please enter a chatpartner: ");
		out.flush();
				
		String partnerName = in.readLine();
		
		Client partner = Server.findClient(partnerName);
		
		if(partner != null) {
			connectWithPartner(partner);
		}
		else {
			out.println("\nSorry, he or she is currently not online...");
			findPartner();
		}
	}
	
	public void connectWithPartner(Client partner) throws IOException {
		partner.getOut().println("\n" + this.name + " wants a conversation with you... Do you agree? (Yes/no)");
		
		//TODO fix that partner-bufferedreader doesn't wait for it's own input, but responds directly to this question
		String agreement = partner.getIn().readLine();
		
		if(agreement.equals("Yes")) {
			partner.getOut().println("You agreed! Opening conversation now...");
			partner.setPartner(this);
			this.partner = partner;
		}
		else {
			partner.getOut().println("You declined. No further actions are required.");
			out.println(partner.getName() + " declined. The conversation won't be opened.");
			this.partner = null;
		}
	}
	
	@Override
	public void run() {
		out.println("Hello, client.");
		
		try {
			chooseNick();
			Server.addClient(this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		out.println("Welcome, " + this.name + "!");
				
		
		while(true){
			try {
				//If partner is set, send messages to partner
				if(this.partner != null) {
					String msg = in.readLine();
					this.partner.getOut().println(this.name + ": " + msg);
				}
				//Else: serve possibilities to open connection with partner
				else {
					String text = in.readLine();
					if(text.equals("open")) {
						findPartner();
					}
					else {
						out.println("You said: " + text);
					}
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
