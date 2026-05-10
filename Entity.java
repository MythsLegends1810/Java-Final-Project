//we will hbave Players, Projectiles, and lootboxes in here since they will all use x,y positions and draw() for all of them.
public class Entity { 
	public double x;
	public double y;
	public double x_vel;
	public double y_vel;
	public boolean isalive;

	public Entity(double new_x, double new_y, double new_x_vel, double new_y_vel, boolean new_isalive) { 
		this.x = new_x;
		this.y = new_y;
		this.x_vel = new_x_vel;
		this.y_vel = new_y_vel;
		this.isalive = new_isalive;
	}

	public double getX() { 
		return this.x;
	}
	public double getY() { 
		return this.y;
	}
	public double getX_vel() { 
		return this.x_vel;
	} 
	public double getY_vel() { 
		return this.y_vel;
	}	
	public boolean getISalive() { 
		return this.isalive;
	}

	public boolean setAlive() { 
		this.isalive = true; //if its false then kill it
		return this.isalive;
	}
}


