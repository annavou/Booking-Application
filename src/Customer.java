/**
 * Αύτη η κλάση αναπαριστά ένα πελάτη με τα βασικά του στοιχεία (όνομα,τηλέφωνο,μειλ,τοποθεσία), ο οποίος μπορεί να αναζητήσει
 * διάφορα καταλύματα και ξενοδοχεία με την χρήση φίλτρων, να κάνει κράτηση του καταλύματος που επιθυμεί με βάση
 * της διαθεσομότητας ,να ακυρώσει οποιαδήποτε κράτηση του και να έχει πρόσβαση στις κρατήσεις και τις ακυρώσεις του.
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.time.LocalDate;

public class Customer extends Person {

    HashMap<Reservations,String> My_Bookings= new HashMap<>();
    HashMap<Reservations,String> Canceled=new HashMap<>();
    HashMap<String,Person> acc_list=new HashMap<>();


    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     * @param aname
     * @param alocation
     * @param aphone_number
     * @param aemail
     */
    public Customer(String aname, String alocation, int aphone_number, String aemail) {
        super(aname,alocation,aphone_number,aemail);
    }

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    public Customer() {
        super();
    }

    /**
     * μέθοδος που αναζητά ένα συγκεκριμένο όνομα καταλύματος
     * @param aname το όνομα που ψάχνουμε
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return το κατάλυμα αν υπάρχει
     */
    public Accommodation search_name(String aname,Collection<Person> persons){
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.getName() == aname) {
                        return acc;
                    }
                }
            }
        }
        return null;
    }


    /**
     * μέθοδος που αναζητά ένα συγκεκριμένο όνομα ξενοδοχείου
     * @param aname το όνομα που ψάχνουμε
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return το ξενοδοχείο αν υπάρχει
     */
    public Hotel search_HotelName(String aname,Collection<Person> persons) {
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel hotel : ((Hotel_Provider) p).Hotels) {
                    if (hotel.getName() == aname) {
                        return hotel;
                    }
                }
            }
        }
        return null;
    }

    /**
     * μέθοδος που αναζητά ένα συγκεκριμένο δωμάτιο ενός ξενοδοχείου
     * @param aname όνομα ξενοδοχείου
     * @param roomName όνομα δωματίου
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return το δωμάτιο αν υπάρχει
     */
    public Hotel_room search_HotelRoomName(String aname,String roomName,Collection<Person> persons){
        Hotel hotel=search_HotelName(aname, acc_list.values());
        if(hotel!=null){
            for(Hotel_room room: hotel.Rooms){
                if(room.getName()==roomName){
                    return room;
                }
            }
        }
        return null;
    }

    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που βρίσκονται σε μια τοποθεσία
     * @param alocation η τοποθεσία που θέλουμε
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που βρίσκονται στην περιοχή που ψάχνουμε
     */
    public ArrayList search_location(String alocation, Collection<Person> persons){
        ArrayList<Accommodation>Location= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (alocation.equals(acc.getLocation())) {
                        Location.add(acc);
                    }
                }
            }
        }
        return Location;
    }

    /**
     *μέθοδος που αναζητά όλα τα ξενοδοχεία που βρίσκονται σε μια τοποθεσία
     *@param alocation η τοποθεσία που θέλουμε
     *@param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     *@return μια λίστα με όλα τα ξενοδοχεία που βρίσκονται στην περιοχή που ψάχνουμε
     */
    public ArrayList search_HotelLocation(String alocation,Collection<Person> persons){
        ArrayList<Hotel> HotelLocation = new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Hotel hotel : ((Hotel_Provider) p).Hotels) {
                    if (hotel.getLocation() == alocation) {
                        HotelLocation.add(hotel);
                    }
                }
            }
        }
        return HotelLocation;
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το εύρος των τιμών ανά βράδυ
     * @param from η κατώτατη τιμή που θέλουμε ανά βράδυ
     * @param till η ανώτατη τιμή που θέλουμε ανά βράδυ
     * @param overnights αριθμός διανυκτεύσεων
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα και την συνολική τους τιμή που κυμαίνεται στα όρια που δώσαμε
     */
    public HashMap search_PriceRange(double from,double till,int overnights,Collection<Person> persons){
        HashMap<Accommodation,Double>Price_range= new HashMap<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.getPrice() >= from && acc.getPrice()<=till) {
                        Price_range.put(acc,overnights*acc.getPrice());
                    }
                }
            }
        }
        return Price_range;
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση το εύρος των τιμών ανά βράδυ
     * @param from η μέγιστη τιμή που θέλουμε ανά βράδυ
     * @param till η ανώτατη τιμή που θέλουμε ανά βράδυ
     * @param overnights αριθμός διανυκτεύσεων
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείων και την συνολική τους τιμή που κυμαίνεται στα όρια που δώσαμε
     */
    public HashMap search_HotelPriceRange(double from, double till, int overnights, Collection<Person> persons){
        HashMap<Hotel_room,Double> HotelPriceRange = new HashMap<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for(Hotel_room room: ho.Rooms) {
                        if (room.getPrice() >= from && room.getPrice()<=till) {
                            HotelPriceRange.put(room,overnights* room.getPrice());
                        }
                    }
                }
            }
        }
        return HotelPriceRange;
    }


    /**
     * μέθοδος που αναζητά καταλύματα με βάση τα αστέρια τους
     * @param astars πόσα αστέρια θέλουμε να είναι το κατάλυμα
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που έχουν τον αριθμό αστεριών που θέλουμε
     */
    public ArrayList search_stars(int astars, Collection<Person> persons){
        ArrayList<Accommodation> Stars= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.getStars() == astars) {
                        Stars.add(acc);
                    }
                }
            }
        }
        return Stars;
    }

    /**
     * μέθοδος που αναζητά ξενοδοχεία με βάση τα αστέρια τους
     * @param astars πόσα αστέρια θέλουμε να είναι το ξενοδοχείο
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα ξενοδοχεία που έχουν τον αριθμό αστεριών που θέλουμε
     */
    public ArrayList search_HotelStars(int astars,Collection<Person> persons){
        ArrayList<Hotel>HotelStars= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel hotel : ((Hotel_Provider) p).Hotels) {
                    if (hotel.getStarts() == astars) {
                        HotelStars.add(hotel);
                    }
                }
            }
        }
        return HotelStars;
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση την χωριτηκότητα σε άτομα
     * @param acapacity πόσα άτομα θέλουμε
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που έχουν την χωριτηκότητα που θέλουμε
     */
    public ArrayList search_capacity(int acapacity, Collection<Person> persons){
        ArrayList<Accommodation> Capacity= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.getCapacity() == acapacity) {
                        Capacity.add(acc);
                    }
                }
            }
        }
        return Capacity;
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση την χωριτηκότητα σε άτομα
     * @param acapacity πόσα άτομα θέλουμε
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείων που έχουν την χωριτηκότητα που θέλουμε
     */
    public ArrayList search_HotelCapacity(int acapacity,Collection<Person> persons){
        ArrayList<Hotel_room>HotelCapacity= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for (Hotel_room room : ho.Rooms) {
                        if (room.getCapacity() == acapacity) {
                            HotelCapacity.add(room);
                        }
                    }
                }
            }
        }
        return HotelCapacity;
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν προσφέρουν πρωινό
     * @param abreakfast αν διαθέτει πρωινό
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που προσφέρουν πρωινό
     */
    public ArrayList search_breakfast(boolean abreakfast, Collection<Person> persons){
        ArrayList<Accommodation> Breakfast= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc :  ((Accommodation_Provider) p).Accommodations) {
                    if (acc.isBreakfast() == abreakfast) {
                        Breakfast.add(acc);
                    }
                }
            }
        }
        return Breakfast;
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείων με βάση το αν προσφέρουν πρωινό
     * @param abreakfast αν διαθέτει πρωινό
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείων που προσφέρουν πρωινό
     */
    public ArrayList search_HotelBreakfast(boolean abreakfast,Collection<Person> persons) {
        ArrayList<Hotel_room> HotelBreakfast = new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for (Hotel_room room : ho.Rooms) {
                        if (room.isBreakfast() == abreakfast) {
                            HotelBreakfast.add(room);
                        }
                    }
                }
            }
        }

        return HotelBreakfast;
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν διθέτουν wifi
     * @param awifi αν διαθέτει wifi
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που διθέτουν wifi
     */
    public ArrayList search_wifi(boolean awifi, Collection<Person> persons){
        ArrayList<Accommodation> Wifi= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.isWifi() == awifi) {
                        Wifi.add(acc);
                    }
                }
            }
        }
        return Wifi;
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείου με βάση το αν διθέτουν wifi
     * @param awifi αν διαθέτει wifi
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείου που διθέτουν wifi
     */
    public ArrayList search_HotelWifi(boolean awifi, Collection<Person> persons){
        ArrayList<Hotel_room>HotelWifi= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for (Hotel_room room : ho.Rooms) {
                        if (room.isWifi() == awifi) {
                            HotelWifi.add(room);
                        }
                    }
                }
            }
        }
        return HotelWifi;
    }

    /**
     * μέθοδος που αναζητά καταλύματα με βάση το αν διθέτουν κλιματισμό
     * @param aac αν διαθέτει κλιματισμό
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που διθέτουν κλιματισμό
     */
    public ArrayList search_AirCondition(boolean aac, Collection<Person> persons){
        ArrayList<Accommodation> AirCondition= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.isAc() == aac) {
                        AirCondition.add(acc);
                    }
                }
            }
        }
        return AirCondition;
    }

    /**
     * μέθοδος που αναζητά δωμάτια ξενοδοχείου με βάση το αν διθέτουν κλιματισμό
     * @param aac αν διαθέτει κλιματισμό
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείου που διθέτουν κλιματισμό
     */
    public ArrayList search_HotelAirCondition(boolean aac, Collection<Person> persons){
        ArrayList<Hotel_room>HotelAirCondition= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for (Hotel_room room : ho.Rooms) {
                        if (room.isAc() == aac) {
                            HotelAirCondition.add(room);
                        }
                    }
                }
            }
        }
        return HotelAirCondition;
    }

    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που διαθέτουν πάρκινγκ
     * @param aparking αν διεθέτει πάρκινγκ
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που διθέτουν πάρκινγκ
     */
    public ArrayList search_Parking(boolean aparking, Collection<Person> persons){
        ArrayList<Accommodation> Parking = new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.isParking() == aparking) {
                        Parking.add(acc);
                    }
                }
            }
        }
        return Parking;
    }

    /**
     * μέθοδος που αναζητά όλα τα δωμάτια ξενοδοχείου που διαθέτουν πάρκινγκ
     * @param aparking αν διεθέτει πάρκινγκ
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείου που διθέτουν πάρκινγκ
     */
    public ArrayList search_HotelParking(boolean aparking, Collection<Person> persons){
        ArrayList<Hotel_room>HotelParking= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for (Hotel_room room : ho.Rooms) {
                        if (room.isParking() == aparking) {
                            HotelParking.add(room);
                        }
                    }
                }
            }
        }
        return HotelParking;
    }

    /**
     * μέθοδος που αναζητά όλα τα καταλύματα που προσφέρουν υπηρεσίες καθαριότητας
     * @param acleaning αν προφέρει υπηρεσίες καθαριότητας
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που προσφέρουν υπηρεσίες καθαριότητας
     */
    public ArrayList search_CleaningServices(boolean acleaning, Collection<Person> persons){
        ArrayList<Accommodation> CleaningServices= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    if (acc.isCleaning_services() == acleaning) {
                        CleaningServices.add(acc);
                    }
                }
            }
        }
        return CleaningServices;
    }

    /**
     * μέθοδος που αναζητά όλα τα δωμάτια ξενοδοχείων που προσφέρουν υπηρεσίες καθαριότητας
     * @param acleaning αν προφέρει υπηρεσίες καθαριότητας
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείων που προσφέρουν υπηρεσίες καθαριότητας
     */
    public ArrayList search_HotelCleaningServices(boolean acleaning, Collection<Person> persons){
        ArrayList<Hotel_room>HotelCleaningServices= new ArrayList<>();
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    for (Hotel_room room : ho.Rooms) {
                        if (room.isCleaning_services() == acleaning) {
                            HotelCleaningServices.add(room);
                        }
                    }
                }
            }
        }
        return HotelCleaningServices;
    }

    /**
     * μέθοδος αναζήτησης καταλυμάτων με βάση την διαθεσιμότητα του κάθε καταλύματος
     * @param f ημερομηνία άφιξης
     * @param t ημερομηνία αναχώρισης
     * @param location τοποθεσία καταλυμάτων
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα καταλύματα που είναι διαθέσιμα τις συγκεκριμένες ημερομηνίες
     */
    public ArrayList search_Availability(LocalDate f,LocalDate t,String location, Collection<Person> persons){
        ArrayList<Accommodation> Availability= new ArrayList<>();
        Reservations r=new Reservations(f,t);
        for (Person p : persons) {
            if (p instanceof Accommodation_Provider) {
                for (Accommodation acc : ((Accommodation_Provider) p).Accommodations) {
                    boolean flag = false;
                    if(acc.getLocation()==location) {
                        for (Reservations res : acc.reservations) {
                            if (res.start.equals(r.start)) {
                                flag = true;
                                break;
                            } else if (r.start.isAfter(res.start) && r.start.isBefore(res.end)) {
                                flag = true;
                                break;
                            } else if (r.end.isAfter(res.start)) {
                                flag = true;
                                break;
                            }
                        }
                        if (flag == false) {
                            Availability.add(acc);
                        }
                    }
                }
            }
        }
        return Availability;
    }

    /**
     * μέθοδος αναζήτησης δωματίων ξενοδοχείου με βάση την διαθεσιμότητα του κάθε δωματίου
     * @param f ημερομηνία άφιξης
     * @param t ημερομηνία αναχώρισης
     * @param location τοποθεσία καταλυμάτων
     * @param persons η λίστα με τους χρήστες που είναι καταχωρημένοι στην εφαρμογή
     * @return μια λίστα με όλα τα δωμάτια ξενοδοχείου που είναι διαθέσιμα τις συγκεκριμένες ημερομηνίες
     */
    public ArrayList search_HotelAvailability(LocalDate f,LocalDate t,String location, Collection<Person> persons){
        ArrayList<Hotel_room> HotelAvailability= new ArrayList<>();
        Reservations r=new Reservations(f,t);
        for (Person p : persons) {
            if (p instanceof Hotel_Provider) {
                for (Hotel ho : ((Hotel_Provider) p).Hotels) {
                    if(ho.getLocation()==location) {
                        for (Hotel_room room : ho.Rooms) {
                            boolean flag = false;
                            for (Reservations res : room.hotelroomreservations) {
                                if (res.start.equals(r.start)) {
                                    flag = true;
                                    break;
                                } else if (r.start.isAfter(res.start) && r.start.isBefore(res.end)) {
                                    flag = true;
                                    break;
                                } else if (r.end.isAfter(res.start)) {
                                    flag = true;
                                    break;
                                }
                            }
                            if (flag == false) {
                                HotelAvailability.add(room);
                            }
                        }
                    }
                }
            }
        }
        return HotelAvailability;
    }

    /**
     * μέθοδος που κάνει κράτηση σε ένα κατάλυμα
     * @param name όνομα καταλύματος που θέλουμε να κάνουμε την κράτηση
     * @param from ημερομηνία άφιξης
     * @param till ημερομηνία αναχώρισης
     */
    public void MakeAnAccommodationReservation(String name,LocalDate from,LocalDate till){
        Accommodation acc;
        acc=search_name(name,acc_list.values());
        if(acc!=null){
            boolean f=false;
            Reservations r= new Reservations(from,till);
            for(Reservations res:acc.reservations){
                if (res.start.equals(r.start)) {
                    f = true;
                    break;
                } else if (r.start.isAfter(res.start) && r.start.isBefore(res.end)) {
                    f = true;
                    break;
                } else if (r.end.isAfter(res.start)) {
                    f = true;
                    break;
                }
            }
            if(f==false){
                acc.reservations.add(r);
                My_Bookings.put(r,name);
            }
        }
    }

    /**
     *  μέθοδος που κάνει κράτηση σε ένα δωμάτιο ξενοδοχείου
     * @param name όνομα ξενοδοχείου που θέλουμε να κάνουμε την κράτηση
     * @param roomName όνομα δωματίου
     * @param from ημερομηνία άφιξης
     * @param till ημερομηνία αναχώρισης
     */
    public void MakeAHotelReservation(String name,String roomName,LocalDate from,LocalDate till){
        Hotel hotel= search_HotelName(name,acc_list.values());
        if(hotel!=null){
            boolean f=false;
            Reservations r= new Reservations(from,till);
            Hotel_room ro=search_HotelRoomName(name,roomName,acc_list.values());
            if(ro!=null) {
                for (Reservations res : ro.hotelroomreservations) {
                    if (res.start.equals(r.start)) {
                        f = true;
                        break;
                    } else if (r.start.isAfter(res.start) && r.start.isBefore(res.end)) {
                        f = true;
                        break;
                    } else if (r.end.isAfter(res.start)) {
                        f = true;
                        break;
                    }
                }
                if(f==false){
                    ro.hotelroomreservations.add(r);
                    My_Bookings.put(r,name);
                }
            }
        }
    }

    /**
     * μέθοδος που ακυρώνει μια κράτηση
     * @param r η κράτηση που θέλουμε να ακυρώσουμε
     */
    public void CancelAReservation(Reservations r){
        for (Reservations res : My_Bookings.keySet()) {
            if (res == r) {

                String name= My_Bookings.get(r);
                Accommodation acc=search_name(name,acc_list.values());
                Hotel hotel=search_HotelName(name, acc_list.values());

                if(acc!=null) {
                    acc.reservations.remove(res);
                    acc.cancellations.add(res);
                }else if(hotel!=null){
                    for(Hotel_room h:hotel.Rooms) {
                        for (Reservations reservation : h.hotelroomreservations) {
                            if (reservation== r) {
                                h.hotelroomreservations.remove(r);
                                h.hotelroomcancellations.add(r);
                                break;
                            }
                        }
                    }
                }

                My_Bookings.remove(res);
                Canceled.put(res,My_Bookings.get(r));

                break;
            }
        }
    }

    /**
     * μέθοδος που εμφανίζει τις κρατήσεις του πελάτη
     */
    public void show_my_bookings(){
        for(Reservations res:My_Bookings.keySet()){
            System.out.println(My_Bookings);
        }
    }

    /**
     * μέθοδος που εμφανίζει τις ακυρώσεις του πελάτη
     */
    public void show_my_cancellations(){
        for(Reservations res: My_Bookings.keySet()){
            System.out.println(Canceled);
        }
    }
}