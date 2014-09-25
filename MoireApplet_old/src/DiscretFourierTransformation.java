import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 8/25/14
 * Time: 4:14 PM
 */
public class DiscretFourierTransformation {
    private static final int width=1024;
    private static final int height=1024;
    private static double xMin=-1.0;
    private static double xMax=1.0;
    private static double yMin=-1.0;
    private static double yMax=1.0;

    private static double xPixelPerUnit=width/(xMax-xMin);
    private static double yPixelPerUnit=height/(yMax-yMin);

    private BufferedImage originalFunction;
    private BufferedImage transform;

    private JComponent functionDrawer;
    private JComponent transformDrawer;

    public DiscretFourierTransformation() {
        originalFunction=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
        transform=new BufferedImage(width, height,BufferedImage.TYPE_4BYTE_ABGR);

        createFrames();

        drawFunction();

        transform();

    }

    private void transform() {
        Complex [][] values=new Complex[width][height];
        double min=Double.MAX_VALUE;
        double max=Double.MIN_VALUE;

        for (int x=0;x<width;x++) {
            for (int y=0;y<height;y++) {
                values[x][y]=new Complex(originalFunction.getRGB(x,y)<-2?1.0:0.0,0.0);

            }
        }

        Fourier2D transformer=new Fourier2D(width,height);
        Complex[][] transformed=transformer.fft(values);

        double[][] transforms=new double[width][height];
        Graphics g=transform.getGraphics();

        for (int x=0; x<width;x++) {
            for (int y=0;y<height;y++) {
                g.setColor(new Color(Math.min(255,(int) (Math.abs(transformed[x][y].real)*5000)),Math.min(255,(int) (Math.abs(transformed[x][y].imag)*2000)),0));
                g.drawLine(x,y,x,y);
            }
        }
        transformDrawer.repaint();
        System.out.println();
    }

    private void drawFunction() {
        Graphics g=originalFunction.getGraphics();
        g.setColor(Color.BLACK);
        for (int i=-10;i<20;i++) {
            double x= (xMin+i*((xMax-xMin)/20));
            Point start=transformToImage(new Point2D.Double(x,yMin));
            Point end=transformToImage(new Point2D.Double(x+0.05,yMax));
            g.drawLine(start.x, start.y, end.x, end.y);
        }
        functionDrawer.repaint();
    }

    private Point transformToImage(Point.Double coordinatePoint) {
        return new Point((int) ((coordinatePoint.getX()-xMin)*xPixelPerUnit), (int) ((coordinatePoint.getY()-yMin)*yPixelPerUnit));
    }

    private Point.Double transformFromImage(Point imagePoint) {
        return new Point2D.Double((imagePoint.getX()+xMin)/xPixelPerUnit, (imagePoint.getY()+yMin)/yPixelPerUnit);
    }

    private void createFrames() {
        JFrame functionFrame=new JFrame("Funktion");
        functionDrawer=new DrawerComponent(originalFunction);
        functionFrame.getContentPane().add(functionDrawer);
        functionFrame.pack();
        functionFrame.setVisible(true);

        JFrame transformFrame=new JFrame("Transformation");
        transformDrawer=new DrawerComponent(transform);
        transformFrame.getContentPane().add(transformDrawer);
        transformFrame.pack();
        transformFrame.setVisible(true);

    }

    public static void main(String[] args) {
        new DiscretFourierTransformation();
    }

    private static class DrawerComponent extends JComponent  {
        private BufferedImage img;

        private DrawerComponent(BufferedImage img) {
            this.img = img;
            setPreferredSize(new Dimension(img.getWidth(), img.getHeight()));
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.drawImage(img,0,0,img.getWidth(), img.getHeight(),null);
        }
    }

    private static class ComplexNumber {
        double r;
        double i;

        private ComplexNumber(double r, double i) {
            this.r = r;
            this.i = i;
        }
    }
}
