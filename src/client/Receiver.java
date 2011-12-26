package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Receiver implements Runnable{

	BufferedReader in;
	
	public Receiver(Socket socket) throws IOException{
		this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	@Override
	public void run() {
		while(true){
			try {
				System.out.println(this.in.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	
}
