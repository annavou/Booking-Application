/**
 * Κλάση moderator η οποία αναπαριστά έναν διαχειριστή.
 * Ο διαχειριστής έχει πρόσβαση στους χρήστες στις κρατήσεις μπορεί να ακύρωση κράτησης,
 * καθώς και να ενεργοποιήσει η να απενεργοποιήσει προφίλ άλλων χρηστών.
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Moderator extends Person{

    @Serial
    private static final long serialVersionUID = 1529685091267757610L;
    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Moderator(){}

    /**
     * @param aname Το όνομα
     * @param ahome_ground Η εδρα
     * @param aphone_number Το τηλέφωνο
     * @param aemail Το email
     */
    Moderator(String aname , String ahome_ground, String aphone_number, String aemail){
        super(aname,ahome_ground,aphone_number,aemail);
        super.setActivated(true);
    }

    /**
     *Μέθοδος που εμφανίζει όλα τα καταλύματα καθώς και ξενοδοχεία
     * @param persons Συλλογή με όλους τους χρήστες
     * @return Το JPanel με που θα εμφανιστει στην οθόνη
     */
    public JPanel see_all_accommodations(Collection<Person> persons){
        JPanel main = new JPanel();
       JPanel[] subs = new JPanel[2];
        String[] s = new String[2];
        s[0] = "Accommodation";
        s[1] = "Hotel";
        for(int i = 0 ; i < 2 ; i ++){
           subs[i] = new JPanel();
           TitledBorder border = BorderFactory.createTitledBorder(s[i]);
           subs[i].setBorder(border);
            main.add(subs[i]);
        }
       for(Person p : persons){
             if(p instanceof Accommodation_Provider x)  {
                 for(int j = 0 ; j < x.Accommodations.size(); j++){
                    subs[0].add(x.Accomodations_Display(x.Accommodations.get(j).getName()));
                }
            }
            if(p instanceof Hotel_Provider x) {
                for (int j = 0; j < x.Hotels.size(); j++) {
                    subs[1].add(x.Hotels_Display(x.Hotels.get(j).getName()));
                }
            }

        }
        GridLayout gridLayout = new GridLayout(2,2);
        main.setLayout(gridLayout);
        return main;
    }

    /**
     *Μέθοδος που εμφανίζει όλους τους χρήστες καθώς και την ιδιότητά τους
     * @param persons Συλλογή με όλους τους χρήστες
     * @return Το JPanel με που θα εμφανιστει στην οθόνη
     */
    public JPanel see_all_users(Collection<Person> persons){
        JPanel main = new JPanel();
        JPanel[] subs = new JPanel[4];
        String[] s = new String[4];
        s[0] = "Accommodation Provider";
        s[1] = "Hotel Provider";
        s[2] = "Moderator";
        s[3] = "Customer";
        for(int i = 0 ; i < 4 ; i ++){
            subs[i] = new JPanel();
            TitledBorder border = BorderFactory.createTitledBorder(s[i]);
            subs[i].setBorder(border);
            main.add(subs[i]);
        }
        for(Person p : persons){
            if(p instanceof  Accommodation_Provider)  {
                subs[0].add(new JLabel(formated(p)));
            }
            if(p instanceof  Hotel_Provider)  {
                subs[1].add(new JLabel(formated(p)));
            }
            if(p instanceof  Moderator)  {
                subs[2].add(new JLabel(formated(p)));
            }
            if(p instanceof  Customer)  {
                subs[3].add(new JLabel(formated(p)));
            }

        }
        GridLayout gridLayout = new GridLayout(2,2);
        main.setLayout(gridLayout);
        return main;
    }

    /**
     *Μέθοδος που μορφοποιεί τις ιδιότητες ενος χρήστη σε ενα String
     * @param p ο person προς εμφάνισει
     * @return το string με τα στοιχεία του p
     */

    private String formated(Person p) {
        String s = " Name: " + p.getName() + " Home Ground: " + p.getHome_ground() + " phone number: " + p.getPhone_number() + " email: " + p.getEmail();
        return s;
    }

    /**
     *Μέθοδος στην οποία ο διαχειριστής μπορεί να ενεργοποιήσει να απενεργοποιήσει το προφίλ ενός χρήστη
     * @param people Συλλογή με όλους τους χρήστες
     * @param acc_list Η λιστα με τους χρήστες που αποθηκεύεται στο αρχείο
     * @return Το JPanel με που θα εμφανιστει στην οθόνη
     */
    public JPanel account_manage(Collection<Person> people, HashMap<Credentials, Person> acc_list) {

        JPanel main = new JPanel();
        int n = people.size();
        Checkbox[] buts = new Checkbox[n];
        final int[] m = {0};
        for(Person p : people){
            buts[m[0]] = new Checkbox();
            buts[m[0]].setLabel(p.getName());
            main.add(buts[m[0]]);
            buts[m[0]].setState(p.isActivated());
            m[0]++;
        }
        JButton Save = new JButton();
        Save.setText("Save");
        Save.addActionListener(e -> {
            m[0] = 0 ;
            for (Person p : people) {
                p.setActivated(buts[m[0]].getState());
                m[0]++;
            }
            try{ FileOutputStream fos = new FileOutputStream("pls.bin");
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(acc_list);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        for (Person p : people)
            if(p.isActivated())
                System.out.println("nai");

        main.add(Save);
        return main;
    }

    /**
     *Μέθοδος στην οποία μπορεί να δει όλες τις κρατήσεις
     * @param people Συλλογή με όλους τους χρήστες
     * @param swits αν θελουμε να εμφανίσει και τις ακυρώσεις
     * @return Το JPanel με που θα εμφανιστει στην οθόνη
     */
    public JPanel see_all_resv(Collection<Person> people,Boolean swits){
        JPanel main = new JPanel();
        JPanel resvs = new JPanel();
        JPanel cancs = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Reservations");
        resvs.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Cancellations");
        cancs.setBorder(titledBorder);
        GridLayout gl = new GridLayout(2,2);
        main.setLayout(gl);
        main.add(resvs,Component.LEFT_ALIGNMENT);
        if(swits)
        main.add(cancs);



        for(Person p : people) {
            if(p instanceof Accommodation_Provider x) {

                ArrayList<Accommodation> non_empty = new ArrayList<>();
                for (Accommodation acc : x.Accommodations)
                    if (!acc.reservations.isEmpty())
                        non_empty.add(acc);
                int n = non_empty.size();

                GridLayout gridLayout = new GridLayout(n, 2);
                resvs.setLayout(gridLayout);

                JPanel[] sub1 = new JPanel[n];


                for (int i = 0; i < n; i++) {
                    sub1[i] = new JPanel();
                    GridLayout layout = new GridLayout(2, 2);
                    sub1[i].setLayout(layout);
                    sub1[i].setBorder(border);
                    titledBorder = BorderFactory.createTitledBorder(non_empty.get(i).getName());
                    sub1[i].setBorder(titledBorder);
                    resvs.add(sub1[i]);

                }
                for (int i = 0; i < n; i++) {
                    int m = non_empty.get(i).reservations.size();
                    for (int j = 0; j < m; j++) {
                        Reservations temp = non_empty.get(i).reservations.get(j);
                        JLabel date = new JLabel("DATE"), customer = new JLabel("CUSTOMER");
                        sub1[i].add(date);
                        sub1[i].add(customer);
                        JLabel b1 = new JLabel(temp.getStart().toString() + " / " + temp.getEnd().toString());
                        JLabel b2 = new JLabel(non_empty.get(i).reservations.get(j).getCustomer().getName());
                        sub1[i].add(b1);
                        sub1[i].add(b2);

                    }
                }


                ArrayList<Accommodation> non_empty2 = new ArrayList<>();
                for (Accommodation acc : x.Accommodations)
                    if (!acc.cancellations.isEmpty())
                        non_empty2.add(acc);
                int n2 = non_empty2.size();
                GridLayout gridLayout2 = new GridLayout(n2, 2);
                cancs.setLayout(gridLayout2);
                JPanel[] sub2 = new JPanel[n2];

                for (int i = 0; i < n2; i++) {
                    sub2[i] = new JPanel();
                    GridLayout layout = new GridLayout(2, 2);
                    sub2[i].setLayout(layout);
                    sub2[i].setBorder(border);
                    titledBorder = BorderFactory.createTitledBorder(non_empty2.get(i).getName());
                    sub2[i].setBorder(titledBorder);
                    cancs.add(sub2[i]);

                }

                for (int i = 0; i < n2; i++) {
                    int m2 = non_empty2.get(i).cancellations.size();
                    for (int j = 0; j < m2; j++) {
                        Reservations temp = non_empty2.get(i).cancellations.get(j);
                        JLabel date = new JLabel("DATE"), customer = new JLabel("CUSTOMER");
                        sub2[i].add(date);
                        sub2[i].add(customer);
                        JLabel b1 = new JLabel(temp.getStart().toString() + " / " + temp.getEnd().toString());
                        JLabel b2 = new JLabel(temp.getCustomer().getName());
                        sub2[i].add(b1);
                        sub2[i].add(b2);

                    }
                }

            }
            if(p instanceof Hotel_Provider x){
                ArrayList<Hotel> non_empty = new ArrayList<>();
                for(Hotel h : x.Hotels)
                    for(Hotel_room hr : h.Rooms)
                        if(!hr.hotelroomreservations.isEmpty()) {
                            non_empty.add(h);
                            break;
                        }
                int n = non_empty.size();
                System.out.println(n);
                JPanel[] sub1 = new JPanel[n];
                for(int i = 0 ; i < n ; i++){
                    sub1[i] = new JPanel();
                    GridLayout layout = new GridLayout(2,2);
                    sub1[i].setLayout(layout);
                    sub1[i].setBorder(border);
                    titledBorder = BorderFactory.createTitledBorder(non_empty.get(i).getName());
                    sub1[i].setBorder(titledBorder);
                    resvs.add(sub1[i]);
                }
                for(int i = 0 ; i < n ; i++){
                    ArrayList<Hotel_room> non_empty2 = new ArrayList<>();
                    for(int j = 0 ; j < non_empty.get(i).Rooms.size() ; j++){
                        if(!non_empty.get(i).Rooms.get(j).hotelroomreservations.isEmpty()){
                            non_empty2.add(non_empty.get(i).Rooms.get(j));
                        }
                    }
                    int m = non_empty2.size();
                    for(int k = 0 ; k < m ; k++){
                        JPanel temp = new JPanel();
                        GridLayout layout = new GridLayout(2,2);
                        temp.setLayout(layout);
                        temp.setBorder(border);
                        titledBorder = BorderFactory.createTitledBorder(non_empty2.get(k).getName());
                        temp.setBorder(titledBorder);
                        sub1[i].add(temp);
                        for(int l = 0 ; l < non_empty2.get(k).hotelroomreservations.size();l++ ){
                            Reservations y = non_empty2.get(k).hotelroomreservations.get(l);
                            JLabel date = new JLabel("DATE") , customer = new JLabel("CUSTOMER");
                            temp.add(date);
                            temp.add(customer);
                            JLabel b1 = new JLabel(y.getStart().toString() + " / " +y.getEnd().toString());
                            JLabel b2 = new JLabel(non_empty2.get(k).hotelroomreservations.get(l).getCustomer().getName());
                            temp.add(b1);
                            temp.add(b2);
                        }
                    }
                }



                ArrayList<Hotel> non_emptyc = new ArrayList<>();
                for(Hotel h : x.Hotels)
                    for(Hotel_room hr : h.Rooms)
                        if(!hr.hotelroomcancellations.isEmpty()) {
                            non_emptyc.add(h);
                            break;
                        }
                int nc = non_emptyc.size();
                JPanel[] sub1c = new JPanel[nc];
                for(int i = 0 ; i < nc ; i++){
                    sub1c[i] = new JPanel();
                    GridLayout layout = new GridLayout(2,2);
                    sub1c[i].setLayout(layout);
                    sub1c[i].setBorder(border);
                    titledBorder = BorderFactory.createTitledBorder(non_emptyc.get(i).getName());
                    sub1c[i].setBorder(titledBorder);
                    cancs.add(sub1c[i]);
                }
                for(int i = 0 ; i < nc ; i++){
                    ArrayList<Hotel_room> non_empty2c = new ArrayList<>();
                    for(int j = 0 ; j < non_emptyc.get(i).Rooms.size() ; j++){
                        if(!non_emptyc.get(i).Rooms.get(j).hotelroomcancellations.isEmpty()){
                            non_empty2c.add(non_emptyc.get(i).Rooms.get(j));
                        }
                    }
                    int mc = non_empty2c.size();
                    for(int k = 0 ; k < mc ; k++){
                        JPanel temp = new JPanel();
                        GridLayout layout = new GridLayout(2,2);
                        temp.setLayout(layout);
                        temp.setBorder(border);
                        titledBorder = BorderFactory.createTitledBorder(non_empty2c.get(k).getName());
                        temp.setBorder(titledBorder);
                        sub1c[i].add(temp);
                        for(int l = 0 ; l < non_empty2c.get(k).hotelroomcancellations.size();l++ ){
                            Reservations y = non_empty2c.get(k).hotelroomcancellations.get(l);
                            JLabel date = new JLabel("DATE") , customer = new JLabel("CUSTOMER");
                            temp.add(date);
                            temp.add(customer);
                            JLabel b1 = new JLabel(y.getStart().toString() + " / " +y.getEnd().toString());
                            JLabel b2 = new JLabel(non_empty2c.get(k).hotelroomcancellations.get(l).getCustomer().getName());
                            temp.add(b1);
                            temp.add(b2);
                        }
                    }
                }
            }
        }
        return main;
    }



    /**
     * Μέθοδος στην οποία μπορεί να ακυρώσει κρατήσεις
     * @param People Συλλογή με όλους τους χρήστες
     * @param acc_list Η λιστα με τους χρήστες που αποθηκεύεται στο αρχείο
     * @return Το JPanel με που θα εμφανιστει στην οθόνη
     */
    public JPanel resv_canc(Collection<Person> People, HashMap<Credentials, Person> acc_list){
            JPanel main = new JPanel();
            final JPanel[] info = {see_all_resv(People, false)};
            JComboBox<String> list1 = new JComboBox<>();
            ArrayList<Reservations> list2 = new ArrayList<>();
            create_list(People,list1,list2);
        if(list2.isEmpty()){
            JLabel er = new JLabel("hh");
            main.add(er);
            return main;
        }

        main.add(info[0]);
        JButton Cancel = new JButton();
        Cancel.setText("Cancel");
        Cancel.addActionListener(e -> {
            String sele = (String) list1.getSelectedItem();
            if(sele == null)
                return;
            char c = sele.charAt(0);
            Reservations to_cancel = list2.get(Character.getNumericValue(c)-1);
            for (Person p : People){
                if(p instanceof Accommodation_Provider ap && to_cancel.getAcc()!=null){
                    for (Accommodation accommodation : ap.Accommodations)
                        if(to_cancel.getAcc().equals(accommodation)){
                            accommodation.reservations.remove(to_cancel);
                            accommodation.cancellations.add(to_cancel);
                        }
                }
                if(p instanceof Hotel_Provider hp && to_cancel.getHot()!=null){
                    for (Hotel h : hp.Hotels)
                        for (Hotel_room hr : h.Rooms)
                            if(to_cancel.getHot().equals(hr)){
                                hr.hotelroomreservations.remove(to_cancel);
                                hr.hotelroomcancellations.add(to_cancel);

                            }
                }
                if(p instanceof Customer f ){
                    if( to_cancel.getCustomer().getName().equals(f.getName()) ){
                        if(to_cancel.getHot() == null) {
                            f.My_Acc_Bookings.remove(to_cancel);
                            f.My_Acc_Canceled.put(to_cancel, to_cancel.getAcc());
                        }
                        if(to_cancel.getAcc() == null) {
                            f.My_Hot_Bookings.remove(to_cancel);
                            f.My_Hot_Canceled.put(to_cancel, to_cancel.getHot());
                        }
                    }
                }

            }
            try{ FileOutputStream fos = new FileOutputStream("pls.bin");
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(acc_list);
            }catch (IOException ex) {
                ex.printStackTrace();
            }
            main.remove(info[0]);
            info[0] = see_all_resv(People,false);
            main.remove(list1);
            list1.removeAllItems();
            main.remove(Cancel);
            SwingUtilities.updateComponentTreeUI(main);
            list2.clear();
            create_list(People,list1,list2);
            if(list2.isEmpty()){
                JLabel er = new JLabel("hh");
                main.add(er);
                return;
            }
            main.add(info[0]);
            main.add(list1);
            main.add(Cancel);
            SwingUtilities.updateComponentTreeUI(main);

        });





        main.add(list1);
        main.add(Cancel);
        return main;
    }

    /**
     * Μέθοδος που δημιουργεί ενα JCombo Box με τα ονόματα απο τις κρατήσεις και ενα arraylist με τις κρατήσεις
     * @param People ολοι οι χρήστες
     * @param list1 το JCombo Box με τα ονόματα απο τις κρατήσεις
     * @param list2 το arraylist με τις κρατήσεις
     */

    private void create_list(Collection<Person> People,JComboBox<String> list1, ArrayList<Reservations> list2) {
        int n = 1 ;
        for(Person p : People){
            if(p instanceof Accommodation_Provider x){
                for(int i = 0 ;i < x.Accommodations.size() ; i++){
                    for(int j = 0 ; j < x.Accommodations.get(i).reservations.size();j++){
                        list1.addItem(n +") " + x.Accommodations.get(i).getName() + " at : " + x.Accommodations.get(i).reservations.get(j).getStart().toString() + " to : "+x.Accommodations.get(i).reservations.get(j).getEnd().toString() );
                        list2.add(x.Accommodations.get(i).reservations.get(j));
                        n++;
                    }
                }
            }
            if(p instanceof Hotel_Provider x){
                for(int i = 0 ; i < x.Hotels.size() ; i++){
                    for(int j = 0 ; j < x.Hotels.get(i).Rooms.size() ; j++){
                        for(int k = 0 ; k < x.Hotels.get(i).Rooms.get(j).hotelroomreservations.size() ; k ++){
                            list1.addItem(n +") " + x.Hotels.get(i).Rooms.get(j).getName() + " from : " + x.Hotels.get(i).Rooms.get(j).hotelroomreservations.get(k).getStart().toString() + " to : " + x.Hotels.get(i).Rooms.get(j).hotelroomreservations.get(k).getEnd().toString());
                            list2.add(x.Hotels.get(i).Rooms.get(j).hotelroomreservations.get(k));
                            n++;
                        }
                    }
                }
            }
        }
    }

}