//This is where we will have the run() loop, update() for physics and paint() for drawing
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

//extends JPanel enables the class to be essentially like drawing onto a canvas
//implements KeyListener provides the ability to detect and respond to keyboard events

public class Game extends JPanel implements KeyListener {
	//using donavon's class
	Player player1 = new Player(300, 300, 0, 0); 

	public Game() {
		this.setFocusable(true);
		this.addKeyListener(this);
	}

	@Override public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLUE);
		g.fillRect(p1.x, p1.y, 40, 40);
	}

	public void keyPressed(keyEvent key) {
		//logic for checking what key was pressed 
		if (key.getKeyCode() == KeyEvent.VK_W) player1.y -= 10;
		if (key.getKeyCode() == KeyEvent.VK_S) player1.y += 10;
		if (key.getKeyCode() == KeyEvent.VK_A) player1.x -= 10;
		if (key.getKeyCode() == KeyEvent.VK_D) player1.x += 10;
		repaint(); 
	}

	public void keyTyped(keyEvent key) {}
	public void keyReleased(keyEvent key) {}
}
