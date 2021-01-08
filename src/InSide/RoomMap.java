package InSide;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class RoomMap {
    private String city;
    private Map<Integer, Room> table;

    public RoomMap() {
        city = "";
        table = new LinkedHashMap<>();
    }

    public RoomMap(String city) {
        this.city = city;
        table = new LinkedHashMap<>();
    }
    public RoomMap(String city, Map<Integer, Room> table){
        this.city = city;
        this.table = table;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTable(Map<Integer, Room> table) {
        this.table = table;
    }

    public Map<Integer, Room> getTable() {
        return table;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return String.format("%1s \n", city);
    }

    public boolean addRoom(int id, Room room) {
        try {

        if (table.containsKey(id)) return false;
        table.put(id, room);

        }catch (Exception ex){

        }
        return true;
    }

    public boolean delRoom(int id) {
        if (table.containsKey(id)) {
            table.remove(id);
            return true;
        }
        return false;
    }


    //удаление если в адресе есть это буквосочетание
    public boolean delRoom(String a) {
        int i = 0;
        ArrayList<Integer> toRemove = new ArrayList<>();
        for (Map.Entry<Integer, Room> entry : table.entrySet()) {
            if (entry.getValue().getAddress().contains(a)) {
                toRemove.add(entry.getKey());
                i++;
            }
        }

        for (Integer integer : toRemove) {
            table.remove(integer);
        }
        if (i == 0) return false;
        return true;
    }

    //сумма площади помещений каждой фирмы
    public Map<String, Double> allArea() {
        Map<String, Double> retur = new LinkedHashMap<String, Double>();
        Map<Integer, Room> temp = table;
        for (Map.Entry<Integer, Room> entry : temp.entrySet()) {
            String firm = entry.getValue().getFirmOwner();
            if (!retur.containsKey(firm)) {
                retur.put(firm, entry.getValue().getArea());
            } else {
                retur.put(firm, retur.get(firm) + entry.getValue().getArea());
            }
        }
        return retur;
    }

    public double largestArea() {
        double retur = 0;
        for (Map.Entry<Integer, Room> entry : table.entrySet()) {
        if(retur < entry.getValue().getArea())
            retur = entry.getValue().getArea();
        }
        return  retur;
    }

    public RoomMap sort(){
        LinkedHashMap<Integer, Room> sorted = table.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(new CompFirmOwnerUpAreaDown()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        return new RoomMap(city, sorted);
}

    //фильтрация по полю название владельца фирмы
    public RoomMap filter(String a){
//        RoomMap temp = new RoomMap(city, table);
//        for (Map.Entry<Integer, Room> entry : temp.getTable().entrySet()) {
//            if (!entry.getValue().getFirmOwner().contains(a)) {
//                temp.delRoom(entry.getKey());
//            }
//        }
//     return temp;

        RoomMap temp = new RoomMap();
        int i = 0;
        ArrayList<Integer> toAdd = new ArrayList<>();
        for (Map.Entry<Integer, Room> entry : table.entrySet()) {
            if (entry.getValue().getFirmOwner().contains(a)) {
                toAdd.add(entry.getKey());
                i++;
            }
        }


        for (Integer integer : toAdd) {
            temp.addRoom(integer, table.get(integer));
        }
     return temp;
    }

    public void readFromFile(File file) throws IOException {
        FileReader fileReader = new FileReader(file);
        Scanner scanner = new Scanner(fileReader);

        while (scanner.hasNextLine()) {
            String[] temp = scanner.nextLine().split(",");
            addRoom(Integer.valueOf(temp[0]), new Room(temp[1],Double.valueOf(temp[2]),temp[3]));

        }

        fileReader.close();
    }
    public void saveInFile(File file) throws IOException {
        FileWriter fileWriter;
        if(!file.getAbsolutePath().contains(".cw")){
        fileWriter = new FileWriter(file.getAbsolutePath() + ".cw");
        } else {
            fileWriter = new FileWriter(file);
        }
        ArrayList<Integer> arrayList = new ArrayList<>(table.keySet());
        for ( Object i:arrayList) {
            fileWriter.write(String.format( "%s,%s,%s,%s\n",i, table.get(i).getFirmOwner(), table.get(i).getArea(), table.get(i).getAddress()));
        }
        fileWriter.flush();
        fileWriter.close();
    }
    public boolean containRoom(int id){
        if(table.containsKey(id))return true;
        return false;
    }
    public void randomRoom(int num){
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            addRoom(random.nextInt(100), new Room(String.valueOf(random.nextInt(100)),random.nextDouble(),String.valueOf(random.nextInt(100))));
        }
    }
}
