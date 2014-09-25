import com.sun.java.swing.plaf.gtk.GTKLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;

import javax.swing.*;
import javax.swing.plaf.multi.MultiLookAndFeel;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.synth.SynthLookAndFeel;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 6/28/14
 * Time: 2:19 PM
 */
public class MainClass {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        try {
            UIManager.setLookAndFeel(new WindowsClassicLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }


        JFrame frame=new JFrame();
        SPanel sPanel=new SPanel(frame);
        frame.add(sPanel.surrounder2);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);



        JFrame drawingAreaFrame = new JFrame();
        drawingAreaFrame.add(sPanel.surrounder);


        drawingAreaFrame.pack();
        drawingAreaFrame.setVisible(true);

        JFrame tableFrame = new JFrame();
        LayerPropertyTable table = new LayerPropertyTable(sPanel);
        JScrollPane scrollpane = new JScrollPane(table);
        scrollpane.setPreferredSize(new Dimension(500,150));

        tableFrame.add(scrollpane);
        tableFrame.pack();
        tableFrame.setVisible(true);
        sPanel.setTable(table);

        JFrame spectrumFrame=new JFrame();
        SpectrumPanel spectrumPanel=new SpectrumPanel(sPanel);
        spectrumFrame.getContentPane().add(spectrumPanel);
        spectrumFrame.setSize(400,400);
        spectrumFrame.setLocation(400,400);
        spectrumFrame.setVisible(true);
        spectrumFrame.setResizable(false);
        sPanel.setSpectrumPanel(spectrumPanel);

    }
}
