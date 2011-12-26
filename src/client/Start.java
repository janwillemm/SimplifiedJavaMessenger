package client;

import java.util.Scanner;

import shared.Command;

public class Start {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		System.out.println("This is SimplifiedJavaMessenger 0.01b. Thanks for using it! \n What is your name?");
		String name = sc.next();
		Command cmd = new Command("NAME", new String[]{name}, -1);
		
		ServerConnection serverConnection = new ServerConnection();
		serverConnection.createSocket(cmd);
	}

}
