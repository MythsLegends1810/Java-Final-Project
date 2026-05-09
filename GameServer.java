//This will be where the ServerSocket is ran and manages the connections
import java.io.*;
import java.net.*;
import java.util.*;


public class GameServer {
	
	private int port; // port number the server runs on
	
	private ArrayList<ClientHandler> clients; // stores all connected players

	public GameServer(int port) {
		this.port = port;

		this.clients = new ArrayList<>();
	}

	public void start() {
		
		try{
			ServerSocket serverSocket = new ServerSocket(port);

            System.out.println("Game server started on port " + port);

            // infinite loop = server stays running forever
            while (true) {
				Socket clientSocket = serverSocket.accept(); //wait until player connects
				System.out.println("New player connected.");

				ClientHandler client = new ClientHandler(clientSocket, this); //create a clienthandler for this player 
				clients.add(client); //adding the player to the connected players list 

				Thread thread = new Thread(client); //creates a thread for that player 

				thread.start(); // self-explanatory
		}

	}

	catch (IOException e) {
		// this is if the server crashes
		//but we maybe will change this later for the die out death screen
		System.out.println("Server error: " + e.getMessage());
	}
}
	
	//sends a message to ever player 
	public synchronized void broadcast(String message, ClientHandler sender) {

        // Loop through every client
        for (ClientHandler client : clients) {

            // Don't send message back to sender
            if (client != sender) {

                // Send the message
                client.sendMessage(message);
            }
        }
    }
		
	//this is for removing a disconnected player 
		// Removes disconnected player
    public synchronized void removeClient(ClientHandler client) {

        // remove them from list
        clients.remove(client);

        System.out.println("A player disconnected.");
    }

    // handles ONE connected player
    private class ClientHandler implements Runnable {

        // player's socket connection
        private Socket socket;

        // reference to server
        private GameServer server;

        // eads data FROM client
        private BufferedReader input;

        // sends data TO client
        private PrintWriter output;

        // stores player's username
        private String playerName;

        // constructor
        public ClientHandler(Socket socket, GameServer server) {

            this.socket = socket;
            this.server = server;
        }

        // thread automatically runs this method
        public void run() {

            try {

                // setup input stream from client
                input = new BufferedReader(
                    new InputStreamReader(
                        socket.getInputStream()
                    )
                );

                // Setup output stream to client
                output = new PrintWriter(
                    socket.getOutputStream(),
                    true
                );

                // Ask for username
                output.println("Enter your player name:");

                // Read username from client
                playerName = input.readLine();

                System.out.println(playerName + " joined.");

                // Tell everyone someone joined
                server.broadcast(
                    playerName + " joined the game.",
                    this
                );

                String message;

                // Continuously listen for messages
                while ((message = input.readLine()) != null) {

                    // Add username to message
                    String fullMessage =
                        playerName + ": " + message;

                    // Print to server console
                    System.out.println(fullMessage);

                    // Send message to all players
                    server.broadcast(fullMessage, this);
                }

            } catch (IOException e) {

                // If client crashes/disconnects
                System.out.println(
                    "Client error: " + e.getMessage()
                );

            } finally {

                // Remove disconnected client
                server.removeClient(this);

                try {

                    // Close socket connection
                    socket.close();

                } catch (IOException e) {

                    System.out.println(
                        "Could not close socket."
                    );
                }
            }
        }

        // Sends a message to this client
        public void sendMessage(String message) {

            output.println(message);
        }
    }
}
