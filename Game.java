//This is where we will have the run() loop, update() for physics and paint() for drawing
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.RenderingHints;
import java.util.List;
import java.util.HashMap;

//extends JPanel enables the class to be essentially like drawing onto a canvas
//implements KeyListener provides the ability to detect and respond to keyboard events

public class Game extends JPanel implements KeyListener, ActionListener {
	//using donavon's class
	private Player player1 = new Player(300, 300, 0, 0); 
	
	private ChatScreen chatScreen = new ChatScreen();
	private GameClient client;

	private HashMap<String, double[]> otherPlayers = new HashMap<>();


	private BulletSpawns spawner = new BulletSpawns();
	private ArrayList<Projectile> bullets = new ArrayList<>();

	private int spawnTimer = 0;
	private int freezeTimer = 0;
	private boolean isTimeStopped = false;


	public Game(GameClient client) {
		this.client = client;
		this.client.setChatScreen(chatScreen);

		this.client.setGame(this);

		this.setPreferredSize(new Dimension(800, 600));
		this.setBackground(Color.GREEN);
		this.setFocusable(true);
		this.addKeyListener(this);

		this.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				requestFocusInWindow();
			}
		}
		);

		Timer timer = new Timer(15, this);
		timer.start();
	}

	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (chatScreen.isOpen()) {
			chatScreen.drawChat(g, getWidth(), getHeight());
			return;
		}

		// Turns on Anti-Aliasing
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		// Draws a dark background 
		g2d.setColor(Color.DARK_GRAY);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		// Draws the player
		g2d.setColor(Color.CYAN);
		g2d.fillRect((int)player1.x, (int)player1.y, 40, 40);

		// Draw all active bullets 
		for (Projectile p : bullets) {
			p.draw(g);
		}

		g2d.setColor(Color.ORANGE);

		for (String name : otherPlayers.keySet()) {
			double[] position = otherPlayers.get(name);

			int x = (int) position[0];
			int y = (int) position[1];

			g2d.fillRect(x, y, 40, 40);
			g2d.drawString(name, x, y - 5);
		}
	}

	public void keyPressed(KeyEvent key) {

		//adam
		if (chatScreen.isOpen()) {
			chatScreen.handleKey(key, client);
			repaint();
			return;
		}

		if (key.getKeyChar() == '/') {
			chatScreen.open();
			repaint();
			return;
		}

		//logic for checking what key was pressed 
		if (key.getKeyCode() == KeyEvent.VK_W) player1.vy -= 5;
		if (key.getKeyCode() == KeyEvent.VK_S) player1.vy += 5;
		if (key.getKeyCode() == KeyEvent.VK_A) player1.vx -= 5;
		if (key.getKeyCode() == KeyEvent.VK_D) player1.vx += 5;
		repaint(); 
	}

	public void keyTyped(KeyEvent key) {}

	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_D || key.getKeyCode() == KeyEvent.VK_A) player1.vx = 0;
		if (key.getKeyCode() == KeyEvent.VK_W || key.getKeyCode() == KeyEvent.VK_S) player1.vy = 0;
	}

	@Override public void actionPerformed(ActionEvent key) {
		//Freeze the time for bullets logic
		if (isTimeStopped) {
			freezeTimer--;
			if(freezeTimer <= 0) {
				isTimeStopped = false;
			}
		}


		player1.x += player1.vx;     // Math for New Position = Old Position + Speed (moving the player)
		player1.y += player1.vy;
			


		// Keep player on screen within the boundaries
		if (player1.x < 0) player1.x = 0;
		if (player1.x > 760) player1.x = 760;
		if (player1.y < 0) player1.y = 0;
		if (player1.y > 560) player1.y = 560;
		

			
		if (client != null) { // Make sure the client exists before trying to send position

		client.sendPosition(player1.x, player1.y); // Send this player's current position to the server
			}


		// Tell java to redraw the screen
		repaint();

		//This is the code for the random spawn of bullets starts here:
		spawnTimer++;
		if (spawnTimer >= 60) {
			double chance = Math.random();

			//This is the math for spawning the bullets near the player but not ontop of them
			double spawnX = player1.x + (Math.random() * 400 - 200);
			double spawnY = player1.y + (Math.random() * 400 - 200);
			
			//boundaries for the bullets to spawn on the players scree.
			spawnX = Math.max(50, Math.min(750, spawnX));
			spawnY = Math.max(50, Math.min(550, spawnY));

			if (chance < 0.3) {
				spawner.spawnVortex(spawnX, spawnY, bullets); //Parameter 1 & 2 should be center cords//TEMP COMMENTED 
			} 
			else if (chance < 0.6) {
				spawner.spawnRing(spawnX, spawnY, bullets); //TEMP COMMENTED
			}
			spawnTimer = 0;
		} //This is the end of the logic for bullets there will be more else if statements for other patterns
		
		//collision loop below:
	if(!isTimeStopped) {
		for (int i = bullets.size() - 1; i >= 0; i--) {
			Projectile p = bullets.get(i);
			p.update();

			//This is the player collision
			if (p.active && player1.active && p.collidesWith(player1)) {
				player1.takeDamage(10); // player takes 10 damage change depending on what we need to balance.
				p.active = false;
			}
			if (!p.active) {
				bullets.remove(i);
			}
		}
	}
		repaint();
	}

	public void FreezeBullets() {
		this.isTimeStopped = true;
		this.freezeTimer = 333;
	}



	public void updateOtherPlayer(String playerName, double x, double y) {
		otherPlayers.put(playerName, new double[] {x, y});
		repaint();
	}
}
