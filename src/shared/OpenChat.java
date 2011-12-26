package shared;

import java.io.Serializable;

import lombok.*;

@Data
@AllArgsConstructor
public class OpenChat implements Serializable{

	private String to;
	private String from;
	private String status;
	
}
