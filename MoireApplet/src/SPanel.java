import sun.awt.image.codec.JPEGParam;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 6/28/14
 * Time: 2:19 PM
 */
public class SPanel extends JPanel {
    static final int MINDIST = 1;
    static final int MAXDIST = 100;
    DrawingArea drawingArea;
    int nLayers = 2;
    JSlider[] distsliders;
    JSlider[] phisliders;
    int counter=0;

    SPanel(JFrame frame) {
        super();
        frame.add(this);
        this.setLayout(new BorderLayout());
        JPanel toolpanel = new JPanel();
        toolpanel.setLayout(new BoxLayout(toolpanel,BoxLayout.PAGE_AXIS));

        nLayers=Integer.parseInt(JOptionPane.showInputDialog("wieviele layer?"));
        distsliders = new JSlider[nLayers];
        phisliders = new JSlider[nLayers];

        for (int i = 0; i < nLayers; i++) {
            distsliders[i] = new JSlider(MINDIST, MAXDIST, 20);
            distsliders[i].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    Object source = e.getSource();
                    JSlider slider = (JSlider) source;
                    if (!slider.getValueIsAdjusting()) {

                        drawingArea.repaint();
                    }
                }
            });
            ;
        }
        for (int i = 0; i < nLayers; i++) {
            phisliders[i] = new JSlider(0, (int) (100 * Math.PI / 2), 0);
            phisliders[i].addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {

                    Object source = e.getSource();
                    JSlider slider = (JSlider) source;
                    if (!slider.getValueIsAdjusting()) {

                        drawingArea.repaint();
                    }
                }
            });
            ;
        }

        for (int i = 0; i < nLayers; i++) {
            toolpanel.add(new JLabel("GRID No. " + (i + 1)));
            toolpanel.add(new JLabel("..."));

            toolpanel.add(new JLabel("Number of Lines"));
            toolpanel.add(distsliders[i]);

            toolpanel.add(new JLabel(("Phi")));
            toolpanel.add(phisliders[i]);
        }

        System.out.println(toolpanel.getLayout().toString());

        JPanel surrounder2 = new JPanel();
        surrounder2.add(toolpanel);
        this.add(surrounder2,BorderLayout.NORTH);

        drawingArea = new DrawingArea(this);
        JPanel surrounder = new JPanel();

        surrounder.setLayout(null);
        surrounder.setPreferredSize(new Dimension(400,400));
        surrounder.add(drawingArea);

        surrounder.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

                Object source = e.getSource();
                JPanel s = (JPanel) source;

                Dimension size = s.getSize();
                System.out.println("surr size: " + size.toString());

                int min = Math.min(size.width, size.height);
                drawingArea.setSize(new Dimension(min, min));
                int x = (size.width - min) / 2;
                int y = (size.height - min) / 2;

                drawingArea.setLocation(x, y);

                System.out.println("drawing area size: " + drawingArea.getSize().toString());
                System.out.println("position: " + drawingArea.getX() + " / " + drawingArea.getY());

                counter++;
                System.out.println("counter: "+counter);
            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

        this.add(surrounder, BorderLayout.CENTER);

    }

    public int getnLayers() {
        return nLayers;
    }


}
