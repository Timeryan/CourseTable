package OutSide;

import javax.swing.*;
import java.awt.*;


public class Dialog extends JDialog {
     JButton buttonOK;
     JButton buttonNO;
     JButton buttonCancel;
        Dialog(){
        super();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setSize(270,120);
        this.setLocationRelativeTo(null);

        JLabel labelText = new JLabel("Хотите сохранить текущую таблицу?");
        labelText.setHorizontalAlignment(SwingConstants.CENTER);
        buttonOK = new JButton("Да");
        buttonNO = new JButton("Нет");
        buttonCancel = new JButton("Отмена");
        this.setLayout(new BorderLayout());
        this.add(labelText, BorderLayout.CENTER);
        JPanel panel = new JPanel(new FlowLayout());
        panel.add(buttonOK);
        panel.add(buttonNO);
        panel.add(buttonCancel);
        this.add(panel,BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
