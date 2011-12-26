package shared;

import java.net.Socket;

public interface DataHandler {

	public void objectReceived(Object object);
	
	public void disconnect();
	
	public Socket getSocket();
}
