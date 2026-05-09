//This will be where the game is talking to the server

import java.io.*;
import java.net.*;

//class for client
public class GameClient {
	
	// IP addy/ host name
	private String host;

	private int port; //port number

	public GameClient(String host, int port) {
		this.host = host;
		this.port = port;
	}


	// start the client

	public void start() {
		try {
			
			Socket socket = new Socket(host, port); //connect to server 

			BufferedReader serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream())); // reads messages from server...holy crap this is confusing I hate java


			PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true); // sends a message to server 

			BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in)); // reads input from a useer
			

			//bro wtf even is this bruh...sperate thread to constantly recieve new messages 
			Thread receiveThread = new Thread(() -> {
				try {
					String serverMessage;

					while((serverMessage = serverInput.readLine()) != null) {
						System.out.println(serverMessage);
					}
				}

				catch (IOException e) {
					System.out.println("Lost connection to server.");
				}	

				}
			); //like WHATTTT BROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO I HATE JAVA 

			receiveThread.start(); //self explanatory

			String userMessage; //capital S galore

			while ((userMessage = keyboardInput.readLine()) != null) { //infinite send loop
				
				serverOutput.println(userMessage);
			} // send typed message to server ^^

		}
		catch (IOException e) {
			
			//this is if connection fails (idek whats happenign anymroe bruh 
			System.out.println("Client error: " + e.getMessage());

		}


		

	}

}
