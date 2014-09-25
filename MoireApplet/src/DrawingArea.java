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

        Dimension areaSize = this.getSize();
        int areaWidth = areaSize.width;

        if (areaWidth != areaSize.height)
            return;


        g.setColor(Color.white);
        g.fillRect(0, 0, areaWidth, areaWidth);
        g.setColor(Color.black);
        float c = 2 * (float) sqrt(2);

        BufferedImage result = new BufferedImage(areaWidth, areaWidth, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D rG = (Graphics2D) result.getGraphics();
        for (int i = 0; i < panel.toolboxes.size(); i++) {
            BufferedImage image = new BufferedImage((int) (areaWidth * 2 / c), (int) (areaWidth * 2 / c), BufferedImage.TYPE_4BYTE_ABGR);

            int imageWidth = (int) (areaWidth * 2 / c);
            Layer layer=panel.toolboxes.get(i).layer;
            if (!layer.isVisible())
                continue;

            int nLines = layer.getNLines();
            int gap = (int) (imageWidth/(nLines*1.0));
            layer.setGap(gap);
            layer.updateStrokewidth();
            int strokewidth = layer.getStrokewidth();



            double phi = layer.getPhi() / 100.0;
            Graphics2D g2 = (Graphics2D) image.getGraphics();


            g2.setColor(Color.BLACK);

            Layer.Type selectedType = layer.getType();
            switch (selectedType) {
                case LINES:
                    g2.setStroke(new BasicStroke(strokewidth));
                    break;
                case DOTS:
                    float dashPhase=(float) (gap-(Math.ceil((((imageWidth - 1) % gap)/2.0-strokewidth/2.0))));
                    g2.setStroke(new BasicStroke(strokewidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{strokewidth, Math.max(0, (gap) - strokewidth)}, dashPhase));
                    break;

            }


            Layer.Figure selectedFigure = layer.getFigure();

            switch (selectedFigure)  {
                case STRAIGHT:
                    for (double x = Math.ceil(((imageWidth - 1) % gap) / 2.0); x <= imageWidth; x+=gap) {

                        Point2D p = new Point2D.Double(x, imageWidth);
                        Point2D q = new Point2D.Double(x, 0);


                        g2.drawLine((int) p.getX(), (int) p.getY(), (int) q.getX(), (int) q.getY());

                    }
                    break;
                case CIRCLE:
                    int linienabstandCirc=max(gap,6);
                    int strokewidthCirc= (int) Math.max(layer.getStrokewidth() * linienabstandCirc / 100.0, 1.0);
                    g2.setStroke(new BasicStroke(strokewidthCirc));
                    for (int r= 0; r < 2*imageWidth; r+=linienabstandCirc) {
                        g2.drawOval( (imageWidth- r) / 2,(imageWidth - r) / 2, r, r);
                    }
                    break;
                case PARABOLA:

                    int parabolaExtension = layer.getCurvature();
                    double extension = (parabolaExtension / 8.0);

                    for (double x = 0; x <= imageWidth * 2; x += gap) {

                        double y = getHeight();
                        Point2D p = new Point2D.Double(x, y);
                        Point2D q = new Point2D.Double(x, 0);
                        Point2D u = new Point2D.Double(x - extension, y / 2.0);

                        QuadCurve2D.Double parabola = new QuadCurve2D.Double(p.getX(), p.getY(), u.getX(), u.getY(), q.getX(), q.getY());
                        g2.draw(parabola);

                    }
                    break;

            }

            int originalOffset = (int) ((areaWidth - (areaWidth * 2 / c)) / 2);


            rG.setTransform(AffineTransform.getRotateInstance(phi, (areaWidth / 2), (areaWidth / 2)));

            rG.drawImage(image, originalOffset, originalOffset, null);


        }
        g.drawImage(result, 0, 0, null);
        panel.updateData();
    }

    private void transformPoint(int k, float c, Point2D p) {
        p.setLocation(p.getX() * k * 2 / c, p.getY() * k * 2 / c);
    }


}


