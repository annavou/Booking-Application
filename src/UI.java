/**
 * Η κλάση UI αποτελεί την βασική κλάση με την οποία επικοινωνεί ο χρήστης με το πρόγραμμα.
 * Περιλαμβάνει το HashMap στο οποιο αποθηκεύονται τα στοιχεία οι χρήστες και τα Credentials τους.
 * Έχει έναν χρήστη κάθε του των οποίο δεσμεύει ανάλογα με το είδος χρήστης το οποίο συνδέεται κάθε φορά.
 * Ακόμα έχει ένα αντικείμενο της κλάσης checker για ελέγχους, Scanned για εισαγωγή δεδομένων, και αλλά βοηθητικά οποίες pattern,
 * και Strings στα οποία αποθηκεύονται προσωρινά δεδομένα.
 */

import java.util.*;
import java.util.regex.Pattern;
import java.time.LocalDate;


public class UI {

    HashMap<Credentials, Person> acc_list = new HashMap<>();


    Person user;
    Accommodation_Provider user_acc;
    Hotel_Provider user_hot ;
    Moderator mod;
    Customer customer;


    String next_string;
    String next_string2;
    String dump;
    Pattern p;

    Checker ch = new Checker();
    Scanner sc = new Scanner(System.in);

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    UI() {
    }


    /**
     * Μέθοδος που αναλαμβάνει την αρχικοποίηση χρηστών κάθε κατηγορίας καθώς και καταλυμάτων ξενοδοχείων των συνθηματικων τους και κρατήσεων
     */
    public void initialize() {

        Hotel_Provider p1 = new Hotel_Provider("γιαννης", "ellada", "6955", "giannhs@gmail.com");
        Accommodation_Provider p2 = new Accommodation_Provider("μαρια", "albania", "335", "...");

        Credentials c1 = new Credentials("γιαννης", "12345");
        Credentials c2 = new Credentials("μαρια", "67890");

        acc_list.put(c1, p1);
        acc_list.put(c2, p2);


        Accommodation j = new Accommodation("k1","k1","300","50","5","4","10",true,false,false,false,false);
        Accommodation k = new Accommodation("θεα","Καβάλα","500","68","3","4","8",true,false,true,false,true);
        LocalDate s = LocalDate.of(2021,1,2);
        LocalDate e = LocalDate.of(2021,1,5);
        Reservations resv = new Reservations(s,e,"γιωργος",k,null);
        k.reservations.add(resv);
        p2.Accommodations.add(k);
        p2.Accommodations.add(j);


        Hotel b = new Hotel("galaxy","kavala","4");
        Hotel_room d1 = new Hotel_room("τρικλινο","3","40",true,true,true,true,false,"50");
        Hotel_room d2 = new Hotel_room("δικλινο","2","30",true,true,true,false,false,"35");
        b.Rooms.add(d1);
        b.Rooms.add(d2);
        p1.Hotels.add(b);
        resv = new Reservations(s,e,"γιωργος",null,d1);
        d1.hotelroomreservations.add(resv);

        Moderator p3 = new Moderator("ouzi","deutchland","5467",",,");
        Credentials c3 = new Credentials("ouzi","10032002");
        acc_list.put(c3,p3);

        Customer p4 = new Customer("γιωργος","thessaloniki","4354","v");
        Credentials c4 = new Credentials("γιωργος","123");
        acc_list.put(c4,p4);
        p2.setActivated(true);
        p4.setActivated(true);
        p1.setActivated(true);

    }

    /**
     *Μέθοδος στην οποία ο χρήστης διαλέγει γιατί θέλει να κάνει σύνδεση η εγγραφή και έπειτα καλεί την μέθοδο με τις επιλογές ανάλογα το είδος του χρήστη
     */
    public void start() {
        System.out.println("Καλωςορισατε, στην εφαρμογη μας!");

        boolean flag = true;
        boolean flag2 = true;
        while (flag) {

            System.out.println("Θα ήθελες να συνδεθείς σε ενα υπάρχων λογαργιασμό η να δημιουργήσεις εναν καινούργιο ? (συνδεση/δημιουργια/εξοδος) ");
            boolean flag1 = true;

            while (flag1) {
                next_string = sc.next();
                switch (next_string) {
                    case "συνδεση" -> {
                        login();
                        flag1 = false;
                    }
                    case "δημιουργεια" -> {
                        register();
                        flag1 = false;
                    }
                    case "εξοδος"->{
                        flag1=false;
                        flag2 = false;
                    }
                    default -> System.out.println("Δεν υπαχρχει τετοια λειτουγια, παρακαλω δοκιμαστε ξανα");
                }
            }




        if(flag2 && user!=null ) {
            user.messages_notifications();

            if (user instanceof Accommodation_Provider) {
                user_acc = (Accommodation_Provider) this.user;
                accommodation_prov();
            } else if (user instanceof Hotel_Provider) {
                user_hot = (Hotel_Provider) this.user;
                hotel_prov();
            } else if (user instanceof Moderator) {
                mod = (Moderator) this.user;
                modder();
            } else {
                customer = (Customer) this.user;
                customer_options();
            }

            System.out.println("Θα θέλατε να συνδεθειτέ σε αλλο λογαργιασμό? (συνεδση/εξοδος)");
            next_string = sc.next();
        }
            if (next_string.equals("εξοδος")) {
                System.out.println("τσαο");
                flag = false;
            }

        }
    }



    /**
     * Μέθοδος στην οποία ο χρήστης εισάγει όνομα και κωδικό ,
     * και αν αυτά αντιστοιχούν σε ένα αντικείμενο credentials το οποίο είναι δεσμευμένο με κάποιο χρήστη συνδέεται ως αυτός
     */
    public void login() {
        boolean flag = true;


        while (flag) {

            System.out.println("Ονομα :");
            next_string = sc.next();



                System.out.println("Κωδικος : ");
                next_string2 = sc.next();
                Credentials temp = new Credentials(next_string,next_string2);

            for (Credentials c : acc_list.keySet()) {
                if (c.equal(temp)) {
                    temp = c;
                    break;
                }
            }


            user = acc_list.get(temp);
            if (user == null) {
                System.out.println("The credentials you gave do not match any account, try again");
                System.out.println("Θες να ξανα δοκιμασεις? (ναι/οχι)");
                next_string = sc.next();
                if( next_string.equals("οχι")) {
                    flag = false;
                    this.user = null;
                }
            }
            else if (!user.isActivated()){
                System.out.println("Your account is not activated yet, an administrator will activate it shortly");
                System.out.println("Θες να ξανα δοκιμασεις? (ναι/οχι)");
                next_string = sc.next();
                if( next_string.equals("οχι")) {
                    flag = false;
                    this.user = null;
                }
            }
            else if (user.isActivated()){
                System.out.println("Welcome to the app " + user.getName());
                flag = false;
            }
        }

    }

    /**
     *Μέθοδος στην οποία ο χρήστης δημιουργεί ένα καινούργιο λογαριασμό εισάγοντας κωδικό,
     * και όνομα και την κατηγορία λογαριασμού που θέλετε δημιουργήσει
     */
    public void register() {
        boolean flag = true ;
        Person neos = new Person();

        Set<Credentials> c = acc_list.keySet();
        Object[] a = c.toArray();

        next_string = ValidCheck("",a);

        System.out.println("Κωδικός :  (ο κωδικος πρεπει να περιεχει και νουμερα , γραμματα και οχι συμβολα!)");
        next_string2 = sc.next();
        p = Pattern.compile("(([a-zA-Z].*[0-9])|([0-9].*[a-zA-Z]))");
        next_string2 = ch.validstring(next_string2,p,"Μη εγκυρος Κωδικος");
        Credentials nea = new Credentials(next_string, next_string2);

        System.out.println("Τι τυπου θα θελατε να ειναι το προφιλ σας? : (παροχος καταλυματων,παροχος ξεναδοχειων,διαχειριστης,πελατης)");
        next_string = sc.nextLine();
        next_string2 = sc.nextLine();
        while (flag) {
            flag = false;
            switch (next_string2) {
                case "παροχος καταλυματων" -> {
                    neos = new Accommodation_Provider();
                    new_person(neos);
                }
                case "παροχος ξεναδοχειων" -> {
                    neos = new Hotel_Provider();
                    new_person(neos);
                }
                case "διαχειριστης" -> {
                    neos = new Moderator();
                    new_person(neos);
                }
                case "πελατης" -> {
                    neos = new Customer();
                    new_person(neos);
                }
                default -> {
                    System.out.println("Μη δεκτή κατηγορία,δοκιμάστε ξανά");
                    next_string = sc.nextLine();
                    flag = true;
                }
            }
        }
        acc_list.put(nea, neos);
        user = neos;
        System.out.println("Καλωσορίσατε  " + user.getName() +"!!");
    }


    /**
     * Μέθοδος στην οποία ο χρήστης ως accommodation provider διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void accommodation_prov() {
        String a = """
                Τι θα θέλατε να κάνετε?\s
                 0-προβολη καταλυματος\s
                 1-προβολη καταλυματων\s
                 2-προσθηκη καταλυματος\s
                 3-επεξεργασια καταλυματος\s
                 4-διαγραφη καταλυματος\s
                 5-Συνολικές κρατήσεις\s
                 6-Συνολικές ακυρώσεις\s
                 7-Συνολικό εισόδιμα\s
                 8-μυνηματα\s
                 9-επεξεργασια στοιχιων\s
                 10-εξοδος""";
        System.out.println(a);
        next_string = sc.next();
        p = Pattern.compile("[0-9]|[10]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        while (!next_string.equals("10")) {
            switch (next_string) {
                case "0" -> user_acc.Accomodations_Display();
                case "1" -> user_acc.Accomodations_Display_All();
                case "2" -> user_acc.Accommodation_add();
                case "3" -> user_acc.Accomodation_Edit();
                case "4" -> user_acc.Accommodation_delete();
                case "5" -> user_acc.sum_resv();
                case "6" -> user_acc.sum_resv_cancel();
                case "7" -> user_acc.sum_income();
                case "8" -> user_acc.message(acc_list.values());
                case "9"-> user_acc.info_edit();
            }
            System.out.println(a);
            next_string = sc.next();
        }

    }

    /**
     * Μέθοδος στην οποία ο χρήστης ως hotel provider διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void hotel_prov() {
        String a = """
                Τι θα θέλατε να κάνετε?\s
                 0-προβολη Ξεναδοχείου\s
                 1-προβολη Ξεναδοχείων\s
                 2-προσθηκη Ξεναδοχείου\s
                 3-επεξεργασια Ξεναδοχείου\s
                 4-διαγραφη Ξεναδοχείου\s
                 5-Συνολικές κρατήσεις\s
                 6-Συνολικές ακυρώσεις\s
                 7-Συνολικό εισόδιμα\s
                 8-μυνηματα\s
                 9-επεξεργασια στοιχιων\s
                 α-εξοδος""";
        System.out.println(a);
        next_string = sc.next();
        p = Pattern.compile("[0-9]|[α]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        while (!next_string.equals("α")) {
            switch (next_string) {
                case "0" -> user_hot.Hotels_Display();
                case "1" -> user_hot.Hotels_Display_All();
                case "2" -> user_hot.Hotel_add();
                case "3" -> user_hot.Hotel_edit();
                case "4" -> user_hot.Hotel_delete();
                case "5" -> user_hot.sum_resv();
                case "6" -> user_hot.sum_resv_cancel();
                case "7" -> user_hot.sum_income();
                case "8" -> user_hot.message(acc_list.values());
                case "9" -> user_hot.info_edit();
            }
            System.out.println(a);
            next_string = sc.next();
        }

    }


    /**
     * Στην οποία ο χρήστης ως moderator διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void modder() {
        String a = """
                Τι θα θέλατε να κάνετε?\s
                 0-προβολη Χρηστών\s
                 1-προβολη Καταλυμάτων και Ξεναδοχείων\s
                 2-Διαχηριση χρηστών\s
                 3-Προβολή όλων των κρατήσεων\s
                 4-Προβολή όλων των ακυρώσεων\s
                 5-Ακύρωση κράτησης\s
                 6-μυνηματα\s
                 7-επεξεργασια στοιχιων\s
                 8-εξοδος""";
        System.out.println(a);
        next_string = sc.next();
        p = Pattern.compile("[0-8]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        while (!next_string.equals("8")) {
            switch (next_string) {
                case "0" -> mod.see_all_users(acc_list.values());
                case "1" -> mod.see_all_accommodations(acc_list.values());
                case "2" -> mod.account_manage(acc_list.values());
                case "3" -> mod.see_all_resv(acc_list.values());
                case "4" -> mod.see_all_resv_cancel(acc_list.values());
                case "5" -> mod.resv_canc(acc_list.values());
                case "6" -> mod.message(acc_list.values());
                case "7" -> mod.info_edit();
            }
            System.out.println(a);
            next_string = sc.next();
        }
    }

    /**
     * Μέθοδος την οποία ο χρήστης ως πελάτης διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void customer_options() {
        Collection<Person> people = acc_list.values();
        String a = """
                Τι θα θέλατε να κάνετε?\s
                 0-προβολη Καταλυμάτων και Ξεναδοχείων\s
                 1-Προβολή των κρατήσεων μου\s
                 2-Προβολή των ακυρώσεων μου\s
                 3-Κράτηση καταλύματος\s
                 4-Κράτηση Ξεναδοχείου\s
                 5-Ακύρωση κράτησης καταλύματος\s
                 6-Ακύρωση κράτησης ξεναδοχείου\s
                 7-μυνηματα\s
                 8-επεξεργασια στοιχιων\s
                 9-εξοδος""";
        System.out.println(a);
        next_string = sc.next();
        p = Pattern.compile("[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        while (!next_string.equals("9")) {
            switch (next_string) {
                case "0" -> customer.see_all_accommodations(people);
                case "1" -> customer.show_my_bookings();
                case "2" -> customer.show_my_cancellations();
                case "3" -> customer.make_a_resv_acc(people);
                case "4" -> customer.make_a_resv_hot(people);
                case "5" -> customer.resv_canc_acc();
                case "6" -> customer.resv_canc_hot();
                case "7" -> customer.message(people);
                case "8" -> customer.info_edit();
            }
            System.out.println(a);
            next_string = sc.next();
        }

    }


    /**
     *Μέθοδος η οποία επαληθεύει ότι το username που έδωσε ο χρήστης δεν υπάρχει ηδη
     * @param s Το όνομα το οποίο έδωσε ο χρήστης και θα ελέγξουμε αν είναι μοναδικό
     * @param a Πίνακας αντικειμένων τα οποία θα χρησιμοποιηθούν για τον έλεγχο
     * @return Το μοναδικό username
     */
    private String ValidCheck(String s,Object[] a) {
        System.out.println("Ονομα :");
        next_string = sc.next();
        for (Object o : a) {
            Credentials c = (Credentials) o;
            if (next_string.equals(c.getUsername())) {
                System.out.println("Το όνομα αυτό υπάρχει ηδη, δοκιμάστε ξανά");
                next_string = ValidCheck(next_string, a);
            }
        }
        return next_string;
    }

    /**
     *Μέθοδος στην οποία ο χρήστης εισάγει τα στοιχεία για να δημιουργήσει έναν καινούργιο person
     * @param  neos User του οποίου θα ενημερώσουμε τα στοιχεία του
     */
    private void new_person(Person neos) {


         System.out.println("Ονομα :");
         next_string2 = sc.nextLine();
         neos.setName(next_string2);

         System.out.println("Εδρα :");
         next_string = sc.next();
         neos.setHome_ground(next_string);

         System.out.println("Τηλέφωνο :");
         next_string = sc.next();
         p = Pattern.compile("[0-9]{10}");
         next_string = ch.validstring(next_string,p,"Μη εγκυρo Τηλέφωνο");
         neos.setPhone_number(next_string);

         System.out.println("Email :");
         next_string = sc.next();
         p = Pattern.compile(".*@+[a-zA-Z]+[.]+[a-zA-Z]+$");
         next_string = ch.validstring(next_string,p,"Μη εγκυρη διεύθυνση email ");
         neos.setEmail(next_string);

         neos.show_person();
    }

}



