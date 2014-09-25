/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 8/26/14
 * Time: 12:14 PM
 */
public class Vector2d {
    public double x;
    public double y;

    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2d add(Vector2d v) {
        return new Vector2d(x + v.x, y + v.y);
    }

    public Vector2d multiply(double k) {
        return new Vector2d(k * x, k * y);
    }

    public Vector2d invert() {
        return this.multiply(-1);
    }


    public Vector2d turn(double phi) {
        return new Vector2d(x * Math.cos(phi) - y * Math.sin(phi), x * Math.sin(phi) + y * Math.cos(phi));

    }

    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }

    public boolean isBetween(double xMin, double xMax, double yMin, double yMax) {
        return x > xMin && y > yMin && x < xMax && y < yMax;
    }

    @Override
    public String toString() {
        return "["+x+"|"+y+"]";
    }
}
