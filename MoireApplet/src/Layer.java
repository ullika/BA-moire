/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 8/25/14
 * Time: 7:30 PM
 */
public class Layer {

    public enum Type {
        LINES,
        DOTS

    }

    public enum Figure {
        STRAIGHT,
        PARABOLA,
        CIRCLE
    }

    private Figure figure;
    private Type type;
    private int id;
    private int nLines;
    private int gap;
    private int phi;
    private int openingRatio;          // in percent

    private int strokewidth;
    private int curvature;
    private boolean visible;

    public Layer(int id) {
        this.id = id;
        this.figure = Figure.STRAIGHT;
        this.type = Type.LINES;
        this.nLines = 15;
        this.phi = 0;
        this.openingRatio=10;
        this.strokewidth = 1;
        this.curvature = 1;
        this.visible = true;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    public int getStrokewidth() {
        return strokewidth;
    }

    public void setStrokewidth(int strokewidth) {
        this.strokewidth = strokewidth;
    }

    public int getOpeningRatio() {
        return openingRatio;
    }

    public void setOpeningRatio(int openingRatio) {
        this.openingRatio = openingRatio;
    }

    public void updateStrokewidth() {
        setStrokewidth(openingRatio*gap/100);

    }

    public Figure getFigure() {
        return figure;
    }

    public void setFigure(Figure figure) {
        this.figure = figure;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getNLines() {
        return nLines;
    }

    public void setNLines(int nLines) {
        this.nLines = nLines;
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



    public int getId() {
        return id;
    }

}
