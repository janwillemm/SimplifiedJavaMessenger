package shared;

import java.net.Socket;

public interface DataHandler {

	Socket socket = null;

	public void objectReceived(Object object);
	
	public void disconnect();
}
