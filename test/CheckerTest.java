import org.junit.Before;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class CheckerTest {
    Pattern p;
    String s1 , s2;
    Checker ck = new Checker();
    @Before
    public void setUp() throws Exception {
         p = Pattern.compile("[0-9]");
         s1 = "4";
         s2 = "hello";
    }

    @Test
    public void valid() {
        assertTrue(ck.valid(s1, p));
        assertFalse(ck.valid(s2, p));



    }
}