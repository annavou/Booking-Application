import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class messagesTest {
    messages m;
    @Before
    public void setUp() throws Exception {
        m= new messages("Γιώργος Παπαχαραλαμπόπουλος","Καλωσόρισμα","Γεια");
    }

    @Test
    public void getFrom() {
        assertEquals("Γιώργος Παπαχαραλαμπόπουλος",m.getFrom());
    }

    @Test
    public void setFrom() {
        m.setFrom("Παπαχαραλαμπόπουλος");
        assertEquals("Παπαχαραλαμπόπουλος",m.getFrom());
    }

    @Test
    public void getTopic() {
        assertEquals("Καλωσόρισμα",m.getTopic());
    }

    @Test
    public void setTopic() {
        m.setTopic("Καλησπέρα");
        assertEquals("Καλησπέρα",m.getTopic());
    }

    @Test
    public void getText() {
        assertEquals("Γεια", m.getText());
    }

    @Test
    public void setText() {
        m.setText("Γ");
        assertEquals("Γ",m.getText());
    }
}