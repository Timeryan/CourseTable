package OutSide;

import javax.swing.*;
import java.awt.*;

public class WindowWelcome extends JWindow {
    WindowWelcome(){
        super();

        ImageIcon image =  new ImageIcon("src\\Image\\iconWelcome.gif");
        JLabel labelImage = new JLabel(image);
        setBackground(new Color(255,255,255,0));
        setSize(1280,720);
        setLocationRelativeTo(null);
        add(labelImage);
        setVisible(true);
        try {
            // Заканчиваем работу через 2 сек
            Thread.currentThread();
            Thread.sleep(1600);
            dispose();
        } catch (Exception e) { }
    }
}

