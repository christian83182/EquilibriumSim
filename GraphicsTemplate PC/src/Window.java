import javax.swing.*;
import java.awt.*;

/**
 * Created by Christian on 27/07/2016.
 */
public class Window extends Canvas {

    public Window(int width,int height,String title, Game mainGame){

        JFrame frame = new JFrame();
        frame.setTitle(title);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(mainGame);
        frame.setVisible(true);

    }
}
