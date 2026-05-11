//This will be extension from Entity.java and will contain player methods like move() on keyboard inputs
import java.io.*;
import java.net.*;
import java.awt.*;

public class Player extends Entity {
	public int hp;
	public int maxHp;
	public int shield;
	public double movementSpeed;


	public Player(double x, double y, int hp, double speed) {
		super(x, y, 0, 0, true);
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
	public void draw(Graphics g) { // need the graphics stuff here I dont know what we use 
		Graphics g2d = (Graphics2D) g;		
	}
}

class Assassin extends Player { //Faster walkspeed but lower hp
	public Assassin(double x_pos, double y_pos) { //Position here cause idk if we are going to have a fixed spawn point, can be removed later; just take out x_pos and y_pos  
		super(x_pos, y_pos, 70, 12.6);
			/*this.hp = 70;
			this.movementSpeed = 12.6;
			this.maxHp = 70;*/
	}
	@Override
	public void draw(Graphics g) { 
		Graphics2D g2d = (Graphics2D) g;
		//Imma try to use an actual jpg here this gunna take some time

	}
}

class Tank extends Player { 
	public Tank(double x_pos, double y_pos) { //Same logic for assasin public class TM_FILENAME_BAS {
		super(x_pos, y_pos, 200, 5.0);
		  /*this.hp = 200;
			this.maxHP = 200;
			this.movementSpeed = 5.0*/;
	}
	@Override
	public void draw(Graphics g) { 
		Graphics g2d = (Graphics2D) g; //Need to work on this later, if all fails just draw a square prob
	}
}

class Gojo extends Player { 
	public Gojo(double x_pos, double y_pos) { 
		super(x_pos, y_pos, 120, 10.0);
			this.shield = 10000;
		/*	this.hp = 120;
			this.maxHP = 120;
			this.movementSpeed = 10.0;*/
		}
		@Override
		public void takeDamage(int amount) { 
			if (amount < 100) 
				System.out.println("Nah I'd Win");
			super.takeDamage(amount);
		}

}




