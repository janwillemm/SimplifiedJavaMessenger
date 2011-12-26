package shared;

import java.io.*;
import java.util.Date;

import lombok.*;

@Data
@AllArgsConstructor
public class Message implements Serializable{
	
	private static final long serialVersionUID = 429393999999555657L;
	private String content;
	private Date date;
	private int from;
	private int to;
}
