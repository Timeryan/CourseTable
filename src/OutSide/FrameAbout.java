package OutSide;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FrameAbout extends JDialog {
    ImageIcon image = null;
    FrameAbout(String a){
        super();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        this.setSize(500,650);
        this.setLocationRelativeTo(null);
        setLayout( new BorderLayout());

        JButton buttonOK = new JButton("Ок");
        JPanel panelButton = new JPanel(new FlowLayout());
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);
        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        StyleConstants.setBold(attributeSet, true);
        StyleConstants.setForeground(attributeSet, Color.LIGHT_GRAY);
        StyleConstants.setFontSize(attributeSet, 40);
        textPane.setCharacterAttributes(attributeSet, true);
        textPane.setText("Сourse work");
        attributeSet = new SimpleAttributeSet();

        textPane.setCharacterAttributes(attributeSet, true);
        Document doc = textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(),
                    a, attributeSet);
        } catch (BadLocationException e) {
            e.printStackTrace();
        }

        buttonOK.setPreferredSize(new Dimension(100,20));
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                dispose();
            }
        });

        image =  new ImageIcon("src\\Image\\iconAbout.png");
        JLabel labelImage = new JLabel(image);

        panelButton.add(buttonOK);
        add(textPane,BorderLayout.CENTER);
        add(labelImage, BorderLayout.WEST);
        add(panelButton,BorderLayout.SOUTH);
        setVisible(true);


    }
}
