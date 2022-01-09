import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CredentialsTest {
    Credentials c;
    @Before
    public void setUp() throws Exception {
        c=new Credentials("mariam","pe123");
    }

    @Test
    public void equal() {
        Credentials ce=new Credentials("mariam","pe123");
        assertEquals(true,c.equal(ce));
    }

    @Test
    public void getPassword() {
        assertEquals("pe123",c.getPassword());
    }

    @Test
    public void setPassword() {
        c.setPassword("pw123");
        assertEquals("pw123",c.getPassword());
    }

    @Test
    public void getUsername() {
        assertEquals("mariam",c.getUsername());
    }

    @Test
    public void setUsername() {
        c.setUsername("maria");
        assertEquals("maria",c.getUsername());
    }
}