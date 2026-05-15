//This will be where the game is talking to the server

import java.io.*;
import java.net.*;

//class for client
public class GameClient {

	// IP addy/ host name
	private String host;

	private int port; //port number

	private String playerName;

	private Game game; // stores Game object so GameClient can update otyher players when position message arrives

	private Socket socket;

	private BufferedReader serverInput;

	private PrintWriter serverOutput; //lets other methods send mesages to the server

	private ChatScreen chatScreen;

	public GameClient(String host, int port, String playerName) {
		this.host = host;
		this.port = port;
		this.playerName = playerName;
	}


	// start the client

	public void start() {
		try {

			socket = new Socket(host, port); //connect to server 

			serverInput = new BufferedReader(new InputStreamReader(socket.getInputStream())); // reads messages from server...holy crap this is confusing I hate java


			//PrintWriter serverOutput = new PrintWriter(socket.getOutputStream(), true); // sends a message to server 

			serverOutput = new PrintWriter(socket.getOutputStream(), true);

			// send the player name immediately so the first chat mesage does not become the name i think
			serverOutput.println(playerName);

			//	BufferedReader keyboardInput = new BufferedReader(new InputStreamReader(System.in)); // reads input from a useer


			//bro wtf even is this bruh...sperate thread to constantly recieve new messages 
			Thread receiveThread = new Thread(() -> {
				try {
					String serverMessage;

					while((serverMessage = serverInput.readLine()) != null) {
						System.out.println(serverMessage);

						if (serverMessage.startsWith("POS ")) { // If message starts with POS, it is a movement update

							String[] parts = serverMessage.split(" "); // Split message into pieces: POS, playerName, x, y

							if (parts.length == 8 && game != null) { // Make sure the message format is correct and Game exists

								String otherPlayerName = parts[1]; // Get the player's name

								double x = Double.parseDouble(parts[2]); // Convert x from String to double

								double y = Double.parseDouble(parts[3]); // Convert y from String to double

								String type = parts[4];

								int hp = Integer.parseInt(parts[5]);

								int maxHp = Integer.parseInt (parts[6]);

								int shield = Integer.parseInt (parts[7]);

								game.updateOtherPlayer(otherPlayerName, x, y, type, hp, maxHp, shield); // Update that player on the screen        ADAM FIX THIS
							}
						}

						else { // Otherwise, it is a normal chat message

							if (chatScreen != null) { // Make sure chat screen exists

								chatScreen.addMessage(serverMessage); // Add the message to the chat UI
							}
						}
					}
				}
			

			catch (IOException e) {
				System.out.println("Lost connection to server.");
			}	

		}
		); //like WHATTTT BROOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO I HATE JAVA 

		receiveThread.start(); //self explanatory

		//String userMessage; //capital S galore

	}
	catch (IOException e) {

		//this is if connection fails (idek whats happenign anymroe bruh 
		System.out.println("Client error: " + e.getMessage());

	}
	}


	



public void setChatScreen(ChatScreen chatScreen) {
	this.chatScreen = chatScreen;
}

public void setGame(Game game) { //Lets Game.java give this GameClient access to the Game 
	this.game = game; // Saves Game object into instance variable


}

public void sendPosition(double x, double y) {
	if (serverOutput != null && game != null && game.player1 != null) {
		Player p = game.player1;
		//Pack all stats
		serverOutput.println("POS " + playerName + " " + x + " " + y + " " + p.type + " " + p.hp + " " + p.maxHp + " " + p.shield);
	}
}






public void sendMessage(String message) {
	if(serverOutput != null) {

		serverOutput.println(message);
		}
	}
	}
