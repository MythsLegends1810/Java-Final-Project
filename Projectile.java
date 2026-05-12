//Extension of Entity.java will contain the math and patterns for the attacks (example sin waves and stuff like that similar to Terrarias calamity fights
//Think like Empress of light attacks etc
import java.awt.*;

public class Projectile extends Entity {
	public double angle;
	public double speed;
	public double rotationSpeed; //This will be how much the bullets turn every frame

	public Projectile() {
		super(0, 0, 0, 0, false);
	}

	public void spawn(double x, double y, double angle, double speed, double rotationSpeed) {
		this.x = x;
		this.y = y;
		this.angle = angle;
		this.speed = speed;
		this.rotationSpeed = rotationSpeed; // This needs to be set to 1 or 2 for a curve but test others depedning on the difficulty
		this.active = true;
	}

	public void update() {
		if (!active) return;
		
		//change the angle slightly  every frame
		angle += rotationSpeed;
		
		//this will be the velocity based on the new angles
		double rad = Math.toRadians(angle);
		this.vx = Math.cos(rad) * speed;
		this.vy = Math.sin(rad) * speed;

		//Move
		this.x += this.vx;
		this.y += this.vy;

		//out of bounds check
		if (x < -50 || x > 850 || y < -50 || y > 650) {
			this.active = false
	}
}

	@Override
	public void draw(Graphics g) {
		if (!active) return;
		g.setColor(Color.RED);
		g.fillOval((int)x, (int)y, 10, 10);
	}
}

