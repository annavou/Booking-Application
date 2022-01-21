/**
 * Αυτή η κλάση αναπαριστά ένα πελάτη με τα βασικά του στοιχεία (όνομα, τηλέφωνο, μειλ, τοποθεσία), ο οποίος μπορεί να αναζητήσει
 * διάφορα καταλύματα και ξενοδοχεία με τη χρήση φίλτρων, να κάνει κράτηση του καταλύματος που επιθυμεί με βάση
 * της διαθεσιμότητας, να ακυρώσει οποιαδήποτε κράτηση του και να έχει πρόσβαση στις κρατήσεις και τις ακυρώσεις του.
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Customer extends Person {

    HashMap<Reservations, Accommodation> My_Acc_Bookings = new HashMap<>();
    HashMap<Reservations, Accommodation> My_Acc_Canceled = new HashMap<>();

    HashMap<Reservations, Hotel_room> My_Hot_Bookings = new HashMap<>();
    HashMap<Reservations, Hotel_room> My_Hot_Canceled = new HashMap<>();



    LocalDate from = null;
    LocalDate till = null ;


    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     *
     * @param aname Το όνομα
     * @param alocation Η εδρα
     * @param aphone_number Το τηλέφωνο
     * @param aemail Το email
     */
    public Customer(String aname, String alocation, String aphone_number, String aemail) {
        super(aname, alocation, aphone_number, aemail);
    }

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    public Customer() {
        super();
    }


    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που εχουν πανω απο κάποια δωμάτια
     *
     * @param arooms τα δώματια που θέλουμε
     */
    public void search_rooms(String arooms, ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> Integer.parseInt(arooms) < Integer.parseInt(acc.getRooms()));
    }


    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που εχουν πανω απο καποια τετραγωνικά
     *
     * @param asqmeters Τετραγωνικά που θέλουμε
     */
    public void search_sqmeters(String asqmeters, ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> Integer.parseInt(asqmeters) > Integer.parseInt(acc.getSqmeter()));
    }


    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων που εχουν πανω απο καποια τετραγωνικά
     * @param asqmeters Τετραγωνικά που θέλουμε
     */
    private void search_Hotelsqmeters(String asqmeters, ArrayList<Hotel> hotel_list) {
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> Integer.parseInt(asqmeters) > Integer.parseInt(room.getSqmeter()));
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που βρίσκονται σε μια τοποθεσία
     *
     * @param alocation η τοποθεσία που θέλουμε
     */
    public void search_location(String alocation, ArrayList<Accommodation> accom_list) {
        accom_list.removeIf(acc -> !alocation.equals(acc.getLocation()));
    }

    /**
     * μέθοδος που αναζητά όλα τα ξενοδοχεία που βρίσκονται σε μια τοποθεσία
     *
     * @param alocation η τοποθεσία που θέλουμε
     */
    public void search_HotelLocation(String alocation, ArrayList<Hotel> hotel_list) {
        hotel_list.removeIf(hotel -> !alocation.equals(hotel.getLocation()));
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το εύρος των τιμών ανά βράδυ
     *
     * @param from η κατώτατη τιμή που θέλουμε ανά βράδυ
     * @param till η ανώτατη τιμή που θέλουμε ανά βράδυ
     */
    public void search_PriceRange(Double from, double till, ArrayList<Accommodation> accom_list) {
        accom_list.removeIf(acc -> Double.parseDouble(acc.getPrice()) < from || Double.parseDouble(acc.getPrice()) > till);

    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση το εύρος των τιμών ανά βράδυ
     *
     * @param from       η μέγιστη τιμή που θέλουμε ανά βράδυ
     * @param till       η ανώτατη τιμή που θέλουμε ανά βράδυ
     */
    public void search_HotelPriceRange(double from, double till, ArrayList<Hotel> hotel_list) {
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> Double.parseDouble(room.getPrice()) < from || Double.parseDouble(room.getPrice()) > till);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση τα αστέρια τους
     * @param astars πόσα αστέρια θέλουμε να είναι το κατάλυμα
     */
    public void search_stars(String astars,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> Integer.parseInt(astars) > Integer.parseInt(acc.getStars()));
    }

    /**
     * μέθοδος που αναζητά ξενοδοχεία με βάση τα αστέρια τους
     * @param astars πόσα αστέρια θέλουμε να είναι το ξενοδοχείο
     */
    public void search_HotelStars(String astars,ArrayList<Hotel> hotel_list){
        hotel_list.removeIf(hotel -> Integer.parseInt(astars) > Integer.parseInt(hotel.getStars()));
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση την χωριτηκότητα σε άτομα
     * @param acapacity πόσα άτομα θέλουμε
     */
    public void search_capacity(String acapacity,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> Integer.parseInt(acapacity) > Integer.parseInt(acc.getCapacity()));
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση την χωριτηκότητα σε άτομα
     * @param acapacity πόσα άτομα θέλουμε
     */
    public void search_HotelCapacity(String acapacity,ArrayList<Hotel> hotel_list){
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> Integer.parseInt(acapacity) > Integer.parseInt(room.getCapacity()));
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν προσφέρουν πρωινό
     * @param abreakfast αν διαθέτει πρωινό
     */
    public void search_breakfast(boolean abreakfast,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> acc.isBreakfast() != abreakfast);
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση το αν προσφέρουν πρωινό
     * @param abreakfast αν διαθέτει πρωινό
     */
    public void search_HotelBreakfast(boolean abreakfast,ArrayList<Hotel> hotel_list) {
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> room.isBreakfast() != abreakfast);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν διθέτουν wifi
     * @param awifi αν διαθέτει wifi
     */
    public void search_wifi(boolean awifi,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> acc.isWifi() != awifi);
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείου με βάση το αν διθέτουν wifi
     * @param awifi αν διαθέτει wifi
     */
    public void search_HotelWifi(boolean awifi,ArrayList<Hotel> hotel_list) {
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> room.isWifi() != awifi);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν διθέτουν κλιματισμό
     * @param aac αν διαθέτει κλιματισμό
     */
    public void search_AirCondition(boolean aac,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> acc.isAc() != aac);
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείου με βάση το αν διθέτουν κλιματισμό
     * @param aac αν διαθέτει κλιματισμό
     */
    public void search_HotelAirCondition(boolean aac, ArrayList<Hotel> hotel_list){

        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> room.isAc() != aac);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που διαθέτουν πάρκινγκ
     * @param aparking αν διεθέτει πάρκινγκ
     */
    public void search_Parking(boolean aparking,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> acc.isParking() != aparking);
    }

    /**
     * μέθοδος που αναζητά όλα τα δωμάτια ξενοδοχείου που διαθέτουν πάρκινγκ
     * @param aparking αν διεθέτει πάρκινγκ
     */
    public void search_HotelParking(boolean aparking,ArrayList<Hotel> hotel_list){

        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> room.isParking() != aparking);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που προσφέρουν υπηρεσίες καθαριότητας
     * @param acleaning αν προφέρει υπηρεσίες καθαριότητας
     */
    public void search_CleaningServices(boolean acleaning,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> acc.isCleaning_services() != acleaning);
    }

    /**
     * μέθοδος που αναζητά όλα τα δωμάτια ξενοδοχείων που προσφέρουν υπηρεσίες καθαριότητας
     * @param acleaning αν προφέρει υπηρεσίες καθαριότητας
     */
    public void search_HotelCleaningServices(boolean acleaning,ArrayList<Hotel> hotel_list){
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> room.isCleaning_services() != acleaning);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }


    /**
     *Μέθοδος με την οποία ο χρήστης κάνει κράτηση σε ένα κατάλυμα με χρήση φίλτρων
     //   * @param people Συλλογή με όλους τους χρήστες
     */
 /*   public void make_a_resv_acc(Collection<Person> people) {
        boolean flag = true;
        Accommodation accommodation ;
        ArrayList<Accommodation> availiable = new ArrayList<>();
        for (Person p : people){
            if( p instanceof Accommodation_Provider){
                availiable.addAll(((Accommodation_Provider) p).Accommodations);
            }
        }

        ArrayList<Accommodation> temp = new ArrayList<>(availiable);

        for (Accommodation acc : availiable) {
            acc.show_accommodation();
        }

        String s = "Θέλετε να βάλετε φίλτο στην αναζήτηση σας? (0-ονομα/1-τοποθεσία/2-αστέρια/3-χωρητικότητα/4-δωμάτια/5-τιμή/6-τετραγωνικά/7-πρωινό/8-wifi/9-air condition/α-parking/β-υπηρεσία καθαρισμού/γ-χωρις φιλτρα/δ-εξοδος";
        System.out.println(s);
        next_string = sc.next();
        p = Pattern.compile("[0-9]|[α]|[β]|[γ]|[δ]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");

        while (!next_string.equals("γ") && !next_string.equals("δ")) {
            switch (next_string) {
                case "0" -> {
                    Accommodation a = search_acc(temp);
                    temp.removeIf(z -> !z.equals(a));
                }
                case "1" -> {
                    System.out.println("Που θέλεις να βρήσκετε το κατάλυμα ?");
                    next_string = sc.next();
                    search_location(next_string, temp);
                }
                case "2" -> {
                    System.out.println("Ποσα αστέργια θέλετε να έχει το κατάλυμα?");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_stars(next_string, temp);

                }
                case "3" -> {
                    System.out.println("Ποσα ατομα να υποστηρίζει?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_capacity(next_string, temp);
                }
                case "4" -> {
                    System.out.println("Ποσα να δωμάτια να εχει το κατάλυμα?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_rooms(next_string, temp);
                }
                case "5" -> {
                    System.out.println("Ευρος τιμης:\n απο:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    System.out.println("εως:");
                    String a = next_string;
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_PriceRange(Double.parseDouble(a), Double.parseDouble(next_string), temp);
                }
                case "6" -> {
                    System.out.println("Πόσα τετραγωνικά τουλάχιστον?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_sqmeters(next_string, temp);
                }
                case "7" -> {
                    System.out.println("Θέλετε να παρέχει πρωινό (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_breakfast(next_bool, temp);
                }
                case "8" -> {
                    System.out.println("Θέλετε να παρέχει Wifi (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_wifi(next_bool, temp);
                }
                case "9" -> {
                    System.out.println("Θέλετε να παρέχει air conditioner (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_AirCondition(next_bool, temp);
                }
                case "α" -> {
                    System.out.println("Θέλετε να παρέχει parking (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_Parking(next_bool, temp);
                }
                case "β" -> {
                    System.out.println("Θέλετε να παρέχει υπηρεσία καθαρισμού (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_CleaningServices(next_bool, temp);
                }

            }

            if (temp.isEmpty()) {
                System.out.println("Δεν βρέθηκαν καταλύματα με αυτα τα κρητίρια αναζήτησης. Θελετε να αφαιρέσετε το τελευταίο φιλτρο(0-ναι/1-εξοδος)");
                next_string = sc.next();
                p = Pattern.compile("[0-1]");
                next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                if (next_string.equals("1")) {
                    return;
                }
                else {
                    temp.addAll(availiable);
                }
            } else {
                availiable.clear();
                availiable.addAll(temp);
            }
            for (Accommodation acc : availiable) {
                acc.show_accommodation();
            }
            System.out.println(s);
            next_string = sc.next();
            p = Pattern.compile("[0-9]|[α]|[β]|[γ]|[δ]");
            next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
            if(next_string.equals("δ") ){
                flag = false;
            }
        }
        if(flag && !next_string.equals("γ")){
            return;
        }
        Reservations resv = new Reservations() ;
        boolean flag1 = true;
        while (flag1) {
            System.out.println("Δώστε τις ημερομηνίες:\n απο:");
            LocalDate start = read_date();
            System.out.println("Εως:");
            LocalDate end = read_date();
            valid_dates(start,end);
            resv = new Reservations(start,end,this.getName(),null,null);
            temp = availiables_acc(availiable, resv);
            flag1 = false;
            if (temp.isEmpty()) {
                System.out.println("Δεν υπάρχει κατάλυμα με αυτές τις ημερομηνίες διαθέσημες. Θέλετε να αλλάξετε ημερομηνια ? (0-εξοδος/1-αλλαγη ημερομηνίας) ");
                next_string = sc.next();
                p = Pattern.compile("[0-1]");
                next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                if (next_string.equals("0")) {
                    return;
                } else {
                    temp.addAll(availiable);
                    flag1 = true;
                }
            } else {
                availiable = temp;
            }
        }
        accommodation = search_acc(availiable);
        if(accommodation==null){
            System.out.println("Δεν υπάεχει τέτοιο κατάλυμα");
        }
        else {
            accommodation.reservations.add(resv);
            My_Acc_Bookings.put(resv,accommodation);
            resv.setAcc(accommodation);
            System.out.println("\nΕπιτυχής Κράτηση!!!\n");
        }

    }



    /**
     *Μέθοδος με την οποία ο χρήστης κάνει κράτηση σε ένα δωμάτιο ξενοδοχείου με χρήση φίλτρων
   //  * @param people Συλλογή με όλους τους χρήστες
     *
     */
   /* public void make_a_resv_hot(Collection<Person> people) {
        Hotel_room room ;
        boolean flag = true;
        Hotel hotel ;
        ArrayList<Hotel> availiable = new ArrayList<>();
        ArrayList<Hotel_room> rooms = new ArrayList<>();
        ArrayList<ArrayList<Hotel_room>> hr = new ArrayList<>();

        for (Person p : people){
            if( p instanceof Hotel_Provider){
                for (int i = 0 ; i < ((Hotel_Provider) p).Hotels.size() ; i++){
                    availiable.add(new Hotel(((Hotel_Provider) p).Hotels.get(i)) );
                }
            }
        }


        ArrayList<Hotel> temp = new ArrayList<>();
        for (int i = 0 ; i < availiable.size(); i++){
            hr.add(new ArrayList<Hotel_room>());
            for (int j=0 ; j< availiable.get(i).Rooms.size() ;j ++){
                hr.get(i).add(availiable.get(i).Rooms.get(j));
            }
        }


        for (Hotel h : availiable) {
           h.show_Hotel();
        }

        String s = "Θέλετε να βάλετε φίλτο στην αναζήτηση σας? (0-ονομα/1-τοποθεσία/2-αστέρια/3-χωρητικότητα/4-τιμή/5-τετραγωνικά/6-πρωινό/7-wifi/8-air condition/9-parking/α-υπηρεσία καθαρισμού/γ-χωρις φιλτρα/δ-εξοδος";
        System.out.println(s);
        next_string = sc.next();
        p = Pattern.compile("[0-9]|[α]|[β]|[γ]|[δ]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        while (!next_string.equals("γ") && !next_string.equals("δ")) {
            switch (next_string) {
                case "0" -> {
                    Hotel a = search_hot(temp);
                    temp.removeIf(z -> !z.equals(a));
                }
                case "1" -> {
                    System.out.println("Που θέλεις να βρήσκετε το κατάλυμα ?");
                    next_string = sc.next();
                    search_HotelLocation(next_string, temp);
                }
                case "2" -> {
                    System.out.println("Ποσα αστέργια θέλετε να έχει το ?");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_HotelStars(next_string, temp);

                }
                case "3" -> {
                    System.out.println("Ποσα ατομα να υποστηρίζει?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_HotelCapacity(next_string, temp);
                }
                case "4" -> {
                    System.out.println("Ευρος τιμης:\n απο:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    System.out.println("εως:");
                    String a = next_string;
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_HotelPriceRange(Double.parseDouble(a), Double.parseDouble(next_string), temp);
                }
                case "5" -> {
                    System.out.println("Πόσα τετραγωνικά τουλάχιστον?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_Hotelsqmeters(next_string, temp);
                }
                case "6" -> {
                    System.out.println("Θέλετε να παρέχει πρωινό (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_HotelBreakfast(next_bool, temp);
                }
                case "7" -> {
                    System.out.println("Θέλετε να παρέχει Wifi (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_HotelWifi(next_bool, temp);
                }
                case "8" -> {
                    System.out.println("Θέλετε να παρέχει air conditioner (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_HotelAirCondition(next_bool, temp);
                }
                case "9" -> {
                    System.out.println("Θέλετε να παρέχει parking (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_HotelParking(next_bool, temp);
                }
                case "α" -> {
                    System.out.println("Θέλετε να παρέχει υπηρεσία καθαρισμού (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile("[0-1]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    next_bool = next_string.equals("1");
                    search_HotelCleaningServices(next_bool, temp);
                }

            }


            if (temp.isEmpty()) {
                System.out.println("Δεν βρέθηκαν Ξεναδοχεία με αυτα τα κρητίρια αναζήτησης. Θελετε να αφαιρέσετε το τελευταίο φιλτρο(0-ναι/1-εξοδος)");
                next_string = sc.next();
                p = Pattern.compile("[0-1]");
                next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                if (next_string.equals("1")) {
                    return;
                }
                else{
                    for (int i = 0 ; i < hr.size() ; i++){
                        temp.add(new Hotel(availiable.get(i)));
                        for (int j = 0 ; j < availiable.get(i).Rooms.size() ; j++){
                            temp.get(i).Rooms.add(hr.get(i).get(j));
                        }
                    }
                }
            } else {
                availiable.clear();
                availiable.addAll(temp);
            }
            for (Hotel h : temp) {
                h.show_Hotel();
            }
            System.out.println(s);
            next_string = sc.next();
            p = Pattern.compile("[0-9]|[α]|[β]|[γ]|[δ]");
            next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
            if(next_string.equals("δ") ){
                flag = false;
            }
        }
        if(flag && !next_string.equals("γ")){
            return;
        }
        System.out.println("Δώστε τις ημερομηνίες:\n απο:");
        LocalDate start = read_date();
        System.out.println("Εως:");
        LocalDate end = read_date();
        Reservations resv = new Reservations(start,end,this.getName(),null,null);
        temp = availiables_hot(availiable,resv);
        if(temp.isEmpty()){
            System.out.println("Δεν υπάρχει Ξεναδοχείο με αυτές τις ημερομηνίες διαθέσημες. Θέλετε να αλλάξετε ημερομηνια ? (0-εξοδος/1-αλλαγη ημερομηνίας) ");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if(next_string.equals("0")){
                return;
            }
            else{
                temp.addAll(availiable);
            }
        }
        else{
            availiable = temp;
        }

        for(Hotel h : availiable){
            rooms.addAll(h.Rooms);
        }

        room = search_Hotel_room(rooms);
        if(room==null){
            System.out.println("Δεν υπάεχει τέτοιο κατάλυμα");
        }
        else {
            room.hotelroomreservations.add(resv);
            My_Hot_Bookings.put(resv,room);
            resv.setHot(room);
            System.out.println("\nΕπιτυχής Κράτηση!!!\n");
        }

    }


    /**
     *Μέθοδος η οποία επιστρέφει τα δωμάτια ξενοδοχείων τα οποία είναι διαθέσιμα για μία συγκεκριμένη ημερομηνία
     * @param hotels Λίστα με επιθυμητά Ξεναδοχεία
     * @param resv Ημερομηνία που θέλουμε να κάνουμε
     * @return τα διαθέσιμα ξεναδοχεία
     */
    private ArrayList<Hotel> availiables_hot(ArrayList<Hotel> hotels, Reservations resv) {
        boolean flag = false ;
        for(int i = 0 ; i < hotels.size() ; i++) {
            for (int j = 0 ; j < hotels.get(i).Rooms.size() ; j++) {
                for (Reservations r : hotels.get(i).Rooms.get(j).hotelroomreservations) {
                    if (r.start.equals(resv.start) || r.end.equals(resv.end)) {
                        flag = true;
                        break;
                    }
                    if (resv.start.isAfter(r.start) && resv.start.isBefore(r.end) || resv.end.isBefore(r.end) && resv.end.isAfter(r.start)) {
                        flag = true;
                        break;
                    }
                    if (resv.start.isBefore(r.start) && resv.end.isAfter(r.end)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    hotels.get(i).Rooms.remove(hotels.get(i).Rooms.get(j));
                }
            }
        }
        hotels.removeIf(hotel -> hotel.Rooms.isEmpty());
        return hotels;
    }



    /**
     *Μέθοδος με την οποία επιστρέφονται τα καταλύματα τα οποία είναι διαθέσιμα για μία συγκεκριμένη ημερομηνία
     * @param accommodations Λίστα με επιθυμητά καταλύματα
     * @param resv Ημερομηνία που θέλουμε να κάνουμε
     * @return τα διαθέσιμα καταλύματα
     */
    private ArrayList<Accommodation> availiables_acc(ArrayList<Accommodation> accommodations, Reservations resv) {
        boolean flag = false;
        for (int i = 0 ; i < accommodations.size() ; i++) {
            for (Reservations r : accommodations.get(i).reservations) {
                if (r.start.equals(resv.start) || r.end.equals(resv.end)) {
                    flag = true;
                    break;
                }
                if (resv.start.isAfter(r.start) && resv.start.isBefore(r.end) || resv.end.isBefore(r.end) && resv.end.isAfter(r.start)) {
                    flag = true;
                    break;
                }
                if (resv.start.isBefore(r.start) && resv.end.isAfter(r.end)) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                accommodations.remove(accommodations.get(i));
            }
        }
        return accommodations;
    }


    /**
     *Μέθοδος η οποία ελέγχει για δύο ημερομηνίες ότι αρχή είναι πριν το τέλος
     * @param start Ημερομηνία αρχής
     * @param till Ημερομηνία Τέλους
     */

    private void valid_dates(LocalDate start,LocalDate till){
        if(start.isAfter(till)){
            System.out.println("Μη έγκυρη ημερομινία. Θελετε να αλλαξε την αρχή η το τελος ?(0-αρχη/1-τελος)");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if( next_string.equals("0")){
                ///start = read_date();
                valid_dates(start,till);
            }
            else {
                // till = read_date();
                valid_dates(start,till);
            }
        }
    }

    public JPanel resv_canc_view() {
        JPanel main = new JPanel();
        JPanel resvs = new JPanel();
        JPanel cancs = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Reservations");
        resvs.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Cancellations");
        cancs.setBorder(titledBorder);
        GridLayout gl = new GridLayout(2,2);
        main.setLayout(gl);
        main.add(resvs,Component.LEFT_ALIGNMENT);
        main.add(cancs);
        JPanel resvsa = new JPanel();
        JPanel cancsa = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Accommodations");
        resvsa.setBorder(titledBorder);
        cancsa.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Hotel Rooms");
        JPanel resvsh = new JPanel();
        JPanel cancsh = new JPanel();
        resvsh.setBorder(titledBorder);
        cancsh.setBorder(titledBorder);


        for(Reservations re : My_Acc_Bookings.keySet()){
            resvsa.add(new JLabel("Reservation at Accommodation : " + My_Acc_Bookings.get(re).getName() + " from : " + re.getStart().toString() + " to : " + re.getEnd().toString()));
        }
        for(Reservations re : My_Hot_Bookings.keySet()){
            resvsh.add(new JLabel("Reservation at Hotel Room : " + My_Hot_Bookings.get(re).getName() + " from : " + re.getStart().toString() + " to : " + re.getEnd().toString()));
        }
        for(Reservations re : My_Acc_Canceled.keySet()){
            cancsa.add(new JLabel("Cancellation at Accommodation : " + My_Acc_Canceled.get(re).getName() + " from : " + re.getStart().toString() + " to : " + re.getEnd().toString()));
        }
        for(Reservations re : My_Hot_Canceled.keySet()){
            cancsh.add(new JLabel("Reservation at Hotel Room : " + My_Hot_Canceled.get(re).getName() + " from : " + re.getStart().toString() + " to : " + re.getEnd().toString()));
        }

        resvs.add(resvsa);
        resvs.add(resvsh);
        cancs.add(cancsa);
        cancs.add(cancsh);
        return main ;
    }

    public JPanel Cancel() {
        JPanel main = new JPanel();
        JPanel resvs = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Reservations");
        resvs.setBorder(titledBorder);
        JPanel acc = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Accommodations");
        acc.setBorder(titledBorder);
        JPanel hot = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Hotel Rooms");
        hot.setBorder(titledBorder);

        resvs.add(acc);
        resvs.add(hot);

        ArrayList<Accommodation> list1 = new ArrayList<>();
        HashMap<String,JPanel> subsa = new HashMap<>();
        ArrayList<Hotel_room> list2 = new ArrayList<>();
        HashMap<String,JPanel> subsc = new HashMap<>();
        JComboBox<String> list3 = new JComboBox<>();
        ArrayList<Reservations> list4 = new ArrayList<>();
        updater(list1,list2,list3,list4,subsa,subsc,acc,hot);
        if(list4.isEmpty()){
            JLabel er = new JLabel("hh");
            main.add(er);
            return main;
        }


        JButton Cancel = new JButton();
        Cancel.setText("Cancel");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = (String) list3.getSelectedItem();
                char c = sele.charAt(0);
                int x = Character.getNumericValue(c);
                Reservations to_canc = list4.get(x-1);
                if(to_canc.getHot() == null){
                    My_Acc_Bookings.remove(to_canc);
                    My_Acc_Canceled.put(to_canc,to_canc.getAcc());
                    to_canc.getAcc().reservations.remove(to_canc);
                    to_canc.getAcc().cancellations.add(to_canc);
                    //acc
                }
                else{
                    My_Hot_Bookings.remove(to_canc);
                    My_Hot_Canceled.put(to_canc,to_canc.getHot());
                    to_canc.getHot().hotelroomreservations.remove(to_canc);
                    to_canc.getHot().hotelroomcancellations.add(to_canc);
                    //hot
                }
                main.removeAll();
                SwingUtilities.updateComponentTreeUI(main);
                list1.clear();
                list2.clear();
                list3.removeAllItems();
                list4.clear();
                subsa.clear();
                subsc.clear();
                acc.removeAll();
                hot.removeAll();
                updater(list1,list2,list3,list4,subsa,subsc,acc,hot);
                if(list4.isEmpty()){
                    JLabel er = new JLabel("hh");
                    main.add(er);
                }
                else {
                    main.add(resvs);
                    main.add(list3);
                    main.add(Cancel);
                }
            }
        });




        main.add(resvs);
        main.add(list3);
        main.add(Cancel);
        return main;
    }








    private void updater(ArrayList<Accommodation> list1, ArrayList<Hotel_room> list2, JComboBox<String> list3, ArrayList<Reservations> list4, HashMap<String, JPanel> subsa, HashMap<String, JPanel> subsc,JPanel acc,JPanel hot) {
        for(Reservations re : My_Acc_Bookings.keySet()){
            if(!list1.contains(re.getAcc())){
                list1.add(re.getAcc());
                TitledBorder titledBorder = BorderFactory.createTitledBorder(re.getAcc().getName());
                JPanel temp = new JPanel();
                temp.setBorder(titledBorder);
                subsa.put(re.getAcc().getName(),temp);
                JLabel date = new JLabel("DATE");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }
            else{
                JPanel temp = subsa.get(re.getAcc().getName());
                JLabel date = new JLabel("DATE");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }

        }

        for(Reservations re : My_Hot_Bookings.keySet()){
            if(!list2.contains(re.getHot())){
                list2.add(re.getHot());
                TitledBorder titledBorder = BorderFactory.createTitledBorder(re.getHot().getName());
                JPanel temp = new JPanel();
                temp.setBorder(titledBorder);
                subsc.put(re.getHot().getName(),temp);
                JLabel date = new JLabel("DATE");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }
            else{
                JPanel temp = subsc.get(re.getHot().getName());
                JLabel date = new JLabel("DATE");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }

        }
        for(String s : subsa.keySet())
            acc.add(subsa.get(s));
        for(String s : subsc.keySet())
            hot.add(subsc.get(s));


        int i = 1 ;
        for(Reservations re : My_Acc_Bookings.keySet()){
            String s = i + ") at : " + re.getAcc().getName() + " from : " + re.getStart().toString() + " to : " + re.getEnd().toString();
            list3.addItem(s);
            list4.add(re);
        }
        for(Reservations re : My_Hot_Bookings.keySet()){
            String s = i + ") at : " + re.getHot().getName() + " from : " + re.getStart().toString() + " to : " + re.getEnd().toString();
            list3.addItem(s);
            list4.add(re);
        }
    }

    public JPanel Reserv(Collection<Person> People) {
        JPanel main = new JPanel();
        JPanel opt = new JPanel();
        JPanel view = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Options");
        opt.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Avail");
        view.setBorder(titledBorder);


        JLabel Name = new JLabel("Name : ");
        JLabel Location = new JLabel("Location : ");
        JLabel Stars = new JLabel("Stars : ");
        JLabel PriceF = new JLabel("Price From : ");
        JLabel PriceT = new JLabel("Price To : ");
        JLabel Square_meters = new JLabel("Square Meters : ");
        JLabel Rooms = new JLabel("Rooms : ");
        JLabel Capacity = new JLabel("Capacity : ");
        JLabel Breakfast = new JLabel("Breakfast : ");
        JLabel Wifi = new JLabel("Wifi : ");
        JLabel Parking = new JLabel("Parking : ");
        JLabel Ac = new JLabel("Ac : ");
        JLabel Cleaning_Service = new JLabel("Cleaning Service : ");


        JTextField NameT = new JTextField("");
        JTextField LocationT = new JTextField("");
        JTextField StarsT = new JTextField("");
        JTextField PriceFT = new JTextField("");
        JTextField PriceTT = new JTextField("");
        JTextField Square_metersT = new JTextField("");
        JTextField RoomsT = new JTextField("");
        JTextField CapacityT = new JTextField("");
        JCheckBox BreakfastT = new JCheckBox("");
        JCheckBox WifiT = new JCheckBox();
        JCheckBox ParkingT = new JCheckBox();
        JCheckBox AcT = new JCheckBox();
        JCheckBox Cleaning_ServiceT = new JCheckBox();


        GridLayout layout = new GridLayout(13,2);
        opt.setLayout(layout);
        opt.add(Name);
        opt.add(NameT);
        opt.add(Location);
        opt.add(LocationT);
        opt.add(Stars);
        opt.add(StarsT);
        opt.add(Square_meters);
        opt.add(Square_metersT);
        opt.add(PriceFT);
        opt.add(PriceTT);
        opt.add(Rooms);
        opt.add(RoomsT);
        opt.add(Capacity);
        opt.add(CapacityT);
        opt.add(Breakfast);
        opt.add(BreakfastT);
        opt.add(Wifi);
        opt.add(WifiT);
        opt.add(Parking);
        opt.add(ParkingT);
        opt.add(Ac);
        opt.add(AcT);
        opt.add(Cleaning_Service);
        opt.add(Cleaning_ServiceT);

        view =  matching(People,NameT.getText(),LocationT.getText(),StarsT.getText(),Square_metersT.getText(),PriceFT.getText(),PriceTT.getText(),RoomsT.getText(),CapacityT.getText(),BreakfastT.isSelected(),WifiT.isSelected(),ParkingT.isSelected(),AcT.isSelected(),Cleaning_ServiceT.isSelected());



        main.add(opt);
        main.add(view);
        return main;
    }

    private JPanel matching(Collection<Person> People,String name, String loc, String stars, String sqm, String Pf, String Pt, String rooms, String capac, boolean breakfast, boolean wifi, boolean parking, boolean ac, boolean clean) {
        JPanel main = new JPanel();
        ArrayList<Accommodation> accs = new ArrayList<>();
        ArrayList<Hotel> hots = new ArrayList<>();
        for(Person p : People)
            if(p instanceof  Accommodation_Provider a)
                accs.addAll(a.Accommodations);
        for(Person p : People)
            if(p instanceof  Hotel_Provider a)
                hots.addAll(a.Hotels);
        for(Accommodation acc : accs){
            int n = 0 ;
            if(name.equals("") || acc.getName().contains(name))
                n++;
            if(loc.equals("") || acc.getLocation().contains(loc))
                n++;
            if(stars.equals("0") || Integer.parseInt(acc.getStars()) < Integer.parseInt(stars))
                n++;
            if(name.equals("") || acc.getName().contains(name))
                n++;
            if(name.equals("") || acc.getName().contains(name))
                n++;

        }


        return main;
    }
}