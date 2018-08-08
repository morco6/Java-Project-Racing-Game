package utilities;

public class Point {

	public static final int MAX_X = 1000000;
	public static final int MIN_X = 0;
	public static final int MAX_Y = 800;
	public static final int MIN_Y = 0;

	private double x;
	private double y;

	public Point() {
		this(0, 0);
	}

	public Point(double x, double y) {
		if (!(this.setX(x))) {
			this.x = 0;
		}
		if (!(this.setY(y))) {
			this.y = 0;
		}
	}

	public Point(Point other) {
		if (other == null) {
			other = new Point(0, 0);
		}
		this.setX(other.x);
		this.setY(other.y);
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public boolean setX(double x) {
		if (x > MAX_X || x < MIN_X) {
			return false;
		}
		this.x = x;
		return true;
	}

	public boolean setY(double y) {
		if (y > MAX_Y || y < MIN_Y) {
			return false;
		}
		this.y = y;
		return true;
	}

	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}

}
