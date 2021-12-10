/**
 * Αυτή η κλάση αναπαριστά έναν πάροχο καταλύματος με τα χαρακτηριστηκά του, ο οποίος μπορεί να προσθέσει το κατάλυμα του,
 * να δεί τα καταλύματα του, να κάνει αλλαγές αν επιθυμεί σε κάποιο, να δεί τις κρατήσεις του καθώς και τις ακυρώσεις του.
 */

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Accommodation_Provider extends Person{


    ArrayList<Accommodation> Accommodations = new ArrayList<>();

    Accommodation acc ;


    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Accommodation_Provider(){}

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    Accommodation_Provider(String aname , String ahome_ground, String aphone_number, String aemail){
        super(aname,ahome_ground,aphone_number,aemail);
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει όλα τα καταλύματα που έχει καταχωρήσει
     */
    public void Accomodations_Display_All(){
        boolean flag = true;
        for(Accommodation acc : Accommodations){
            acc.show_accommodation();
            flag = false;
        }
        if (flag){
            System.out.println("Δεν έχετε καταχωριμένα καταλύματα");
        }
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει ένα συγγεκριμένο απο τα καταλύματα του
     */
    public void Accomodations_Display(){
        Accommodation acc = search_acc(Accommodations);
        if (acc == null){
            return;
        }
        acc.show_accommodation();

    }

    /**
     * μέθοδος με την οποία ο πάροχος κάνει επεξεργασία ένα κατάλυμα
     */
    public void Accomodation_Edit() {
        boolean flag = true;

        acc = search_acc(Accommodations);

        if (acc == null) {
            return;
        }

        System.out.println("Τι θα ήθελες να αλλάξεις");
        next_string = sc.next();

        while (flag) {
            flag = false;
            switch (next_string) {
                case "ονομα" -> {
                    System.out.println("Δώσε το καινούργιο ονομα:");
                    next_string = sc.next();
                    acc.setName(next_string);
                    acc.show_accommodation();
                }
                case "τοποθεσια" -> {
                    System.out.println("Δώσε την καινούργια τοποθεσία:");
                    next_string = sc.next();
                    acc.setLocation(next_string);
                    acc.show_accommodation();
                }
                case "τιμη" -> {
                    System.out.println("Δώσε την καινούργια τιμή:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη τιμή");
                    acc.setPrice(next_string);
                    acc.show_accommodation();
                }
                case "τετραγωνικά" -> {
                    System.out.println("Δώσε τα καινούργια τετραγωνικά:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρα τετραγωνικά");
                    acc.setSqmeter(next_string);
                    acc.show_accommodation();
                }
                case "αστεργια" -> {
                    System.out.println("Δώσε τα καινούργια αστέργια");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρα αστεργια");
                    acc.setStars(next_string);
                    acc.show_accommodation();
                }
                case "δωματια" -> {
                    System.out.println("Δώσε τα καινούργια δωμάτια:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρος αριθμος δωματίων");
                    acc.setRooms(next_string);
                    acc.show_accommodation();
                }
                case "χωρητικοτητα" -> {
                    System.out.println("Δώσε τη καινούργια χωρητικότητα σε ατομα: (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη χωρητικοτητα ");
                    acc.setCapacity(next_string);
                    acc.show_accommodation();
                }
                case "ac" -> {
                    System.out.println("Θα παρέχει το κατάλυμα ac? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setAc(next_bool);
                    acc.show_accommodation();
                }
                case "πρωίνο" -> {
                    System.out.println("Θα παρέχει το κατάλυμα πρωινό? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setBreakfast(next_bool);
                    acc.show_accommodation();
                }
                case "wifi" -> {
                    System.out.println("Θα παρέχει το κατάλυμα wifi? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setWifi(next_bool);
                    acc.show_accommodation();
                }
                case "parking" -> {
                    System.out.println("Θα παρέχει το κατάλυμα parking? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setParking(next_bool);
                    acc.show_accommodation();
                }
                case "υπηρεσια καθαρισμου" -> {
                    System.out.println("Θα παρέχει το κατάλυμα υπηρεσία καθαρισμού? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setCleaning_services(next_bool);
                    acc.show_accommodation();
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
     * μέθοδος με την οποία ο πάροχος προσθέτει ενα κατάλυμα
     */
    public void Accommodation_add() {
        String aname ;
        String alocation;
        String aprice;
        String asqmeter;
        String astars;
        String arooms;
        String acapacity;
        boolean abreakfast;
        boolean awifi;
        boolean aac;
        boolean aparkin;
        boolean acleaning_services;



        System.out.println("Ονομα");
        aname = sc.next();

        System.out.println("Τοποθεσία");
        alocation = sc.next();

        System.out.println("Τιμή");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Τιμή");
        aprice = next_string ;

        System.out.println("Τετραγωνικά");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρα Τετραγωνικά");
        asqmeter = next_string;

        System.out.println("Αστέργια");
        next_string = sc.next();
        p = Pattern.compile("[0-5]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρα Αστέργια");
        astars = next_string;

        System.out.println("Δωμάτια");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρα Δωμάτια");
        arooms = next_string;

        System.out.println("Χωριτικότητα");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Χωριτικότητα");
        acapacity = next_string;

        System.out.println("Air conditioner (0-OXI/1-NAI) ");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Τιμή");
        aac = next_string.equals("1");

        System.out.println("Πρωινό (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Τιμή");
        abreakfast = next_string.equals("1");

        System.out.println("Parking (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Τιμή");
        aparkin = next_string.equals("1");


        System.out.println("Wifi (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Τιμή");
        awifi = next_string.equals("1");

        System.out.println("Υπηρεσία καθαρισμού (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη εγκυρη Τιμή");
        acleaning_services = next_string.equals("1");


        Accommodation temp = new Accommodation(aname,alocation,aprice,asqmeter,astars,arooms,acapacity,abreakfast,awifi,aac,aparkin,acleaning_services);
        Accommodations.add(temp);
        temp.show_accommodation();
    }

    /**
     * μέθοδος με την οποία ο πάροχος διγράφει ένα απο τα καταλύματα του
     */
    public void Accommodation_delete() {
        acc = search_acc(Accommodations);
        if (acc == null){
            return;
        }
        Accommodations.remove(acc);
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει τις κρατήσεις των καταλυμάτων του
     */
    public void sum_resv() {
        for (Accommodation acc : Accommodations) {
            if (!acc.reservations.isEmpty()) {
                System.out.println("Το κατάλυμα " + acc.getName() + " είναι κλεισμένο για : ");
                for (Reservations resv : acc.reservations) {
                    resv.show();
                }
            }
        }

    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει τις ακυρώσεις των καταλυμάτων του
     */
    public void sum_resv_cancel() {
        for (Accommodation acc : Accommodations){
            System.out.println("Το κατάλυμα "+acc.getName() + " εχει ακυρωθεί για : ");
            for (Reservations resv : acc.cancellations){
                resv.show();
            }
        }
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει το συνολικό εισόδημα από τα καταλύματα του
     */
    public void sum_income() {
        double sum = 0;
        double total  ;
        for (Accommodation acc : Accommodations) {
            for (Reservations resv : acc.reservations){
                total = ChronoUnit.DAYS.between(resv.start, resv.end);
                sum = sum + Math.abs(Double.parseDouble(acc.getPrice()) * total) ;
            }
        }
        System.out.println("Συνολικό εισόδημα απο υπάρχων κρατήσεις : "+ sum);

    }
}
