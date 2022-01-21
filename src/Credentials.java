import java.io.Serial;
import java.util.Objects;

/**
 * Η Κλάση Credentials αποτελεί μία κλάση η οποία αποθηκεύει σαν string το password και το username ενός χρήστη
 */
public class Credentials implements java.io.Serializable{


    private String Password ;
    private String Username ;

    @Serial
    private static final long serialVersionUID = 1229685098267757690L;

    /**
     * Ο κενός κατασκευαστής
     */
    Credentials(){}


    /**
     *Κατασκευαστής που δέχεται ορίσματα username και password
     * @param a το username
     * @param b Ο κωδικός
     */
    Credentials(String a,String b ){
        Username = a ;
        Password = b ;
    }


    /**
     *Μέθοδος η οποία ελέγχει αν δύο credentials είναι ίδια
     * @param a ενα απο τα 2 Credentials
     * @return αν είναι ισα
     */
    public boolean equal(Credentials a){
        return this.Username.equals(a.Username) && Objects.equals(this.Password, a.Password);
    }


    /**
     *Μέθοδος που μας επιστρέφει τον κωδικό
     * @return κωδικός
     */
    public String getPassword() {
        return Password;
    }


    /**
     *Μέθοδος αλλαγής κωδικού
     * @param  password κωδικός
     */
    public void setPassword(String password) {
        Password = password;
    }


    /**
     *Μέθοδος που μας επιστρέφει το username
     * @return username
     */
    public String getUsername() {
        return Username;
    }


    /**
     *Μέθοδος αλλαγής username
     * @param  username όνομα
     */
    public void setUsername(String username) {
        Username = username;
    }
}