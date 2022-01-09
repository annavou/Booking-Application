import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccommodationTest {
    Accommodation acc;
    @Before
    public void setUp() throws Exception {
         acc = new Accommodation("Annas_House", "Χαλκιδική", "60", "35",
                "4", "1", "3", false, true, true, false, false);
    }

    @Test
    public void getName() {
        assertEquals("Annas_House",acc.getName());
    }

    @Test
    public void setName() {
        acc.setName("Annas");
        assertEquals("Annas",acc.getName());
    }

    @Test
    public void getLocation() {
        assertEquals("Χαλκιδική",acc.getLocation());
    }

    @Test
    public void setLocation() {
        acc.setLocation("Θεσσαλονίκη");
        assertEquals("Θεσσαλονίκη",acc.getLocation());
    }

    @Test
    public void getPrice() {
        assertEquals("60",acc.getPrice());
    }

    @Test
    public void setPrice() {
        acc.setPrice("70");
        assertEquals("70",acc.getPrice());
    }

    @Test
    public void getSqmeter() {
        assertEquals("35",acc.getSqmeter());
    }

    @Test
    public void setSqmeter() {
        acc.setSqmeter("40");
        assertEquals("40",acc.getSqmeter());
    }

    @Test
    public void getStars() {
        assertEquals("4",acc.getStars());
    }

    @Test
    public void setStars() {
        acc.setStars("5");
        assertEquals("5",acc.getStars());
    }

    @Test
    public void getRooms() {
        assertEquals("1",acc.getRooms());
    }

    @Test
    public void setRooms() {
        acc.setRooms("2");
        assertEquals("2", acc.getRooms());
    }

    @Test
    public void getCapacity() {
        assertEquals("3",acc.getCapacity());
    }

    @Test
    public void setCapacity() {
        acc.setCapacity("2");
        assertEquals("2",acc.getCapacity());
    }

    @Test
    public void isBreakfast() {
        assertEquals(false,acc.isBreakfast());
    }

    @Test
    public void setBreakfast() {
        acc.setBreakfast(true);
        assertEquals(true,acc.isBreakfast());
    }

    @Test
    public void isWifi() {
        assertEquals(true,acc.isWifi());
    }

    @Test
    public void setWifi() {
        acc.setWifi(false);
        assertEquals(false,acc.isWifi());
    }

    @Test
    public void isAc() {
        assertEquals(true,acc.isAc());
    }

    @Test
    public void setAc() {
        acc.setAc(false);
        assertEquals(false,acc.isAc());
    }

    @Test
    public void isParking() {
        assertEquals(false,acc.isParking());
    }

    @Test
    public void setParking() {
        acc.setParking(true);
        assertEquals(true,acc.isParking());
    }

    @Test
    public void isCleaning_services() {
        assertEquals(false,acc.isCleaning_services());
    }

    @Test
    public void setCleaning_services() {
        acc.setCleaning_services(true);
        assertEquals(true,acc.isCleaning_services());
    }

    @Test
    public void hasBreakfast() {
        assertEquals(false,acc.isBreakfast());
    }

    @Test
    public void hasWifi() {assertEquals(true, acc.isWifi());}

    @Test
    public void hasParking() {
        assertEquals(false,acc.isParking());
    }

    @Test
    public void hasAc() {
        assertEquals(true ,acc.isAc());
    }

    @Test
    public void hasCleaningservice() {
        assertEquals(false,acc.isCleaning_services());
    }
}