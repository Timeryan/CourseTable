package OutSide;

import InSide.Room;
import InSide.RoomMap;

import javax.imageio.ImageIO;
import javax.management.relation.Relation;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class GUI extends JFrame {
    RoomMap roomMap = new RoomMap();
    RoomMap roomMapFiltred = null;
    JTable table;
    File file;

    JButton buttonIcoAdd;
    JButton buttonIcoDel;
    JButton buttonAdd;
    JButton buttonChange;
    JButton buttonDel;
    JButton buttonDelGroup;
    JButton buttonIcoFilter;
    JButton buttonIcoStatist;

    JTextArea labelTotal1;
    JTextArea labelTotal2;

    JButton buttonSort;
    JButton buttonFilter;
    JTextField textFieldFilter;

    JPanel rightPanel;

    JMenuBar menuBar;
    JMenu fileMenu;
    JMenu editMenu;
    JMenu aboutMenu;

    JMenuItem create;
    JMenuItem open;
    JMenuItem save;
    JMenuItem saveLike;
    JMenuItem close;

    JMenuItem startEdit;
    JMenuItem stopEdit;

    JMenuItem about;
    JMenuItem about1;

    JPanel leftPanel;
    JScrollPane scrollPane;

    JLabel labelTopName;
    JLabel labelDownName;

    JLabel labelNum;
    JLabel labelFirm;
    JLabel labelArea;
    JLabel labelAdres;
    JLabel labelNumOrAdres;
    JTextField editNum;
    JTextField editFirm;
    JTextField editArea;
    JTextField editAdres;

    boolean isOpen = false;

    public GUI() {
        super("Система хранения и обработки данных");
        WindowWelcome windowWelcome = new WindowWelcome();
        try {
            setIconImage(ImageIO.read( new File("src\\Image\\Icon.png" )));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //изменение цвета
        UIManager.put("nimbusBase", new Color(100, 100, 100));
        UIManager.put("nimbusBlueGrey", new Color(255, 255, 255));
        UIManager.put("control", new Color(238, 238, 238));

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        eListener listener = new eListener();


        //верхняя и нижняя надпись


        labelTopName = new JLabel("Данные о служебных помещениях города");
        labelTopName.setBorder(BorderFactory.createEmptyBorder(0,600,5,600));
        labelDownName = new JLabel("Курсовой проект по дисциплине \" Программирование \". СевГУ 2019");
        labelDownName.setBorder(BorderFactory.createEmptyBorder(0,550,5,550));

        //верхнее меню
        menuBar = new JMenuBar();
        fileMenu = new JMenu("Файл");
        editMenu = new JMenu("Редактировать");
        aboutMenu = new JMenu("Справка");

        create = new JMenuItem("Новый");
        create.addActionListener(listener);
        open = new JMenuItem("Открыть");
        open.addActionListener(listener);
        save = new JMenuItem("Сохранить");
        save.addActionListener(listener);
        saveLike = new JMenuItem("Сохранить как...");
        saveLike.addActionListener(listener);
        close = new JMenuItem("Закрыть");
        close.addActionListener(listener);

        startEdit = new JMenuItem("Начать редактирование");
        startEdit.addActionListener(listener);
        stopEdit = new JMenuItem("Закончить редактирование");
        stopEdit.addActionListener(listener);

        about = new JMenuItem("О программе");
        about.addActionListener(listener);
        about1 = new JMenuItem("Описание ИС");
        about1.addActionListener(listener);
        //таблица


        
        //Правая панель добавления
        rightPanel = new JPanel(new FlowLayout());
        rightPanel.setPreferredSize(new Dimension(250,100));
        labelNum = new JLabel(String.format("%10s","Номер: "));
        labelFirm = new JLabel(String.format("%10s","Фирма: "));
        labelArea = new JLabel(String.format("%10s","Площадь: "));
        labelAdres = new JLabel(String.format("%10s","Адрес: "));
        labelNumOrAdres = new JLabel(String.format("%10s","Номер или Адрес: "));
        editNum = new JTextField(20);
        editFirm = new JTextField(20);
        editArea = new JTextField(20);
        editAdres = new JTextField(20);
        buttonAdd = new JButton("Добавить");
        buttonAdd.addActionListener(listener);
        buttonChange = new JButton("Изменить");
        buttonChange.addActionListener(listener);
        buttonDel = new JButton("Удалить");
        buttonDel.addActionListener(listener);
        buttonDelGroup = new JButton("Удалить по адресу");
        buttonDelGroup.addActionListener(listener);

        labelTotal1 = new JTextArea("Общая площадь каждой фирмы");
        labelTotal2 = new JTextArea("Максимальная площадь помещений");

        buttonFilter = new JButton("Отфильтровать по фирмам");
        buttonFilter.addActionListener(listener);
        buttonSort = new JButton("Сортировать");
        buttonSort.addActionListener(listener);
        textFieldFilter = new JTextField(20);



        //левая панель добавления и удаления
        leftPanel = new JPanel(new FlowLayout());
        leftPanel.setPreferredSize(new Dimension(50,100));



        buttonIcoAdd = loadImage("iconAdd.png");
        buttonIcoAdd.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonIcoAdd.setPreferredSize(new Dimension(40,40));
        buttonIcoAdd.addActionListener(listener);


        buttonIcoDel = loadImage("iconDel.png");
        buttonIcoDel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonIcoDel.setPreferredSize(new Dimension(40,40));
        buttonIcoDel.addActionListener(listener);


        buttonIcoFilter = loadImage("iconFilter.png");
        buttonIcoFilter.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonIcoFilter.setPreferredSize(new Dimension(40,40));
        buttonIcoFilter.addActionListener(listener);


        buttonIcoStatist = loadImage("iconStatist.png");
        buttonIcoStatist.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        buttonIcoStatist.setPreferredSize(new Dimension(40,40));
        buttonIcoStatist.addActionListener(listener);



        fileMenu.add(create);
        fileMenu.add(open);
        fileMenu.addSeparator();
        fileMenu.add(save);
        fileMenu.add(saveLike);
        fileMenu.addSeparator();
        fileMenu.add(close);

        editMenu.add(startEdit);
        editMenu.add(stopEdit);

        aboutMenu.add(about);
        aboutMenu.add(about1);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(aboutMenu);


        setJMenuBar(menuBar);
        setVisible(true);

    }

    private void save(){
        if(isOpen) {
        if((file != null)){
            try {
                roomMap.saveInFile(file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }else{

                JFileChooser fileopen = new JFileChooser();
                fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Рассширение курсовой работы (*.cw)", "cw"));
                fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int ret = fileopen.showSaveDialog(null);
                if(!(ret == JFileChooser.CANCEL_OPTION || ret == JFileChooser.ERROR_OPTION)) {
                    try {
                        file = fileopen.getSelectedFile();
                        roomMap.saveInFile(file);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
    private void close(){
        roomMap.getTable().clear();
        this.remove(leftPanel);
        this.remove(rightPanel);
        this.remove(scrollPane);
        this.remove(labelDownName);
        this.remove(labelTopName);
        isOpen = false;
        file = null;
        this.setVisible(false);
        this.setVisible(true);
        setTitle("Система хранения и обработки данных");
    }

    JButton loadImage(String name){
        BufferedImage image = null;
        try {
            image = ImageIO.read( new File("src\\Image\\" + name));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new JButton(new ImageIcon(image.getScaledInstance(30,30,Image.SCALE_DEFAULT)));
    }


    class eListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == buttonIcoAdd){
                add(rightPanel, BorderLayout.EAST);
                rightPanel.removeAll();
                rightPanel.add(labelNum);
                editNum.setText("");
                editAdres.setText("");
                editArea.setText("");
                editFirm.setText("");
                rightPanel.add(editNum);
                rightPanel.add(labelFirm);
                rightPanel.add(editFirm);
                rightPanel.add(labelArea);
                rightPanel.add(editArea);
                rightPanel.add(labelAdres);
                rightPanel.add(editAdres);

                rightPanel.add(buttonAdd);
                rightPanel.add(buttonChange);

                rightPanel.updateUI();
            }
            if(e.getSource() == buttonIcoDel){
                add(rightPanel, BorderLayout.EAST);
                rightPanel.removeAll();
                editNum.setText("");
                rightPanel.add(labelNumOrAdres);
                rightPanel.add(editNum);

                rightPanel.add(buttonDel);
                rightPanel.add(buttonDelGroup);

                rightPanel.updateUI();
            }
            if(e.getSource() == buttonIcoStatist){
                add(rightPanel, BorderLayout.EAST);
                Map<String, Double> temp = null;
                rightPanel.removeAll();
                if(roomMapFiltred == null && roomMap == null){
                    temp = null;
                }
                if(roomMap != null){
                    temp = roomMap.allArea();
                }
                if(roomMapFiltred != null){
                    temp = roomMapFiltred.allArea();
                }
                labelTotal1.setText("Общая площадь каждой фирмы \n");
                for (Map.Entry<String, Double> entry : temp.entrySet()) {
                labelTotal1.setText(String.format("%s %s = %s %n", labelTotal1.getText(), entry.getKey(),entry.getValue()));
                }
                labelTotal1.setEditable(false);
                JScrollPane scrollPane1 = new  JScrollPane(labelTotal1);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                scrollPane1.setPreferredSize(new Dimension(225,400));
                rightPanel.add(scrollPane1);

                labelTotal2.setEditable(false);
                if(roomMapFiltred == null && roomMap == null){
                    labelTotal2.setText("Максимальная площадь помещений: \n");
                }
                if(roomMap != null){
                    labelTotal2.setText("Максимальная площадь помещений: \n" + roomMap.largestArea());
                }
                if(roomMapFiltred != null){
                    labelTotal2.setText("Максимальная площадь помещений: \n" + roomMapFiltred.largestArea());
                }
                rightPanel.add(labelTotal2);
                rightPanel.updateUI();
            }
            if(e.getSource() == buttonIcoFilter){
                add(rightPanel, BorderLayout.EAST);
                rightPanel.removeAll();
                rightPanel.add(textFieldFilter);
                rightPanel.add(buttonFilter);
                rightPanel.add(buttonSort);
                rightPanel.updateUI();
            }

            if(e.getSource() == create){
                if(!isOpen){
                    TableModel tableModel = new TableModel(roomMap);
                    table = new JTable(tableModel);
                    table.setGridColor(new Color(180, 180, 180));
                    table.setShowGrid(true);
                    scrollPane = new JScrollPane(table);
                    scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                    setLayout(new BorderLayout());
                add(labelTopName, BorderLayout.NORTH);
                add(scrollPane, BorderLayout.CENTER);

                add(labelDownName, BorderLayout.SOUTH);
                setVisible(true);
                isOpen = true;
                }else{


                }
                }
                if(e.getSource() == startEdit){
                    if (isOpen){
                        table.setModel(new TableModel(roomMap));
                        table.validate();
                    add(leftPanel,BorderLayout.WEST);
                    leftPanel.add(buttonIcoAdd);
                    leftPanel.add(buttonIcoDel);
                    leftPanel.remove(buttonIcoFilter);
                    leftPanel.remove(buttonIcoStatist);
                    rightPanel.removeAll();
                    rightPanel.updateUI();
                    leftPanel.updateUI();
                    }
                }
                if(e.getSource() == stopEdit){
                    if (isOpen){
                    add(leftPanel,BorderLayout.WEST);
                    leftPanel.add(buttonIcoFilter);
                    leftPanel.add(buttonIcoStatist);
                    leftPanel.remove(buttonIcoAdd);
                    leftPanel.remove(buttonIcoDel);
                    rightPanel.removeAll();
                    rightPanel.updateUI();
                    leftPanel.updateUI();
                    }
                }
                if(e.getSource() == open){
                    if(!isOpen) {
                        JFileChooser fileopen = new JFileChooser();
                        fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Рассширение курсовой работы (*.cw)", "cw"));
                        fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Текстовый файл(*.txt)", "txt"));
                        fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        int ret = fileopen.showOpenDialog(null);
                        if(!(ret == JFileChooser.CANCEL_OPTION || ret == JFileChooser.ERROR_OPTION)) {
                            try {
                                file = fileopen.getSelectedFile();
                                setTitle(file.getAbsolutePath());
                                roomMap.readFromFile(file);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }

                            TableModel tableModel = new TableModel(roomMap);
                            table = new JTable(tableModel);
                            table.setGridColor(new Color(180, 180, 180));
                            table.setShowGrid(true);
                            scrollPane = new JScrollPane(table);
                            scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

                            setLayout(new BorderLayout());
                            add(labelTopName, BorderLayout.NORTH);
                            add(scrollPane, BorderLayout.CENTER);

                            add(labelDownName, BorderLayout.SOUTH);
                            setVisible(true);
                            isOpen = true;
                        }

                    }
                }
                if(e.getSource() == saveLike){
                    if(isOpen) {
                        JFileChooser fileopen = new JFileChooser();
                        fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Рассширение курсовой работы (*.cw)", "cw"));
                        fileopen.addChoosableFileFilter(new FileNameExtensionFilter("Текстовый файл(*.txt)", "txt"));
                        fileopen.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        int ret = fileopen.showSaveDialog(null);
                        if(!(ret == JFileChooser.CANCEL_OPTION) || !(ret == JFileChooser.ERROR_OPTION)) {
                            try {
                                file = fileopen.getSelectedFile();
                                setTitle(file.getAbsolutePath());
                                roomMap.saveInFile(file);
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
                if(e.getSource() == save){
                    save();
                }
                if(e.getSource() == buttonAdd){
                    try {
                       roomMap.randomRoom(100);
                        double area;
                        String firm;
                        String adres;
                        try{
                            area = Double.valueOf(editArea.getText());
                        }catch (Exception ex){
                            area = 0;
                        }
                        if(editFirm.getText().equals("")){
                            firm = "Отсутствует";
                        }else{
                            firm = editFirm.getText();
                        }
                        if(editAdres.getText().equals("")){
                            adres = "Отсутствует";
                        }else{
                            adres = editAdres.getText();
                        }
                        roomMap.addRoom(Integer.valueOf(editNum.getText()), new Room(firm, area, adres));
                        table.setModel(new TableModel(roomMap));
                        table.validate();
                    }catch (Exception ex){
                        System.out.println("Ошибка добавления");
                    }
                }
                if(e.getSource() == close) {
                    if (isOpen) {
                        Dialog dialog = new Dialog();
                        dialog.buttonOK.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                save();
                                close();
                                dialog.dispose();
                            }
                        });
                        dialog.buttonNO.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                close();
                                dialog.dispose();

                            }
                        });
                        dialog.buttonCancel.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent actionEvent) {
                                dialog.dispose();
                            }
                        });
                    }
                }
                if(e.getSource() == buttonChange){
                    if(roomMap.containRoom(Integer.valueOf(editNum.getText()))){
                    try {
                    roomMap.delRoom(Integer.valueOf(editNum.getText()));
                        double area;
                        String firm;
                        String adres;
                        try{
                            area = Double.valueOf(editArea.getText());
                        }catch (Exception ex){
                            area = 0;
                        }
                        if(editFirm.getText().equals("")){
                            firm = "Отсутствует";
                        }else{
                            firm = editFirm.getText();
                        }
                        if(editAdres.getText().equals("")){
                            adres = "Отсутствует";
                        }else{
                            adres = editAdres.getText();
                        }
                        roomMap.addRoom(Integer.valueOf(editNum.getText()), new Room(firm, area, adres));
                        table.setModel(new TableModel(roomMap));
                        table.validate();
                    }catch (Exception ex){
                        System.out.println("Ошибка изменения");
                    }
                    }
                }
                if(e.getSource() == buttonDel){
                    try{
                    if(roomMap.containRoom(Integer.valueOf(editNum.getText()))){
                    roomMap.delRoom(Integer.valueOf(editNum.getText()));
                    table.setModel(new TableModel(roomMap));
                    table.validate();
                    }
                    }catch (Exception e1){
                        System.out.println("Неудачное удаление");
                    }
                }
                if(e.getSource() == buttonDelGroup){
                    try{
                        roomMap.delRoom(editNum.getText());
                        table.setModel(new TableModel(roomMap));
                        table.validate();
                    }catch (Exception e1){
                        System.out.println("Неудачное удаление");
                    }
                }
                if(e.getSource() == buttonSort){
                    if(roomMapFiltred == null){
                        roomMap = roomMap.sort();
                        table.setModel(new TableModel(roomMap));
                        table.validate();
                    }else{
                        roomMapFiltred = roomMapFiltred.sort();
                        table.setModel(new TableModel(roomMapFiltred));
                        table.validate();
                    }
                }
                if(e.getSource() == buttonFilter){
                    if(textFieldFilter.getText() =="")
                        table.setModel(new TableModel(roomMap));
                        table.validate();
                    {
                        roomMapFiltred = roomMap.filter(textFieldFilter.getText());
                        table.setModel(new TableModel(roomMapFiltred));
                        table.validate();
                    }
                }
                if(e.getSource() == about){
                    FrameAbout frameAbout = new FrameAbout("\nКурсовой проект по \"Программированию\". СевГУ -2019 \n \"Course Work\" это система хранения и обработки данных\n Программист: Бадертдинов Тимерьян");
                }
                if(e.getSource() == about1){
                    FrameAbout frameAbout = new FrameAbout("\nОписание ИС\n\n Вариант 47.\n\n Таблица содержит данные о служебных помещениях города.\n\n Структура записи:\n 1) Кадастровый номер помещения (ключевое поле);\n 2) Название фирмы-владельца;\n 3) Площадь;\n 4) Адрес.\n\n Возможности:\n 1) Подсчитать общую площадь помещений для каждой фирмы.\n 2) Подсчитать максимальную площадь помещения для всей таблицы.\n 3) Удалить данные по номеру.\n 4) Удалить данные по буквосочетанию содержащимся в адресе.\n 5)Сортировка по возрастанию в поле название фирмы, в рамках одной фирмы\n по убыванию в поле площадь помещения.\n 6)Фильтрация по полю название фирмы.\n");
                }
        }
    }

}
