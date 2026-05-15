//we will hbave Players, Projectiles, and lootboxes in here since they will all use x,y positions and draw() for all of them.
import java.awt.*;

public class Entity { 
	public double x;
	public double y;
	public double vx;
	public double vy;
	public int width, height;
	public boolean active;

	public Entity(double new_x, double new_y, double new_x_vel, double new_y_vel, boolean new_isalive) { 
		this.x = new_x;
		this.y = new_y;
		this.vx = new_x_vel;
		this.vy = new_y_vel;
		this.width = width;
		this.height = height;
		this.active = new_isalive;
	}

	//This is the 'box' that we talked about in class 5/11/2026 for AABB
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, width, height);
	}
	
	public boolean collidesWith(Entity other) {
		//return this.getBounds().intersects(other.getBounds());
		return x < other.x + other.width && x + width > other.x && y < other.y + other.height && y + height > other.y;
	}

	public double getX() { 
		return this.x;
	}
	public double getY() { 
		return this.y;
	}
	public double getX_vel() { 
		return this.vx;
	} 
	public double getY_vel() { 
		return this.vy;
	}	
	public boolean getISalive() { 
		return this.active;
	}

	public boolean setAlive() { 
		this.active = true; //if its false then kill it
		return this.active;
	}
	public void draw(Graphics g) {  
		Graphics2D g2d = (Graphics2D) g;
	}
}


/*class Hitbox extends Enttity { 
	private double radius;
	
	public Hitbox(double new_radius) { 
		this.radius = new_radius;
	}
	
	public void keyPressed(KeyEvent e) { 

	]

}*/


