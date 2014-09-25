import sun.print.resources.serviceui_es;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.QuadCurve2D;
import java.math.MathContext;
import java.text.NumberFormat;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 8/26/14
 * Time: 11:34 AM
 */
public class SpectrumPanel extends JPanel {
    SPanel sPanel;

    private static final double xMin = -0.5;
    private static final double xMax = 0.5;
    private static final double yMin = -0.5;
    private static final double yMax = 0.5;
    double maxRange = Math.sqrt(2)*Math.max(Math.max(Math.abs(xMin), xMax), Math.max(Math.abs(yMin), yMax));

    double threshold=0.02;
    double visibilityRadius=0.2;


    private int xPixelPerUnit;
    private int yPixelPerUnit;


    public SpectrumPanel(SPanel sPanel) {
        this.sPanel = sPanel;
        this.setBackground(Color.white);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateResolution();
            }
        });
    }

    private void updateResolution() {
        xPixelPerUnit = (int) (getWidth() / (xMax - xMin));
        yPixelPerUnit = (int) (getHeight() / (yMax - yMin));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, getHeight() - (int) ((1 - yMin) * yPixelPerUnit), getWidth() / 2 - 5, getHeight() - (int) ((1 - yMin) * yPixelPerUnit));

        g.drawOval((int) ((-visibilityRadius-xMin)*xPixelPerUnit), (int) (getHeight()-(visibilityRadius-yMin)*getHeight() / (yMax-yMin)),(int) (2*visibilityRadius*xPixelPerUnit),(int)(2*visibilityRadius*yPixelPerUnit));


        ArrayList<ImpulseComb> allCombs = new ArrayList<ImpulseComb>();
        double maxDC=0;


        if (sPanel.toolboxes.size()==0)
            return;


        for (Toolbox toolbox : sPanel.toolboxes) {
            Layer layer = toolbox.layer;
            if (layer.isVisible() == false) {
                continue;
            }

            int absDistance = layer.getGap();
            int strokewidth = layer.getStrokewidth();
            double theta = layer.getPhi() / 100.0 + Math.PI / 2;

            Vector2d v = new Vector2d(Math.sin(theta), Math.cos(theta));

            v = v.multiply(1.0 / absDistance);
            double dc=strokewidth/(absDistance*1.0);
            maxDC = Math.max(dc, maxDC);
            allCombs.add(new ImpulseComb(v, dc, strokewidth, absDistance));


            if (layer.getType()== Layer.Type.DOTS) {
                allCombs.add(new ImpulseComb(v.turn(Math.PI / 2.0), dc, strokewidth, absDistance));

            }




        }

        for (ImpulseComb comb : allCombs) {
            Vector2d v = comb.getLocation();
            int i=0;
            while (v.multiply(i).getLength()<maxRange) {
                i++;
                Vector2d multiply = v.multiply(i);
                drawVector(g, multiply,false);
                drawVector(g,multiply.invert(),false);

            }
        }


        int m= (int) (Math.pow(maxDC,allCombs.size())/(Math.PI*threshold)) +1;


        g.setColor(Color.RED);

        for (int i=0; i<(Math.pow(2*m+1,allCombs.size()));i++) {
            Vector2d res = new Vector2d(0,0);
            double impulse=1;
            int x = i;

            for (ImpulseComb comb : allCombs) {

                Vector2d v = comb.getLocation();
                int k = x % (2 * m + 1) - m;
                if (k!=0) {
                    impulse = Math.abs(impulse * 1 / (k * Math.PI) * Math.sin(Math.PI * k * comb.getStrokewidth() / (comb.getAbsDistance()*1.0)));
                }


                x = x / (2 * m + 1);
                res = res.add(v.multiply(k));

            }

            if (res.isBetween(xMin, xMax, yMin, yMax)) {
                g.setColor(new Color((float) 1,(float) 0,(float) 0,(float) (Math.pow(impulse, 0.4))));
                drawVector(g,res,true);
            }



        }



    }

    private void drawVector(Graphics g, Vector2d v, boolean onlyEndPoint) {
        g.fillOval((int) ((v.x - xMin) * xPixelPerUnit) - 3, (int) (getHeight() - (v.y - yMin) * getHeight() / (yMax - yMin)) - 3, 6, 6);
        if (!onlyEndPoint)
            g.drawLine(getWidth() / 2, getHeight() / 2, (int) ((v.x - xMin) * xPixelPerUnit), (int) (getHeight() - (v.y - yMin) * getHeight() / (yMax - yMin)));
    }

}
