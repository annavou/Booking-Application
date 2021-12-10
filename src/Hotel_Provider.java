/**
 * Κλάση η οποία αναπαριστά έναν πάροχο ξενοδοχείου.
 * Κληρονόμοι από την κλάση person τα βασικά χαρακτηριστικά.
 * Ακόμα έχει μία λίστα με ξενοδοχεία τα οποία είναι τα ξενοδοχεία τα οποία προσφέρει
 */

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;

public class Hotel_Provider extends  Person {

    ArrayList<Hotel> Hotels = new ArrayList<>();

    Hotel hotel;


    /**
     * Ο κενός κατασκευαστής
     */
    Hotel_Provider() {

    }


    /**
     * Κατασκευαστής ο οποίος δέχεται ως όρισμα τα πεδία του person και τα περνάει σε αυτόν
     */
    Hotel_Provider(String aname, String ahome_ground, String aphone_number, String aemail) {
        super(aname, ahome_ground, aphone_number, aemail);
    }



    /**
     * Μέθοδος η οποία εμφανίζει όλα τα ξενοδοχεία
     */
    public void Hotels_Display_All() {
        boolean flag = true;
        for (Hotel h : Hotels) {
            h.show_Hotel();
            flag = false;
        }
        if (flag) {
            System.out.println("Δεν έχετε καταχωριμένα Ξεναδοχεία");
        }
    }


    /**
     * Μέθοδος η οποία εμφανίζει ένα συγκεκριμένο ξενοδοχείο
     */
    public void Hotels_Display() {
        System.out.println("Ποίο Ξεναδοχείο θα θέλατε να δείτε?");

        hotel = search_hot(Hotels);
        if (hotel == null) {
            return;
        }

        hotel.show_Hotel();

    }


    /**
     * Μέθοδος η οποία προσθέτει ένα καινούργιο ξενοδοχείο στη λίστα
     */
    public void Hotel_add() {
        String aname;
        String alocation;
        String astars;

        System.out.println("Όνομα");
        aname = sc.next();

        System.out.println("Τοποθεσία");
        alocation = sc.next();

        System.out.println("Αστέρια");
        next_string = sc.next();
        p = Pattern.compile("[0-5]");
        next_string = ch.validstring(next_string, p, "Μη εγκυρα Αστέρια");
        astars = next_string;

        Hotel temp = new Hotel(aname, alocation, astars);
        Hotels.add(temp);

        System.out.println("Θα θέλατε να προσθέσετε και δωμάτιο/α (0-ΟΧΙ/1-ΝΑΙ)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
        if (next_string.equals("1")) {
            temp.add_Hotel_room();
        }
        temp.show_Hotel();
    }


    /**
     * Μέθοδος η οποία φέρει ένα ξενοδοχείο από τη λίστα
     */
    public void Hotel_delete() {
        next_string = sc.next();
        hotel = search_hot(Hotels);
        if (hotel == null) {
            return;
        }
        Hotels.remove(hotel);
    }


    /**
     * Μέθοδος επεξεργασίας ενός ξενοδοχείου
     */
    public void Hotel_edit() {
        boolean flag = true;
        hotel = search_hot(Hotels);

        if (hotel == null) {
            return;
        }

        System.out.println("Τι θα ήθελες να αλλάξεις; (ονομα/τοποθεσια/αστεργια/εξοδος/δωματια)");
        next_string = sc.next();

        while (flag) {
            flag = false;
            switch (next_string) {
                case "ονομα" -> {
                    System.out.println("Δώσε το καινούργιο όνομα:");
                    next_string = sc.next();
                    hotel.setName(next_string);
                    hotel.show_Hotel();
                }
                case "τοποθεσια" -> {
                    System.out.println("Δώσε την καινούργια τοποθεσία:");
                    next_string = sc.next();
                    p = Pattern.compile("[^\\w]&&[^[0-9]]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη τοποθεσία");
                    hotel.setLocation(next_string);
                    hotel.show_Hotel();
                }
                case "αστεργια" -> {
                    System.out.println("Δώσε τα καινούργια αστέργια");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρα αστέρια");
                    hotel.setStars(next_string);
                    hotel.show_Hotel();

                }
                case "δωματιο" -> {
                    boolean flag1 = true;
                    System.out.println("Θέλετε να προσθέσετε καινούργιο/α δωμάτιο/α η να επεξεργαστείτε ενα ηδη υπάρχων? (προσθηκη,επεξεργασια,εξοδος)");
                    next_string = sc.next();

                    while (flag1) {
                        flag1 = false;
                        switch (next_string) {
                            case "Προσθήκη" -> hotel.add_Hotel_room();
                            case "Επεξεργασία" -> hotel.edit_Hotel_room();
                            default -> {
                                System.out.println("Δεν υπάρχει τέτοια κατηγοριά");
                                System.out.println("Θέλετε να ξαναδοκιμάσετε η οχι (Κατηγορια/οχι)");
                                next_string = sc.next();
                                flag1 = !next_string.equals("οχι");
                            }
                        }
                    }
                }
                case "εξοδος" -> {
                }
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
     * Μέθοδος προβολής συνολικών κρατήσεων σε όλα τα ξενοδοχεία
     */
    public void sum_resv() {
        for (Hotel hotel : Hotels) {
            System.out.println("Το Ξεναδοχείο " + hotel.getName() + " είναι κλεισμένο για : ");
            for (Hotel_room room : hotel.Rooms) {
                for (Reservations resv : room.hotelroomreservations) {
                    resv.show();
                }
            }
        }
    }


    /**
     * Μέθοδος προβολής συνολικών ακυρώσεων σε όλα τα ξενοδοχεία
     */
    public void sum_resv_cancel() {
        for (Hotel hotel : Hotels) {
            System.out.println("Το Ξεναδοχείο " + hotel.getName() + " είναι ακυρωθέι για : ");
            for (Hotel_room room : hotel.Rooms) {
                for (Reservations resv : room.hotelroomcancellations) {
                    resv.show();
                }
            }
        }
    }


    /**
     *Μέθοδος εμφανίσεις συνολικού εισοδήματος από κρατήσεις
     */
    public void sum_income() {
        double sum = 0;
        double total = 0 ;
        for (Hotel hotel : Hotels) {
            for (Hotel_room hr : hotel.Rooms) {
                for (Reservations resv : hr.hotelroomreservations){
                    total = ChronoUnit.DAYS.between(resv.start, resv.end);
                    sum = sum + Math.abs(Double.parseDouble(hr.getPrice()) * total) ;
                }
            }
        }
        System.out.println("Συνολικό εισόδημα απο υπάρχων κρατήσεις : "+sum);
    }
}