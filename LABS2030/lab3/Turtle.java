package eecs2030.lab3;

import princeton.introcs.StdDraw;
import java.awt.Color;

/**
 * A class that supports turtle graphics. A turtle moves between two points in a
 * straight line drawing the line as it moves.
 * 
 * A turtle has-a <code>Point2</code> instance that represents the position of
 * the turtle, and a <code>Color</code> instance that represents the current pen
 * color. The turtle maintains ownership of its position at all times.
 * 
 * 
 * @author EECS2030 Fall 2016-17
 * 
 */
public class Turtle {
    private Point2 position;
    private double angle;
    private Color penColor;

    /**
     * Create a turtle at location <code>(0.5, 0.5)</code> with an angle of
     * <code>0.0</code> degrees and a pen color of <code>Color.BLACK</code>.
     */
    public Turtle() {
        this(new Point2(0.5, 0.5), 0.0, Color.BLACK);
    }

    /**
     * Create a turtle from another turtle. The created turtle is initialized
     * having the same position, angle, and pen color as the other turtle, but
     * moves independently of the other turtle.
     * 
     * @param other
     *            the turtle to copy
     */
    public Turtle(Turtle other) {
        this(other.getPosition(), other.getAngle(), other.getPenColor());
    }

    /**
     * Create a turtle at location <code>position</code> with an angle of
     * <code>0.0</code> degrees and a pen color of <code>Color.BLACK</code>. The
     * turtle is responsible for its own position.
     * 
     * <p>
     * The starting position must be inside the square with corners
     * <code>(0.0, 0.0)</code> and <code>(1.0, 1.0)</code>, otherwise an
     * <code>IllegalArgumentException</code> will be thrown.
     * 
     * @param position
     *            the starting position of the turtle
     * @throws IllegalArgumentException
     *             if the starting position is not in the square with corners
     *             <code>(0.0, 0.0)</code> and <code>(1.0, 1.0)</code>
     */
    public Turtle(Point2 position) {
        this(position, 0.0, Color.BLACK);
    }

    /**
     * Create a turtle at location <code>position</code> with an angle of
     * <code>angle</code> degrees and a pen color of <code>Color.BLACK</code>.
     * The starting position must be inside the square with corners
     * <code>(0.0, 0.0)</code> and <code>(1.0, 1.0)</code>, otherwise an
     * <code>IllegalArgumentException</code> will be thrown.
     * 
     * @param position
     *            the starting position of the turtle
     * @param angle
     *            the angle in degrees from the x axis that the turtle is facing
     *            in
     * @throws IllegalArgumentException
     *             if the starting position is not in the square with corners
     *             <code>(0.0, 0.0)</code> and <code>(1.0, 1.0)</code>
     */
    public Turtle(Point2 position, double angle) {
        this(position, angle, Color.BLACK);
    }

    /**
     * Create a turtle with the given starting position, angle, and pen color.
     * The starting position must be inside the square with corners
     * <code>(0.0, 0.0)</code> and <code>(1.0, 1.0)</code>, otherwise an
     * <code>IllegalArgumentException</code> will be thrown.
     * 
     * @param position
     *            the starting position of the turtle
     * @param angle
     *            the angle in degrees from the x axis that the turtle is facing
     *            in
     * @param c
     *            the pen color
     * @throws IllegalArgumentException
     *             if the starting position is not in the square with corners
     *             <code>(0.0, 0.0)</code> and <code>(1.0, 1.0)</code>
     * @throws IllegalArgumentException
     *             if the pen color c is null
     */
    public Turtle(Point2 position, double angle, Color c) {
        double x = position.getX();
        double y = position.getY();
        if (x >= 0. && x <= 1.0 && y >= 0. && y <= 1.0 && c != null) {
            this.position = new Point2(x, y);
            this.angle = angle;
            this.penColor = c;
        } else {
            throw new IllegalArgumentException("Invalid starting position!");
        }
    }

    /**
     * Moves the turtle by a given distance in the direction the turtle is
     * currently facing. A line is drawn as the turtle moves to the new position
     * using the current pen color. The distance can be negative, in which case
     * the turtle moves backwards in the direction opposite to the direction it
     * is currently facing in.
     * 
     * @param distance
     *            the distance to move
     */
    public void move(double distance) {
        Point2 current = new Point2(this.position);
        Vector2 delta = Vector2.dirVector(this.angle);
        delta.multiply(distance);
        this.position.add(delta);

        StdDraw.setPenColor(this.getPenColor());
        StdDraw.line(current.getX(), current.getY(), this.position.getX(), this.position.getY());
    }

    /**
     * Turns the turtle to the left, increasing its angle by 90.0 degrees.
     * The angle of the turtle is always corrected to lie within
     * the range of <code>-360</code> degrees and <code>+360</code> degrees.
     */
    public void turnLeft() {
        this.turn(90.0);
    }

    /**
     * Turns the turtle to the right, decreasing its angle by 90.0 degrees.
     * The angle of the turtle is always corrected to lie within
     * the range of <code>-360</code> degrees and <code>+360</code> degrees.
     */
    public void turnRight() {
        this.turn(-90.0);
    }

    /**
     * Turns the turtle by the specified amount in degrees. A positive
     * <code>delta</code> turns the turtle to the left (counterclockwise) and
     * the negative <code>delta</code> turns the turtle to the right
     * (clockwise). The angle of the turtle is always corrected to lie within
     * the range of <code>-360</code> degrees and <code>+360</code> degrees.
     * 
     * @param delta
     *            the amount by which to turn the turtle
     */
    public void turn(double delta) {
        this.angle += delta;
        this.angle %= 360.0;
    }

    /**
     * Sets the pen color.
     * 
     * @param c
     *            the new pen color
     * @throws IllegalArgumentException
     *            if the pen color c is null
     */
    public void setPenColor(Color c) {
        if (c == null) {
            throw new IllegalArgumentException("pen color is null");
        }
        this.penColor = c;
    }

    /**
     * Gets the current pen color.
     * 
     * @return the current pen color
     */
    public Color getPenColor() {
        return this.penColor;
    }

    /**
     * Gets the current position of the turtle. The client cannot change the
     * position of the turtle using the <code>Point2</code> returned by this
     * method. To move the turtle the client must use <code>move</code>.
     * 
     * @return the current position of the turtle
     */
    public Point2 getPosition() {
        return new Point2(this.position);
    }

    /**
     * Gets the direction that the turtle is facing in as an angle measured from
     * the x axis. The angle of the turtle is always in the range of
     * <code>-360</code> degrees and <code>+360</code> degrees.
     * 
     * @return the angle measured in degrees from the x axis that the turtle is
     *         facing
     */
    public double getAngle() {
        return this.angle;
    }

    /**
     * Returns a hash code for this turtle.
     * 
     * @return a hash code for this turtle 
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(angle);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((penColor == null) ? 0 : penColor.hashCode());
        result = prime * result + ((position == null) ? 0 : position.hashCode());
        return result;
    }

    /**
     * Compares this turtle with another object for equality. This turtle
     * can be equal to only other turtles.
     * 
     * <p>
     * Two turtles are equal if their positions, directions, and pen colors
     * are all equal; otherwise, the turtles are not equal.
     * 
     * @param obj
     *           An object to compare for equality.
     * @return 
     *           True if the position, direction, and pen color of this turtle
     *           are equal to the position, direction, and pen color of the other
     *           turtle, and false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Turtle other = (Turtle) obj;
        if (Double.doubleToLongBits(angle) != Double.doubleToLongBits(other.angle)) {
            return false;
        }
        else if (!penColor.equals(other.penColor)) {
            return false;
        } 
        else if (!position.equals(other.position)) {
            return false;
        }
        return true;
    }
    
    
    /**
     * Returns a string representation of this turtle. The string representation
     * is:
     * 
     * <ol>
     * <li>the position of the turtle (as given by <code>Point2.toString</code>),
     * followed by
     * <li>a comma and a space, followed by
     * <li>the direction, followed by
     * <li>a space, the string "degrees", a space, and a comma, followed by
     * <li>the pen color (as given by <code>Color.toString</code>)
     * </ol>
     * 
     * @return a string representation of this turtle
     */
    @Override
    public String toString() {
        String s = String.format("%s, %f degrees, %s",
                this.getPosition(), this.getAngle(), this.getPenColor());
        return s;
    }

}

