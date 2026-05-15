//This will be where the game is talking to the server

import java.io.*;
import java.net.*;

//class for client
public class GameClient {
	
	// IP addy/ host name
	private String host;

	private int port; //port number

	private String playerName;

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

						if (chatScreen != null) {
							chatScreen.addMessage(serverMessage);
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
			
			/*
			while ((userMessage = keyboardInput.readLine()) != null) { //infinite send loop
				
				serverOutput.println(userMessage);
			} // send typed message to server ^^
			*/
				/*
			System.out.println();
			System.out.println("GAME MODE");
			System.out.println("Type / and press ENTER to open chat.");

			String userInput;

			while((userInput = keyboardInput.readLine()) != null) {
				if (userInput.equals("/")) {
					openChatMode(keyboardInput);
				}
				else {
					System.out.println("You are in GAME MODE. TYPE \"/\" to open Chat.");
				}
			}

		}*/
	}
		catch (IOException e) {
			
			//this is if connection fails (idek whats happenign anymroe bruh 
			System.out.println("Client error: " + e.getMessage());

		}


		

	}
	/*
	private void openChatMode(BufferedReader keyboardInput) throws IOException {
    clearScreen();

    System.out.println("====================================");
    System.out.println("              CHAT MODE             ");
    System.out.println("====================================");
    System.out.println("Type your message and press ENTER.");
    System.out.println("Type /back to return to game mode.");
    System.out.println();

    String message;

    while ((message = keyboardInput.readLine()) != null) {

        if (message.equals("/back")) {
            clearScreen();
            System.out.println("GAME MODE");
            System.out.println("Type / and press ENTER to open chat.");
            return;
        }

        if (!message.trim().equals("")) {
            sendMessage(message);
        }
    }
}





*/

	public void setChatScreen(ChatScreen chatScreen) {
		this.chatScreen = chatScreen;
	}






	public void sendMessage(String message) {
		if(serverOutput != null) {
			
			serverOutput.println(message);
		}
	}
}
/*
	private void clearScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
}

}
*/
