/**
 * Αυτή η κλάση αναπαριστά μια κράτηση η οποία αποτελείτε από την ημερομηνία άφιξης και την ημερομηνία αναχώρησης,
 * το όνομα του πελάτη που κάνει την κράτηση, το κατάλυμα ήτο ξενοδοχείο που έγινε η κράτηση
 */

import java.time.LocalDate;

public class Reservations {

    protected LocalDate start;
    protected LocalDate end ;
    protected String customer_name;
    protected Accommodation acc;
    protected Hotel_room hot ;

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    public Reservations(){
        start = null;
        end = null ;
        customer_name = null ;
    }

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    public Reservations(LocalDate a, LocalDate b,String name,Accommodation accom,Hotel_room hr){
        start = a ;
        end = b ;
        customer_name = name ;
        acc = accom;
        hot = hr;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public Accommodation getAcc() {
        return acc;
    }

    public void setAcc(Accommodation acc) {
        this.acc = acc;
    }

    public Hotel_room getHot() {
        return hot;
    }

    public void setHot(Hotel_room hot) {
        this.hot = hot;
    }

    /**
     * μέθοδος που εμφανίζει τα χαρακτηριστικά μιας κράτησης
     */
    public void show(){
        System.out.println(start.getDayOfMonth()+"/"+start.getMonth()+"/"+start.getYear()+" - "+end.getDayOfMonth()+"/"+end.getMonth()+"/"+end.getYear() +" απο τον "+customer_name);
    }
}
