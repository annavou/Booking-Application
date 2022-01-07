/**
 * Κλάση moderator η οποία αναπαριστά έναν διαχειριστή.
 * Ο διαχειριστής έχει πρόσβαση στους χρήστες στις κρατήσεις μπορεί να ακύρωση κράτησης,
 * καθώς και να ενεργοποιήσει η να απενεργοποιήσει προφίλ άλλων χρηστών.
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;

public class Moderator extends Person{

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Moderator(){}

    /**
     * @param atype  Ο τύπος χρήστη
     * @param aname Το όνομα
     * @param ahome_ground Η έδρα
     * @param aphone_number Το τηλέφωνο
     * @param aemail Το email
     */
    Moderator(String atype,String aname , String ahome_ground, String aphone_number, String aemail){
        super(atype,aname,ahome_ground,aphone_number,aemail);
        super.setActivated(true);
    }

    /**
     *Μέθοδος που εμφανίζει όλα τα καταλύματα καθώς και ξενοδοχεία
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
     *Μέθοδος που εμφανίζει όλους τους χρήστες καθώς και την ιδιότητά τους
     * @param persons Συλλογή με όλους τους χρήστες
     */
    public void see_all_users(Collection<Person> persons){
        for (Person p : persons ){
            p.show_person();
        }
    }

    /**
     *Μέθοδος στην οποία ο διαχειριστής μπορεί να ενεργοποιήσει να απενεργοποιήσει το προφίλ ενός χρήστη
     * @param people Συλλογή με όλους τους χρήστες
     */
    public void account_manage(Collection<Person> people) {
        boolean flag ;
        boolean flag1 = true ;
        System.out.println("Ενεργοποιημένοι χρήστες:");
        for (Person p : people){
            if(p.isActivated())
                p.show_person();
        }

        System.out.println("Μη ενεργοποιημένοι χρήστες:");
        for (Person p : people){
            if(!p.isActivated())
                p.show_person();
        }
        next_string = "1";
        while (!next_string.equals("2")){

            System.out.println("Θελετε να ενεγοποιήσετε η να απενροποιήσετε κανενα? (0-ενεργοποιήση/1-απενεργοποιήση/2-εξοδος)");

            p = Pattern.compile("[0-2]");
            next_string = sc.next();
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");

            flag = next_string.equals("0");

            System.out.println("Ποιον θα Θέλατε??");
            next_string = sc.next();
            for (Person p : people){
                if (next_string.equals(p.getName())){
                    p.setActivated(flag);
                    flag1 = false ;
                }
            }
            if(flag1){
                System.out.println("Δεν βρέθηκε τέτοιος χρήστης");
            }
        }

    }

    /**
     *Μέθοδος στην οποία μπορεί να δει όλες τις κρατήσεις
     * @param people Συλλογή με όλους τους χρήστες
     */
    public void see_all_resv(Collection<Person> people){
        for (Person p : people ){
            if (p instanceof Accommodation_Provider AP){
                AP.sum_resv();
            }
            if(p instanceof Hotel_Provider HP){
                HP.sum_resv();
            }
        }
    }

    /**
     *Μέθοδος στην οποία μπορεί να δει όλες τις ακυρώσεις
     * @param People Συλλογή με όλους τους χρήστες
     */
    public void see_all_resv_cancel(Collection<Person> People){
        for (Person p : People ){
            if (p instanceof Accommodation_Provider AP){
                AP.sum_resv_cancel();
            }
            if(p instanceof Hotel_Provider HP){
                HP.sum_resv_cancel();
            }
        }
    }

    /**
     * Μέθοδος στην οποία μπορεί να ακυρώσει κρατήσεις
     * @param People Συλλογή με όλους τους χρήστες
     */
    public void resv_canc(Collection<Person> People){
        Reservations to_cancel = new Reservations();
        System.out.println("Θέλετε να ακυρώσετε κράτηση ξεναδοχείου η καταλύματος(0-ξεναδοχελιου/1-καταλύματος)");
        next_string = sc.next();
        p = Pattern.compile("[0-1]");
        next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
        if(next_string.equals("1")){
            acc_canc(People);
        }
        else{
            hot_canc(People);
        }
    }

    /**
     *Μέθοδος ακύρωσης κράτησης καταλύματος
     * @param People Συλλογή με όλους τους χρήστες
     */
    private void acc_canc(Collection<Person> People) {
        boolean flag = true;
        ArrayList<Reservations> resevrs = new ArrayList<>();
        for (Person p : People) {
            if (p instanceof Accommodation_Provider ap) {
                for (Accommodation acc : ap.Accommodations) {
                    resevrs.addAll(acc.reservations);
                }
            }
        }
        for(int i = 0 ; i < resevrs.size(); i++){
            System.out.println((i+1)+") ");
            resevrs.get(i).show();
        }
        while (flag) {
            flag = false;
            System.out.println("Ποιά κράτηση θέλετε να ακυρώσετε? (Δωστε νουμερο)");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if( Integer.parseInt(next_string) < 1 || Integer.parseInt(next_string) > resevrs.size()){
                System.out.println("Μη έγκυρη τιμή");
                flag = true;
            }
        }
        Reservations to_cancel = resevrs.get(Integer.parseInt(next_string)-1);

        for (Person p : People){
            if(p instanceof Customer c){
                if( to_cancel.getCustomer().getName().equals(c.getName()) ){
                    c.My_Acc_Bookings.remove(to_cancel);
                    c.My_Acc_Canceled.put(to_cancel,to_cancel.getAcc());
                }
            }
            if(p instanceof Accommodation_Provider ap){
                for (Accommodation accommodation : ap.Accommodations)
                    if(to_cancel.getAcc().equals(accommodation)){
                        accommodation.reservations.remove(to_cancel);
                        accommodation.cancellations.add(to_cancel);
                    }
            }
        }
    }


    /**
     *Μέθοδος ακύρωσης κράτησης ξενοδοχείου
     * @param People Συλλογή με όλους τους χρήστες
     */
    private void hot_canc(Collection<Person> People) {
        boolean flag = true;
        ArrayList<Reservations> resevrs = new ArrayList<>();
        for (Person p : People) {
            if (p instanceof Hotel_Provider hp) {
                for(Hotel h : hp.Hotels)
                    for (Hotel_room hr : h.Rooms) {
                        resevrs.addAll(hr.hotelroomreservations);
                    }
            }
        }
        for(int i = 0 ; i < resevrs.size(); i++){
            System.out.println((i+1)+") ");
            resevrs.get(i).show();
        }
        while (flag) {
            flag = false;
            System.out.println("Ποιά κράτηση θέλετε να ακυρώσετε? (Δωστε νουμερο)");
            next_string = sc.next();
            p = Pattern.compile(".*[0-9]");
            next_string = ch.validstring(next_string,p,"Μη έγκυρη τιμή");
            if( Integer.parseInt(next_string) < 1 || Integer.parseInt(next_string) > resevrs.size()){
                System.out.println("Μη έγκυρη τιμή");
                flag = true;
            }
        }
        Reservations to_cancel = resevrs.get(Integer.parseInt(next_string)-1);

        for (Person p : People){
            if(p instanceof Customer c){
                if( to_cancel.getCustomer().getName().equals(c.getName()) ){
                    c.My_Hot_Bookings.remove(to_cancel);
                    c.My_Hot_Canceled.put(to_cancel,to_cancel.getHot());
                }
            }
            if(p instanceof Hotel_Provider hp){
                for (Hotel h : hp.Hotels)
                    for (Hotel_room hr : h.Rooms)
                        if(to_cancel.getHot().equals(hr)){
                            hr.hotelroomreservations.remove(to_cancel);
                            hr.hotelroomcancellations.add(to_cancel);
                        }
            }
        }
    }


}