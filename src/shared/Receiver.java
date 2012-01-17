package shared;

/**
 * A thread to keep waiting for new packages
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.*;


public class Receiver implements Runnable{

	ObjectInputStream in;
	DataHandler returnTo;
	
	/**
	 * Constructor
	 * @param returnTo class to where data should be sent
	 * @throws IOException
	 */
	public Receiver(DataHandler returnTo) throws IOException{
		this.in = new ObjectInputStream(returnTo.getSocket().getInputStream());
		this.returnTo = returnTo;
	}
	
	/**
	 * Keeps on listening for new objects
	 */
	@Override
	public void run() {
		while(true){
			try {
				// Hand over Object to handler
				this.returnTo.objectReceived(this.in.readObject());
			}
			
			// If something goes wrong, disconnect and stop the loop
			catch (IOException e) {
				this.returnTo.disconnect();
				break;
			}
			catch (ClassNotFoundException e){
				this.returnTo.disconnect();
				break;
			}
		}
	}
}