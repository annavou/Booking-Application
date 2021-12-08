import java.util.ArrayList;

public class Hotel_room {

    String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private int capacity ;
    private double price ;
    private boolean breakfast, wifi, ac, parking, cleaning_services;
    private  int sqmeter;
    ArrayList<Reservations> hotelroomreservations = new ArrayList<>();
    ArrayList<Reservations> hotelroomcancellations= new ArrayList<>();

    Hotel_room(){}

    Hotel_room(int acapacity,int aprice,boolean abreakfast,boolean awifi, boolean aac, boolean aparkin, boolean acleaning_services,int asqmeter,String atype){
        capacity = acapacity ;
        price = aprice;
        breakfast = abreakfast ;
        wifi = awifi ;
        ac = aac ;
        parking = aparkin ;
        cleaning_services = acleaning_services;
        sqmeter = asqmeter;

    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isBreakfast() {
        return breakfast;
    }

    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isAc() {
        return ac;
    }

    public void setAc(boolean ac) {
        this.ac = ac;
    }

    public boolean isParking() {
        return parking;
    }

    public void setParking(boolean parking) {
        this.parking = parking;
    }

    public boolean isCleaning_services() {
        return cleaning_services;
    }

    public void setCleaning_services(boolean cleaning_services) {
        this.cleaning_services = cleaning_services;
    }

    public int getSqmeter() {
        return sqmeter;
    }

    public void setSqmeter(int sqmeter) {
        this.sqmeter = sqmeter;
    }

    public void display() {
        System.out.println("A room for "+capacity+" at "+price+" $ and is "+sqmeter+" sqmtr");
    }
}