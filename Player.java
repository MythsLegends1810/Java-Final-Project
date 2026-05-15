//This will be extension from Entity.java and will contain player methods like move() on keyboard inputs
import java.time.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;

public class Player extends Entity {
	public int hp;
	public int maxHp;
	public int shield;
	public double movementSpeed;
	public boolean Hakari = false;


	public Player(double x, double y, int hp, double speed) {
		super(x, y, 0, 0, true);
		this.hp = hp;
		this.maxHp = hp;
		this.shield = 0;
		this.movementSpeed = speed;

		this.width = 40;
		this.height = 40;
	}

	//dx and dy are the direction values (-1, 0, or 1) from keyboard input
	public void move(int dx, int dy) {
		this.vx = dx * movementSpeed;
		this.vy = dy * movementSpeed;

		// Update postion based on velocity
		this.x += vx;
		this.y += vy;
	}

	public void ShieldBuff(int armor) { 	
		this.shield += armor;
	}

	public void HealthBuff(int heals) { 
		this.hp += heals;
		if (this.hp > this.maxHp && !this.Hakari) { 
			this.hp = maxHp;
		}
	}

	public void SpeedBuff(double boost) { 
		this.movementSpeed += boost;
	}

	public void JackpotBuff() { //Find a time class to work with. rn is eepy time 
		this.Hakari = true;
		System.out.println("Guess you lucked out. For now...");
		//logic to kill the bullets here
		

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
		//Graphics g2d = (Graphics2D) g;		
		//Drawing the health bar background (red - missing health)
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y - 15, width, 5);

		// Drawing the current health (green)
		g.setColor(Color.GREEN);
		//calculate width of the green bar based on health percentage
		int healthWidth = (int) ((double) hp / maxHp * width);
		g.fillRect((int)x, (int)y - 15, healthWidth, 5);

		// Draw shield Bar if they have on (Blue)
		if (shield > 0) {
			g.setColor(Color.CYAN);
			//makes shield bar slightly thinner right below/above
			int shieldWidth = (int) (Math.min(1.0, (double) shield / maxHp) * width);
			g.fillRect((int)x, (int)y - 20, shieldWidth, 3);
		}

		//Drawing a thin black border around the bars so they pop
		g.setColor(Color.BLACK);
		g.drawRect((int)x, (int)y - 15, width, 6);
	}
}

class Assassin extends Player { //Faster walkspeed but lower hp
	private Image sprite; //stores the image here
	public Assassin(double x_pos, double y_pos) { //Position here cause idk if we are going to have a fixed spawn point, can be removed later; just take out x_pos and y_pos  
		super(x_pos, y_pos, 70, 12.6);
		/*this.hp = 70;
		  this.movementSpeed = 12.6;
		  this.maxHp = 70;*/
		// it then loads the image ONCE when the player is created
		this.sprite = Toolkit.getDefaultToolkit().getImage("koro_sensei.png");
	}
	@Override
	public void draw(Graphics g) { 
		super.draw(g);
		Graphics2D g2d = (Graphics2D) g;
		//commenting out these below because this would interfere with the draw method. Also, the scale function would cause everything drawn after the player
		//to be caled down like 90% and shift to top-left
		//Image img1 = Toolkit.getDefaultToolkit().getImage("koro_sensei.png");	//Imma try to use an actual jpg here this gunna take some time
		//g2d.scale(0.9,0.9);
		g2d.drawImage(sprite, (int)x, (int)y, 35, 35, null);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}
}

class Tank extends Player { 
	private Image sprite1;
	public Tank(double x_pos, double y_pos) { //Same logic for assasin public class TM_FILENAME_BAS {
		super(x_pos, y_pos, 200, 5.0);
		this.shield = 100;
		this.maxHp = 200;
		/*this.hp = 200;
		  this.maxHP = 200;
		  this.movementSpeed = 5.0*/;
		this.sprite1 = Toolkit.getDefaultToolkit().getImage("Escanor.png");
	}
	@Override
	public void draw(Graphics g) { 
		super.draw(g);
		Graphics2D g2d = (Graphics2D) g; //Need to work on this later, if all fails just draw a square prob
		//Image img2 = Toolkit.getDefaultToolkit().getImage("Escanor.png");
		//g2d.scale(0.9,0.9);
		g2d.drawImage(sprite1, (int)x, (int)y, 35, 35, null);
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//Same thing needs an image to be applied
	}
}

	class Gojo extends Player { 
		private Image sprite2;
		public Gojo(double x_pos, double y_pos) { 
			super(x_pos, y_pos, 120, 10.0);
			this.shield = 10000;
			this.maxHp = 120;
			/*	this.hp = 120;
				this.maxHP = 120;
				this.movementSpeed = 10.0;*/
			this.sprite2 = Toolkit.getDefaultToolkit().getImage("Satoru.png");
		}
		@Override
		public void takeDamage(int amount) { 
			if (amount < 100) { 
				System.out.println("Nah I'd Win");
				return; //Exit out to remove dmg
			}
			super.takeDamage(amount);	
		}
		@Override 
		public void draw(Graphics g) { 
			super.draw(g);
			Graphics2D g2d = (Graphics2D) g;
			//Image img3 = Toolkit.getDefaultToolkit().getImage("Satoru.png");
			//g2d.scale(0.9,0.9);
			g2d.drawImage(sprite2, (int)x, (int)y, 35, 35, null);
	    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//Needs the image and the logc to print it to the screen
		}

	}

	class Gambler extends Player { 
		private Image sprite3;
		public Gambler(double x_pos, double y_pos) { 
			super(x_pos, y_pos, 120, 10.0);
			this.shield = 30;
			this.sprite3 = Toolkit.getDefaultToolkit().getImage("Shigeru.png");
		}
		@Override 
		public void takeDamage(int amount) { 
			if (Math.random() < 0.3) { 
				System.out.println("The essence of gambling is a meaningless death");
				return; //Ensures the damage is not applied
			} 
		super.takeDamage(amount);	
		}
		@Override 
		public void draw(Graphics g) { 
			super.draw(g);
			Graphics2D g2d = (Graphics2D) g;
			//Image img4 = Toolkit.getDefaultToolkit().getImage("Shigeru.png");
			//g2d.scale(0.9,0.9);
            g2d.drawImage(sprite3, (int)x, (int)y, 35, 35, null);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			//Needs the image and the logc to print it to the screen
		}
	}
