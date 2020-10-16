package eecs2030.lab3;

public class Rectangle {
	Point2 topLeft;
	Point2 bottomRight;
	
	public Rectangle(Point2 tl, Point2 br) {
		this.topLeft = tl;
		this.bottomRight = br;
		
	}
	public Point2 getTopLeft() {
		return this.topLeft;
		
	}
	public Point2 getBottomRight() {
		return this.bottomRight;
	}
	public void setTopLeft(Point2 p) {
		this.topLeft = p;
	}

}
