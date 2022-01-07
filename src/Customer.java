/**
 * Αυτή η κλάση αναπαριστά ένα πελάτη με τα βασικά του στοιχεία (όνομα, τηλέφωνο, email, τοποθεσία), ο οποίος μπορεί να αναζητήσει
 * διάφορα καταλύματα και ξενοδοχεία με τη χρήση φίλτρων, να κάνει κράτηση του καταλύματος που επιθυμεί με βάση
 * της διαθεσιμότητας, να ακυρώσει οποιαδήποτε κράτηση του και να έχει πρόσβαση στις κρατήσεις και τις ακυρώσεις του.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
     * @param atype Ο τύπος χρήστης
     * @param aname Το όνομα
     * @param alocation Η εδρα
     * @param aphone_number Το τηλέφωνο
     * @param aemail Το email
     */
    public Customer(String atype, String aname, String alocation, String aphone_number, String aemail) {
        super(atype, aname, alocation, aphone_number, aemail);
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
     * μέθοδος που αναζητά όλα τα καταλύματα που έχουν πάνω απο κάποια τετραγωνικά
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
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση τη χωρητικότητα σε άτομα
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
     * μέθοδος που αναζητά δωμάτια ξενοδοχείου με βάση το αν διαθέτουν wifi
     * @param awifi αν διαθέτει wifi
     */
    public void search_HotelWifi(boolean awifi,ArrayList<Hotel> hotel_list) {
        for (Hotel hotel : hotel_list) {
            hotel.Rooms.removeIf(room -> room.isWifi() != awifi);
        }
        hotel_list.removeIf(hotel -> hotel.Rooms.isEmpty());
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν διαθέτουν κλιματισμό
     * @param aac αν διαθέτει κλιματισμό
     */
    public void search_AirCondition(boolean aac,ArrayList<Accommodation> accom_list){
        accom_list.removeIf(acc -> acc.isAc() != aac);
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείου με βάση το αν διαθέτουν κλιματισμό
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
     * @param aparking αν διαθέτει πάρκινγκ
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
     * μέθοδος που εμφανίζει τις κρατήσεις του πελάτη
     */
    public void show_my_bookings(){
        if(!My_Acc_Bookings.isEmpty()) {
            System.out.println("Έχετε κρατήσεις στα καταλύματα:");
            for (Reservations res : My_Acc_Bookings.keySet()) {
                System.out.println("Στο '" + My_Acc_Bookings.get(res).getName() + "' για τις:");
                res.show();
            }
        }
        else{
            System.out.println("Δεν έχετε κάνει καμία κράτηση σε κατάλυμα");
        }

        if(!My_Hot_Bookings.isEmpty()) {
            System.out.println("Έχετε κρατήσεις στα Ξενοδοχεία :");
            for (Reservations res : My_Hot_Bookings.keySet()) {
                System.out.println("Στο '" + My_Hot_Bookings.get(res).getName() + "' για τις:");
                res.show();
            }
        }
        else{
            System.out.println("Δεν έχετε κάνει καμία κράτηση σε Ξενοδοχείο");
        }
    }

    /**
     * μέθοδος που εμφανίζει τις ακυρώσεις του πελάτη
     */
    public void show_my_cancellations(){
        if(!My_Acc_Canceled.isEmpty()) {
            System.out.println("Έχετε ακυρώσεις στα καταλύματα :");
            for (Reservations res : My_Acc_Canceled.keySet()) {
                System.out.println("Στο '" + My_Acc_Canceled.get(res).getName() + "' για τις:");
                res.show();
            }
        }
        else{
            System.out.println("Δεν έχετε κάνει καμία ακύρωση σε κατάλυμα");
        }
        if(!My_Hot_Canceled.isEmpty()) {
            System.out.println("Έχετε ακυρώσεις Ξενοδοχεία στα :");
            for (Reservations res : My_Hot_Canceled.keySet()) {
                System.out.println("Στο '" + My_Hot_Canceled.get(res).getName() + "' για τις:");
                res.show();
            }
        }
        else{
            System.out.println("Δεν έχετε κάνει καμία κράτηση σε Ξενοδοχείο");
        }
    }


    /**
     *Μέθοδος με την οποία ο χρήστης κάνει κράτηση σε ένα κατάλυμα με χρήση φίλτρων
     * @param people Συλλογή με όλους τους χρήστες
     */
    public void make_a_resv_acc(Collection<Person> people) {
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

        String s = "Θέλετε να βάλετε φίλτρο στην αναζήτηση σας? (0-όνομα/1-τοποθεσία/2-αστέρια/3-χωρητικότητα/4-δωμάτια/5-τιμή/6-τετραγωνικά/7-πρωινό/8-wifi/9-air condition/α-parking/β-υπηρεσία καθαρισμού/γ-χωρις φιλτρα/δ-εξοδος";
        System.out.println(s);
        next_string = sc.next();
        p = Pattern.compile("[0-9]|[α]|[β]|[γ]|[δ]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");

        while (!next_string.equals("γ") && !next_string.equals("δ")) {
            switch (next_string) {
                case "0" -> {
                    Accommodation a = (Accommodation) search_acc(temp);
                    temp.removeIf(z -> !z.equals(a));
                }
                case "1" -> {
                    System.out.println("Που θέλεις να βρίσκετε το κατάλυμα ?");
                    next_string = sc.next();
                    search_location(next_string, temp);
                }
                case "2" -> {
                    System.out.println("Πόσα αστέρια θέλετε να έχει το κατάλυμα?");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_stars(next_string, temp);

                }
                case "3" -> {
                    System.out.println("Πόσα άτομα να υποστηρίζει?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_capacity(next_string, temp);
                }
                case "4" -> {
                    System.out.println("Πόσα να δωμάτια να έχει το κατάλυμα?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_rooms(next_string, temp);
                }
                case "5" -> {
                    System.out.println("Εύρος τιμής:\n απο:");
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
                System.out.println("Δεν βρέθηκαν καταλύματα με αυτά τα κριτήρια αναζήτησης. Θέλετε να αφαιρέσετε το τελευταίο φίλτρο(0-ναι/1-έξοδος)");
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
            resv = new Reservations(start,end,this,null,null,null);
            temp = availiables_acc(availiable, resv);
            flag1 = false;
            if (temp.isEmpty()) {
                System.out.println("Δεν υπάρχει κατάλυμα με αυτές τις ημερομηνίες διαθέσιμες. Θέλετε να αλλάξετε ημερομηνία ? (0-έξοδος/1-αλλαγή ημερομηνίας) ");
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
        accommodation = (Accommodation) search_acc(availiable);
        if(accommodation==null){
            System.out.println("Δεν υπάρχει τέτοιο κατάλυμα");
        }
        else {
            accommodation.reservations.add(resv);
            My_Acc_Bookings.put(resv,accommodation);
            resv.setAcc(accommodation);
            System.out.println("\nΕπιτυχής Κράτηση!!!\n");
        }

        try(BufferedWriter buffer=new BufferedWriter(new FileWriter("reservations.txt",true))){
            buffer.write("Κατάλυμα " + resv.getAcc().getName()+" από " + resv.getStart() + " έως " + resv.getEnd() +  " πελάτης "
                    + resv.getCustomer().getName());
            buffer.newLine();
            buffer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }


    }


    /**
     *Μέθοδος με την οποία εμφανίζονται όλα τα καταλύματα
     * @param persons Συλλογή με όλους τους χρήστες
     */
    public void see_all_accommodations(Collection<Person> persons){
        for (Person p : persons ){
            if (p instanceof Accommodation_Provider AP){
                AP.Accomodations_Display_All();
            }
            if(p instanceof Hotel_Provider HP){
                HP.Hotels_Display_All();
            }
        }
    }


    /**
     *Μέθοδος με την οποία ο χρήστης ακυρώνει μία κράτηση σε ένα κατάλυμα
     */
    public void resv_canc_acc() throws IOException {
        if(My_Acc_Bookings.isEmpty()){
            System.out.println("Δεν υπάρχουν κρατήσεις");
            return;
        }
        boolean flag = true;
        ArrayList<Reservations> resvs = new ArrayList<>(My_Acc_Bookings.keySet());
        for(int i = 0 ; i < resvs.size() ; i++ ){
            System.out.println((i+1)+ ")");
            resvs.get(i).show();
        }
        while (flag) {
            flag = false;
            System.out.println("Ποία κράτηση θέλετε να ακυρώσετε? (Δώστε νούμερο)");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if( Integer.parseInt(next_string) < 1 || Integer.parseInt(next_string) > resvs.size()){
                System.out.println("Μη έγκυρη τιμή");
                flag = true;
            }
        }
        Reservations to_cancel = resvs.get(Integer.parseInt(next_string)-1);
        My_Acc_Bookings.remove(to_cancel);
        My_Acc_Canceled.put(to_cancel,to_cancel.getAcc());
        to_cancel.getAcc().reservations.remove(to_cancel);
        to_cancel.getAcc().cancellations.add(to_cancel);


        String start= "Κατάλυμα "+ to_cancel.getAcc().getName();

        BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp4.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp4.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("reservations.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter buffer=new BufferedWriter(new FileWriter("cancellations.txt",true))){
            buffer.write("Ακυρώθηκε η κράτηση για το κατάλυμα " + to_cancel.getAcc().getName() + " από " + to_cancel.getStart()
                    + " έως " + to_cancel.getEnd() +  " πελάτης " + to_cancel.getCustomer().getName());
            buffer.newLine();
            buffer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     *Μέθοδος με την οποία ο χρήστης κάνει κράτηση σε ένα δωμάτιο ξενοδοχείου με χρήση φίλτρων
     * @param people Συλλογή με όλους τους χρήστες
     *
     */
    public void make_a_resv_hot(Collection<Person> people) {
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

        String s = "Θέλετε να βάλετε φίλτρο στην αναζήτηση σας? (0-όνομα/1-τοποθεσία/2-αστέρια/3-χωρητικότητα/4-τιμή/5-τετραγωνικά/6-πρωινό/7-wifi/8-air condition/9-parking/α-υπηρεσία καθαρισμού/γ-χωρις φιλτρα/δ-εξοδος";
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
                    System.out.println("Που θέλεις να βρίσκετε το κατάλυμα ?");
                    next_string = sc.next();
                    search_HotelLocation(next_string, temp);
                }
                case "2" -> {
                    System.out.println("Πόσα αστέρια θέλετε να έχει το ?");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_HotelStars(next_string, temp);

                }
                case "3" -> {
                    System.out.println("Πόσα άτομα να υποστηρίζει?");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    search_HotelCapacity(next_string, temp);
                }
                case "4" -> {
                    System.out.println("Εύρος τιμής:\n απο:");
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
                System.out.println("Δεν βρέθηκαν Ξενοδοχεία με αυτά τα κριτήρια αναζήτησης. Θέλετε να αφαιρέσετε το τελευταίο φίλτρο(0-ναι/1-έξοδος)");
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
        Reservations resv = new Reservations(start,end,this,null,null,null);
        temp = availiables_hot(availiable,resv);
        if(temp.isEmpty()){
            System.out.println("Δεν υπάρχει Ξενοδοχείο με αυτές τις ημερομηνίες διαθέσιμες. Θέλετε να αλλάξετε ημερομηνία ? (0-έξοδος/1-αλλαγή ημερομηνίας) ");
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
            System.out.println("Δεν υπάρχει τέτοιο κατάλυμα");
        }
        else {
            room.hotelroomreservations.add(resv);
            My_Hot_Bookings.put(resv,room);
            resv.setHot(room);
            System.out.println("\nΕπιτυχής Κράτηση!!!\n");
        }

        try(BufferedWriter buffer=new BufferedWriter(new FileWriter("reservations.txt",true))){
            buffer.write("Ξενοδοχείο " + resv.getHotel().getName() + " δωμάτιο " + resv.getHot().getName() +
                    " από " + resv.getStart() + " έως " + resv.getEnd() +  " πελάτης " + resv.getCustomer().getName());
            buffer.newLine();
            buffer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

    }


    /**
     *Μέθοδος η οποία επιστρέφει τα δωμάτια ξενοδοχείων τα οποία είναι διαθέσιμα για μία συγκεκριμένη ημερομηνία
     * @param hotels Λίστα με επιθυμητά Ξενοδοχεία
     * @param resv Ημερομηνία που θέλουμε να κάνουμε
     * @return τα διαθέσιμα ξενοδοχεία
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
     *Μέθοδος με την οποία ακυρώνεται η κράτηση σε ένα δωμάτιο ξενοδοχείου
     */
    public void resv_canc_hot() throws IOException {
        if(My_Hot_Bookings.isEmpty()){
            System.out.println("Δεν υπάρχουν κρατήσεις");
            return;
        }
        boolean flag = true;
        ArrayList<Reservations> resvs = new ArrayList<>(My_Hot_Bookings.keySet());
        for(int i = 0 ; i < resvs.size() ; i++ ){
            System.out.println((i+1)+") ");
            resvs.get(i).show();
        }
        while (flag) {
            flag = false;
            System.out.println("Ποία κράτηση θέλετε να ακυρώσετε? (Δώστε νούμερο)");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if( Integer.parseInt(next_string) < 1 || Integer.parseInt(next_string) > resvs.size()){
                System.out.println("Μη έγκυρη τιμή");
                flag = true;
            }
        }
        Reservations to_cancel = resvs.get(Integer.parseInt(next_string)-1);
        My_Hot_Bookings.remove(to_cancel);
        My_Hot_Canceled.put(to_cancel,to_cancel.getHot());
        to_cancel.getHot().hotelroomreservations.remove(to_cancel);
        to_cancel.getHot().hotelroomcancellations.add(to_cancel);


        String start= "Ξενοδοχείο "+ to_cancel.getHotel().getName();
        String roo= "Δωμάτιο " + to_cancel.getHot().getName();

        BufferedReader reader = new BufferedReader(new FileReader("reservations.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp4.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start) && currentLine.contains(roo)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp4.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("reservations.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try(BufferedWriter buffer=new BufferedWriter(new FileWriter("cancellations.txt",true))){
            buffer.write("Ακυρώθηκε η κράτηση για το Ξενοδοχείο " + to_cancel.getHotel().getName() + " από " + to_cancel.getStart()
                    + " έως " + to_cancel.getEnd() +  " πελάτης " + to_cancel.getCustomer().getName());
            buffer.newLine();
            buffer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
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
     * Μέθοδος στην οποία διαβάζεται μία ημερομηνία
     */
    private LocalDate read_date(){
        LocalDate test;
        p = Pattern.compile(".*[0-9]");
        int d,m,y;
        do {
            System.out.println("Χρόνος:");
            next_string = sc.next();
            next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
            y = Integer.parseInt(next_string);
            if(!(y > 2020 && y < 2030)){
                System.out.println("Μη έγκυρη τιμή");
            }
        }while (!(y > 2020 && y < 2030));

        do {
            System.out.println("Μήνας:");
            next_string = sc.next();
            m = Integer.parseInt(ch.validstring(next_string, p, "Μη έγκυρη τιμή"));
            if(!(m > 0 && m <= 12)){
                System.out.println("Μη έγκυρη τιμή");
            }
        }while(!(m > 0 && m <= 12));
        test = LocalDate.of(y,m,1);
        do{
            System.out.println("Ημέρα:");
            next_string = sc.next();
            d = Integer.parseInt(ch.validstring(next_string, p, "Μη έγκυρη τιμή"));
            if(test.lengthOfMonth() < d ){
                System.out.println("Μη έγκυρη τιμή");
            }
        } while (test.lengthOfMonth() < d );
        test = LocalDate.of(y,m,d);
        return test;
    }


    /**
     *Μέθοδος με την οποία διαλέγουμε ένα δωμάτιο ξενοδοχείου
     * @param Rooms Λίστα με δωμάτια ξενοδοχείων
     * @return Το επιλεγμένο δωμάτιο
     */
    public Hotel_room search_Hotel_room(ArrayList<Hotel_room> Rooms){
        Hotel_room room;
        for (int i = 0; i < Rooms.size(); i++) {
            System.out.println((i + 1) + ") Δωμάτιο : " + Rooms.get(i).getName());
        }
        System.out.println("Ποίο θέλετε? (δώστε το αντίστοιχο νούμερο)");
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

    /**
     *Μέθοδος η οποία ελέγχει για δύο ημερομηνίες ότι αρχή είναι πριν το τέλος
     * @param start Ημερομηνία αρχής
     * @param till Ημερομηνία Τέλους
     */

    private void valid_dates(LocalDate start,LocalDate till){
        if(start.isAfter(till)){
            System.out.println("Μη έγκυρη ημερομηνία. Θέλετε να αλλάξετε την αρχή η το τέλος ?(0-αρχή/1-τέλος)");
            next_string = sc.next();
            p = Pattern.compile("[0-1]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if( next_string.equals("0")){
                start = read_date();
                valid_dates(start,till);
            }
            else {
                till = read_date();
                valid_dates(start,till);
            }
        }
    }
}