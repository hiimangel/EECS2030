package eecs2030.lab3;

public class Testing {
	public static void main(String[] args) {
		Point2 topleft = new Point2(1.0, 10.0);
		Point2 botright = new Point2(5.0, 1.0);
		Rectangle r1 = new Rectangle(topleft, botright);
		
		System.out.println(r1.getTopLeft());
		
		topleft.setX(2.0);
		topleft.setY(4.0);
		
		System.out.println(r1.getTopLeft());
		
		Point2 tl2 = r1.getTopLeft();
		System.out.println(tl2);
		tl2.setY(-10.0);
		System.out.println(tl2);
		System.out.println(r1.getTopLeft());
	}

}
