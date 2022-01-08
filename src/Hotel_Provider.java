/**
 * Κλάση η οποία αναπαριστά έναν πάροχο ξενοδοχείου.
 * Κληρονόμοι από την κλάση person τα βασικά χαρακτηριστικά.
 * Ακόμα έχει μία λίστα με ξενοδοχεία τα οποία είναι τα ξενοδοχεία τα οποία προσφέρει
 */

import java.io.*;//
import java.nio.file.Files;//
import java.nio.file.Path;//
import java.nio.file.Paths;//
import java.nio.file.StandardCopyOption;//
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;

public class Hotel_Provider extends  Person {

    ArrayList<Hotel> Hotels = new ArrayList<>();

    Hotel hotel;


    /**
     * Ο κενός κατασκευαστής
     */
    Hotel_Provider() {}


    /**
     * Κατασκευαστής ο οποίος δέχεται ως όρισμα τα πεδία του person και τα περνάει σε αυτόν
     */
    Hotel_Provider(String atype, String aname, String ahome_ground, String aphone_number, String aemail) {
        super(atype, aname, ahome_ground, aphone_number, aemail);
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
            System.out.println("Δεν έχετε καταχωρημένα Ξενοδοχεία");
        }
    }


    /**
     * Μέθοδος η οποία εμφανίζει ένα συγκεκριμένο ξενοδοχείο
     */
    public void Hotels_Display() {
        System.out.println("Ποίο Ξενοδοχείο θα θέλατε να δείτε?");

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

        try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){//
            buffer.write("Ξενοδοχείο: " + temp.getName() +" - "+ "Στην τοποθεσία: " + temp.getLocation() + " - " + "Αστέρια Ξενοδοχείου: "
                    + temp.getStars() + " - " + "Με τα εξής δωμάτια: " );
            buffer.newLine();

            for(Hotel_room r: temp.Rooms){
                buffer.write("            ");
                buffer.write("Δωμάτιο: " + r.getName() + " - "+"Τιμή ανα βράδυ: " + r.getPrice() + "$" + " - " + "Τετραγωνικά δωματίου: "
                        + r.getSqmeter() + " - " + "Χωρητικότητα Δωματίου: " + r.getCapacity() +"άτομα" + " - " + "Το κατάλυμα προσφέρει: ");

                if(r.isAc()){
                    buffer.write("Κλιματισμό ");
                }
                if(r.isBreakfast()){
                    buffer.write("Πρωινό ");
                }
                if (r.isCleaning_services()){
                    buffer.write("Υπηρεσίες Καθαρισμού ");
                }
                if (r.isParking()){
                    buffer.write("Parking ");
                }
                if(r.isWifi()){
                    buffer.write("Wifi.");
                }
                buffer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }//
    }


    /**
     * Μέθοδος η οποία φέρει ένα ξενοδοχείο από τη λίστα
     */
    public void Hotel_delete() throws IOException {
        next_string = sc.next();
        hotel = search_hot(Hotels);
        if (hotel == null) {
            return;
        }
        Hotels.remove(hotel);

        String start= "Ξενοδοχείο: "+hotel.getName();//
        String rooms="            ";

        BufferedReader reader = new BufferedReader(new FileReader("accommodations.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)|| currentLine.contains(rooms)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }//
    }


    /**
     * Μέθοδος επεξεργασίας ενός ξενοδοχείου
     */
    public void Hotel_edit() throws IOException {
        boolean flag = true;
        hotel = search_hot(Hotels);

        if (hotel == null) {
            return;
        }

        String start= "Ξενοδοχείο: "+hotel.getName();//
        String rooms="            ";

        BufferedReader reader = new BufferedReader(new FileReader("accommodations.txt"));
        BufferedWriter writer = new BufferedWriter(new FileWriter("temp.txt"));


        String currentLine;

        while((currentLine = reader.readLine()) != null) {
            if(currentLine.contains(start)|| currentLine.contains(rooms)) continue;
            writer.write(currentLine);
            writer.newLine();
        }

        writer.close();
        reader.close();

        Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp.txt");

        try {
            Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }//

        System.out.println("Τι θα ήθελες να αλλάξεις; (Όνομα/Τοποθεσία/Αστέρια/Έξοδος/Δωμάτια)");
        next_string = sc.next();

        while (flag) {
            flag = false;
            switch (next_string) {
                case "Όνομα" -> {
                    System.out.println("Δώσε το καινούργιο όνομα:");
                    next_string = sc.next();
                    hotel.setName(next_string);
                    hotel.show_Hotel();
                }
                case "Τοποθεσία" -> {
                    System.out.println("Δώσε την καινούργια τοποθεσία:");
                    next_string = sc.next();
                    p = Pattern.compile("[^\\w]&&[^[0-9]]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρη τοποθεσία");
                    hotel.setLocation(next_string);
                    hotel.show_Hotel();
                }
                case "Αστέρια" -> {
                    System.out.println("Δώσε τα καινούργια αστέρια");
                    next_string = sc.next();
                    p = Pattern.compile("[0-5]");
                    next_string = ch.validstring(next_string, p, "Μη έγκυρα αστέρια");
                    hotel.setStars(next_string);
                    hotel.show_Hotel();

                }
                case "Δωμάτια" -> {
                    boolean flag1 = true;
                    System.out.println("Θέλετε να προσθέσετε καινούργιο/α δωμάτιο/α η να επεξεργαστείτε ένα ήδη υπάρχων? (Προσθήκη,Επεξεργασία,Έξοδος)");
                    next_string = sc.next();

                    while (flag1) {
                        flag1 = false;
                        switch (next_string) {
                            case "Προσθήκη" -> hotel.add_Hotel_room();
                            case "Επεξεργασία" -> hotel.edit_Hotel_room();
                            default -> {
                                System.out.println("Δεν υπάρχει τέτοια κατηγορία");
                                System.out.println("Θέλετε να ξαναδοκιμάσετε η οχι (Κατηγορία/0χι)");
                                next_string = sc.next();
                                flag1 = !next_string.equals("0χι");
                            }
                        }
                    }
                }
                case "Έξοδος" -> {
                }
                default -> {
                    System.out.println("Δεν υπάρχει τέτοια κατηγορία");
                    System.out.println("Θέλετε να ξαναδοκιμάσετε η οχι (Κατηγορία/οχι)");
                    next_string = sc.next();
                    flag = !next_string.equals("Οχι");
                }

            }
        }

        try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){//
            buffer.write("Ξενοδοχείο: " + hotel.getName() +" - "+ "Στην τοποθεσία: " + hotel.getLocation() + " - " + "Αστέρια Ξενοδοχείου: "
                    + hotel.getStars() + " - " + "Με τα εξής δωμάτια: " );
            buffer.newLine();

            for(Hotel_room r: hotel.Rooms){
                buffer.write("            ");
                buffer.write("Δωμάτιο: " + r.getName() + " - "+"Τιμή ανα βράδυ: " + r.getPrice() + "$" + " - " + "Τετραγωνικά δωματίου: "
                        + r.getSqmeter() + " - " + "Χωρητικότητα Δωματίου: " + r.getCapacity() +"άτομα" + " - " + "Το κατάλυμα προσφέρει: ");

                if(r.isAc()){
                    buffer.write("Κλιματισμό ");
                }
                if(r.isBreakfast()){
                    buffer.write("Πρωινό ");
                }
                if (r.isCleaning_services()){
                    buffer.write("Υπηρεσίες Καθαρισμού ");
                }
                if (r.isParking()){
                    buffer.write("Parking ");
                }
                if(r.isWifi()){
                    buffer.write("Wifi.");
                }
                buffer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }//
    }


    /**
     * Μέθοδος προβολής συνολικών κρατήσεων σε όλα τα ξενοδοχεία
     */
    public void sum_resv() {
        for (Hotel hotel : Hotels) {
            System.out.println("Το Ξενοδοχείο " + hotel.getName() + " είναι κλεισμένο για : ");
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
            System.out.println("Το Ξενοδοχείο " + hotel.getName() + " είναι ακυρωθεί για : ");
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