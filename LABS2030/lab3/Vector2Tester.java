package eecs2030.lab3;

import static org.junit.Assert.*;

import org.junit.Test;



import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Vector2Tester {

	private double rand() {
		double r = ((int) (2.0 * (Math.random() - 0.5) * 1000)) / 1000.0;
		if (r == 0.0) {
			r = 0.001;
		}
		return r;
	}

	private void testX(Vector2 v, double x, String error) {
		String s = String.format("%s\nthe x-coordinate was not %s\n", error, x);
		
		assertTrue(s, v.getX() == x);
	}

	private void testY(Vector2 v, double y, String error) {
		String s = String.format("%s\nthe y-coordinate was not %s\n", error, y);
		assertTrue(s, v.getY() == y);
	}

	@Test
	public void test01_defaultCtor() {
		Vector2 v = new Vector2();
		testX(v, 0.0, "new Vector2() failed");
		testY(v, 0.0, "new Vector2() failed");
	}

	@Test
	public void test02_ctor() {
		// try a few random coordinate values
		for (int i = 0; i < 10; i++) {
			double x = this.rand();
			double y = this.rand();
			Vector2 v = new Vector2(x, y);
			testX(v, x, String.format("new Vector2(%s, %s) failed", x, y));
			testY(v, y, String.format("new Vector2(%s, %s) failed", x, y));
		}
	}

	@Test
	public void test03_copyCtor() {
		// try a few random coordinate values
		for (int i = 0; i < 10; i++) {
			double x = this.rand();
			double y = this.rand();
			Vector2 v = new Vector2(x, y);
			Vector2 w = new Vector2(v);
			testX(w, x, String.format("new Vector2(w) failed", x, y));
			testY(w, y, String.format("new Vector2(w) failed", x, y));
		}
	}

	@Test
	public void test04_setX() {
		// try a few random coordinate values
		Vector2 v = new Vector2();
		for (int i = 0; i < 10; i++) {
			double x = this.rand();
			v.setX(x);
			testX(v, x, String.format("setX(%s) failed\n", x));
		}
	}

	@Test
	public void test05_setY() {
		// try a few random coordinate values
		Vector2 v = new Vector2();
		for (int i = 0; i < 10; i++) {
			double y = this.rand();
			v.setY(y);
			testY(v, y, String.format("setY(%s) failed\n", y));
		}
	}

	@Test
	public void test06_set() {
		// try a few random coordinate values
		Vector2 v = new Vector2();
		for (int i = 0; i < 10; i++) {
			double x = this.rand();
			double y = this.rand();
			v.set(x, y);
			testX(v, x, String.format("set(%s, %s) failed", x, y));
			testY(v, y, String.format("set(%s, %s) failed", x, y));
		}
	}

	@Test
	public void test07_add() {
		double x = 0.0;
		double y = 0.0;
		Vector2 v = new Vector2(x, y);
		Vector2 vNew = new Vector2(v);
		for (int i = 0; i < 10; i++) {
			double xi = this.rand();
			double yi = this.rand();
			Vector2 w = new Vector2(xi, yi);
			vNew.add(w);
			x += xi;
			y += yi;
			testX(vNew, x, String.format("%s.add(%s) failed", v, w));
			testY(vNew, y, String.format("%s.add(%s) failed", v, w));
			v.set(x, y);
		}
	}

	@Test
	public void test08_subtract() {
		double x = 0.0;
		double y = 0.0;
		Vector2 v = new Vector2(x, y);
		Vector2 vNew = new Vector2(v);
		for (int i = 0; i < 10; i++) {
			double xi = this.rand();
			double yi = this.rand();
			Vector2 w = new Vector2(xi, yi);
			vNew.subtract(w);
			x -= xi;
			y -= yi;
			testX(vNew, x, String.format("%s.subtract(%s) failed", v, w));
			testY(vNew, y, String.format("%s.subtract(%s) failed", v, w));
			v.set(x, y);
		}
	}

	@Test
	public void test09_multiply() {
		double x = 1.0;
		double y = 1.0;
		Vector2 v = new Vector2(x, y);
		Vector2 vNew = new Vector2(v);
		for (int i = 0; i < 10; i++) {
			double si = this.rand();
			vNew.multiply(si);
			x *= si;
			y *= si;
			testX(vNew, x, String.format("%s.multiply(%s) failed", v, si));
			testY(vNew, y, String.format("%s.multiply(%s) failed", v, si));
			v.set(x, y);
		}
	}

	@Test
	public void test10_mag() {
		for (int i = 0; i < 10; i++) {
			double x = i * this.rand();
			double y = i * this.rand();
			Vector2 v = new Vector2(x, y);
			String error = String.format("%s.mag() failed\n", v);
			double exp = Math.hypot(x, y);
			double got = v.mag();
			assertEquals(error, exp, got, Math.ulp(exp));
		}
	}

	@Test
	public void test11_addStatic() {
		for (int i = 0; i < 10; i++) {
			double vx = this.rand();
			double vy = this.rand();
			double wx = this.rand();
			double wy = this.rand();
			Vector2 v = new Vector2(vx, vy);
			Vector2 w = new Vector2(wx, wy);
			Vector2 got = Vector2.add(v, w);
			testX(got, vx + wx,
					String.format("Vector2.add(%s, %s) failed", v, w));
			testY(got, vy + wy,
					String.format("Vector2.add(%s, %s) failed", v, w));
		}
	}

	@Test
	public void test12_subtractStatic() {
		for (int i = 0; i < 10; i++) {
			double vx = this.rand();
			double vy = this.rand();
			double wx = this.rand();
			double wy = this.rand();
			Vector2 v = new Vector2(vx, vy);
			Vector2 w = new Vector2(wx, wy);
			Vector2 got = Vector2.subtract(v, w);
			testX(got, vx - wx,
					String.format("Vector2.subtract(%s, %s) failed", v, w));
			testY(got, vy - wy,
					String.format("Vector2.subtract(%s, %s) failed", v, w));
		}
	}

	@Test
	public void test13_multiplyStatic() {
		for (int i = 0; i < 10; i++) {
			double vx = this.rand();
			double vy = this.rand();
			double s = this.rand();
			Vector2 v = new Vector2(vx, vy);
			Vector2 got = Vector2.multiply(s, v);
			testX(got, s * vx,
					String.format("Vector2.multiply(%s, %s) failed", s, v));
			testY(got, s * vy,
					String.format("Vector2.multiply(%s, %s) failed", s, v));
		}
	}

	@Test
	public void test14_dirVector() {
		for (int i = -720; i < 720; i++) {
			Vector2 v = Vector2.dirVector(i);
			testX(v, Math.cos(Math.toRadians(i)),
					String.format("Vector2.dirVector(%s) failed", i));
			testY(v, Math.sin(Math.toRadians(i)),
					String.format("Vector2.dirVector(%s) failed", i));
		}
	}

	@Test
	public void test15_similarTo() {
		double rad = this.rand();
		double x = Math.cos(rad);
		double y = Math.sin(rad);
		Vector2 unit = new Vector2(x, y);
		for (int i = -6; i <= 6; i++) {
			double tol = Math.pow(10.0, i);

			// if we scale unit by maxScale we will be at the limits of similarity
			double maxScale = 1.0 + tol;
			double scale = Math.nextAfter(maxScale, Double.NEGATIVE_INFINITY);
			Vector2 w = new Vector2(scale * x, scale * y);
			assertTrue(unit.similarTo(w, tol));

			scale = Math.nextAfter(maxScale, Double.POSITIVE_INFINITY);
			w = new Vector2(scale * x, scale * y);
			assertFalse(unit.similarTo(w, tol));
		}
	}





}
