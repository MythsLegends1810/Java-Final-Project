import java.io.*;
import java.net.*;
import java.awt.*;
import javax.swing.*;


public class Main {

public static void main(String[] args) {
	
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
		String playerName = "Player";

		if(args.length >= 2) {
			playerName = args[1];
		}

		GameClient client = new GameClient("localhost", 5000, playerName); //create a game client w/ localhost, same port as GameServer
		
		client.start(); //start tje client

		// Setting up the window using invokeLater to keep it on a separate thread
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("The FourHorsemen: Bullet Hell");
				Game gamePanel = new Game(client);
				
				frame.add(gamePanel);
				frame.pack();  //Adjusts window size to fit the panel
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);

				gamePanel.requestFocusInWindow();
			}
		});

	}

	else {
		System.out.println("Invalid option. Use server or client."); //kinda obvious what this does
	}
/*
	try 
		{ 
	Desktop.getDesktop().browse(new java.net.URI("https://www.youtube.com/watch?v=o1YjuTtBEXE&list=RDo1YjuTtBEXE&start_radio=1")); 
	}
	catch (Exception ex) {}

	}
	*/

	}
}

