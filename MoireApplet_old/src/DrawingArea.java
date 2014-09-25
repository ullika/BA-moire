import javax.swing.*;
import javax.tools.Tool;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;

import static java.lang.Math.floor;
import static java.lang.Math.max;
import static java.lang.Math.sqrt;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 6/28/14
 * Time: 2:20 PM
 */

public class DrawingArea extends JPanel {
    SPanel panel;


    DrawingArea(SPanel panel) {
        this.panel = panel;

    }


    @Override
    public void paint(Graphics g) {

        Dimension size = this.getSize();
        int w = size.width;
        int h = size.height;
        if (w != h)
            return;
        int k = w;

        g.setColor(Color.white);
        g.fillRect(0, 0, k, k);
        g.setColor(Color.black);
        float c = 2 * (float) sqrt(2);

        BufferedImage result = new BufferedImage(k, k, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D rG = (Graphics2D) result.getGraphics();
        for (int i = 0; i < panel.toolboxes.size(); i++) {
            BufferedImage image = new BufferedImage((int) (k * 2 / c), (int) (k * 2 / c), BufferedImage.TYPE_4BYTE_ABGR);

            int bildbreite = (int) (k * 2 / c);
            Layer layer=panel.toolboxes.get(i).layer;
            if (!layer.isVisible())
                continue;
            int strokewidth = layer.getStrokewidth();
            int n = layer.getDistance();


            double linienabstand= bildbreite/(n*1.0);
            int linienabstandInt = (int) linienabstand;
            n = bildbreite / linienabstandInt;
            System.out.println("neues n: "+n);

            System.out.println("linienabstand: " + linienabstand);  //abstand der mittelpunkte der linien in pixeln.

            System.out.println("strokewidth alt: "+strokewidth);
            strokewidth= (int) Math.max(strokewidth * linienabstand / 100.0, 1.0);
            System.out.println("strokewidth neu: "+strokewidth);
            double phi = layer.getPhi() / 100.0;

            Graphics2D g2 = (Graphics2D) image.getGraphics();


            g2.setColor(Color.BLACK);

            String selectedType = layer.getType();
            if (selectedType.equals("lines")) {
                g2.setStroke(new BasicStroke(strokewidth));
            } else if (selectedType.equals("dots")) {
                System.out.println("linienabstandInt " + linienabstandInt);
                System.out.println("strokewidth: "+strokewidth);
                g2.setStroke(new BasicStroke(strokewidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{strokewidth, Math.max(0, (linienabstandInt) - strokewidth)}, 0));
            } else {
                System.out.println("???");
                //this should never happen
            }

            String selectedFigure = layer.getFigure();
            System.out.println(bildbreite);
            if (selectedFigure.equals("straight")) {

                for (double x = Math.ceil(((bildbreite - 1) % linienabstandInt) / 2.0); x <= bildbreite; x+=linienabstandInt) {
                    //System.out.println("x start: "+((bildbreite+1)%linienabstandInt)/2);

                    Point2D p = new Point2D.Double(x, bildbreite);
                    Point2D q = new Point2D.Double(x, 0);


                    g2.drawLine((int) p.getX(), (int) p.getY(), (int) q.getX(), (int) q.getY());

                }

            } else if (selectedFigure.equals("circle")) {
                int linienabstandCirc=max(linienabstandInt,6);
                int strokewidthCirc= (int) Math.max(layer.getStrokewidth() * linienabstandCirc / 100.0, 1.0);
                g2.setStroke(new BasicStroke(strokewidthCirc));
                for (int r= 0; r < 2*bildbreite; r+=linienabstandCirc) {

                    g2.drawOval( (bildbreite- r) / 2,(bildbreite - r) / 2, r, r);
                }

            } else if (selectedFigure.equals("parabola")) {
                int parabolaExtend = layer.getCurvature();
                double extend = (parabolaExtend / 8.0);

                for (double x = 0; x < bildbreite * 2; x += linienabstandInt) {

                    double y = 1;
                    Point2D p = new Point2D.Double(x, y);
                    Point2D q = new Point2D.Double(x, 0);
                    Point2D u = new Point2D.Double(x - extend, y / 2.0);
                    transformPoint(k, c, p);  // hier wird der einheitkreis richtig skaliert und verschoben, damit er im fenster an der richtigen position ist.
                    transformPoint(k, c, q);
                    transformPoint(k, c, u);
                    QuadCurve2D.Double parabola = new QuadCurve2D.Double(p.getX(), p.getY(), u.getX(), u.getY(), q.getX(), q.getY());
                    g2.draw(parabola);

                }
            } else {
                System.out.println("???");
                //this should never happen, too.
            }

            int originalOffset = (int) ((k - (k * 2 / c)) / 2);

            rG.setTransform(AffineTransform.getRotateInstance(phi, (k / 2), (k / 2)));

            rG.drawImage(image, originalOffset, originalOffset, null);


        }
        g.drawImage(result, 0, 0, null);

    }

    private void transformPoint(int k, float c, Point2D p) {
        p.setLocation(p.getX() * k * 2 / c, p.getY() * k * 2 / c);
    }


}


