/**
 *Η κλάση checker αποτελεί μία κλάση με μία συνάρτηση η οποία ελέγχει αν ένα String ακολουθεί ένα pattern
 */

import java.util.Scanner;
import java.util.regex.Pattern;

public class Checker {

    Scanner sc = new Scanner(System.in);

    /**
     *
     * @param s String θέλουμε να ελένξουμε
     * @param p το Pattern το ποιο πρέπει να ακολουθέι
     * @param y το μυνημα σε περίπτωση λάθους
     * @return το String το οποίο ακολουθεί το Pattern p
     */
    public String validstring(String s, Pattern p,String y){
        if( s.matches(p.pattern())){
            return s;
        }
        else {
            System.out.println(y);
            s = sc.next();
            return validstring(s,p,y);
        }
    }
}
