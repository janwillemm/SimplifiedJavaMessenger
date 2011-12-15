package server;

import java.io.*;
import java.net.*;

public class listener {
	
	public static Thread startListeningForClient(int portNumber) throws IOException{
		Server.start(portNumber);
		
		Socket socket = Server.get().accept();
		Client client = new Client(socket);
		
		return new Thread(client);
	}
	
	
}
