//This is where we will have the run() loop, update() for physics and paint() for drawing
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

//extends JPanel enables the class to be essentially like drawing onto a canvas
//implements KeyListener provides the ability to detect and respond to keyboard events

public class Game extends JPanel implements KeyListener, ActionListener {
	//using donavon's class
	Player player1 = new Player(300, 300, 0, 0); 
	
	private ChatScreen chatScreen = new ChatScreen();

	private BulletSpawner spawner = new BulletSpawner();
	
	private int spawnTimer = 0;

	public Game() {
		this.setFocusable(true);
		this.addKeyListener(this);
		Timer timer = new Timer(15, this);
		timer.start();
	}

	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (chatScreen.isOpen()) {
			chatScreen.drawChat(g, getWidth(), getHeight());
			return;
		}

		g.setColor(Color.BLUE);
		g.fillRect(player1.x, player1.y, 40, 40);
	}

	public void keyPressed(KeyEvent key) {

		//adam
		if (chatScreen.isOpen()) {
			chatScreen.handleKey(key, null);
			repaint();
			return;
		}

		if (key.getKeyChar() == '/') {
			chatScreen.open();
			repaint();
			return;
		}


		//logic for checking what key was pressed 
		if (key.getKeyCode() == KeyEvent.VK_W) player1.vel_y -= 5;
		if (key.getKeyCode() == KeyEvent.VK_S) player1.vel_y += 5;
		if (key.getKeyCode() == KeyEvent.VK_A) player1.vel_x -= 5;
		if (key.getKeyCode() == KeyEvent.VK_D) player1.vel_x += 5;
		repaint(); 
	}

	public void keyTyped(KeyEvent key) {}

	public void keyReleased(KeyEvent key) {
		if (key.getKeyCode() == KeyEvent.VK_D || key.getKeyCode() == KeyEvent.VK_A) player1.x_vel = 0;
		if (key.getKeyCode() == KeyEvent.VK_W || key.getKeyCode() == KeyEvent.VK_S) player1.y_vl = 0;
	}

	public void actionPerformed(ActionEvent key) {
		player1.x += player1.x_vel;     // Math for New Position = Old Position + Speed
		player1.y += player1.y_vel;
		repaint();

		//This is the code for the random spawn of bullets starts here:
		spawnTimer++;
		if (spawnTimer >= 60) {
			double chance = Math.random();
			
			if (chance < 0.3) {
				spawner.spawnVortex(400, 300, bullets); //Parameter 1 & 2 should be center cords
			} 
			else if (chance < 0.6) {
				spawner.spawnRing(player1.x, player1.y, bullets);
			}
			spawnTimer = 0;
		} //This is the end of the logic for bullets there will be more else if statements for other patterns
	}
}
