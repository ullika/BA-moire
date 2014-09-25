import sun.awt.image.codec.JPEGParam;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 6/28/14
 * Time: 2:19 PM
 */
public class SPanel extends JPanel {
    JFrame frame;
    DrawingArea drawingArea;
    JPanel toolpanel;
    JPanel surrounder;
    JPanel surrounder2;
    int nLayers;
    ArrayList<Toolbox> toolboxes;

    private LayerPropertyTable table;
    private SpectrumPanel spectrumPanel;

    public void setTable(LayerPropertyTable table) {
        this.table = table;
    }

    SPanel(JFrame frame) {
        super();
        this.frame = frame;

        this.setLayout(new BorderLayout());
        toolpanel = new JPanel();

        BoxLayout boxLayout = new BoxLayout(toolpanel, BoxLayout.PAGE_AXIS);

        toolpanel.setLayout(boxLayout);
        nLayers = 0;
        toolboxes = new ArrayList<Toolbox>();

        JButton newLayerButton = new JButton("New Layer");

        newLayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createNewLayer();
            }
        });
        toolpanel.add(newLayerButton);

        System.out.println(toolpanel.getLayout().toString());


        surrounder2 = new JPanel();
        surrounder2.add(toolpanel);

        drawingArea = new DrawingArea(this);
        surrounder = new JPanel();

        surrounder.setLayout(null);
        surrounder.setPreferredSize(new Dimension(400, 400));
        surrounder.add(drawingArea);

        surrounder.addComponentListener(new ComponentAdapter() {
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



            }
        });


    }

    void createNewLayer() {

        final Toolbox box = new Toolbox(this, new Layer(nLayers));
        toolboxes.add(box);
        nLayers++;
        toolpanel.add(box);
        toolpanel.setVisible(false);
        toolpanel.setVisible(true);
        update();
        this.frame.pack();

    }

    public void update() {
        drawingArea.repaint();
        updateData();
    }


    public void removeLayer(Layer layer) {
        for (int i = 0; i < toolboxes.size(); i++) {
            if (toolboxes.get(i).layer == layer) {
                toolboxes.remove(i);
                toolpanel.remove(i + 1);
            }
        }
        toolpanel.invalidate();
        toolpanel.revalidate();
        update();
        frame.pack();

    }

    public void updateData() {
        if (table != null)  {
            table.update();

        }
        if (spectrumPanel!=null) {
            spectrumPanel.repaint();
        }
    }

    public void setSpectrumPanel(SpectrumPanel spectrumPanel) {
        this.spectrumPanel = spectrumPanel;
    }
}
