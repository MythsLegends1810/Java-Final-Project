//Extension of Entity.java will contain the math and patterns for the attacks (example sin waves and stuff like that similar to Terrarias calamity fights
//Think like Empress of light attacks etc
public class Projectile {
	public double x, y, vx, vy; //(x, y) position on the screen and (vx, vy) will be the velocity of the Projectile
	public boolean active; //This will be like the on/off switch for the GC

	public Projectile() { //This will be so when we spawn the bullets we set them to false
		this.active = false;
	}

	public void spawn(double x, double y, double vx, double vy) {
		x = x;
		y = y;
		vx = vx;
		vy = vy;
		active = true;
	}

	public void update() {
		if (!active) return;

		x += vx;
		y += vy;

		if (x < -50 || x > 850 || y < -50 || y > 650) { //This is the bounds check temporary values
			active = false;
		}
	}
}
