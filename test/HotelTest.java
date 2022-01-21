import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HotelTest {
    Hotel b;
    @Before
    public void setUp() throws Exception {
        b = new Hotel("Galaxy","Καβάλα","4");
    }

    @Test
    public void getName() {
        assertEquals("Galaxy",b.getName());
    }

    @Test
    public void setName() {
        b.setName("Gal");
        assertEquals("Gal",b.getName());
    }

    @Test
    public void getLocation() {
        assertEquals("Καβάλα",b.getLocation());
    }

    @Test
    public void setLocation() {
        b.setLocation("Θεσσαλονίκη");
        assertEquals("Θεσσαλονίκη", b.getLocation());
    }

    @Test
    public void getStars() {
        assertEquals("4", b.getStars());
    }

    @Test
    public void setStars() {
        b.setStars("5");
        assertEquals("5",b.getStars());
    }
}