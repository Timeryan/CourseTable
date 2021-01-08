package InSide;

import java.util.Comparator;

public class CompFirmOwnerUpAreaDown implements Comparator<Room> {

    public int compare(Room room1, Room room2) {
        String subj1 = room1.getFirmOwner();
        String subj2 = room2.getFirmOwner();
        double sem1 = room1.getArea();
        double sem2 = room2.getArea();
        if (subj1.compareTo(subj2) < 0) return -1;
        if (subj1.compareTo(subj2) > 0) return 1;
        if (sem1 > sem2) return -1;
        if (sem1 < sem2) return 1;
        return 0;
    }
}
