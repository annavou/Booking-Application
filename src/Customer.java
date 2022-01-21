/**
 * Αυτή η κλάση αναπαριστά ένα πελάτη με τα βασικά του στοιχεία (όνομα, τηλέφωνο, μειλ, τοποθεσία), ο οποίος μπορεί να αναζητήσει
 * διάφορα καταλύματα και ξενοδοχεία με τη χρήση φίλτρων, να κάνει κράτηση του καταλύματος που επιθυμεί με βάση
 * της διαθεσιμότητας, να ακυρώσει οποιαδήποτε κράτηση του και να έχει πρόσβαση στις κρατήσεις και τις ακυρώσεις του.
 */

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.*;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Customer extends Person {

    HashMap<Reservations, Accommodation> My_Acc_Bookings = new HashMap<>();
    HashMap<Reservations, Accommodation> My_Acc_Canceled = new HashMap<>();

    HashMap<Reservations, Hotel_room> My_Hot_Bookings = new HashMap<>();
    HashMap<Reservations, Hotel_room> My_Hot_Canceled = new HashMap<>();




    @Serial
    private static final long serialVersionUID = 6529685098267757690L;


    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     *
     * @param aname Το όνομα
     * @param alocation Η έδρα
     * @param aphone_number Το τηλέφωνο
     * @param aemail Το email
     */
    public Customer(String aname, String alocation, String aphone_number, String aemail) {
        super(aname, alocation, aphone_number, aemail);
    }

    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    public Customer() {
        super();
    }

    /**
     * Μέθοδος με την οποία ο πελάτης βλέπει τις κρατήσεις και τις ακυρώσεις του
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    public JPanel resv_canc_view() {
        JPanel main = new JPanel();
        JPanel resvs = new JPanel();
        JPanel cancs = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Κρατήσεις");
        resvs.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Ακυρώσεις");
        cancs.setBorder(titledBorder);
        GridLayout gl = new GridLayout(2,2);
        main.setLayout(gl);
        main.add(resvs,Component.LEFT_ALIGNMENT);
        main.add(cancs);
        JPanel resvsa = new JPanel();
        JPanel cancsa = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Καταλύματα");
        resvsa.setBorder(titledBorder);
        cancsa.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Δωμάτια Ξενοδοχείου");
        JPanel resvsh = new JPanel();
        JPanel cancsh = new JPanel();
        resvsh.setBorder(titledBorder);
        cancsh.setBorder(titledBorder);


        for(Reservations re : My_Acc_Bookings.keySet()){
            resvsa.add(new JLabel("Κράτηση στο Κατάλυμα : " + My_Acc_Bookings.get(re).getName() + " από : " + re.getStart().toString() + " έως : " + re.getEnd().toString()));
        }
        for(Reservations re : My_Hot_Bookings.keySet()){
            resvsh.add(new JLabel("Κράτηση σε Δωμάτιο Ξενοδοχείου : " + My_Hot_Bookings.get(re).getName() + " από : " + re.getStart().toString() + " έως : " + re.getEnd().toString()));
        }
        for(Reservations re : My_Acc_Canceled.keySet()){
            cancsa.add(new JLabel("Ακύρωση Κράτησης Καταλύματος : " + My_Acc_Canceled.get(re).getName() + " από : " + re.getStart().toString() + " έως : " + re.getEnd().toString()));
        }
        for(Reservations re : My_Hot_Canceled.keySet()){
            cancsh.add(new JLabel("Ακύρωση Κράτησης σε Δωμάτιο Ξενοδοχείου : " + My_Hot_Canceled.get(re).getName() + " από : " + re.getStart().toString() + " έως : " + re.getEnd().toString()));
        }

        resvs.add(resvsa);
        resvs.add(resvsh);
        cancs.add(cancsa);
        cancs.add(cancsh);
        return main ;
    }

    /**
     * Μέθοδος με την οποία ο πελάτης ακυρώνει μια κράτηση του
     * @param acc_list Η λίστα με τους χρήστες που αποθηκεύεται στο αρχείο
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    public JPanel Cancel(HashMap<Credentials, Person> acc_list) {
        JPanel main = new JPanel();
        JPanel resvs = new JPanel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Κρατήσεις");
        resvs.setBorder(titledBorder);
        JPanel acc = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Καταλύματα");
        acc.setBorder(titledBorder);
        JPanel hot = new JPanel();
        titledBorder = BorderFactory.createTitledBorder("Δωμάτια Ξενοδοχείου");
        hot.setBorder(titledBorder);

        resvs.add(acc);
        resvs.add(hot);

        ArrayList<Accommodation> list1 = new ArrayList<>();
        HashMap<String,JPanel> subsa = new HashMap<>();
        ArrayList<Hotel_room> list2 = new ArrayList<>();
        HashMap<String,JPanel> subsc = new HashMap<>();
        JComboBox<String> list3 = new JComboBox<>();
        ArrayList<Reservations> list4 = new ArrayList<>();
        updater(list1,list2,list3,list4,subsa,subsc,acc,hot);
        if(list4.isEmpty()){
            JLabel er = new JLabel("Δεν υπάρχουν Κρατήσεις προς Ακύρωση");
            main.add(er);
            return main;
        }


        JButton Cancel = new JButton();
        Cancel.setText("Ακύρωση");
        Cancel.addActionListener(e -> {
            String sele = (String) list3.getSelectedItem();
            if(sele == null)
                return;
            char c = sele.charAt(0);
            int x = Character.getNumericValue(c);
            Reservations to_canc = list4.get(x-1);
            if(to_canc.getHot() == null){
                My_Acc_Bookings.remove(to_canc);
                My_Acc_Canceled.put(to_canc,to_canc.getAcc());
                to_canc.getAcc().reservations.remove(to_canc);
                to_canc.getAcc().cancellations.add(to_canc);
            }
            else{
                My_Hot_Bookings.remove(to_canc);
                My_Hot_Canceled.put(to_canc,to_canc.getHot());
                to_canc.getHot().hotelroomreservations.remove(to_canc);
                to_canc.getHot().hotelroomcancellations.add(to_canc);
            }
            try{ FileOutputStream fos = new FileOutputStream("pls.bin");
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(acc_list);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
            main.removeAll();
            SwingUtilities.updateComponentTreeUI(main);
            list1.clear();
            list2.clear();
            list3.removeAllItems();
            list4.clear();
            subsa.clear();
            subsc.clear();
            acc.removeAll();
            hot.removeAll();
            updater(list1,list2,list3,list4,subsa,subsc,acc,hot);
            if(list4.isEmpty()){
                JLabel er = new JLabel("Δεν υπάρχουν Κρατήσεις προς Ακύρωση");
                main.add(er);
            }
            else {
                main.add(resvs);
                main.add(list3);
                main.add(Cancel);
            }
        });




        main.add(resvs);
        main.add(list3);
        main.add(Cancel);
        return main;
    }

    /**
     * Μέθοδος με την οποία ενημερώνονται και δημιουργούνται κάποιες λίστες και γραφικά στοιχεία βοηθητικά για την ακυρώσει κράτησης
     * @param list1 Λίστα με ολα τα καταλύματα που έχουν κρατήσεις
     * @param list2 Λίστα με ολα τα δωμάτια ξενοδοχείων που έχουν κρατήσεις
     * @param list3 JCombo Box με τα ονόματα των κρατήσεων
     * @param list4 Arraylist με τις κρατήσεις
     * @param subsa Βοηθητικό HashMap για να βάζει τις κρατήσεις στα σωστά JPanels για καταλύματα
     * @param subsc Βοηθητικό HashMap για να βάζει τις κρατήσεις στα σωστά JPanels για Δωμάτια
     * @param acc Το JPanel με τις κρατήσεις σε καταλύματα
     * @param hot Το JPanel με τις κρατήσεις σε Δωμάτια
     */
    private void updater(ArrayList<Accommodation> list1, ArrayList<Hotel_room> list2, JComboBox<String> list3, ArrayList<Reservations> list4, HashMap<String, JPanel> subsa, HashMap<String, JPanel> subsc,JPanel acc,JPanel hot) {
        for(Reservations re : My_Acc_Bookings.keySet()){
            if(!list1.contains(re.getAcc())){
                list1.add(re.getAcc());
                TitledBorder titledBorder = BorderFactory.createTitledBorder(re.getAcc().getName());
                JPanel temp = new JPanel();
                temp.setBorder(titledBorder);
                subsa.put(re.getAcc().getName(),temp);
                JLabel date = new JLabel("Ημερομηνία");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }
            else{
                JPanel temp = subsa.get(re.getAcc().getName());
                JLabel date = new JLabel("Ημερομηνία");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }

        }

        for(Reservations re : My_Hot_Bookings.keySet()){
            if(!list2.contains(re.getHot())){
                list2.add(re.getHot());
                TitledBorder titledBorder = BorderFactory.createTitledBorder(re.getHot().getName());
                JPanel temp = new JPanel();
                temp.setBorder(titledBorder);
                subsc.put(re.getHot().getName(),temp);
                JLabel date = new JLabel("Ημερομηνία");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }
            else{
                JPanel temp = subsc.get(re.getHot().getName());
                JLabel date = new JLabel("Ημερομηνία");
                temp.add(date);
                JLabel b1 = new JLabel(re.getStart().toString() + " / " + re.getEnd().toString());
                temp.add(b1);
            }

        }
        for(String s : subsa.keySet())
            acc.add(subsa.get(s));
        for(String s : subsc.keySet())
            hot.add(subsc.get(s));


        int i = 1 ;
        for(Reservations re : My_Acc_Bookings.keySet()){
            String s = i + ") Στο : " + re.getAcc().getName() + " από  : " + re.getStart().toString() + " έως : " + re.getEnd().toString();
            list3.addItem(s);
            list4.add(re);
        }
        for(Reservations re : My_Hot_Bookings.keySet()){
            String s = i + ") Στο : " + re.getHot().getName() + " από  : " + re.getStart().toString() + " έως : " + re.getEnd().toString();
            list3.addItem(s);
            list4.add(re);
        }
    }

    /**
     * Μέθοδος με τον οποία ο χρήστης κάνει μια κράτηση
     * @param People όλοι οι χρήστες
     * @param acc_list Η λίστα με τους χρήστες που αποθηκεύεται στο αρχείο
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    public JPanel Reserv(Collection<Person> People, HashMap<Credentials, Person> acc_list) {
        JPanel main = new JPanel();
        JPanel opt = new JPanel();
        final JPanel[] view = {new JPanel()};
        JComboBox<String> list  = new JComboBox<>();
        JLabel output = new JLabel();
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Επιλογές");
        opt.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Διαθέσιμα");
        view[0].setBorder(titledBorder);
        Customer egw = this;
        final JButton[] Make_Reservation = {new JButton()};

        ArrayList<Accommodation> list2 = new ArrayList<>();
        ArrayList<Hotel_room> list3 = new ArrayList<>();

        final int[] Day1 = new int[1];
        final int[] Month1 = new int[1];
        final int[] Year1 = new int[1];
        final int[] Day2 = new int[1];
        final int[] Month2 = new int[1];
        final int[] Year2 = new int[1];


        JLabel Name = new JLabel("Όνομα : ");
        JLabel Location = new JLabel("Τοποθεσία : ");
        JLabel Stars = new JLabel("Αστέρια : ");
        JLabel PriceF = new JLabel("Τιμή από : ");
        JLabel PriceT = new JLabel("Τιμή έως : ");
        JLabel Square_meters = new JLabel("Τετραγωνικά Μέτρα : ");
        JLabel Rooms = new JLabel("Δωμάτια : ");
        JLabel Capacity = new JLabel("Χωρητικότητα : ");
        JLabel Breakfast = new JLabel("Πρωινό : ");
        JLabel Wifi = new JLabel("Wifi : ");
        JLabel Parking = new JLabel("Parking : ");
        JLabel Ac = new JLabel("Κλιματισμός : ");
        JLabel Cleaning_Service = new JLabel("Υπηρεσίες Καθαρισμού : ");
        JLabel From = new JLabel("Από : (dd/mm/yyyy) ");
        JLabel Till = new JLabel("Έως : (dd/mm/yyyy)");


        JTextField NameT = new JTextField("");
        JTextField LocationT = new JTextField("");
        JTextField StarsT = new JTextField("");
        JTextField PriceFT = new JTextField("");
        JTextField PriceTT = new JTextField("");
        JTextField Square_metersT = new JTextField("");
        JTextField RoomsT = new JTextField("");
        JTextField CapacityT = new JTextField("");
        JCheckBox BreakfastT = new JCheckBox("");
        JCheckBox WifiT = new JCheckBox();
        JCheckBox ParkingT = new JCheckBox();
        JCheckBox AcT = new JCheckBox();
        JCheckBox Cleaning_ServiceT = new JCheckBox();
        JTextField from = new JTextField("");
        JTextField till = new JTextField("");



        GridLayout layout = new GridLayout(15,2);
        opt.setLayout(layout);
        opt.add(Name);
        opt.add(NameT);
        opt.add(Location);
        opt.add(LocationT);
        opt.add(Stars);
        opt.add(StarsT);
        opt.add(Square_meters);
        opt.add(Square_metersT);
        opt.add(PriceF);
        opt.add(PriceFT);
        opt.add(PriceT);
        opt.add(PriceTT);
        opt.add(Rooms);
        opt.add(RoomsT);
        opt.add(Capacity);
        opt.add(CapacityT);
        opt.add(Breakfast);
        opt.add(BreakfastT);
        opt.add(Wifi);
        opt.add(WifiT);
        opt.add(Parking);
        opt.add(ParkingT);
        opt.add(Ac);
        opt.add(AcT);
        opt.add(Cleaning_Service);
        opt.add(Cleaning_ServiceT);
        opt.add(From);
        opt.add(from);
        opt.add(Till);
        opt.add(till);

       // LocalDate f = null;
       // LocalDate t = null;
        JButton Search = new JButton();
        Search.setText("Αναζήτηση");
        Search.addActionListener(e -> {
            output.setText(valid_input(from.getText(),till.getText(),StarsT.getText(),Square_metersT.getText(),PriceFT.getText(),PriceTT.getText(),RoomsT.getText(),CapacityT.getText()));
            if(!output.getText().equals("Αποθήκευση")){
                main.add(output);
                main.remove(list);
                main.remove(Make_Reservation[0]);
                return;
            }
            else{
                char[] s = from.getText().toCharArray();
                ArrayList<Character> d = new ArrayList<>();
                ArrayList<Character> m = new ArrayList<>();
                ArrayList<Character> y = new ArrayList<>();
                for (int i = 0; i < s.length; i++) {
                    if (s[i] != '/') {
                        d.add(s[i]);
                        s[i] = '0';
                    } else {
                        s[i] = '0';
                        break;
                    }
                }
                for (int i = 0; i < s.length; i++) {
                    if (s[i] != '/') {
                        m.add(s[i]);
                        s[i] = '0';
                    } else {
                        s[i] = '0';
                        break;
                    }
                }
                for (int i = 0; i < s.length; i++) {
                    if (s[i] != '/') {
                        y.add(s[i]);
                        s[i] = '0';
                    } else {
                        s[i] = '0';
                        break;
                    }
                }
                StringBuilder day = new StringBuilder();
                StringBuilder month = new StringBuilder();
                StringBuilder year = new StringBuilder();
                for(char c : d)
                    day.append(c);
                for(char c : m)
                    month.append(c);
                for(char c : y)
                    year.append(c);
                String s1 = day.toString();
                String s2 = month.toString();
                String s3 = year.toString();



                Day1[0] = Integer.parseInt(s1);
                Month1[0] = Integer.parseInt(s2);
                Year1[0] = Integer.parseInt(s3);


                char[] w = till.getText().toCharArray();
                ArrayList<Character> d2 = new ArrayList<>();
                ArrayList<Character> m2 = new ArrayList<>();
                ArrayList<Character> y2 = new ArrayList<>();
                for (int i = 0; i < w.length; i++) {
                    if (w[i] != '/') {
                        d2.add(w[i]);
                        w[i] = '0';
                    } else {
                        w[i] = '0';
                        break;
                    }
                }
                for (int i = 0; i < w.length; i++) {
                    if (w[i] != '/') {
                        m2.add(w[i]);
                        w[i] = '0';
                    } else {
                        w[i] = '0';
                        break;
                    }
                }
                for (int i = 0; i < w.length; i++) {
                    if (w[i] != '/') {
                        y2.add(w[i]);
                        w[i] = '0';
                    } else {
                        w[i] = '0';
                        break;
                    }
                }
                StringBuilder day2 = new StringBuilder();
                StringBuilder month2 = new StringBuilder();
                StringBuilder year2 = new StringBuilder();
                for(char c : d2)
                    day2.append(c);
                for(char c : m2)
                    month2.append(c);
                for(char c : y2)
                    year2.append(c);
                String s12 = day2.toString();
                String s22 = month2.toString();
                String  s32 = year2.toString();


                Day2[0] = Integer.parseInt(s12);
                Month2[0] = Integer.parseInt(s22);
                Year2[0] = Integer.parseInt(s32);

            }
            LocalDate f1 = LocalDate.of(Year1[0],Month1[0],Day1[0]);
            LocalDate t1 = LocalDate.of(Year2[0],Month2[0],Day2[0]);
            view[0].removeAll();
            view[0].add(matching(f1, t1,list,list2,list3,People,NameT.getText(),LocationT.getText(),StarsT.getText(),Square_metersT.getText(),PriceFT.getText(),PriceTT.getText(),RoomsT.getText(),CapacityT.getText(),BreakfastT.isSelected(),WifiT.isSelected(),ParkingT.isSelected(),AcT.isSelected(),Cleaning_ServiceT.isSelected()));
            if(list2.isEmpty() && list3.isEmpty()){
                main.remove(list);
                main.remove(output);
                main.remove(Make_Reservation[0]);
                SwingUtilities.updateComponentTreeUI(main);
            }
            else {
                main.remove(opt);
                main.remove(view[0]);
                main.remove(Search);
                main.remove(list);
                main.remove(output);
                main.remove(Make_Reservation[0]);
                SwingUtilities.updateComponentTreeUI(main);
                main.add(opt);
                main.add(view[0]);
                main.add(Search);
                main.add(list);
                main.add(output);
                main.add(Make_Reservation[0]);
                System.out.println(Day1[0]);
                System.out.println(Day2[0]);
                System.out.println(Month1[0]);
                System.out.println(Month2[0]);
                System.out.println(Year1[0]);
                System.out.println(Year2[0]);


            }
        });
         Make_Reservation[0] = new JButton();
        Make_Reservation[0].setText("Κάντε Κράτηση");
        Make_Reservation[0].addActionListener(e -> {
              String sele = (String) list.getSelectedItem();
            if(sele == null)
                return;
              char q = sele.charAt(0);
              int in = Character.getNumericValue(q) - 1 ;
              Reservations re = new Reservations();
            LocalDate f = LocalDate.of(Year1[0],Month1[0],Day1[0]);
            LocalDate t = LocalDate.of(Year2[0],Month2[0],Day2[0]);
              if( in < list2.size() ){
                  re = new Reservations(f,t,egw,list2.get(in),null,null);
                  System.out.println("acc" + in);
                  My_Acc_Bookings.put(re,list2.get(in));
                  list2.get(in).reservations.add(re);
              }
              else{
                  in = in - list2.size() ;
                  re = new Reservations(f,t,egw,null,list3.get(in),null);
                  System.out.println("hot" + in);
                  My_Hot_Bookings.put(re,list3.get(in));
                  list3.get(in).hotelroomreservations.add(re);
              }
            try{ FileOutputStream fos = new FileOutputStream("pls.bin");
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(acc_list);
            }catch (IOException ex) {
                ex.printStackTrace();
            }

        });



        main.add(opt);
        main.add(view[0]);
        main.add(Search);
        main.add(output);
       // main.add(Make_Reservation);
        return main;
    }

    /**
     * Μέθοδος με την οποία ελέγχετε αν οι ημερομηνίες είναι σωστές και κάποια επιθυμητά πεδία έχουν αριθμητική τιμή κατά την αναζήτηση καταλύματος η Ξενοδοχείου
     * @param from Ημερομηνία απο
     * @param till Ημερομηνία εως
     * @param stars Αστέρια
     * @param sqmtrs Τετραγωνικά
     * @param pricef Τιμή απο
     * @param pricet Τιμή εως
     * @param rooms Δωμάτια
     * @param capacity Χωρητικότητα
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    private String valid_input(String from ,String till,String stars, String sqmtrs, String pricef, String pricet, String rooms, String capacity) {
        String x = "";
        Pattern p = Pattern.compile(".*[0-9]");
        if(!(pricef.equals("") && pricet.equals("")) && (!pricef.matches(p.pattern()) || !pricet.matches(p.pattern())) )
            x = x + "Λάθος Τιμή/";
        if(!rooms.equals("") && !rooms.matches(p.pattern()))
            x = x + "Λάθος Δωμάτια/";
        if(!capacity.equals("") && !capacity.matches(p.pattern()))
            x = x + "Λάθος Χωρητικότητα/";
        if(!sqmtrs.equals("") && !sqmtrs.matches(p.pattern()))
            x = x + "Λάθος Τετραγωνικά/";
        if(!correct_date(from) || !correct_date(till) )
            x = x + "Λάθος Ημερομηνία/";
        p = Pattern.compile("[1-5]");
        if(!stars.equals("") && !stars.matches(p.pattern()))
            x = x + "Λάθος Αστέρια/";
        if(!x.equals(""))
            return x ;
        return "Αποθηκεύτηκαν";

    }

    /**
     * Μέθοδος που ελέγχει αν οι ημερομηνίες αναπαριστούν όντως ημερομηνίες και
     * @param from Ημερομηνία προς έλεγχο
     * @return αν είναι σωστή
     */
    private boolean correct_date(String from) {
        char[] s = from.toCharArray();
        ArrayList<Character> d = new ArrayList<>();
        ArrayList<Character> m = new ArrayList<>();
        ArrayList<Character> y = new ArrayList<>();
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '/') {
                d.add(s[i]);
                s[i] = '0';
            } else {
                s[i] = '0';
                break;
            }
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '/') {
                m.add(s[i]);
                s[i] = '0';
            } else {
                s[i] = '0';
                break;
            }
        }
        for (int i = 0; i < s.length; i++) {
            if (s[i] != '/') {
                y.add(s[i]);
                s[i] = '0';
            } else {
                s[i] = '0';
                break;
            }
        }
        StringBuilder day = new StringBuilder();
        StringBuilder month = new StringBuilder();
        StringBuilder year = new StringBuilder();
        for(char c : d)
            day.append(c);
        for(char c : m)
            month.append(c);
        for(char c : y)
            year.append(c);
        String s1 = day.toString();
        String s2 = month.toString();
        String s3 = year.toString();
        Pattern p = Pattern.compile(".*[0-9]");

        if(!s1.matches(p.pattern()) || !s2.matches(p.pattern()) || !s3.matches(p.pattern()))
            return false;

        int Day,Month,Year;
        Day = Integer.parseInt(s1);
        Month = Integer.parseInt(s2);
        Year = Integer.parseInt(s3);


        if(Day > 31 || Day < 1 || Month < 1 || Month > 12 || Year < 2000 || Year > 2100)
            return false;
        return Month != 2 || Day <= 28;
    }

    /**
     * Μέθοδος η οποία επιστρέφει τα Καταλύματα/Ξενοδοχεία που ταιριάζουν στα φίλτρα του χρήστη
     * @param from Ημερομηνία απο
     * @param till Ημερομηνία εως
     * @param list το JCombo box με τα ονόματα των ξενοδοχείων και καταλυμάτων που ταιριάζουν
     * @param list2 τα καταλύματα που ταιριάζουν
     * @param list3 τα δωμάτια ξενοδοχείου που ταιριάζουν
     * @param People όλοι οι χρήστες
     * @param name όνομα
     * @param loc τοποθεσία
     * @param stars Αστέρια
     * @param sqm Τετραγωνικά
     * @param Pf Τιμή απο
     * @param Pt Τιμή εως
     * @param rooms Δωμάτια
     * @param capac Χωρητικότητα
     * @param breakfast Πρωινό
     * @param wifi  Wifi
     * @param parking Parking
     * @param ac Air Conditioner
     * @param clean Υπηρεσία Καθαρισμού
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    private JPanel matching(LocalDate from ,LocalDate till, JComboBox<String> list,ArrayList<Accommodation> list2,ArrayList<Hotel_room> list3,Collection<Person> People,String name, String loc, String stars, String sqm, String Pf, String Pt, String rooms, String capac, boolean breakfast, boolean wifi, boolean parking, boolean ac, boolean clean) {
        JPanel main = new JPanel();
        ArrayList<Accommodation> accs = new ArrayList<>();
        ArrayList<Hotel> hots = new ArrayList<>();
        list.removeAllItems();
        list2.clear();
        list3.clear();


        for(Person p : People)
            if(p instanceof  Accommodation_Provider a)
                accs.addAll(a.Accommodations);

        for(Person p : People)
            if(p instanceof  Hotel_Provider a)
                hots.addAll(a.Hotels);

        ArrayList<Accommodation> to_remv = new ArrayList<>();
        for(Accommodation acc : accs){
            int n = 0 ;
            if(name.equals("") || acc.getName().contains(name))
                n++;
            if(loc.equals("") || acc.getLocation().contains(loc))
                n++;
            if(stars.equals("") || Integer.parseInt(acc.getStars()) >= Integer.parseInt(stars))
                n++;
            if(sqm.equals("") ||Integer.parseInt(acc.getSqmeter()) >= Integer.parseInt(sqm))
                n++;
            if(rooms.equals("") || Integer.parseInt(acc.getRooms()) >= Integer.parseInt(rooms))
                n++;
            if(capac.equals("") || Integer.parseInt(acc.getCapacity()) >= Integer.parseInt(capac))
                n++;
            if((Pf.equals("") && Pt.equals("")) || (Integer.parseInt(acc.getPrice()) <= Integer.parseInt(Pt) && Integer.parseInt(acc.getPrice()) >= Integer.parseInt(Pf) ))
                n++;
            if(!breakfast || acc.isBreakfast())
                n++;
            if(!parking || acc.isParking())
                n++;
            if(!ac || acc.isAc())
                n++;
            if(!wifi || acc.isWifi())
                n++;
            if(!clean || acc.isCleaning_services())
                n++;
            if(chrono(from,till,acc.reservations))
                n++;
            if(n != 13 )
               to_remv.add(acc);

        }


        ArrayList<Hotel> to_remv2 = new ArrayList<>();
        ArrayList<Hotel_room> to_remv3 = new ArrayList<>();
        for(Hotel hotel : hots){
            int n = 0 ;
            if(name.equals("") || hotel.getName().contains(name))
                n++;
            if(loc.equals("") || hotel.getLocation().contains(loc))
                n++;
            if(stars.equals("") || Integer.parseInt(hotel.getStars()) > Integer.parseInt(stars))
                n++;
                for(Hotel_room hr : hotel.Rooms) {
                    int m = 0;
                    if (sqm.equals("") || Integer.parseInt(hr.getSqmeter()) > Integer.parseInt(sqm))
                        m++;
                    if (capac.equals("") || Integer.parseInt(hr.getCapacity()) > Integer.parseInt(capac))
                        m++;
                    if((Pf.equals("") && Pt.equals("")) || (Integer.parseInt(hr.getPrice()) <= Integer.parseInt(Pt) && Integer.parseInt(hr.getPrice()) >= Integer.parseInt(Pf) ))
                        m++;
                    if (!breakfast || hr.isBreakfast())
                        m++;
                    if (!parking || hr.isParking())
                        m++;
                    if (!ac || hr.isAc())
                        m++;
                    if (!wifi || hr.isWifi())
                        m++;
                    if (!clean || hr.isCleaning_services())
                        m++;
                    if(chrono(from,till,hr.hotelroomreservations))
                        m++;
                    if( m != 9 )
                        to_remv3.add(hr);
                }
                if(to_remv3.containsAll(hotel.Rooms) || n!= 3)
                    to_remv2.add(hotel);
        }

        int k1= 1;
        for(Accommodation acc : accs)
            if(!to_remv.contains(acc)) {
                main.add(new JLabel(acc.getName()));
                list.addItem(k1 +") " + acc.getName());
                list2.add(acc);
                k1++;
            }
        for(Hotel hotel : hots)
            if(!to_remv2.contains(hotel)) {
                JPanel temp = new JPanel();
                TitledBorder titledBorder = BorderFactory.createTitledBorder(hotel.getName());
                temp.setBorder(titledBorder);
                main.add(temp);
                for (Hotel_room hr : hotel.Rooms)
                    if (!to_remv3.contains(hr)) {
                        temp.add(new JLabel(hr.getName()));
                        list.addItem(k1 + ") " + hotel.getName() + " : " + hr.getName());
                        k1++;
                        list3.add(hr);
                    }
            }
        if(to_remv.containsAll(accs) && to_remv2.containsAll(hots)){
            JLabel er = new JLabel("Δεν Βρέθηκαν Καταλύματα/Ξενοδοχεία");
            main.removeAll();
            main.add(er);
            return main;
        }

        return main;
    }

    /**
     * Μέθοδος που ελέγχει αν η ημερομηνία from, till συμπίπτει με κάποιο ήδη υπάρχουσα κράτηση του συγκεκριμένου καταλύματος/Δωματίου
     * @param from ημερομηνία from/απο
     * @param till ημερομηνία till/εως
     * @param Res πίνακας με ήδη υπάρχουσες κρατήσεις
     * @return αν δε συμπίπτει
     */
    private boolean chrono(LocalDate from, LocalDate till, ArrayList<Reservations> Res) {
        boolean flag = true;
            for (Reservations r : Res) {
                if (r.start.equals(from) || r.end.equals(till)) {
                    flag = false;
                    break;
                }
                if (from.isAfter(r.start) && from.isBefore(r.end) || till.isBefore(r.end) && till.isAfter(r.start)) {
                    flag = false;
                    break;
                }
                if (from.isBefore(r.start) && till.isAfter(r.end)) {
                    flag = false;
                    break;
                }
            }
        return flag;
    }
}