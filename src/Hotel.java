import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
/**
 *Κλάση οποία αναπαριστά ένα ξενοδοχείο.
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
        this.Rooms = new ArrayList<Hotel_room>(hotel.Rooms);
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
     *Μέθοδος που εμφανίζει μορφοποιημένο το ξενοδοχείο με τα χαρακτηριστικά του
     */
    public void show_Hotel() {
        System.out.println("Το Ξεναδοχείο '"+ name +"' ,Στην "+ location +", έχει " + stars +" αστέρια, και έχει τα Δωμάτια:");
        for (Hotel_room room : Rooms){
            room.display();
        }
    }


    /**
     * Μέθοδος με την οποία προστίθεται ένα καινούργιο δωμάτιο
     */
    public void add_Hotel_room() {

        String aname ;
        String aprice;
        String asqmeter;
        String acapacity;
        boolean abreakfast;
        boolean awifi;
        boolean aac;
        boolean aparkin;
        boolean acleaning_services;

        System.out.println("Πόσα δωμάτια θα θέλατε να προσθέσετε? ");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        int loops = Integer.parseInt(next_string) ;
        for (int i = 0; i < loops; i++) {
            System.out.println("Δωμάτιο "+(i+1)+":");

            System.out.println("Ονομα");
            aname = sc.next();

            System.out.println("Τιμή");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
            aprice = next_string;

            System.out.println("Τετραγωνικά");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρα Τετραγωνικά");
            asqmeter = next_string;

            System.out.println("Χωριτικότητα");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Χωριτικότητα");
            acapacity = next_string;

            System.out.println("Air conditioner (0-OXI/1-NAI) ");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
            aac = next_string.equals("1");

            System.out.println("Πρωινό (0-OXI/1-NAI)");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
            abreakfast = next_string.equals("1");

            System.out.println("Parking (0-OXI/1-NAI)");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
            aparkin = next_string.equals("1");


            System.out.println("Wifi (0-OXI/1-NAI)");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
            awifi = next_string.equals("1");

            System.out.println("Υπηρεσία καθαρισμού (0-OXI/1-NAI)");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
            acleaning_services = next_string.equals("1");

            Hotel_room temp = new Hotel_room(aname, acapacity, aprice, abreakfast, awifi, aac, aparkin, acleaning_services, asqmeter);
            this.Rooms.add(temp);
        }
    }


    /**
     *Μέθοδος με την οποία επεξεργαζόμαστε ενα υπάρχων δωμάτια στο ξενοδοχείο
     */
    public void edit_Hotel_room() {
        boolean flag = true;

        room = search_Hotel_room(Rooms);

        System.out.println("Τι θα ήθελες να αλλάξεις");
        next_string = sc.next();

        while (flag) {
            flag = false;
            switch (next_string) {
                case "ονομα" -> {
                    System.out.println("Δώσε το καινούργιο ονομα:");
                    next_string = sc.next();
                    room.setName(next_string);
                    room.display();
                }
                case "τιμη" -> {
                    System.out.println("Δώσε την καινούργια τιμή:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη τιμή");
                    room.setPrice(next_string);
                    room.display();
                }
                case "τετραγωνικά" -> {
                    System.out.println("Δώσε τα καινούργια τετραγωνικά:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρα τετραγωνικά");
                    room.setSqmeter(next_string);
                    room.display();
                }
                case "χωρητικοτητα" -> {
                    System.out.println("Δώσε τη καινούργια χωρητικότητα σε ατομα: (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη χωρητικοτητα ");
                    room.setCapacity(next_string);
                    room.display();
                }
                case "ac" -> {
                    System.out.println("Θα παρέχει το κατάλυμα ac? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    room.setAc(next_bool);
                    room.display();
                }
                case "πρωίνο" -> {
                    System.out.println("Θα παρέχει το κατάλυμα πρωινό? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    room.setBreakfast(next_bool);
                    room.display();
                }
                case "wifi" -> {
                    System.out.println("Θα παρέχει το κατάλυμα wifi? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    room.setWifi(next_bool);
                    room.display();
                }
                case "parking" -> {
                    System.out.println("Θα παρέχει το κατάλυμα parking? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    room.setParking(next_bool);
                    room.display();
                }
                case "υπηρεσια καθαρισμου" -> {
                    System.out.println("Θα παρέχει το κατάλυμα υπηρεσία καθαρισμού? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    room.setCleaning_services(next_bool);
                    room.display();
                }
                case "εξοδος" -> {}
                default -> {
                    System.out.println("Δεν υπάρχει τετοια κατηγοριά");
                    System.out.println("Θέλετε να ξαναδοκιμάσετε η οχι (Κατηγορια/οχι)");
                    next_string = sc.next();
                    flag = !next_string.equals("οχι");
                }
            }
        }

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
        System.out.println("Ποιό θέλετε? (δωστε το αντίστοιχο νουμερο)");
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
