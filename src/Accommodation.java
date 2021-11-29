import java.util.ArrayList;

public class Accommodation {


    private String name;
    private String location;
    private double price;
    private  int sqmeter;
    private int stars;
    private String type;
    private  int rooms;
    private int capacity;
    ArrayList<Reservations> reservations = new ArrayList<>();
    ArrayList<Reservations>cancellations= new ArrayList<>();


    private boolean breakfast, wifi, ac, parking, cleaning_services;


    public Accommodation() {
        name = null;
        location = null;
        price = 0;
        sqmeter = 0;
        stars = 0;
        type = null;
        rooms = 0;
        capacity = 0;

        breakfast = false;
        wifi = false;
        ac = false;
        parking = false;
        cleaning_services = false;
    }

    public Accommodation(String aname, String alocation, double aprice, int asqmeter, int astars, String atype, int arooms, int acapacity, boolean abreakfast,
                         boolean awifi, boolean aac, boolean aparkin, boolean acleaning_services) {
        name = aname;
        location = alocation;
        price = aprice;
        sqmeter = asqmeter;
        stars = astars;
        type = atype;
        rooms = arooms;
        capacity = acapacity;

        breakfast = abreakfast;
        wifi = awifi;
        ac = aac;
        parking = aparkin;
        cleaning_services = acleaning_services;
    }


    public void show_accommodation() {
        System.out.println("Name : " + name + "\n" + "Location : " + location + "\n");

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSqmeter() {
        return sqmeter;
    }

    public void setSqmeter(int sqmeter) {
        this.sqmeter = sqmeter;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
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

}
