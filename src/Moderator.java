/**
 * Κλάση moderator η οποία αναπαριστά έναν διαχειριστή.
 * Ο διαχειριστής έχει πρόσβαση στους χρήστες στις κρατήσεις μπορεί να ακύρωση κράτησης,
 * καθώς και να ενεργοποιήσει η να απενεργοποιήσει προφίλ άλλων χρηστών.
 */
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collection;

public class Moderator extends Person{

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
     * @return
     */
    public JPanel see_all_accommodations(Collection<Person> persons){
        JPanel main = new JPanel();
       JPanel[] subs = new JPanel[4];
        String[] s = new String[4];
        s[0] = "Κατάλυμα";
        s[1] = "Ξενοδοχείο";
        for(int i = 0 ; i < 2 ; i ++){
           subs[i] = new JPanel();
           TitledBorder border = BorderFactory.createTitledBorder(s[i]);
           subs[i].setBorder(border);
            main.add(subs[i]);
        }
       for(Person p : persons){
             if(p instanceof  Accommodation_Provider)  {
               Accommodation_Provider x = (Accommodation_Provider) p ;
                for(int j = 0 ; j < x.Accommodations.size(); j++){
                    subs[0].add(x.Accomodations_Display(x.Accommodations.get(j).getName()));
                }
            }
            if(p instanceof  Hotel_Provider) {
                Hotel_Provider x = (Hotel_Provider) p;
                for (int j = 0; j < x.Hotels.size(); j++) {
                    subs[1].add(x.Hotels_Display(x.Hotels.get(j).getName()));
                }
            }

        }
        GridLayout gridLayout = new GridLayout();
        main.setLayout(gridLayout);
        return main;
    }

    /**
     *Μέθοδος που εμφανίζει όλους τους χρήστες καθώς και την ιδιότητά τους
     * @param persons Συλλογή με όλους τους χρήστες
     * @return
     */
    public JPanel see_all_users(Collection<Person> persons){
        JPanel main = new JPanel();
        JPanel[] subs = new JPanel[4];
        String[] s = new String[4];
        s[0] = "Πάροχος Καταλύματος";
        s[1] = "Πάροχος Ξενοδοχείου";
        s[2] = "Διαχειριστής";
        s[3] = "Πελάτης";
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

        }

        return main;
    }

    private String formated(Person p) {
        String s = " Ονοματεπώνυμο: " + p.getName() + " Έδρα: " + p.getHome_ground() + " Αριθμός Τηλεφώνου: " + p.getPhone_number() + " email: " + p.getEmail();
        return s;
    }

    /**
     *Μέθοδος στην οποία ο διαχειριστής μπορεί να ενεργοποιήσει να απενεργοποιήσει το προφίλ ενός χρήστη
     * @param people Συλλογή με όλους τους χρήστες
     * @return
     */
    public JPanel account_manage(Collection<Person> people) {

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
        Save.setText("Αποθήκευση");
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m[0] = 0 ;
                for (Person p : people) {
                    p.setActivated(buts[m[0]].getState());
                    m[0]++;
                }
            }
        });
        for (Person p : people)
            if(p.isActivated())
                System.out.println("Ναι");

        main.add(Save);
        return main;
    }

    /**
     *Μέθοδος στην οποία μπορεί να δει όλες τις κρατήσεις
     * @param people Συλλογή με όλους τους χρήστες
     * @return
     */
    public JPanel see_all_resv(Collection<Person> people,Boolean swits){
        JPanel main = new JPanel();
        JPanel resvs = new JPanel();
        JPanel cancs = new JPanel();
        Border border = BorderFactory.createLineBorder(Color.black);
        TitledBorder titledBorder = BorderFactory.createTitledBorder("Κρατήσεις");
        resvs.setBorder(titledBorder);
        titledBorder = BorderFactory.createTitledBorder("Ακυρώσεις");
        cancs.setBorder(titledBorder);
        GridLayout gl = new GridLayout(2,2);
        main.setLayout(gl);
        main.add(resvs,Component.LEFT_ALIGNMENT);
        if(swits)
        main.add(cancs);



        for(Person p : people) {
            if(p instanceof Accommodation_Provider) {
                Accommodation_Provider x = (Accommodation_Provider) p ;

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
                        JLabel date = new JLabel("Ημερομηνία"), customer = new JLabel("Πελάτης");
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
                cancs.setLayout(gridLayout);
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
                        JLabel date = new JLabel("Ημερομηνία"), customer = new JLabel("Πελάτης");
                        sub2[i].add(date);
                        sub2[i].add(customer);
                        JLabel b1 = new JLabel(temp.getStart().toString() + " / " + temp.getEnd().toString());
                        JLabel b2 = new JLabel(temp.getCustomer().getName());
                        sub2[i].add(b1);
                        sub2[i].add(b2);

                    }
                }

            }
            if( p instanceof Hotel_Provider){
                Hotel_Provider x = (Hotel_Provider) p ;
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
                            JLabel date = new JLabel("Ημερομηνία") , customer = new JLabel("Πελάτης");
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
                            JLabel date = new JLabel("Ημερομηνία") , customer = new JLabel("Πελάτης");
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
     * @return
     */
    public JPanel resv_canc(Collection<Person> People){
            JPanel main = new JPanel();
            final JPanel[] info = {see_all_resv(People, false)};
            JComboBox<String> list1 = new JComboBox<>();
            ArrayList<Reservations> list2 = new ArrayList<>();
            create_list(People,list1,list2);
        if(list2.isEmpty()){
            JLabel er = new JLabel("Δεν Υπάρχουν Καταχωρημένες Ακυρώσεις");
            main.add(er);
            return main;
        }

        main.add(info[0]);
        JButton Cancel = new JButton();
        Cancel.setText("Ακύρωση");
        Cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = (String) list1.getSelectedItem();
                Character c = sele.charAt(0);
                Reservations to_cancel = list2.get(Character.getNumericValue(c)-1);
                for (Person p : People){
                 //   if(p instanceof Customer c){
                 //       if( to_cancel.getCustomer_name().equals(c.getName()) ){
                 //           c.My_Acc_Bookings.remove(to_cancel);
                 //           c.My_Acc_Canceled.put(to_cancel,to_cancel.getAcc());
                 //       }
                 //   }
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

                }

                if(to_cancel.getHotel()!=null){
                    String start= "Ξενοδοχείο "+ to_cancel.getHotel().getName();//
                    String roo= "Δωμάτιο " + to_cancel.getHot().getName();

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("reservations.txt"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter("temp.txt"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    String currentLine;

                    try {
                        while((currentLine = reader.readLine()) != null) {
                            if(currentLine.contains(start) && currentLine.contains(roo)) continue;
                            writer.write(currentLine);
                            writer.newLine();
                        }
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }


                    try {
                        writer.close();
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp.txt");

                    try {
                        Files.move(oldFile, oldFile.resolveSibling("reservations.txt"), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    try(BufferedWriter buffer=new BufferedWriter(new FileWriter("cancellations.txt",true))){
                        buffer.write("Ακυρώθηκε η κράτηση για το Ξενοδοχείο " + to_cancel.getHotel().getName() + " από " + to_cancel.getStart()
                                + " έως " + to_cancel.getEnd() +  " πελάτης " + to_cancel.getCustomer().getName());
                        buffer.newLine();
                        buffer.flush();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }//
                }else{
                    String start= "Κατάλυμα "+ to_cancel.getAcc().getName();//

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("reservations.txt"));
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    BufferedWriter writer = null;
                    try {
                        writer = new BufferedWriter(new FileWriter("temp.txt"));
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }


                    String currentLine;

                    try {
                        while((currentLine = reader.readLine()) != null) {
                            if(currentLine.contains(start)) continue;
                            writer.write(currentLine);
                            writer.newLine();
                        }
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }

                    try {
                        writer.close();
                        reader.close();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    Path oldFile = Paths.get("C:\\Users\\voylk\\IdeaProjects\\mybooking-anna-akis\\temp.txt");

                    try {
                        Files.move(oldFile, oldFile.resolveSibling("reservations.txt"), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    try(BufferedWriter buffer=new BufferedWriter(new FileWriter("cancellations.txt",true))){
                        buffer.write("Ακυρώθηκε η κράτηση για το κατάλυμα " + to_cancel.getAcc().getName() + " από " + to_cancel.getStart()
                                + " έως " + to_cancel.getEnd() +  " πελάτης " + to_cancel.getCustomer().getName());
                        buffer.newLine();
                        buffer.flush();
                    }catch (IOException ex){
                        ex.printStackTrace();
                    }//
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
                    JLabel er = new JLabel("Δεν Υπάρχουν Καταχωρημένες Ακυρώσεις");
                    main.add(er);
                    return;
                }
                main.add(info[0]);
                main.add(list1);
                main.add(Cancel);
                SwingUtilities.updateComponentTreeUI(main);

            }
        });





        main.add(list1);
        main.add(Cancel);
        return main;
    }

    private void create_list(Collection<Person> People,JComboBox<String> list1, ArrayList<Reservations> list2) {
        int n = 1 ;
        for(Person p : People){
            if(p instanceof Accommodation_Provider ){
                Accommodation_Provider x = (Accommodation_Provider) p ;
                for(int i = 0 ;i < x.Accommodations.size() ; i++){
                    for(int j = 0 ; j < x.Accommodations.get(i).reservations.size();j++){
                        list1.addItem(n +") " + x.Accommodations.get(i).getName() + " στις : " + x.Accommodations.get(i).reservations.get(j).toString() );
                        list2.add(x.Accommodations.get(i).reservations.get(j));
                        n++;
                    }
                }
            }
            if(p instanceof  Hotel_Provider){
                Hotel_Provider x = (Hotel_Provider) p ;
                for(int i = 0 ; i < x.Hotels.size() ; i++){
                    for(int j = 0 ; j < x.Hotels.get(i).Rooms.size() ; j++){
                        for(int k = 0 ; k < x.Hotels.get(i).Rooms.get(j).hotelroomreservations.size() ; k ++){
                            list1.addItem(n +") " + x.Hotels.get(i).Rooms.get(j).getName() + " στις : " + x.Hotels.get(i).Rooms.get(j).hotelroomreservations.get(k).toString() );
                            list2.add(x.Hotels.get(i).Rooms.get(j).hotelroomreservations.get(k));
                            n++;
                        }
                    }
                }
            }
        }
    }

}