import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class ReservationsTest {
    Reservations r;
    LocalDate s;
    LocalDate e;
    Customer c;
    Accommodation acc;
    Hotel h;
    Hotel_room hr;
    @Before
    public void setUp() throws Exception {
        s= LocalDate.of(2022,3,15);
        e=LocalDate.of(2022,3,20);
        c= new Customer("Γιώργος_Παπαδόπουλος","Θεσσαλονίκη","6940526352","giwrgos@gmail.com");
        acc=new Accommodation("Annas_House", "Χαλκιδική", "60", "35",
                "4", "1", "3", false, true, true, false, false);
        h=new Hotel("Philoxenia","Χαλκιδική","4");
        hr= new Hotel_room("Δίκλινο","2","50",true,true,true,true,true,"30");
        r=new Reservations(s,e,c,acc,hr,h);
    }

    @Test
    public void getStart() {
        assertEquals(s,r.getStart());
    }

    @Test
    public void setStart() {
        r.setStart(LocalDate.of(2022,3,17));
        assertEquals(LocalDate.of(2022,3,17),r.getStart());
    }

    @Test
    public void getEnd() {
        assertEquals(e,r.getEnd());
    }

    @Test
    public void setEnd() {
        r.setEnd(LocalDate.of(2022,3,21));
        assertEquals(LocalDate.of(2022,3,21),r.getEnd());
    }

    @Test
    public void getCustomer() {
        assertEquals(c,r.getCustomer());
    }

    @Test
    public void setCustomer() {
        c.setName("Γίωργος_Παπ");
        assertEquals(c,r.getCustomer());
    }

    @Test
    public void getAcc() {
        assertEquals(acc,r.getAcc());
    }

    @Test
    public void setAcc() {
        acc.setName("Annas");
        assertEquals(acc,r.getAcc());
    }

    @Test
    public void getHotel() {
        assertEquals(h,r.getHotel());
    }

    @Test
    public void setHotel() {
        h.setLocation("Θεσσαλονίκη");
        assertEquals(h, r.getHotel());
    }

    @Test
    public void getHot() {
        assertEquals(hr,r.getHot());
    }

    @Test
    public void setHot() {
        hr.setName("Τρίκλινο");
        hr.setCapacity("3");
        assertEquals(hr, r.getHot());
    }
}