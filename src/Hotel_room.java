/**
 *Κλάση η οποία αναπαριστά ένα δωμάτιο ξενοδοχείου.
 * Περιλαμβάνει strings με την χωρητικότητα την τιμή το όνομα και τετραγωνικά του δωματίου.
 * Και bullets για το αν παρέχει πρωινό internet air condition parking και υπηρεσίες καθαρισμού
 */

import java.util.ArrayList;

public class Hotel_room {

    String name ;


    private String capacity ;
    private String price ;
    private boolean breakfast, wifi, ac, parking, cleaning_services;
    private  String sqmeter;
    ArrayList<Reservations> hotelroomreservations = new ArrayList<>();
    ArrayList<Reservations> hotelroomcancellations= new ArrayList<>();

    /**
     * Ο κενός κατασκευαστής
     */
    Hotel_room(){}


    /**
     *Κατασκευαστής που δέχεται ως ορίσματα τιμές για όλα τα πεδία του δωματίου
     * @param aname ονομα
     * @param acapacity χωρητικότητα
     * @param aprice τιμή
     * @param abreakfast πρωινού
     * @param awifi wifi
     * @param aac air conditioner
     * @param aparking parking
     * @param acleaning_services υπηρεσίες καθαρισμού
     * @param asqmeter  τετραγωνικά
     */
    Hotel_room(String aname,String acapacity,String aprice,boolean abreakfast,boolean awifi, boolean aac, boolean aparking, boolean acleaning_services,String asqmeter){
        name = aname ;
        capacity = acapacity ;
        price = aprice;
        breakfast = abreakfast ;
        wifi = awifi ;
        ac = aac ;
        parking = aparking ;
        cleaning_services = acleaning_services;
        sqmeter = asqmeter;

    }



    /**
     *Μέθοδος που επιστρέφει το όνομα
     * @return Ονομα
     */
    public String getName() {
        return name;
    }


    /**
     *Μέθοδος αλλαγής ονόματος
     * @param name ονομα
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     *Μέθοδος που επιστρέφει τη χωρητικότητα
     * @return χωρητικότητα
     */
    public String getCapacity() {
        return capacity;
    }


    /**
     *Μέθοδος αλλαγής χωρητικότητας
     * @param capacity χωρητικότητα
     */
    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }


    /**
     *Μέθοδος που επιστρέφει τη τιμή
     * @return τιμή
     */
    public String getPrice() {
        return price;
    }


    /**
     *Μέθοδος αλλαγής τιμής
     * @param price τιμή
     */
    public void setPrice(String price) {
        this.price = price;
    }


    /**
     * Μέθοδος που επιστρέφει αν το δωμάτιο παρέχει πρωινό
     * @return πρωινό
     */
    public boolean isBreakfast() {
        return breakfast;
    }


    /**
     *Μέθοδος αλλαγής παροχής πρωινού
     * @param breakfast πρωινού
     */
    public void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }


    /**
     * Μέθοδος που επιστρέφει αν το δωμάτιο παρέχει wifi
     * @return wifi
     */
    public boolean isWifi() {
        return wifi;
    }


    /**
     *Μέθοδος αλλαγής παροχής wifi
     * @param wifi wifi
     */
    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }



    /**
     * Μέθοδος που επιστρέφει αν το δωμάτιο παρέχει air conditioner
     * @return air conditioner
     */
    public boolean isAc() {
        return ac;
    }


    /**
     *Μέθοδος αλλαγής παροχής air conditioner
     * @param ac air conditioner
     */
    public void setAc(boolean ac) {
        this.ac = ac;
    }


    /**
     * Μέθοδος που επιστρέφει αν το δωμάτιο παρέχει parking
     * @return parking
     */
    public boolean isParking() {
        return parking;
    }


    /**
     *Μέθοδος αλλαγής παροχής parking
     * @param parking parking
     */
    public void setParking(boolean parking) {
        this.parking = parking;
    }


    /**
     * Μέθοδος που επιστρέφει αν το δωμάτιο παρέχει υπηρεσίες καθαρισμού
     * @return υπηρεσίες καθαρισμού
     */
    public boolean isCleaning_services() {
        return cleaning_services;
    }


    /**
     *Μέθοδος αλλαγής παροχής υπηρεσίες καθαρισμού
     * @param cleaning_services υπηρεσίες καθαρισμού
     */
    public void setCleaning_services(boolean cleaning_services) {
        this.cleaning_services = cleaning_services;
    }


    /**
     *
     */
    public String getSqmeter() {
        return sqmeter;
    }


    /**
     *Μέθοδος αλλαγής τετραγωνικών
     * @param sqmeter τιμή
     */
    public void setSqmeter(String sqmeter) {
        this.sqmeter = sqmeter;
    }


    /**
     * Μέθοδος που επιστρέφει σε μορφή string αν το δωμάτιο παρέχει ή δεν παρέχει πρωινό
     * @return αν εχει η οχι πρωίνο
     */
    public String hasBreakfast(){
        if(isBreakfast()){
            return "παρέχει ";
        }
        return "δεν παρέχει ";
    }


    /**
     * Μέθοδος που επιστρέφει σε μορφή string αν το δωμάτιο παρέχει ή δεν παρέχει wifi
     * @return αν εχει η οχι wifi
     */
    public String hasWifi(){
        if(isWifi()){
            return "παρέχει ";
        }
        return "δεν παρέχει ";
    }


    /**
     * Μέθοδος που επιστρέφει σε μορφή string αν το δωμάτιο παρέχει ή δεν παρέχει parking
     * @return αν εχει η οχι parking
     */
    public String hasParking(){
        if(isParking()){
            return "παρέχει ";
        }
        return "δεν παρέχει ";
    }


    /**
     * Μέθοδος που επιστρέφει σε μορφή string αν το δωμάτιο παρέχει ή δεν παρέχει air conditioner
     * @return αν εχει η οχι air conditioner
     */
    public String hasAc(){
        if(isAc()){
            return "παρέχει ";
        }
        return "δεν παρέχει ";
    }


    /**
     * Μέθοδος που επιστρέφει σε μορφή string αν το δωμάτιο παρέχει ή δεν παρέχει υπηρεσίες καθαρισμού
     * @return αν εχει η οχι υπηρεσίες καθαρισμού
     */
    public String hasCleaningservice(){
        if(isCleaning_services()){
            return "παρέχει ";
        }
        return "δεν παρέχει ";
    }


    /**
     * Μέθοδος που εμφανίζει μορφοποιημένο το δωμάτιο
     */
    public void display() {
        System.out.println("Δωμάτιο '" + name + " είναι " + sqmeter + "τμ, και είναι για " + capacity +" άτομα");
        System.out.println(hasBreakfast() + "Πρωίνο\n" + hasWifi()+"Wifi\n"+hasParking()+"Parking\n"+hasAc()+"Air conditioner\n"+hasCleaningservice()+"Υπηρεσία καθαρισμού");
        System.out.println("Τιμή: "+price+"$ ανα Βράδυ\n");
    }
}
