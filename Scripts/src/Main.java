import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Main {
    public static void main(String[] args) {
        GameManager gm = new GameManager();
        JFrame frame = new JFrame();
        frame.setTitle("Amazing Maze");
        frame.add(gm);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        gm.start();
    }
}

