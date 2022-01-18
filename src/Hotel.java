import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *Κλάση η οποία αναπαριστά ένα ξενοδοχείο.
 *Περιέχει string με το όνομα την τοποθεσία και τα αστέρια του ξενοδοχείου καθώς και μία λίστα με τα δωμάτια τα οποία περιέχει.
 *Έχει βοηθητικές μεταβλητές τύπου string boolean scanner pattern και checker οι οποίες είναι για προσωρινή αποθήκευση δεδομένων
 */
public class Hotel {

    private String name ;
    private String location ;
    private String stars ;
    ArrayList<Hotel_room> Rooms = new ArrayList<>();


    String next_string;
    boolean next_bool;
    Scanner sc = new Scanner(System.in);
    Pattern p ;
    Checker ch = new Checker();

    Hotel_room room ;

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Hotel(){}


    /**
     *Κατασκευαστής ο οποίος δέχεται ως όρισμα το όνομα την τοποθεσία και τα αστέρια
     * @param aname όνομα
     * @param alocation τοποθεσία
     * @param astars αστέρια
     */
    Hotel(String aname,String alocation,String astars){
        name = aname;
        location = alocation ;
        stars = astars ;
    }


    /**
     * Κατασκευαστής ο οποίος δέχεται ως όρισμα ένα αντικείμενο της ίδιας κλάσης και το αντιγράφει
     * @param hotel το ξεναδοχείο προς αντιγραφή
     */
    public Hotel(Hotel hotel) {
        this.name = hotel.name;
        this.location = hotel.location;
        this.stars = hotel.stars;
        this.Rooms = new ArrayList<>(hotel.Rooms);
    }


    /**
     * Μέθοδος που επιστρέφει το όνομα
     * @return όνομα
     */
    public String getName() {
        return name;
    }


    /**
     *  μέθοδος αλλαγής ονόματος
     * @param name όνομα
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Μέθοδος που επιστρέφει την τοποθεσία
     * @return τοποθεσία
     */
    public String getLocation() {
        return location;
    }


    /**
     * Μέθοδος αλλαγής τοποθεσίας
     * @param location τοποθεσία
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Μέθοδος που επιστρέφει τα αστέρια
     * @return αστέρια
     */
    public String getStars() {
        return stars;
    }


    /**
     *Μέθοδος αλλαγής αστεριών
     * @param  stars αστέρια
     */
    public void setStars(String stars) {
        this.stars = stars;
    }



    /**
     *Μέθοδος με την οποία διαλέγουμε ένα δωμάτιο ξενοδοχείου από μία λίστα με δωμάτια
     * @param Rooms Λίστα με δωμάτια
     * @return το επιλεγμένο δωμάτιο
     */
    public Hotel_room search_Hotel_room(ArrayList<Hotel_room> Rooms){
        Hotel_room room;
        for (int i = 0; i < Rooms.size(); i++) {
            System.out.println((i + 1) + ") Δωμάτιο : " + Rooms.get(i).getName());
        }
        System.out.println("Ποιό θέλετε? (Δώστε το αντίστοιχο νουμερο)");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        if( Integer.parseInt(next_string) > 0 && Integer.parseInt(next_string) <= Rooms.size()){
            room = Rooms.get(Integer.parseInt(next_string) -1);
            return room;
        }
        else {
            System.out.println("O αριθμός αυτός δεν αντιστοιχεί σε Δωμάτιο");
            return null;
        }
    }
}