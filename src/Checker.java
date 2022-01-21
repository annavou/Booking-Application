/**
 *Η κλάση checker αποτελεί μία κλάση με μία συνάρτηση η οποία ελέγχει αν ένα String ακολουθεί ένα pattern
 */

import java.io.Serial;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Checker implements java.io.Serializable{

@Serial
private static final long serialVersionUID = 6779685098267757690L;

    /**
     *
     * @param s String θέλουμε να ελέγξουμε
     * @param p το Pattern το ποιο πρέπει να ακολουθεί
     * @return Αν το s ακολουθεί το p
     */
    public boolean valid(String s, Pattern p){
        return s.matches(p.pattern());
    }
}