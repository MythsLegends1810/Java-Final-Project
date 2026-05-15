// Will have the logic for our gacha system just in case
import java.util.*;
import java.io.*;
import java.net.*;
import java.awt.*;


class Gacha extends Entity { //Parent/Super class
	protected int spawn_rate;
	protected String Buff_name;
	protected String Rarity;
	protected Image sprite = Toolkit.getDefaultToolkit().getImage("Lootbox.png");

	public Gacha(int spawn_rate, String Buff_name, String Rarity) { //Java default constructor
		super(0, 0, 10, 10, false);
		this.spawn_rate = spawn_rate;
		this.Buff_name = Buff_name;
		this.Rarity = Rarity;
	}
	public int getSpawn_rate() { 
		return this.spawn_rate;
	}
	public String getBuff_name() { 
		return this.Buff_name;
	}
	public String getRarity() {             //Getters and setters to calm mind. tho they vary per subclass 
		return this.Rarity;
	}
	public void printBuffs() {                    //Pritns out the buff the user acquires when they pick it up
		System.out.println("You have recieved: " + Buff_name);
	}		
	public void transfer(Player buff) { //Java giving me a migraine I pray this works 
		//logic lol
	}
	public void position() { 
		if (x < -50 || x > 850 || y < -50 || y > 650) { //bounds
			this.active = false;
		}
	}
	public void draw(Graphics g, int x, int y) { 
		if (!active) return;

		g.setColor(Color.YELLOW);
		g.fillRect((int)this.x, (int)this.y, 35, 35);	

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(sprite, (int)x, (int)y, 35, 35, null);  
	}
}

class Shield extends Gacha {  //First power up is shield
	private int dmgReduction; //This is unqiue to the class

	public Shield(int reduction) {  		
		super(100, "Shield", "Common"); //Thinking of having Shield subtract damage from the multiplier of the particles that do dmg  	
		this.dmgReduction = reduction;	
		this.spawn_rate = (int) Math.ceil(Math.random() * 1.0); //Rand() in Java apparently
	}
	public int getDmgReduction() { 
		return this.dmgReduction;
	}
	@Override //Override structure in Java
	public void printBuffs() { 
		System.out.println("You have recieved: " + Buff_name);
	}
	@Override 
	public void transfer(Player buff) { 
		int shield = 69;
		buff.ShieldBuff(shield);	
	}
}

class Heal extends Gacha { //Health Regen Powerup follows logic from above
	private int hpRegen;
	public Heal(int Regen) {
		super(70, "Heal", "Rare");
		this.hpRegen = Regen;
		this.spawn_rate = (int) Math.ceil(Math.random() * 0.7);
	}	
	public int getHpRegen() { 
		return this.hpRegen;
	}
	@Override 
	public void printBuffs() { 
		System.out.println("You have recieved: " + Buff_name);
	}
	@Override 
	public void transfer(Player buff) { 
		int heals = 42;
		buff.HealthBuff(heals);
	}
}

class Speed extends Gacha { //Speed Boost powerup follows same logic, all three inherit from the super class
	private int speedBoost;
	public Speed(int boost) {
		super(70, "Speed Boost", "Rare"); 
		this.speedBoost = boost;
		this.spawn_rate = (int) Math.ceil(Math.random() * 0.7); 	
	}
	public int getSpeedBoost() { 
		return this.speedBoost;
	}
	@Override
	public void printBuffs() { 
		System.out.println("You have recievied" + Buff_name);
	} 
	public void transfer(Player buff) { 
		double speed = 3.0;
		buff.SpeedBuff(speed);
	}
}

/*class Jackpot extends Gacha { //Freeze stops all the particles but the player can move: Im thinking we can do multithreading to run teh player and particles separately:? 
	private int Glorious;
	public Jackpot(int Godlike) {
		super(20, "Time Stop", "Legendary"); 
		this.Glorious = Godlike;
		this.spawn_rate = (int) Math.ceil(Math.random() * 0.3); 	
	}
	public int getFreeze() { 
		return this.Glorious;
	}
	@Override
	public void printBuffs() { 
		System.out.println("You have recievied" + Buff_name);
	}
}*/

class Overflow extends Gacha { 
	private int word;
	public Overflow() { 
		super(30, "Have fun :)", "common");
		this.word = 100;
		this.spawn_rate = (int) Math.ceil(Math.random() * 0.7);
	}
	public int getWord() { 
		return word;
	}
	@Override 
	public void printBuffs() { 
		for (int i = 0; i < word; i++) { 
			System.out.println("Mwahaha, don't die now. " + Buff_name);
		}
	}
}

class Eepy extends Gacha { 
	public Eepy() {
		super(50, "Night night", "Rare");
		this.spawn_rate = (int) Math.ceil(Math.random() * 0.5);
	}
	@Override
	public void printBuffs() { 
	try { 
		System.out.println("Siphons of symphony-");
		Thread.sleep(3000);
		System.out.println("haunt your dreams");
	}
	catch (InterruptedException e) { 
		Thread.currentThread().interrupt();
		System.out.println("Dang that wasn't supposed to happen");
		}
	}
}



