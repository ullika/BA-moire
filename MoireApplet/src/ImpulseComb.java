/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 9/10/14
 * Time: 6:31 PM
 */
public class ImpulseComb {
    Vector2d location;
    double DC;
    int strokewidth;
    int absDistance;


    public ImpulseComb(Vector2d location, double DC, int strokewidth, int absDistance) {
        this.location = location;
        this.DC = DC;
        this.strokewidth = strokewidth;
        this.absDistance = absDistance;
    }

    public Vector2d getLocation() {
        return location;
    }


    public double getDC() {
        return DC;
    }


    public int getStrokewidth() {
        return strokewidth;
    }


    public int getAbsDistance() {
        return absDistance;
    }

}
