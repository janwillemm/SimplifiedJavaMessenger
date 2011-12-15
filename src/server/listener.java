package server;

import java.io.*;
import java.net.*;

public class Listener {
	
	public static Thread startListeningForClient(int portNumber) throws IOException, InterruptedException{
		Server.start(portNumber);
		System.out.println("hi");
		Socket socket = Server.get().accept();
		Client client = new Client(socket);
		
		
		return new Thread(client);
	}
	
	
}
