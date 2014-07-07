import javax.swing.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Created with IntelliJ IDEA.
 * User: ullika
 * Date: 6/28/14
 * Time: 2:19 PM
 */
public class MainClass {

    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        new SPanel(frame);
        frame.pack();

        frame.setVisible(true);

    }
}
