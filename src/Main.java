import java.io.IOException;

/**
 * Η κλάση main αποτελεί τη βασική κλάση του προγράμματος η οποία δημιουργεί ένα αντικείμενο ui και εκτελεί πάνω του την initialize και τη start
 */
public class Main {

    public static void main(String[] args) throws IOException {
        UI ui = new UI() ;

        ui.initialize();

        ui.start();

    }

}
