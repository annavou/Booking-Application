/**
 * Αυτή η κλάση αναπαριστά μια κράτηση η οποία αποτελείτε από την ημερομηνία άφιξης και την ημερομηνία αναχώρησης,
 * το όνομα του πελάτη που κάνει την κράτηση, το κατάλυμα ήτο ξενοδοχείο που έγινε η κράτηση
 */

import java.time.LocalDate;

public class Reservations {

    protected LocalDate start;
    protected LocalDate end ;
    protected Customer customer;//
    protected Accommodation acc;
    protected Hotel hotel;//
    protected Hotel_room hot ;

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    public Reservations(){
        start = null;
        end = null ;
        customer = null ;//
    }

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    public Reservations(LocalDate a, LocalDate b,Customer cus,Accommodation accom,Hotel h,Hotel_room hr){
        start = a ;
        end = b ;
        customer = cus ;//
        acc = accom;
        hotel=h;//
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

    public Customer getCustomer() {
        return customer;
    }//

    public void setCustomer(Customer customer) {this.customer = customer;}//

    public Accommodation getAcc() {return acc;}

    public void setAcc(Accommodation acc) {this.acc = acc;}

    public Hotel getHotel(){return hotel;}//

    public void setHotel(Hotel h){hotel=h;}//

    public Hotel_room getHot() {return hot;}

    public void setHot(Hotel_room hot) {this.hot = hot;}

    /**
     * μέθοδος που εμφανίζει τα χαρακτηριστικά μιας κράτησης
     */
    public void show(){//
        System.out.println(start.getDayOfMonth()+"/"+start.getMonth()+"/"+start.getYear()+" - "
                +end.getDayOfMonth()+"/"+end.getMonth()+"/"+end.getYear() +" στο " );
        if(this.getHotel()!=null){
            System.out.println( "ξενοδοχείο " + this.getHotel().getName());
        }else {
            System.out.println("κατάλυμα " + this.getAcc().getName());
        }
    }//
}
