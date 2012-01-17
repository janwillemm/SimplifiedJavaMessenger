package shared;

/**
 * Holds a command
 * @author Jan-Willem Manenschijn & Rick Wieman
 */

import java.io.Serializable;

import lombok.*;
	
@Data
@AllArgsConstructor
public class Command implements Serializable{
	
	private static final long serialVersionUID = -5698066377154655650L;
	public String command;
	public String[] parameters = null;
	public int from;
	
}
