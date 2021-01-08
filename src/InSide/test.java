package InSide;


import javax.swing.*;
import java.io.IOException;
import java.util.Map;

public class test {
    public static void main(String[] args) {

            RoomMap roomMap = new RoomMap("Москва");
//        JFileChooser fileopen = new JFileChooser();
//        int ret = fileopen.showDialog(null, "Открыть файл");
//        try {
//            roomMap.readFromFile(fileopen.getSelectedFile());
//        } catch (IOException e1) {
//            e1.printStackTrace();
//        }
        roomMap.addRoom(1, new Room("RERERE", 10.0,"12345"));
        roomMap.addRoom(2, new Room("WOWQWQ", 20.0,"12345"));
        roomMap.addRoom(3, new Room("TRTRTR", 30.0,"123456"));
        roomMap.addRoom(4, new Room("WOREQW", 10.0,"12345"));
        roomMap.addRoom(5, new Room("WHTR", 20.0,"12345"));
        roomMap.addRoom(6, new Room("GDRTB", 30.0,"123456"));
//
//
//        System.out.println("Общая площади помещений \n");
//        Map<String, Double> temp = roomMap.allArea();
//        for (Map.Entry<String, Double> entry : temp.entrySet()) {
//            System.out.println(entry.getKey() + " == " + entry.getValue());
//        }
        System.out.println("\n Вывод всей таблицы " + roomMap);

        for (Map.Entry<Integer,Room> entry : roomMap.getTable().entrySet()) {
            System.out.println(entry);
        }
//        System.out.println("\n Сортировка");
//
//        roomMap = roomMap.sort();
//        for (Map.Entry<Integer,Room> entry : roomMap.getTable().entrySet()) {
//            System.out.println(entry);
//        }
//
//        System.out.println("\n  Удаление с заданным буквосочетанием в адресе");
//
//        roomMap.delRoom("56");
//        for (Map.Entry<Integer,Room> entry : roomMap.getTable().entrySet()) {
//            System.out.println(entry);
//        }
//        System.out.println("\n Самая большая площадь помещения " + roomMap.largestArea());
//
        System.out.println("\n Фильтрация по полю название фирмы");
        RoomMap roomMap1 = new RoomMap();
        roomMap1 = roomMap.filter("WO");
        for (Map.Entry<Integer,Room> entry : roomMap1.getTable().entrySet()) {
            System.out.println(entry.getKey() +":"+ entry.getValue());
        }
        System.out.println("\n Вывод всей таблицы " + roomMap);

        for (Map.Entry<Integer,Room> entry : roomMap.getTable().entrySet()) {
            System.out.println(entry);
        }

    }

}
