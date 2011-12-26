package shared;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

import lombok.*;


@Data
@AllArgsConstructor
public class Message implements Serializable{

	private String content;
	private Date date;
	private String from;
	private String to;
	
	public void serialize(ObjectOutputStream stream) throws IOException{
		stream.writeObject(this);
	}
}
