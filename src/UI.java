/**
 * Η κλάση UI αποτελεί τη βασική κλάση με την οποία επικοινωνεί ο χρήστης με το πρόγραμμα.
 * Περιλαμβάνει το HashMap στο οποίο αποθηκεύονται τα στοιχεία οι χρήστες και τα Credentials τους.
 * Έχει έναν χρήστη κάθε του των οποίο δεσμεύει ανάλογα με το είδος χρήστης το οποίο συνδέεται κάθε φορά.
 * Ακόμα έχει ένα αντικείμενο της κλάσης checker για ελέγχους, Scanned για εισαγωγή δεδομένων, και αλλά βοηθητικά οποίες pattern,
 * και Strings στα οποία αποθηκεύονται προσωρινά δεδομένα.
 */

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.time.LocalDate;


public class UI {

    HashMap<Credentials, Person> acc_list = new HashMap<>();

    Credentials usercred;
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
     * Μέθοδος που αναλαμβάνει την αρχικοποίηση χρηστών κάθε κατηγορίας καθώς και καταλυμάτων ξενοδοχείων των συνθηματικών τους και κρατήσεων
     */
    public void initialize() {

        Hotel_Provider p1 = new Hotel_Provider("Ξενοδόχος","Γιάννης Παπαδόπουλος", "Ελλάδα", "6940519933", "giannhs@gmail.com");
        Accommodation_Provider p2 = new Accommodation_Provider("Πάροχος Καταλύματος","Maria McArthur", "USA", "0054861839", "mariamc@gmail.com");

        Credentials c1 = new Credentials("giannis_pap", "12345");
        Credentials c2 = new Credentials("maria_mc", "67890");

        acc_list.put(c1, p1);
        acc_list.put(c2, p2);

        Moderator p3 = new Moderator("Διαχειριστής","Ouzi Makris","Germany","5467538428","ouzi@gmail.com");
        Credentials c3 = new Credentials("ouzi_mak","10032002");
        acc_list.put(c3,p3);

        Customer p4 = new Customer("Πελάτης","Γιώργος Παπαχαραλαμπόπουλος","Θεσσαλονίκη","4354","georgepap@gmail.com");
        Credentials c4 = new Credentials("geo_papaxar","123456");
        acc_list.put(c4,p4);
        p2.setActivated(true);
        p4.setActivated(true);
        p1.setActivated(true);


        Accommodation j = new Accommodation("Αθηνά","Ύδρα","300","50","5","4","10",true,false,false,false,false);
        Accommodation k = new Accommodation("Θέα","Καβάλα","500","68","3","4","8",true,false,true,false,true);
        LocalDate s = LocalDate.of(2021,1,2);
        LocalDate e = LocalDate.of(2021,1,5);
        Reservations resv = new Reservations(s,e,p4,k,null,null);
        k.reservations.add(resv);
        p2.Accommodations.add(k);
        p2.Accommodations.add(j);
        p4.My_Acc_Bookings.put(resv,k);


        Hotel b = new Hotel("Galaxy","Καβάλα","4");
        Hotel_room d1 = new Hotel_room("Τρίκλινο","3","40",true,true,true,true,false,"50");
        Hotel_room d2 = new Hotel_room("Δίκλινο","2","30",true,true,true,false,false,"35");
        b.Rooms.add(d1);
        b.Rooms.add(d2);
        p1.Hotels.add(b);
        resv = new Reservations(s,e,p4,null,b,d1);
        d1.hotelroomreservations.add(resv);
        p4.My_Hot_Bookings.put(resv,d1);

        try(BufferedReader buffer= new BufferedReader(new FileReader("reservations.txt"))){
            String line=buffer.readLine();
        }catch (IOException ex){
            ex.printStackTrace();
        }

        try(BufferedReader buffer= new BufferedReader(new FileReader("users.txt"))){
            String line=buffer.readLine();
        }catch (IOException ex){
            ex.printStackTrace();
        }


        try(BufferedReader buffer= new BufferedReader(new FileReader("accommodations.txt"))){
            String line=buffer.readLine();
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     *Μέθοδος στην οποία ο χρήστης διαλέγει γιατί θέλει να κάνει σύνδεση η εγγραφή και έπειτα καλεί τη μέθοδο με τις επιλογές ανάλογα το είδος του χρήστη
     */
    public void start() throws IOException {
        System.out.println("Καλωσορίσατε, στην εφαρμογή μας!");

        boolean flag = true;
        boolean flag2 = true;
        while (flag) {

            System.out.println("Θα ήθελες να συνδεθείς σε ενα υπάρχων λογαριασμό η να δημιουργήσεις έναν καινούργιο ? (Σύνδεση/Δημιουργία/Έξοδος) ");
            boolean flag1 = true;

            while (flag1) {
                next_string = sc.next();
                switch (next_string) {
                    case "Σύνδεση" -> {
                        login();
                        flag1 = false;
                    }
                    case "Δημιουργία" -> {
                        register();
                        flag1 = false;
                    }
                    case "Έξοδος"->{
                        flag1=false;
                        flag2 = false;
                    }
                    default -> System.out.println("Δεν υπάρχει τέτοια λειτουργία, παρακαλώ δοκιμάστε ξανά");
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

                System.out.println("Θα θέλατε να συνδεθείτε σε άλλο λογαριασμό? (Σύνδεση/Έξοδος)");
                next_string = sc.next();
            }
            if (next_string.equals("Έξοδος")) {
                System.out.println("Ευχαριστούμε που χρησιμοποιήσατε την εφαρμογή μας!");
                flag = false;
            }

        }
    }



    /**
     * Μέθοδος στην οποία ο χρήστης εισάγει όνομα και κωδικό
     * και αν αυτά αντιστοιχούν σε ένα αντικείμενο credentials το οποίο είναι δεσμευμένο με κάποιο χρήστη συνδέεται ως αυτός
     */
    public void login() {
        boolean flag = true;


        while (flag) {

            System.out.println("Username :");
            next_string = sc.next();



            System.out.println("Κωδικός : ");
            next_string2 = sc.next();
            Credentials temp = new Credentials(next_string,next_string2);

            for (Credentials c : acc_list.keySet()) {
                if (c.equal(temp)) {
                    temp = c;
                    break;
                }
            }

            usercred=temp;
            user = acc_list.get(temp);
            if (user == null) {
                System.out.println("Τα στοιχεία που δώσατε δεν ταιριάζουν σε κανέναν λογαριασμό, προσπαθήστε ξανά");
                System.out.println("Θέλετε να δοκιμάσετε ξανά; (Ναι/Όχι)");
                next_string = sc.next();
                if( next_string.equals("Όχι")) {
                    flag = false;
                    this.user = null;
                }
            }
            else if (!user.isActivated()){
                System.out.println("Your account is not activated yet, an administrator will activate it shortly");
                System.out.println("Θέλετε να δοκιμάσετε ξανά; (Ναι/Όχι)");
                next_string = sc.next();
                if( next_string.equals("Όχι")) {
                    flag = false;
                    this.user = null;
                }
            }
            else if (user.isActivated()){
                System.out.println("Καλωσορίσατε στην εφαρμογή " + user.getName());
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

        System.out.println("Κωδικός :  (ο κωδικός πρέπει να περιέχει και νούμερα , γράμματα και οχι σύμβολα!)");
        next_string2 = sc.next();
        p = Pattern.compile("(([a-zA-Z].*[0-9])|([0-9].*[a-zA-Z]))");
        next_string2 = ch.validstring(next_string2,p,"Μη έγκυρος Κωδικός");
        Credentials nea = new Credentials(next_string, next_string2);

        System.out.println("Τι τύπου θα θέλατε να είναι το προφίλ σας; : (Πάροχος καταλυμάτων, Ξενοδόχος, Διαχειριστής, Πελάτης)");
        next_string = sc.nextLine();
        next_string2 = sc.nextLine();
        while (flag) {
            flag = false;
            switch (next_string2) {
                case "Πάροχος καταλυμάτων" -> {
                    neos = new Accommodation_Provider();
                    neos.setType(next_string2);
                    new_person(neos);
                }
                case "Ξενοδόχος" -> {
                    neos = new Hotel_Provider();
                    neos.setType(next_string2);
                    new_person(neos);
                }
                case "Διαχειριστής" -> {
                    neos = new Moderator();
                    neos.setType(next_string2);
                    new_person(neos);
                }
                case "Πελάτης" -> {
                    neos = new Customer();
                    neos.setType(next_string2);
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

        try(BufferedWriter buffer=new BufferedWriter(new FileWriter("users.txt",true))) {
            buffer.write( "Username" + ":" + nea.getUsername() +" - " + "Κωδικός" + ":" + nea.getPassword()
                    + " - " + user.getType() +":" + user.getName() +" - "+ "Τόπος Κατοικίας: " + user.getHome_ground()
                    + " - " + "Email: " + user.getEmail() + " - " + "Τηλέφωνο Επικοινωνίας: " + user.getPhone_number());

            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * Μέθοδος στην οποία ο χρήστης ως accommodation provider διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void accommodation_prov() throws IOException {
        String a = """
                 Τι θα θέλατε να κάνετε;\s
                 0-Προβολή καταλύματος\s
                 1-Προβολή καταλυμάτων\s
                 2-Προσθήκη καταλύματος\s
                 3-Επεξεργασία καταλύματος\s
                 4-Διαγραφή καταλύματος\s
                 5-Συνολικές κρατήσεις\s
                 6-Συνολικές ακυρώσεις\s
                 7-Συνολικό εισόδημα\s
                 8-Μηνύματα\s
                 9-Επεξεργασία στοιχείων\s
                 10-Έξοδος""";
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
                case "9"-> user_acc.info_edit(usercred);
            }
            System.out.println(a);
            next_string = sc.next();
        }

    }

    /**
     * Μέθοδος στην οποία ο χρήστης ως hotel provider διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void hotel_prov() throws IOException {
        String a = """
                 Τι θα θέλατε να κάνετε;\s
                 0-Προβολή Ξενοδοχείου\s
                 1-Προβολή Ξενοδοχείων\s
                 2-Προσθήκη Ξενοδοχείου\s
                 3-Επεξεργασία Ξενοδοχείου\s
                 4-Διαγραφή Ξενοδοχείου\s
                 5-Συνολικές κρατήσεις\s
                 6-Συνολικές ακυρώσεις\s
                 7-Συνολικό εισόδημα\s
                 8-Μηνύματα\s
                 9-Επεξεργασία στοιχείων\s
                 α-Έξοδος""";
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
                case "9" -> user_hot.info_edit(usercred);
            }
            System.out.println(a);
            next_string = sc.next();
        }

    }


    /**
     * Στην οποία ο χρήστης ως moderator διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void modder() throws IOException {
        String a = """
                Τι θα θέλατε να κάνετε?\s
                 0-Προβολή Χρηστών\s
                 1-Προβολή Καταλυμάτων και Ξενοδοχείων\s
                 2-Διαχείριση χρηστών\s
                 3-Προβολή όλων των κρατήσεων\s
                 4-Προβολή όλων των ακυρώσεων\s
                 5-Ακύρωση κράτησης\s
                 6-Μηνύματα\s
                 7-Επεξεργασία στοιχείων\s
                 8-Έξοδος""";
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
                case "7" -> mod.info_edit(usercred);
            }
            System.out.println(a);
            next_string = sc.next();
        }
    }

    /**
     * Μέθοδος την οποία ο χρήστης ως πελάτης διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void customer_options() throws IOException {
        Collection<Person> people = acc_list.values();
        String a = """
                Τι θα θέλατε να κάνετε?\s
                 0-Προβολή Καταλυμάτων και Ξενοδοχείων\s
                 1-Προβολή των κρατήσεων μου\s
                 2-Προβολή των ακυρώσεων μου\s
                 3-Κράτηση καταλύματος\s
                 4-Κράτηση Ξενοδοχείου\s
                 5-Ακύρωση κράτησης καταλύματος\s
                 6-Ακύρωση κράτησης ξενοδοχείου\s
                 7-Μηνύματα\s
                 8-Επεξεργασία στοιχείων\s
                 9-Έξοδος""";
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
                case "8" -> customer.info_edit(usercred);
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
        System.out.println("Username :");
        next_string = sc.next();
        for (Object o : a) {
            Credentials c = (Credentials) o;
            if (next_string.equals(c.getUsername())) {
                System.out.println("Το username αυτό υπάρχει ήδη, δοκιμάστε ξανά");
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

        System.out.println("Όνομα :");
        next_string2 = sc.nextLine();
        neos.setName(next_string2);

        System.out.println("Έδρα :");
        next_string = sc.next();
        neos.setHome_ground(next_string);

        System.out.println("Τηλέφωνο :");
        next_string = sc.next();
        p = Pattern.compile("[0-9]{10}");
        next_string = ch.validstring(next_string,p,"Μη έγκυρo Τηλέφωνο");
        neos.setPhone_number(next_string);

        System.out.println("Email :");
        next_string = sc.next();
        p = Pattern.compile(".*@+[a-zA-Z]+[.]+[a-zA-Z]+$");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη διεύθυνση email ");
        neos.setEmail(next_string);

        neos.show_person();
    }

}

