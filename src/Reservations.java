/**
 * Αυτή η κλάση αναπαριστά μια κράτηση η οποία αποτελείτε από την ημερομηνία άφιξης και την ημερομηνία αναχώρησης,
 * το όνομα του πελάτη που κάνει την κράτηση, το κατάλυμα ήτο ξενοδοχείο που έγινε η κράτηση
 */

import java.io.Serial;
import java.time.LocalDate;

public class Reservations implements java.io.Serializable{

    protected LocalDate start;
    protected LocalDate end ;
    protected Customer customer;
    protected Accommodation acc;
    protected Hotel_room hot ;
    protected Hotel hotel ;
    @Serial
    private static final long serialVersionUID = 6529615098262757190L;

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    public Reservations(){
        start = null;
        end = null ;
        customer = null ;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    public Reservations(LocalDate a, LocalDate b,Customer name,Accommodation accom,Hotel_room hr,Hotel h){
        start = a ;
        end = b ;
        customer = name ;
        acc = accom;
        hot = hr;
        hotel = h ;
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
    }

    public void setCustomer(Customer customer_) {
        this.customer = customer_;
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

}
