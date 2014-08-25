/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 8/25/14
 * Time: 7:30 PM
 */
public class Layer {
    private String figure;
    private String type;
    private int id;
    private int distance;
    private int phi;
    private int strokewidth;
    private int curvature;
    private boolean visible;

    public Layer(int id) {
        this.id = id;
        this.figure="straight";
        this.type = "lines";
        this.distance = 15;
        this.phi = 0;
        this.strokewidth = 1;
        this.curvature = 1;
        this.visible = true;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPhi() {
        return phi;
    }

    public void setPhi(int phi) {
        this.phi = phi;
    }

    public int getCurvature() {
        return curvature;
    }

    public void setCurvature(int curvature) {
        this.curvature = curvature;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getStrokewidth() {
        return strokewidth;
    }

    public void setStrokewidth(int strokewidth) {
        this.strokewidth = strokewidth;
    }

    public int getId() {
        return id;
    }

}
