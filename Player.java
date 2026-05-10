//This will be extension from Entity.java and will contain player methods like move() on keyboard inputs
import java.io.*;
import java.net.*;

public class Player extends Entity {
	public int hp;
	public int maxHp;
	public int sheild;
	public double movementSpeed;


	public Player(double x, double y, int hp, double speed) {
		super(x, y);
		this.hp = hp;
		this.maxHp = hp;
		this.shield = 0;
		this.movementSpeed = speed;
	}

	//dx and dy are the direction values (-1, 0, or 1) from keyboard input
	public void move(int dx, int dy) {
		this.vx = dx * movementSpeed;
		this.vy = dy * movementSpeed;

		// Update postion based on velocity
		this.x += vx;
		this.y += vy;
	}
	
	public void takeDamage(int amount) {
	if (shield > 0) {
		shield -= amount;
		if (shield < 0) {
			hp += shield; //This will make the damage from shield transfer to HP
			shield = 0;
		}
	} else {
		hp -= amount;
	}
	
	if (hp <= 0) {
		hp = 0;
		active = false; // "Player will "DIE"
		System.out.println("Player has died!"); //Temporary shows dead will change to close putty if possible in Java :)
		}
	}
	
	@Override
	public void draw() { // need the graphics stuff here I dont know what we use 
		
	}
}
