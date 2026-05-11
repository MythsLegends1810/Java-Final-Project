import java.io.*;
import java.net.*;


public class Main {

public static void main(String[] args) {

	JFrame frame = new JFrame("The FourHorsemen: Bullet Hell");
	Game gamePanel = new Game();
	frame.add(gamePanel);
	frame.pack();  //Adjusts window size to fit the panel
	frame.setVisible(true);

	if (args.length == 0) { // if user doesn't type to server/client
		
		//how to run it
		System.out.println("Usage:");
        System.out.println("java Main server");
        System.out.println("java Main client");
        
		return;

	}

	if (args[0].equalsIgnoreCase("server")) { //if they typed server
		GameServer server = new GameServer(5000); //creates a GameServer object using port 5000
		server.start(); //start server
	}

	else if (args[0].equalsIgnoreCase("client")) { //if they typed client
		GameClient client = new GameClient("localhost", 5000); //create a game client w/ localhost, same port as GameServer
		client.start(); //start tje client
	}

	else {
		System.out.println("Invalid option. Use server or client."); //kinda obvious what this does
	}
}

}
