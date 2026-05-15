//This is where the logic for the Chat Screen will be, hence the name ChatScreen

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChatScreen {

		private boolean open; // true = chat scren open, false = closed
		
		private String currentMessage; // what the player is currently typing

		private ArrayList<String> messages; // list of messages already sent/received

		public ChatScreen() {
			open = false;
			currentMessage = "";
			messages = new ArrayList<>();
		}


		public void open() {
			open = true;
			currentMessage = "";
		}

		public void close() {
			open = false;
			currentMessage = "";
		
		}

		public boolean isOpen() {
			return open;	
		}

		public void addMessage(String message) {
			messages.add(message); //I know this is confusing, but it basically just takes in message as a parameter, and then adds it to the ArrayList
		}
	
		public void handleKey(KeyEvent keyPressed, GameClient client) {
			int keyCode = keyPressed.getKeyCode();
			char keyChar = keyPressed.getKeyChar();
		
		
		//this is so if ESC is pressed, it closes chat
		if (keyCode == KeyEvent.VK_ESCAPE) {
			close();
			return;
		}

		//enter will send the message 
		if (keyCode == KeyEvent.VK_ENTER) {
			
			//.trim() : Used to Remove Leading and trailing whitespace forma  string
			//.equals() : Checks if two objects are logically equal based on their values or content, EX:  two differens tring objects that both contain "HELLO"
			//not the same as ==, == checks if 2 vairables point to exact same memory address
			if (!currentMessage.trim().equals("")) {
				if(client != null) { //js to make sure server only sends if client exists	
					client.sendMessage(currentMessage);
					}
				addMessage("Me: " + currentMessage);
				currentMessage = "";
			}
			return;
		}

		//this is for backspace
		if (keyCode == KeyEvent.VK_BACK_SPACE) {
			if (currentMessage.length() > 0) {
				currentMessage = currentMessage.substring(0, currentMessage.length() - 1); //removes 1 character freom currentMessage
			}
			return;
		}

		//now this is for normal typing 

		else {
			if (!Character.isISOControl(keyChar) && keyChar != KeyEvent.CHAR_UNDEFINED)
			currentMessage += keyChar;
		}

}

	public void drawChat(Graphics g, int width, int height) {
		
		//fills screen with dark gray color 
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, width, height); //fillRect is a method used ti draw a solid filled in rectangle on a graphical component, via wikapedia
		
		g.setColor(Color.WHITE);
		
		g.setFont(new Font("Serif", Font.BOLD, 40)); //Make Title chat BIGGGFTGGER

		//u can remove this later but this measures how wide the word chat is
		FontMetrics fm = g.getFontMetrics();

		// Center the "CHAT" title in the middle
		int titleWidth = fm.stringWidth("CHAT");
		int titleX = (width - titleWidth) / 2;

		g.drawString("CHAT", titleX, 50);

		g.setFont(new Font("Arial", Font.PLAIN, 14));

		//g.drawString("CHAT", 20, 30); // CHAT title at the top (I tHink)
		
		int y = 90;

		for (int i = 0; i < messages.size(); i++) { //loops thru every message in ArrayList
		
			g.drawString(messages.get(i), 20, y);

			//ok now we have to move down the y for every message so theyd ont overlap
			y += 20;
		}

		g.drawString("> " + currentMessage, 20, height - 20); //draw the text the player is currently typing near the bottom basically I hope


	}

}


