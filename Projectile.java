//Extension of Entity.java will contain the math and patterns for the attacks (example sin waves and stuff like that similar to Terrarias calamity fights
//Think like Empress of light attacks etc
public class Projectile extends Entity {
	public double angle;
	public double speed;
	public double rotationSpeed; //This will be how much the bullets turn every frame

	public void spawn(double x, double y, double angle, duble speed, double rotationSpeed) {
		this.x = x;
		this.y = y;
		this.agnle = angle;
		this.speed = speed;
		this.rotationSpeed = rotationSpeed; // This needs to be set to 1 or 2 for a curve but test others depedning on the difficulty
		this.//add the entity alive variable here.
	}

	public void update() {
		if (!//add the entity alive variable here) return;
		
		//change the angle slightly  every frame
		angle += rotationSpeed;
		
		//this will be the velocity based on the new angles
		double rad = //need to add the math for radians here
		vx = 
		vy =

		//Move
		x += vx;
		y += vy;

		//out of bounds check
		if (x < -50 || x > 850 || y < -50 || y > 650) {
			//entity alive variable here = false
	}
}
