import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Hotel_roomTest {

    Hotel_room hr;
    @Before
    public void setUp() throws Exception {
        hr= new Hotel_room("Δίκλινο","2","50",true,true,true,true,true,"30");
    }

    @Test
    public void getName() {
        assertEquals("Δίκλινο", hr.getName());
    }

    @Test
    public void setName() {
        hr.setName("Τρίκλινο");
        assertEquals("Τρίκλινο", hr.getName());
    }

    @Test
    public void getCapacity() {
        assertEquals("2",hr.getCapacity());
    }

    @Test
    public void setCapacity() {
        hr.setCapacity("3");
        assertEquals("3", hr.getCapacity());
    }

    @Test
    public void getPrice() {
        assertEquals("50",hr.getPrice());
    }

    @Test
    public void setPrice() {
        hr.setPrice("60");
        assertEquals("60", hr.getPrice());
    }

    @Test
    public void isBreakfast() {
        assertEquals(true,hr.isBreakfast());
    }

    @Test
    public void setBreakfast() {
        hr.setBreakfast(false);
        assertEquals(false,hr.isBreakfast());
    }

    @Test
    public void isWifi() {
        assertEquals(true,hr.isWifi());
    }

    @Test
    public void setWifi() {
        hr.setWifi(false);
        assertEquals(false,hr.isWifi());
    }

    @Test
    public void isAc() {
        assertEquals(true,hr.isAc());
    }

    @Test
    public void setAc() {
        hr.setAc(false);
        assertEquals(false,hr.isAc());
    }

    @Test
    public void isParking() {
        assertEquals(true,hr.isParking());
    }

    @Test
    public void setParking() {
        hr.setParking(false);
        assertEquals(false,hr.isParking());
    }

    @Test
    public void isCleaning_services() {
        assertEquals(true,hr.isCleaning_services());
    }

    @Test
    public void setCleaning_services() {
        hr.setCleaning_services(false);
        assertEquals(false,hr.isCleaning_services());
    }

    @Test
    public void getSqmeter() {
        assertEquals("30",hr.getSqmeter());
    }

    @Test
    public void setSqmeter() {
        hr.setSqmeter("40");
        assertEquals("40",hr.getSqmeter());
    }

    @Test
    public void hasBreakfast() {
        assertEquals("Παρέχει",hr.hasBreakfast());
    }

    @Test
    public void hasWifi() {
        assertEquals("Παρέχει",hr.hasWifi());
    }

    @Test
    public void hasParking() {
        assertEquals("Παρέχει",hr.hasParking());
    }

    @Test
    public void hasAc() {
        assertEquals("Παρέχει",hr.hasAc());
    }

    @Test
    public void hasCleaningservice() {
        assertEquals("Παρέχει",hr.hasCleaningservice());
    }
}