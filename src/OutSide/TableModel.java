package OutSide;

import InSide.Room;
import InSide.RoomMap;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;



public class TableModel extends AbstractTableModel {

    RoomMap roomMap;
    ArrayList <Integer> kList;

    public TableModel(RoomMap roomMap) {
        super();
        this.roomMap = roomMap;
        kList = new ArrayList(roomMap.getTable().keySet());
    }
    @Override
    public int getRowCount() {
        return roomMap.getTable().size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

        @Override
        public String getColumnName(int c) {
            String result = "";
            switch (c) {
                case 0:
                    result = "№";
                    break;
                case 1:
                    result = "Фирма";
                    break;
                case 2:
                    result = "Площадь";
                    break;
                case 3:
                    result = "Адрес";
                    break;
            }
            return result;  }


    @Override
    public Object getValueAt(int r, int c) {

        Integer key = kList.get(r);
        Room val = roomMap.getTable().get(key);

        switch (c) {
            case 0:
                return key;
            case 1:
                return val.getFirmOwner();
            case 2:
                return val.getArea();
            case 3:
                return val.getAddress();
            default:
                return "";
        }
    }
}
