import java.util.Objects;

/**
 * Η Κλάση Credentials αποτελεί μία κλάση η οποία αποθηκεύει σαν string το password και το username ενός χρήστη
 */
public class Credentials {


    private String Password ;
    private String Username ;

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
     *  μέθοδος που εμφανίζει μορφοποιημένα τα Credentials
     */
    public void Display(){
        System.out.println(Username + "  " + Password);
    }


    /**
     *Μέθοδος η οποία ελέγχει αν δύο credentials είναι ίδια
     * @param a ενα απο τα 2 Credentials
     * @return αν ειναι ισα
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
     * @return usename
     */
    public String getUsername() {
        return Username;
    }


    /**
     *Μέθοδος αλλαγής username
     * @param  username ονομα
     */
    public void setUsername(String username) {
        Username = username;
    }
}
