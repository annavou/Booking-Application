import java.io.Serial;
import java.util.ArrayList;
/**
 *Κλάση η οποία αναπαριστά ένα ξενοδοχείο.
 *Περιέχει string με το όνομα την τοποθεσία και τα αστέρια του ξενοδοχείου καθώς και μία λίστα με τα δωμάτια τα οποία περιέχει.
 *Έχει βοηθητικές μεταβλητές τύπου string boolean scanner pattern και checker οι οποίες είναι για προσωρινή αποθήκευση δεδομένων
 */
public class Hotel implements java.io.Serializable {

    private String name;
    private String location;
    private String stars;
    ArrayList<Hotel_room> Rooms = new ArrayList<>();


    @Serial
    private static final long serialVersionUID = 6529685040067757690L;

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Hotel() {
    }


    /**
     * Κατασκευαστής ο οποίος δέχεται ως όρισμα το όνομα την τοποθεσία και τα αστέρια
     *
     * @param aname     όνομα
     * @param alocation τοποθεσία
     * @param astars    αστέρια
     */
    Hotel(String aname, String alocation, String astars) {
        name = aname;
        location = alocation;
        stars = astars;
    }



    /**
     * Μέθοδος που επιστρέφει το όνομα
     *
     * @return όνομα
     */
    public String getName() {
        return name;
    }


    /**
     * μέθοδος αλλαγής ονόματος
     *
     * @param name όνομα
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * Μέθοδος που επιστρέφει την τοποθεσία
     *
     * @return τοποθεσία
     */
    public String getLocation() {
        return location;
    }


    /**
     * Μέθοδος αλλαγής τοποθεσίας
     *
     * @param location τοποθεσία
     */
    public void setLocation(String location) {
        this.location = location;
    }


    /**
     * Μέθοδος που επιστρέφει τα αστέρια
     *
     * @return αστέρια
     */
    public String getStars() {
        return stars;
    }


    /**
     * Μέθοδος αλλαγής αστεριών
     *
     * @param stars αστέρια
     */
    public void setStars(String stars) {
        this.stars = stars;
    }
}

