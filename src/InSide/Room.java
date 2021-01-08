package InSide;

public class Room implements  Comparable<Room>{
    private String firmOwner;
    private double area;
    private String address;

     public Room(){
        firmOwner = "";
        area = 0;
        address = "";
    }

    public Room( String firmOwner, double area, String address){
         this.firmOwner = firmOwner;
         this.area = area;
         this.address = address;
    }

    public void setAddress(String address) { this.address = address; }
    public void setArea(double area) { this.area = area; }
    public void setFirmOwner(String firmOwner) { this.firmOwner = firmOwner; }

    public double getArea() { return area; }
    public String getAddress() { return address; }
    public String getFirmOwner() { return firmOwner; }

    @Override
    public String toString() {
        return String.format("%1s  %1f  %1s",firmOwner,area,address);
    }

    @Override
    public boolean equals (Object ob) {
        if (ob == this) return true; // ссылки равны – один и тот же объект
        if (ob == null) return false; //в метод передана null-ссылка
        if (getClass() != ob.getClass())return false; //объекты разных классов
        Room room = (Room) ob;
        return (firmOwner.equals(room.firmOwner)) &&
                (area == room.area)&&
                (address.equals(room.address));
    }

    @Override
    public int hashCode(){
        return  11 * firmOwner.hashCode()+
                13 * (new Double(area)).hashCode()+
                17 * address.hashCode();
    }

    public int compareTo(Room room){
        if(firmOwner.compareTo(room.firmOwner)<0) return -1;
        if(firmOwner.compareTo(room.firmOwner)>0) return 1;
        if(area < room.area) return -1;
        if(area > room.area) return 1;
        if(address.compareTo(room.address)< 0) return -1;
        if(address.compareTo(room.address)> 0) return 1;
        return 0;
    }
}
