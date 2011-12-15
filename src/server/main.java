package server;

import java.io.IOException;

public class main {

	public static void main(String[] args){
		try {
			while(true){
				System.out.println("* Waiting for client...");
				Thread thread = Listener.startListeningForClient(1337);
				thread.start();
				System.out.println("* Client connected!");
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
