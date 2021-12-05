import java.util.ArrayList;
//ΕΤΟΙΜΟ
public class Hotel {

    private String name ;
    private String location ;
    private int starts ;
    ArrayList<Hotel_room> Rooms = new ArrayList<>();
    Hotel_room room ;

    Hotel(){}

    Hotel(String aname,String alocation,int astars){
        name = aname;
        location = alocation ;
        starts = astars ;
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

    public int getStarts() {
        return starts;
    }

    public void setStarts(int starts) {
        this.starts = starts;
    }

    public void show_Hotel() {
        System.out.println("The hotel "+name+" is located in "+ location+" and has: " );
        for (Hotel_room room : Rooms){
            room.display();
        }
    }
}
