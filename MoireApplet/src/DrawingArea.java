import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

import static java.lang.Math.sqrt;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 6/28/14
 * Time: 2:20 PM

 */



//vielleicht die unregelmaessigen luecken zwischen den strichen mit "floor" verbessern oder so...
public class DrawingArea extends JPanel {
    SPanel panel;


    DrawingArea(SPanel panel) {
        this.panel=panel;

    }


    @Override
    public void paint (Graphics g) {

        Dimension size=this.getSize();
        int w = size.width;
        int h = size.height;
        if (w!=h)
            return;
        int k=w;
      //  System.out.println(size.toString());
        g.setColor(Color.white);
        g.fillRect(0, 0, k, k);
        g.setColor(Color.black);
        for (int i=0;i<panel.getnLayers();i++) {
            int n=panel.distsliders[i].getValue();
            double phi=panel.phisliders[i].getValue();
            for (int j=0;j<n;j++) {
                Point2D p=new Point2D.Double((-1+2*(double)j/n), Math.sqrt(1-sqr(-1+2*(double)j/n)));
                Point2D q=new Point2D.Double((-1+2*(double)j/n), -Math.sqrt(1-sqr(-1+2*(double)j/n)));
                p.setLocation(turn(p,phi));
                q.setLocation(turn(q, phi));
                p.setLocation(p.getX()*k/2+k/2,p.getY()*k/2+k/2);
                q.setLocation(q.getX()*k/2+k/2,q.getY()*k/2+k/2);
                g.drawLine((int)p.getX(),(int)p.getY(),(int)q.getX(),(int)q.getY());

            }

        }

    }


    static double sqr(double x) {
        return Math.pow(x,2);
    }

    static Point2D turn(Point2D p, double phi) {
    return new Point2D.Double(Math.cos(phi)*p.getX()+Math.sin(phi)*p.getY(),Math.sin(phi)*p.getX()-Math.cos(phi)*p.getY());
    }

}


