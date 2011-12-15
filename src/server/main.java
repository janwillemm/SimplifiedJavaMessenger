package server;

import java.io.IOException;

public class main {

	public static void main(String[] args){
		try {
			while(true){
				Thread thread = Listener.startListeningForClient(1337);
				thread.run();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
