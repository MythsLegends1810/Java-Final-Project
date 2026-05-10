// Will have the logic for our gacha system just in case

class Gacha { //Parent/Super class
	protected int spawn_rate;
	protected String Buff_name;
	protected String Rarity;

	public Gacha(int spawn_rate, String Buff_name, String Rarity) { //Java default constructor
		this.spawn_rate = spawn_rate;
		this.Buff_name = Buff_name;
		this.Rarity = Rarity;

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
	}
}

public class Shield extends Gacha {  //First power up is shield
	private int dmgReduction; //This is unqiue to the class

	public Shield(int reduction) {  		
		super(100, "Shield", "Common"); //Thinking of having Shield subtract damage from the multiplier of the particles that do dmg  	
		this.dmgReduction = reduction;	
		this.spawn_rate = (int) Math.ceil(Math.random() * 100); //Rand() in Java apparently
	}
	public int getDmgReduction() { 
		return this.dmgReduction;
	}
	@override //Override structure in Java
	public void printBuffs() { 
		System.out.println("You have recieved: " + Buff_name);
	}
}

public class Heal extends Gacha { //Health Regen Powerup follows logic from above
	private int hpRegen;
	public Heal(int Regen) {
		super(70, "Heal", "Rare");
		this.hpRegen = Regen;
		this.spawn_rate = (int) Math.ceil(Math.random() * 70);
	}	
	public int getHpRegen() { 
		return this.hpRegen;
	}
	@override 
	public void printBuffs() { 
		System.out.println("You have recieved: " + Buff_name);
	}
}

public class Speed extends Gacha { //Speed Boost powerup follows same logic, all three inherit from the super class
	private int speedBoost;
	public Speed(int boost) {
		super (70, "Speed Boost", "Rare"); 
		this.speedBoost = boost;
		this.spawn_rate = (int) Math.ceil(Math.random() * 70); 	
	}
	public int getSpeedBoost() { 
		return this.speedBoost;
	}
	@override
	public void printBuffs() { 
		System.out.println("You have recievied" + Buff_name);
	}
}

public class TimeStop extends Gacha { //Freeze stops all the particles but the player can move: Im thinking we can do multithreading to run teh player and particles separately:? 
	private int Freeze;
	public Speed(int stop) {
		super (20, "Time Stop", "Legendary"); 
		this.Freeze = stop;
		this.spawn_rate = (int) Math.ceil(Math.random() * 70); 	
	}
	public int getFreeze() { 
		return this.Freeze;
	}
	@override
	public void printBuffs() { 
		System.out.println("You have recievied" + Buff_name);
	}
}

 

