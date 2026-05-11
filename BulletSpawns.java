import java.util.ArrayList;

public class BulletSpawner {
	private double vortexRotation = 0;

	public void spawnVortex(double x, double y, ArrayList<Projectile> bullets) {
		vortexRotation += 15.0;
		for (int i = 0; i < 4; i++) {
			double angle = vortexRotation + (i * 90);
			Projectile p = new Projectile();
			P.spawn(x, y, 3.0, 0.5);
			bullets.add(p);
		}
	}

	public void spawnRing(double x, double y, ArrayList<Projectile> bullets) {
		for (int angle = 0; angle < 360; angle += 30) {
			Projectile p = new Projectile();
			p.spawn(x, y, angle, 4.0, 0);
			bullets.add(p);
		}
	}
}
