import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersonTest {
    Person p;

    @Before
    public void setUp() throws Exception {
        p= new Person("Άννα","Θεσσαλονίκη","6940586235","anna@gmail.com");
    }

    @Test
    public void isActivated() {
        assertEquals(false,p.isActivated());
    }

    @Test
    public void setActivated() {
        p.setActivated(true);
        assertEquals(true,p.isActivated());
    }

    @Test
    public void getName() {
        assertEquals("Άννα", p.getName());
    }

    @Test
    public void setName() {
        p.setName("Ανν");
        assertEquals("Ανν", p.getName());
    }

    @Test
    public void getHome_ground() {
        assertEquals("Θεσσαλονίκη", p.getHome_ground());
    }

    @Test
    public void setHome_ground() {
        p.setHome_ground("Θες");
        assertEquals("Θες", p.getHome_ground());
    }

    @Test
    public void getPhone_number() {
        assertEquals("6940586235", p.getPhone_number());
    }

    @Test
    public void setPhone_number() {
        p.setPhone_number("6940518063");
        assertEquals("6940518063", p.getPhone_number());
    }

    @Test
    public void getEmail() {
        assertEquals("anna@gmail.com", p.getEmail());
    }

    @Test
    public void setEmail() {
        p.setEmail("ann@gmail.com");
        assertEquals("ann@gmail.com",p.getEmail());
    }
}