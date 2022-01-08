/**
 * Αυτή η κλάση αναπαριστά ένα χρήστη με τα χαρακτηριστικά του, ο οποίος μπορεί να δει τα στοιχεία του, τα μηνύματα που έχει και
 * να αναζητήσει ένα κατάλυμα ή ξενοδοχείο
 */

import java.io.*;//
import java.nio.file.Files;//
import java.nio.file.Path;//
import java.nio.file.Paths;//
import java.nio.file.StandardCopyOption;//
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Person {

    private String Type;//
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
        Type=null;//
        Name = null;
        Home_ground = null;
        Phone_number = null;
        Email = null;
        Activated = false;
    }

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    Person(String atype, String aname, String ahome_ground, String aphone_number, String aemail) {
        Type=atype;//
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

    public String getType(){return Type;}//

    public void setType(String type){
        Type=type;
    }//

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
        System.out.println("Ποίο θέλετε? (δώστε το αντίστοιχο νούμερο)");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        if( Integer.parseInt(next_string) > 0 && Integer.parseInt(next_string) <= accommodations.size()){
            acc = accommodations.get(Integer.parseInt(next_string) -1);
            return acc;
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
            System.out.println((i + 1) + ") Ξενοδοχείο : " + hotels.get(i).getName());
        }
        System.out.println("Ποίο θέλετε? (δώστε το αντίστοιχο νούμερο)");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        if( Integer.parseInt(next_string) > 0 && Integer.parseInt(next_string) <= hotels.size()){
            h = hotels.get(Integer.parseInt(next_string) -1);
            return h ;
        }
        else {
            System.out.println("O αριθμός αυτός δεν αντιστοιχεί σε Ξενοδοχείο");
            return null;
        }
    }


    /**
     * μέθοδος που εμφανίζει τα στοιχεία του χρήστη
     */
    public void show_person() {
        System.out.println(" Ο/Η " + getName() + "\n τηλ: " + getPhone_number() + "\n Έδρα: " + getHome_ground() + "\n email: " + getEmail());
        if (this instanceof Accommodation_Provider) {
            System.out.println("Πάροχος καταλύματος\n");
        } else if (this instanceof Hotel_Provider) {
            System.out.println("Πάροχος ξενοδοχείου\n");
        } else if (this instanceof Moderator) {
            System.out.println("Διαχειριστής\n");
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
            System.out.println("Έχεις " + mess + " νέα μήνυμα(τα)");
            messages_count = messages.size();
        }
    }

    /**
     * μέθοδος με την οποία ένας χρήστης μπορεί να συντάξει και να στείλει ένα μήνυμα
     * @param acc_list λίστα με τα μηνύματα
     */
    public void message_send(Collection<Person> acc_list) {

        Person temp = new Person();
        System.out.println("Σε ποιον χρήστη θα ήθελες να στείλεις μήνυμα; Όνομα χρήστη:");
        next_string = sc.next();
        for (Person p : acc_list) {
            if (p.getName().equals(next_string)) {
                temp = p;
            }
        }
        System.out.println("Γράψτε το μήνυμα σας :");
        next_string = sc.next();
        //next_string = sc.nextLine();  //
        temp.messages.add(next_string);

        try (BufferedWriter buffer=new BufferedWriter(new FileWriter("messages.txt",true))){//
            buffer.write("Μήνυμα: " + next_string + " - " + "Παραλήπτης: " + temp.getName() +" - " + "Αποστολέας: " + this.Name);
            buffer.newLine();
            buffer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }//
    }

    /**
     * μέθοδος με την οποία ο χρήστης αποφασίζει την αποστολή, την προβολή, τη διαγραφή ή την έξοδο απο τα μηνύματα
     * @param acc_list λίστα με τα μηνύματα
     */
    public void message(Collection<Person> acc_list) throws IOException {
        boolean flag = true;
        while (flag) {
            System.out.println("Θα θέλατε να δείτε, να στείλετε ή να διαγράψετε μήνυμα;  (Αποστολή,Προβολή,Διαγραφή,Έξοδος)");

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
     * μέθοδος που διαγράφει ένα μήνυμα
     */
    private void messages_delete() throws IOException {
        messages_view();
        System.out.println("Ποίο θα θέλατε να σβήσετε?");
        next_int = sc.nextInt();

        String start= "Μήνυμα: "+ messages.get(next_int-1);

        BufferedReader reader = new BufferedReader(new FileReader("messages.txt"));//
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("messages.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        messages.remove(next_int - 1);//
    }

    /**
     * μέθοδος για την προβολή των μηνυμάτων
     */
    private void messages_view() {
        if (messages.size()==1){//
            try (BufferedWriter buffer=new BufferedWriter(new FileWriter("messages.txt",true))){
                buffer.write("Μήνυμα: " + "Καλωσόρισες " + getName() + "!!" + " - " + "Παραλήπτης: "
                        + Name +" - " + "Αποστολέας: mybooking");
                buffer.newLine();
                buffer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//
        for (int i = 0; i < messages.size(); i++)
            System.out.println((i + 1) + ") " + messages.get(i));
    }

    /**
     * μέθοδος για την επεξεργασία και την αλλαγή των στοιχείων του χρήστη
     */
    public void info_edit(Credentials c) throws IOException {
        Pattern p ;
        Checker ch = new Checker();
        String dump  ;
        boolean flag = true;
        while (flag) {
            System.out.println("Θα θέλατε να αλλάξετε κάτι (Όνομα,Έδρα,Τηλέφωνο,email,Έξοδος)");

            next_string = sc.next();
            switch (next_string) {
                case "Όνομα" -> {
                    System.out.println("Δώστε το νέο όνομα:");
                    dump = sc.nextLine();
                    next_string = sc.nextLine();
                    this.setName(next_string);
                }
                case "Έδρα" -> {
                    System.out.println("Δώστε την νέα έδρα:");
                    next_string = sc.next();
                    this.setHome_ground(next_string);
                }
                case "Τηλέφωνο" -> {
                    System.out.println("Δώστε το νέο τηλέφωνο:");
                    next_string = sc.next();
                    p = Pattern.compile("[0-9]{10}");
                    next_string = ch.validstring(next_string,p,"Μη Έγκυρo Τηλέφωνο");
                    this.setPhone_number(next_string);
                }
                case "email" -> {
                    System.out.println("¨Δώστε το νέο email:");
                    next_string = sc.next();
                    p = Pattern.compile(".*@+[a-zA-Z]+[.]+[a-zA-Z]+$");
                    next_string = ch.validstring(next_string,p,"Μη έγκυρη διεύθυνση email ");
                    this.setEmail(next_string);
                }
                case "Έξοδος" -> flag = false;

            }
        }
        this.show_person();

        String start= "Username:"+c.getUsername();//

        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("users.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
           e.printStackTrace();
        }

        try(BufferedWriter buffer=new BufferedWriter(new FileWriter("users.txt",true))) {
            buffer.write("Username" + ":" + c.getUsername()
                    +" - " + "Κωδικός" + ":" + c.getPassword() + " - " + this.getType() +":" + this.getName()   + " - " + "Τόπος Κατοικίας: " + this.getHome_ground()
                    + " - " + "Email: " + this.getEmail() + " - " + "Τηλέφωνο Επικοινωνίας: " + this.getPhone_number());

            buffer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }//
    }
}