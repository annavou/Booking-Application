/**
 * Αυτή η κλάση αναπαριστά έναν πάροχο καταλύματος με τα χαρακτηριστικά του, ο οποίος μπορεί να προσθέσει το κατάλυμα του,
 * να δεί τα καταλύματα του, να κάνει αλλαγές αν επιθυμεί σε κάποιο, να δεί τις κρατήσεις του καθώς και τις ακυρώσεις του.
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    Accommodation_Provider(String atype,String aname , String ahome_ground, String aphone_number, String aemail){
        super(atype,aname,ahome_ground,aphone_number,aemail);
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
            System.out.println("Δεν έχετε καταχωρημένα καταλύματα");
        }
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει ένα συγκεκριμένο απο τα καταλύματα του
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
    public void Accomodation_Edit() throws IOException {
        boolean flag = true;

        acc = search_acc(Accommodations);

        if (acc == null) {
            return;
        }

        String start= "Κατάλυμα:"+acc.getName();

        BufferedReader reader = new BufferedReader(new FileReader("accommodations.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp2.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp2.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Τι θα ήθελες να αλλάξεις");
        next_string = sc.next();

        while (flag) {
            flag = false;
            switch (next_string) {
                case "Όνομα" -> {
                    System.out.println("Δώσε το καινούργιο όνομα:");
                    next_string = sc.next();
                    acc.setName(next_string);
                    acc.show_accommodation();
                }
                case "Τοποθεσία" -> {
                    System.out.println("Δώσε την καινούργια τοποθεσία:");
                    next_string = sc.next();
                    acc.setLocation(next_string);
                    acc.show_accommodation();
                }
                case "Τιμή" -> {
                    System.out.println("Δώσε την καινούργια τιμή:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη τιμή");
                    acc.setPrice(next_string);
                    acc.show_accommodation();
                }
                case "Τετραγωνικά" -> {
                    System.out.println("Δώσε τα καινούργια τετραγωνικά:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρα τετραγωνικά");
                    acc.setSqmeter(next_string);
                    acc.show_accommodation();
                }
                case "Αστέρια" -> {
                    System.out.println("Δώσε τα καινούργια αστέρια");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρα αστέρια");
                    acc.setStars(next_string);
                    acc.show_accommodation();
                }
                case "Δωμάτια" -> {
                    System.out.println("Δώσε τα καινούργια δωμάτια:");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρος αριθμός δωματίων");
                    acc.setRooms(next_string);
                    acc.show_accommodation();
                }
                case "Χωρητικότητα" -> {
                    System.out.println("Δώσε τη καινούργια χωρητικότητα σε άτομα: (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    p = Pattern.compile(".*[0-9]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη χωρητικότητα ");
                    acc.setCapacity(next_string);
                    acc.show_accommodation();
                }
                case "AC" -> {
                    System.out.println("Θα παρέχει το κατάλυμα ac? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setAc(next_bool);
                    acc.show_accommodation();
                }
                case "Πρωίνο" -> {
                    System.out.println("Θα παρέχει το κατάλυμα πρωινό? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη εγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setBreakfast(next_bool);
                    acc.show_accommodation();
                }
                case "Wifi" -> {
                    System.out.println("Θα παρέχει το κατάλυμα wifi? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setWifi(next_bool);
                    acc.show_accommodation();
                }
                case "Parking" -> {
                    System.out.println("Θα παρέχει το κατάλυμα parking? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setParking(next_bool);
                    acc.show_accommodation();
                }
                case "Υπηρεσία Καθαρισμού" -> {
                    System.out.println("Θα παρέχει το κατάλυμα υπηρεσία καθαρισμού? : (0-ΟΧΙ/1-ΝΑΙ)");
                    next_string = sc.next();
                    Pattern p = Pattern.compile("[0-1]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη Τιμή");
                    next_bool = next_string.equals("1");
                    acc.setCleaning_services(next_bool);
                    acc.show_accommodation();
                }
                case "Έξοδος" -> {}
                default -> {
                    System.out.println("Δεν υπάρχει τέτοια κατηγορία");
                    System.out.println("Θέλετε να ξαναδοκιμάσετε η οχι (Κατηγορία/οχι)");
                    next_string = sc.next();
                    flag = !next_string.equals("οχι");
                }
            }
        }


        try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){
            buffer.write("Κατάλυμα:" + acc.getName()+" - "+ "Τοποθεσία: " + acc.getLocation() + " - "+ "Τιμή ανα βράδυ: " + acc.getPrice() + "$"
                    + " - " + "Τετραγωνικά δωματίου: " + acc.getSqmeter() + " - " + "Χωρητικότητα Δωματίου: " + acc.getCapacity() +"άτομα"
                    + " - " + "Αστέρια Δωματίου: " + acc.getStars() +" - "+ "Το κατάλυμα προσφέρει: ");

            if(acc.isAc()){
                buffer.write("Κλιματισμό ");
            }
            if(acc.isBreakfast()){
                buffer.write("Πρωινό ");
            }
            if (acc.isCleaning_services()){
                buffer.write("Υπηρεσίες Καθαρισμού ");
            }
            if (acc.isParking()){
                buffer.write("Parking ");
            }
            if(acc.isWifi()){
                buffer.write("Wifi.");
            }
            buffer.newLine();
            buffer.flush();
        }catch (IOException e){
            e.printStackTrace();
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



        System.out.println("Όνομα");
        aname = sc.next();

        System.out.println("Τοποθεσία");
        alocation = sc.next();

        System.out.println("Τιμή");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Τιμή");
        aprice = next_string ;

        System.out.println("Τετραγωνικά");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρα Τετραγωνικά");
        asqmeter = next_string;

        System.out.println("Αστέρια");
        next_string = sc.next();
        p = Pattern.compile("[0-5]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρα Αστέρια");
        astars = next_string;

        System.out.println("Δωμάτια");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρα Δωμάτια");
        arooms = next_string;

        System.out.println("Χωρητικότητα");
        next_string = sc.next();
        p = Pattern.compile(".*[0-9]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Χωρητικότητα");
        acapacity = next_string;

        System.out.println("Air Condition (0-OXI/1-NAI) ");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Τιμή");
        aac = next_string.equals("1");

        System.out.println("Πρωινό (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Τιμή");
        abreakfast = next_string.equals("1");

        System.out.println("Parking (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Τιμή");
        aparkin = next_string.equals("1");


        System.out.println("Wifi (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Τιμή");
        awifi = next_string.equals("1");

        System.out.println("Υπηρεσία καθαρισμού (0-OXI/1-NAI)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη Τιμή");
        acleaning_services = next_string.equals("1");


        Accommodation temp = new Accommodation(aname,alocation,aprice,asqmeter,astars,arooms,acapacity,abreakfast,awifi,aac,aparkin,acleaning_services);
        Accommodations.add(temp);
        temp.show_accommodation();

        try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){
            buffer.write("Κατάλυμα:" + temp.getName()+" - "+ "Τοποθεσία: " + temp.getLocation() + " - "+ "Τιμή ανα βράδυ: " + temp.getPrice() + "$"
                    + " - " + "Τετραγωνικά δωματίου: " + temp.getSqmeter() + " - " + "Χωρητικότητα Δωματίου: " + temp.getCapacity() +"άτομα"
                    + " - " + "Αστέρια Δωματίου: " + temp.getStars() +" - "+ "Το κατάλυμα προσφέρει: ");

            if(temp.isAc()){
                buffer.write("Κλιματισμό ");
            }
            if(temp.isBreakfast()){
                buffer.write("Πρωινό ");
            }
            if (temp.isCleaning_services()){
                buffer.write("Υπηρεσίες Καθαρισμού ");
            }
            if (temp.isParking()){
                buffer.write("Parking ");
            }
            if(temp.isWifi()){
                buffer.write("Wifi.");
            }
            buffer.newLine();
            buffer.flush();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * μέθοδος με την οποία ο πάροχος διαγράφει ένα απο τα καταλύματα του
     */
    public void Accommodation_delete() throws IOException {
        acc = search_acc(Accommodations);
        if (acc == null){
            return;
        }
        Accommodations.remove(acc);

        String start= "Κατάλυμα:"+acc.getName();

        BufferedReader reader = new BufferedReader(new FileReader("accommodations.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp2.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp2.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

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
            System.out.println("Το κατάλυμα "+acc.getName() + " έχει ακυρωθεί για : ");
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
