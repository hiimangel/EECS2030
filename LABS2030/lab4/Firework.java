package eecs2030.lab4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Firework {

	/**
	 * The random number generator used by the class.
	 */
	private static Random rng = new Random();

	/**
	 * The number of fireworks created.
	 */
	private static int numberOfFireworksCreated = 0;

	/**
	 * The number of particles created.
	 */
	private static int numberOfParticlesCreated = 0;

	/**
	 * The particles for this firework.
	 */
	private List<Particle> particles;

	/**
	 * Initializes this firework to have zero particles.
	 */
	
	/* ========Turkish keywords are as follows==========
	 * 
	 * 			hiz = velocity/speed
	 * 			gercek = real
	 * 			rastgele = random
	 * 			pozisyon = position
	 * 			baslangic = starting position
	 * 			parcacik = particle
	 * */
	
	
	private Firework() {
		
		particles = new ArrayList<Particle>();
		Firework.numberOfFireworksCreated++;
	
		// create an empty particle list (use ArrayList)
		// track number of instances of fireworks using one of the class fields above
	}

	/**
	 * Returns a yellow firework made up of a single particle that travels
	 * straight up.
	 * 
	 * @param position
	 *            the starting position of the firework
	 * @param speed
	 *            the starting speed of the firework
	 * @return a yellow firework made up of a single particle that travels
	 *         straight up
	 */
	public static Firework pearl(Point2 position, double speed) {
		
		Firework f = new Firework();
		
		Vector2 vel = new Vector2(0.0, 1.0);
		vel.multiply(speed);
		
		Particle p = new Particle(position, vel, 5, Color.YELLOW);
		
		f.particles.add(p);
		
		Firework.numberOfParticlesCreated += f.particles.size();
		
		return f;
	}

	/**
	 * Returns a yellow firework made up of n particles that travel straight up.
	 * The particles are randomly spread out around the given position and
	 * travel with a speed that is randomly distributed around the given speed.
	 * See the Lab 4 document for details.
	 * 
	 * @param n
	 *            the number of particles in the firework
	 * @param position
	 *            the average starting position of the particles
	 * @param speed
	 *            the average starting speed of the particles
	 * @return a yellow firework made up of n particles that travel straight up
	 */
	public static Firework pearls(int n, Point2 position, double speed) {
		
		Firework fisek = new Firework();
		
		double sigma = speed * 0.05;
		for (int i = 0; i < n; i++) {
			
			//Particle(Point2 position, eecs2030.lab4.Vector2 velocity, double lifeTime, Color color)
			// have to create a new vector everytime, because static version doesnt exist in the class given.
			
			Vector2 hiz = new Vector2(0.0, 1.0);
			
			//creating random positions for x
			double rastgele_x = Firework.rng.nextDouble() - 0.5;
			double rastgele_pozisyon_x = position.getX() + rastgele_x * 5;
			
			//creating random positions for y
			double rastgele_y = Firework.rng.nextDouble() - 0.5;
			double rastgele_pozisyon_y = position.getY() + rastgele_y * 15;
			
			//random speed for the particles
			
			double rastgele_hiz_carpici = Firework.rng.nextGaussian();
			double gercek_hiz = rastgele_hiz_carpici * sigma + speed;
			hiz.multiply(gercek_hiz);
			
			
			Point2 baslangic = new Point2(rastgele_pozisyon_x, rastgele_pozisyon_y);
			
			Particle parcacik = new Particle(baslangic, hiz, 5, Color.YELLOW); 
			
			fisek.particles.add(parcacik);
			
		}
		
		Firework.numberOfParticlesCreated += fisek.particles.size();
		return fisek;
			
	}
		
	/**
	 * Returns a blue firework made up of 36 particles moving outward in a
	 * circle centered on the given position. The particles are arranged evenly
	 * on the perimeter of a circle (i.e., every 10 degrees).
	 * 
	 * @param position
	 *            the starting position of each particle in the firework
	 * @param speed
	 *            the speed of each particle
	 * @return a blue firework made up of 36 particles moving outward in a
	 *         circle centered on the given position
	 */
	public static Firework ring(Point2 position, double speed) {
		
		Firework f = new Firework();
		
		for (int i = 0; i < 360; i += 10) {
			Vector2 vel = Vector2.dirVector(i);
			
			double s = Firework.rng.nextGaussian() * (0.03 * speed) + speed;
			vel.multiply(s);
			
			Particle p = new Particle(position, vel, 3, Color.BLUE);
			f.particles.add(p);
		}
		
		Firework.numberOfParticlesCreated += f.particles.size();
		return f;
	}

	/**
	 * Returns a firework made up of a pair of rings. The outer blue ring and
	 * the inner red ring are both made up of 36 particles moving outward in a
	 * circle centered on the given position. The inner red ring has particles
	 * that move at half of the speed of the particles in the blue ring. The
	 * particles are arranged evenly on the perimeter of a circle (i.e., every
	 * 10 degrees).
	 * 
	 * @param position
	 *            the starting position of each particle in the firework
	 * @param speed
	 *            the speed of each particle in the blue ring
	 * @return a firework made up of a pair rings as described above
	 */
	public static Firework rings(Point2 position, double speed) {
		
		// ==blue is outer and the given speed is for blue== important
		
		Firework fisek = new Firework();
		double sigma = speed * 0.03;
		
		for (int i = 0; i < 360; i += 10) {
			
			Vector2 yon_hiz = Vector2.dirVector(i); //direction vectors for each angle;
			double rastgele_hiz_carpici = Firework.rng.nextGaussian() * sigma;
			
			// things to do for blue ring
			Vector2 gercek_hiz_blue = yon_hiz.multiply(rastgele_hiz_carpici + speed);
			Particle blue = new Particle(position, gercek_hiz_blue, 3, Color.BLUE);
			fisek.particles.add(blue);
			
			//things to do for red one
			//have to create another direction vector due to inconvinence of the lack of
			//a clone or a returned vector instead of manipulated vector due to multiply method of Vector2-
			Vector2 yon_hiz_red = Vector2.dirVector(i);
			Vector2 gercek_hiz_red = yon_hiz_red.multiply(rastgele_hiz_carpici + speed);
			gercek_hiz_red = gercek_hiz_red.multiply(0.5);
			Particle red = new Particle(position, gercek_hiz_red, 2, Color.RED);
			fisek.particles.add(red);
			
		}
		
		Firework.numberOfParticlesCreated += fisek.particles.size();
		return fisek;
		
		
		
	}

	/**
	 * Creates a firework made up of n magenta particles that travel primarily
	 * upwards. The particles travel in a direction that is randomly distributed
	 * around the vertical direction with a speed that is randomly distributed
	 * around the given speed.
	 * 
	 * @param n
	 *            the number of particles in the fountain
	 * @param position
	 *            the starting position of the particles
	 * @param speed
	 *            the starting average speed of the particles
	 * @return a firework made up of n magenta particles that travel primarily
	 *         upwards
	 */
	public static Firework fountain(int n, Point2 position, double speed) {

		//Particle(Point2 position, eecs2030.lab4.Vector2 velocity, double lifeTime, Color color)
		Firework fisek = new Firework();
		double sigma1 = 2.0;
		double sigma2 = 0.05 * speed;
		for (int i = 0; i < n; i++) {
			
			//angle calculations
			double rastgele_aci = Firework.rng.nextGaussian() * sigma1 + 90;
			
			//velocity calculations
			double gercek_hiz_carpici = Firework.rng.nextGaussian() * sigma2 + speed;
			
			//make the direction vector first and then multiply by the random speed based on normal distrubtion
			Vector2 vektor_hiz = Vector2.dirVector(rastgele_aci);
			vektor_hiz.multiply(gercek_hiz_carpici);
			
			Particle parcacik = new Particle(position, vektor_hiz, 10, Color.MAGENTA);
			fisek.particles.add(parcacik);
		}
		Firework.numberOfParticlesCreated += fisek.particles.size();
		return fisek;
	}

	/**
	 * Creates a firework that forms the pistil pattern;
	 * see the Lab 4 document for details.
	 * 
	 * @param position
	 *            the starting position of the particles
	 * @param speed
	 *            the starting average speed of the particles
	 * @return a firework forming the pistil pattern
	 */
	public static Firework pistil(Point2 position, double speed) {

		Firework fisek = new Firework();
		double sigma = speed * 0.03; //sigma initilazed
		for (int i = 0; i < 360; i += 10) {
			
			Vector2 yon_hiz = Vector2.dirVector(i); //direction vectors for each angle;
			double rastgele_hiz_carpici = Firework.rng.nextGaussian() * sigma;
			
			Vector2 gercek_hiz_blue = yon_hiz.multiply(rastgele_hiz_carpici + speed); //the actual speed of blue in vector2
			Particle blue = new Particle(position, gercek_hiz_blue, 3, Color.BLUE);
			fisek.particles.add(blue); //individually add blue
			
		}
		double max_speed = 0.3 * speed; //maximum speed is 30 percent of given speed
		for (int j = 0; j < 500; j++) {
			
			// 0 - 360 random using uniform dist
			double random_aci = Firework.rng.nextDouble() * 360.0;
			Vector2 vektor = Vector2.dirVector(random_aci);
			vektor.multiply(Firework.rng.nextDouble() * max_speed);
			Particle parcacik = new Particle(position, vektor, 2, Color.GREEN);
			fisek.particles.add(parcacik);
			
		}
		
		Firework.numberOfParticlesCreated += fisek.particles.size();
		return fisek;
		
	}

	/**
	 * Returns the number of fireworks created.
	 * 
	 * @return the number of fireworks created
	 */
	public static int getNumberOfFireworksCreated() {

		return Firework.numberOfFireworksCreated;
		
	}

	/**
	 * Returns the number of particles created.
	 * 
	 * @return the number of particles created
	 */
	public static int getNumberOfParticlesCreated() {

		return Firework.numberOfParticlesCreated;
		
	}

	/**
	 * Updates all of the firework particles. Causes every particle in the
	 * firework to move.
	 * 
	 * @param dt
	 *            the amount of time over which to move the firework particles
	 */
	public void update(double dt) {
		
		//Particle(Point2 position, eecs2030.lab4.Vector2 velocity, double lifeTime, Color color)
		for (Particle particle : this.particles) {
			particle.move(dt);
		}
		
	}

	/**
	 * Returns true if at least one particle in the firework is alive, and false
	 * otherwise.
	 * 
	 * @return true if at least one particle in the firework is alive, and false
	 *         otherwise
	 */
	public boolean isAlive() {
	
		for(Particle particle : this.particles) {
			
			if (particle.isAlive()){
				return true;
			}	
		}
			return false;
	}

	/**
	 * Returns the number of particles in this firework.
	 * 
	 * @return the number of particles in this firework
	 */
	public int getNumberOfParticles() {
		
		return this.particles.size();
		
	}

	/**
	 * Returns a list of particles making up this firework. Modifying the list
	 * or the particles in the list will modify the firework.
	 * 
	 * @return a list of particles making up this firework
	 */
	public List<Particle> getParticles() {
		
		List<Particle> partikler = this.particles;
		// use aggregation (i.e. an alias to particle list) when implementing this method
		return partikler;
		
	}
}
