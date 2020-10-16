package eecs2030.lab4;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import princeton.introcs.StdDrawComposite;

public class FireworkDisplay implements ActionListener {

	static {
		StdDrawComposite.setCanvasSize(20 * 40, 20 * 40);
		StdDrawComposite.setXscale(0.0, 200.0);
		StdDrawComposite.setYscale(0.0, 200.0);
		StdDrawComposite.clear(Color.BLACK);
	}

	private Timer timer;
	private Firework fw;
	private final double radius = 2.0;
	private static final double X_LEFT = 0.0;
	private static final double X_RIGHT = 20.0;

	public FireworkDisplay() {
		this.timer = new Timer(20, this);
		this.fw = makeFirework();
	}
	
	private static Firework makeFirework() {
		Firework f;
		
		double rand = Math.random(); 
		if (rand < 0.167) {
			f = Firework.ring(new Point2(100, 100), 40);
		}
		else if (rand < 0.33) {
			f = Firework.rings(new Point2(100, 100), 40);
		}
		else if (rand < 0.5) {
			f = Firework.fountain(500, new Point2(100, 10), 60);
		}
		else if (rand < 0.67) {
			f = Firework.pistil(new Point2(100, 100), 30);
		}
		else if (rand < 0.83) {
			f = Firework.pearl(new Point2(100, 10), 60);
		}
		else {
			f = Firework.pearls(10, new Point2(100, 10), 60);
		}
		return f;
	}

	private void drawFirework() {
		List<Particle> particles = this.fw.getParticles();
		for (Particle p : particles) {
			double x = p.getPosition().getX();
			double y = p.getPosition().getY();
			Color c = p.getColor();
			StdDrawComposite.setPenColor(c);
			StdDrawComposite.filledCircle(x, y, this.radius);
		}
	}

	public void run() {

		this.timer.start();
		while (true) {
			StdDrawComposite.show(0);
			StdDrawComposite.clear(Color.BLACK);
			this.drawFirework();
			
			StdDrawComposite.setPenColor(Color.WHITE);
			StdDrawComposite.textLeft(10, 190,
					String.format("number of fireworks created : %d", 
							Firework.getNumberOfFireworksCreated()));
			StdDrawComposite.textLeft(10, 180,
					String.format("number of particles created : %d", 
							Firework.getNumberOfParticlesCreated()));
			
			StdDrawComposite.show(0);
			if (StdDrawComposite.hasNextKeyTyped()) {
				char c = StdDrawComposite.nextKeyTyped();
				if (c == 'q') {
					this.timer.stop();
					break;
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!this.fw.isAlive()) {
			this.fw = makeFirework();
		}
		else {
			this.fw.update(0.05);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		FireworkDisplay test = new FireworkDisplay();
		test.run();
		StdDrawComposite.close();
	}

}
