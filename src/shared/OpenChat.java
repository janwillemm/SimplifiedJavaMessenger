package shared;

import java.io.Serializable;

import lombok.*;

@Data

	
@AllArgsConstructor
public class OpenChat implements Serializable{
	private static final long serialVersionUID = 7779393999999555657L;
	private String to;
	private String from;
	private String status;
	
}
