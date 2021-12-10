/**
 * Αυτή η κλάση αναπαριστά ένα χρήστη με τα χαρακτηριστηκά του, ο οποίος μπορεί να δει τα στοιχεία του, τα μυνήματα που έχει και
 * να αναζητήσει ένα κατάλυμα ή ξενοδοχείο
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Person {

    private String Name;
    private String Home_ground;
    private String Phone_number;
    private String Email;
    private boolean Activated;


    ArrayList<String> messages = new ArrayList<>();
    int messages_count;

    Scanner sc = new Scanner(System.in);

    int next_int;
    boolean next_bool;
    String next_string;
    Pattern p;
    Checker ch = new Checker();

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Person() {
        Name = null;
        Home_ground = null;
        Phone_number = null;
        Email = null;
        Activated = false;
    }

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    Person(String aname, String ahome_ground, String aphone_number, String aemail) {
        Name = aname;
        Home_ground = ahome_ground;
        Phone_number = aphone_number;
        Email = aemail;
        messages.add("Καλωσόρισες " + getName() + "!!");
        messages_count = 0;
        Activated = false;
    }

    public boolean isActivated() {
        return Activated;
    }

    public void setActivated(boolean activated) {
        Activated = activated;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHome_ground() {
        return Home_ground;
    }

    public void setHome_ground(String home_ground) {
        Home_ground = home_ground;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    /**
     * μέθοδος με την οποία ένας χρήστης μπορεί να αναζητήσει κάποιο κατάλυμα
     * @param accommodations λίστα με όλα τα καταλύματα
     * @return το κατάλυμα που ψάχνει αν υπάρχει
     */
    public Accommodation search_acc(ArrayList<Accommodation> accommodations) {
        Accommodation acc ;
        for (int i = 0; i < accommodations.size(); i++) {
            System.out.println((i + 1) + ") Κατάλυμα : " + accommodations.get(i).getName());
        }
        System.out.println("Ποιό θέλετε? (δωστε το αντίστοιχο νουμερο)");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        if( Integer.parseInt(next_string) > 0 && Integer.parseInt(next_string) <= accommodations.size()){
            acc = accommodations.get(Integer.parseInt(next_string) -1);
            return acc ;
        }
        else {
            System.out.println("O αριθμός αυτός δεν αντιστοιχεί σε Κατάλυμα");
            return null;
        }
    }

    /**
     * μέθοδος με την οποία ένας χρήστης μπορεί να αναζητήσει κάποιο ξενοδοχείο
     * @param hotels λίστα με όλα τα ξενοδοχεία
     * @return το ξενοδοχείο που ψάχνει αν υπάρχει
     */
    public Hotel search_hot(ArrayList<Hotel> hotels) {
        Hotel h;
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println((i + 1) + ") Ξεναδοχείο : " + hotels.get(i).getName());
        }
        System.out.println("Ποιό θέλετε? (δωστε το αντίστοιχο νουμερο)");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        if( Integer.parseInt(next_string) > 0 && Integer.parseInt(next_string) <= hotels.size()){
            h = hotels.get(Integer.parseInt(next_string) -1);
            return h ;
        }
        else {
            System.out.println("O αριθμός αυτός δεν αντιστοιχεί σε Ξεναδοχείο");
            return null;
        }
    }


    /**
     * μέθοδος που εμφανίζει τα στοιχεία του χρήστη
     */
    public void show_person() {
        System.out.println(" Ο/Η " + getName() + "\n τηλ: " + getPhone_number() + "\n Εδρα: " + getHome_ground() + "\n email: " + getEmail());
        if (this instanceof Accommodation_Provider) {
            System.out.println("Πάροχος καταλυμάτων\n");
        } else if (this instanceof Hotel_Provider) {
            System.out.println("Πάροχος ξεναδοχείων\n");
        } else if (this instanceof Moderator) {
            System.out.println("Διαχηρηστής\n");
        } else {
            System.out.println("Πελάτης\n");
        }

    }

    /**
     * μέθοδος η οποία εμφανίζει τις ειδοποιήσεις των μηνυμάτων
     */
    public void messages_notifications() {
        int mess = messages.size() - messages_count;
        if (mess > 0) {
            System.out.println("Έχεις " + mess + " νέα μηνυμα(τα)");
            messages_count = messages.size();
        }
    }

    /**
     * μέθοδος με την οποία ένας χρήστης μπορεί να συντάξει και να στείλει ένα μήνυμα
     * @param acc_list λίστα με τα μηνύματα
     */
    public void message_send(Collection<Person> acc_list) {

        Person temp = new Person();
        System.out.println("Σε ποιον χρήστη θα ήθελες να στείλεις μήνυμα?");
        next_string = sc.next();
        for (Person p : acc_list) {
            if (p.getName().equals(next_string)) {
                temp = p;
            }
        }
        System.out.println("Γράψτε το μήνυμα σας :");
        next_string = sc.next();
        next_string = sc.nextLine();
        temp.messages.add(next_string);
    }

    /**
     * μέθοδος με την οποία ο χρήστης αποφασίζει την αποστολή,την προβολή, την διαγραφή ή υην έξοδο απο τα μηνύματα
     * @param acc_list λίστα με τα μηνύματα
     */
    public void message(Collection<Person> acc_list) {
        boolean flag = true;
        while (flag) {
            System.out.println("Θα θελατε να δείτε να στείλετε να διαγράψετε μυνημα?  (αποστολη,προβολη,διαγραφη,εξοδος)");

            next_string = sc.next();
            switch (next_string) {
                case "Αποστολή" -> message_send(acc_list);
                case "Προβολή" -> messages_view();
                case "Διαγραφή" -> messages_delete();
                case "Έξοδος" -> flag = false;

            }
        }
    }

    /**
     * μέθοδος που διγράφει ένα μήνυμα
     */
    private void messages_delete() {
        messages_view();
        System.out.println("Ποίο θα θέλατε να σβήσετε?");
        next_int = sc.nextInt();
        messages.remove(next_int - 1);
    }

    /**
     * μέθοδος για την προβολή των μηνυμάτων
     */
    private void messages_view() {
        for (int i = 0; i < messages.size(); i++)
            System.out.println((i + 1) + ") " + messages.get(i));
    }

    /**
     * μέθοδος για την επεξεργασία και την αλλαγή των στοιχείων του χρήστη
     */
    public void info_edit() {
        Pattern p ;
        Checker ch = new Checker();
        String dump  ;
        boolean flag = true;
        while (flag) {
            System.out.println("Θα θέλατε να αλλάξετε κάτι (ονομα,εδρα,τηλεφωνο,email,εξοδος)");

            next_string = sc.next();
            switch (next_string) {
                case "ονομα " -> {
                    System.out.println("Δώστε το νέο όνομα:");
                    dump = sc.nextLine();
                    next_string = sc.nextLine();
                    this.setName(next_string);
                }
                case "εδρα" -> {
                    System.out.println("Δώστε την νέα έδρα:");
                    next_string = sc.next();
                    this.setHome_ground(next_string);
                }
                case "τηλεφωνο" -> {
                    System.out.println("Δώστε το νέο τηλέφωνο:");
                    next_string = sc.next();
                    p = Pattern.compile("[0-9]{10}");
                    next_string = ch.validstring(next_string,p,"Μη Έγκυρo Τηλέφωνο");
                    this.setPhone_number(next_string);
                }
                case "email" -> {
                    System.out.println("¨Δώστε το νέο emial:");
                    next_string = sc.next();
                    p = Pattern.compile(".*@+[a-zA-Z]+[.]+[a-zA-Z]+$");
                    next_string = ch.validstring(next_string,p,"Μη έγκυρη διεύθυνση email ");
                    this.setEmail(next_string);
                }
                case "Έξοδος" -> flag = false;

            }
        }
        this.show_person();
    }
}
