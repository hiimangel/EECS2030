package eecs2030.lab3;

/**
 * A simple class for representing points in 2D Cartesian
 * coordinates. Every <code>Point2D</code> instance has an
 * x and y coordinate.
 * 
 * @author EECS2030 Fall 2020
 *
 */
public class Point2 {

    private double x;
    private double y;
    
    /**
     * Create a point with coordinates <code>(0, 0)</code>.
     */
    public Point2() {
        this.set(0.0, 0.0);
    }
    
    /**
     * Create a point with coordinates <code>(newX, newY)</code>.
     * 
     * @param newX the x-coordinate of the point
     * @param newY the y-coordinate of the point
     */
    public Point2(double newX, double newY) {
        this.set(newX, newY);
    }
    
    /**
     * Create a point with the same coordinates as <code>other</code>.
     * 
     * @param other another point
     */
    public Point2(Point2 other) {
        this(other.x, other.y);
    }
    
    /**
     * Returns the x-coordinate of this point.
     * 
     * @return the x-coordinate of this point
     */
    public double getX() {
        return this.x;
    }
    
    /**
     * Returns the y-coordinate of this point.
     * 
     * @return the y-coordinate of this point
     */
    public double getY() {
        return this.y;
    }
    
    /**
     * Sets the x-coordinate of this point to <code>newX</code>.
     * 
     * @param newX the new x-coordinate of this point
     */
    public void setX(double newX) {
        this.x = newX;
    }
    
    /**
     * Sets the y-coordinate of this point to <code>newY</code>.
     * 
     * @param newY the new y-coordinate of this point
     */
    public void setY(double newY) {
        this.y = newY;
    }
    
    
    /**
     * Sets the x-coordinate and y-coordinate of this point to
     * <code>newX</code> and <code>newY</code>, respectively.
     * 
     * @param newX the new x-coordinate of this point
     * @param newY the new y-coordinate of this point
     */
    public void set(double newX, double newY) {
        this.x = newX;
        this.y = newY;
    }
    
    
    /**
     * Adds a vector to this point changing the coordinates of this point.
     * 
     * <p>
     * Mathematically, if this point is <code>a</code> and the vector is
     * <code>v</code> then invoking this method is equivalent to computing
     * <code>a + v</code> and assigning the value back to <code>a</code>.
     * 
     * @param v a vector
     * @return this <code>Point2</code> object
     */
    public Point2 add(Vector2 v) {
        this.x += v.getX();
        this.y += v.getY();
        return this;
    }
    
    /**
     * Returns a new <code>Point2</code> equal to <code>a - b</code>.
     * 
     * @param a
     *            a point
     * @param b
     *            another point
     * @return a new <code>Point2</code> equal to <code>a - b</code>
     */
    public static Vector2 subtract(Point2 a, Point2 b) {
        Vector2 result = new Vector2(a.getX() - b.getX(), a.getY() - b.getY());
        return result;
    }
    
    
    /**
     * Returns a string representation of this point. The string
     * representation of this point is the x and y-coordinates
     * of this point, separated by a comma and space, inside a pair
     * of parentheses. 
     * 
     * @return a string representation of this point
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        String s = String.format("(%s, %s)", this.getX(), this.getY());
        return s;
    }

    /**
     * Returns a hash code for this point
     * 
     * @return a hash code for this point
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(this.x);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(this.y);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    
    /**
     * Compares this point with the given object. The result is
     * <code>true</code> if and only if the argument is not <code>null</code>
     * and is a <code>Point2</code> object having the same coordinates as this
     * object.
     * 
     * @param obj
     *            the object to compare this vector against
     * @return <code>true</code> if the given object represents a
     *         <code>Point2</code> equivalent to this point,
     *         <code>false</code> otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Point2 other = (Point2) obj;
        if (Double.doubleToLongBits(this.x) != Double.doubleToLongBits(other.x)) {
            return false;
        }
        if (Double.doubleToLongBits(this.y) != Double.doubleToLongBits(other.y)) {
            return false;
        }
        return true;
    }
    
    /**
     * Determines if two points are almost equal (similar). Two points are
     * similar if the magnitude of their vector difference is smaller than the
     * specified tolerance.
     * 
     * @param other
     *            the other point to compare
     * @param tol
     *            the threshold length of the vector difference
     *            <code>(this - other)</code>
     * @return <code>true</code> if the length of <code>(this - other)</code> is
     *         less than <code>tol</code>, and <code>false</code> otherwise
     */
    public boolean similarTo(Point2 other, double tol) {
        Vector2 delta = Point2.subtract(this, other);
        return delta.mag() < Math.abs(tol);
    }
}
