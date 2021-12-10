/**
 * Η κλάση main αποτελεί την βασική κλάση του προγράμματος η οποία δημιουργεί ένα αντικείμενο ui και εκτελεί πάνω του την initialize και την start
 */
public class Main {

    public static void main(String[] args) {
        UI ui = new UI() ;

        ui.initialize();

        ui.start();

    }

}
