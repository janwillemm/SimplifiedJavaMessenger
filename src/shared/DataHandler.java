package shared;

/**
 * Interface for all classes that handle data, so that Receiver can be used at both client-side and server-side.
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.net.Socket;

public interface DataHandler {

	public void objectReceived(Object object);
	
	public void disconnect();
	
	public Socket getSocket();
}
