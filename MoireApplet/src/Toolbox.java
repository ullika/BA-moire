import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 7/28/14
 * Time: 11:07 AM
 */
public class Toolbox extends JPanel {
    Layer layer;
    SPanel sPanel;
    Toolbox(SPanel spanel, Layer layer) {
        this.sPanel = spanel;
        this.layer = layer;
        createUI();
    }

    private void createUI() {
        visibleBox = new JCheckBox("Layer "+layer.getId(),true);
        visibleBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                layer.setVisible(visibleBox.isSelected());
                sPanel.update();

            }
        });
        add(visibleBox);




        String[] types={"lines", "dots"};

        typeBox=new JComboBox(types);
        typeBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layer.setType((String) typeBox.getSelectedItem());
                sPanel.update();
            }
        });

        add(typeBox);

        String[] figures = {"straight", "circle", "parabola"};

        figureBox=new JComboBox(figures);
        figureBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parabolaSlider.setEnabled(figureBox.getSelectedItem().equals("parabola"));
                layer.setFigure((String) figureBox.getSelectedItem());
                sPanel.update();
            }
        });
        add(figureBox);

        distslider=new JSlider(1, 100, 15);

        distslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (!distslider.getValueIsAdjusting()) {
                    layer.setDistance(distslider.getValue());
                    sPanel.update();
                }
            }
        });
        ;
        distslider.setPreferredSize(new Dimension(100, (int) distslider.getPreferredSize().getHeight()));
        add(new JLabel("Distance:"));
        add(distslider);

        phislider=new JSlider(0, (int) (100 * Math.PI), 0);
        phislider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (!phislider.getValueIsAdjusting()) {
                    layer.setPhi(phislider.getValue());
                    sPanel.update();
                }
            }
        });
        ;
        add(new JLabel("Rotation:"));
        phislider.setPreferredSize(new Dimension(100, (int) phislider.getPreferredSize().getHeight()));
        add(phislider);

        strokeslider = new JSlider(0, 90, 10);
        strokeslider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (!strokeslider.getValueIsAdjusting()) {
                    layer.setStrokewidth(strokeslider.getValue());
                    sPanel.update();
                }
            }
        });

        add(new JLabel("Strokewidth:"));
        strokeslider.setPreferredSize(new Dimension(100, strokeslider.getPreferredSize().height));
        add(strokeslider);

        parabolaSlider=new JSlider(1,15);
        parabolaSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {

                if (!parabolaSlider.getValueIsAdjusting()) {
                    layer.setCurvature(parabolaSlider.getValue());
                    sPanel.update();
                }
            }
        });
        add(new JLabel("Curvature:"));
        parabolaSlider.setPreferredSize(new Dimension(100, parabolaSlider.getPreferredSize().height));
        add(parabolaSlider);

        parabolaSlider.setEnabled(false);

        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand("remove"+layer.getId());
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sPanel.removeLayer(layer);
            }
        });
        removebutton = removeButton;
        add(removeButton);
    }


    JComboBox figureBox;
    JComboBox typeBox;
    JSlider phislider;
    JSlider distslider;
    JSlider strokeslider;
    JSlider parabolaSlider;
    JCheckBox visibleBox;
    JButton removebutton;


}
