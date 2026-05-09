//This will be extension from Entity.java and will contain player methods like move() on keyboard inputs
import java.io.*;
import java.net.*;

public class Player {
	public int x, y;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void move() {
		x += 5;
	}
}
