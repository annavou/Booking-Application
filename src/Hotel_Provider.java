/**
 * Κλάση η οποία αναπαριστά έναν πάροχο ξενοδοχείου.
 * Κληρονόμοι από την κλάση person τα βασικά χαρακτηριστικά.
 * Ακόμα έχει μία λίστα με ξενοδοχεία τα οποία είναι τα ξενοδοχεία τα οποία προσφέρει
 */

import javax.swing.*;
import java.io.*;//
import java.nio.file.Files;//
import java.nio.file.Path;//
import java.nio.file.Paths;//
import java.nio.file.StandardCopyOption;//
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.time.temporal.ChronoUnit;

public class Hotel_Provider extends  Person {

    ArrayList<Hotel> Hotels = new ArrayList<>();

    Hotel hotel;


    /**
     * Ο κενός κατασκευαστής
     */
    Hotel_Provider() {

    }


    /**
     * Κατασκευαστής ο οποίος δέχεται ως όρισμα τα πεδία του person και τα περνάει σε αυτόν
     */
    Hotel_Provider(String aname, String ahome_ground, String aphone_number, String aemail) {
        super(aname, ahome_ground, aphone_number, aemail);
    }



    /**
     * Μέθοδος η οποία εμφανίζει όλα τα ξενοδοχεία
     * @return
     */
    public JPanel Hotels_Display_All() {
        int lenght = Hotels.size();
        JPanel[] jps = new JPanel[lenght];


        JPanel panel1 = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Accom_displ");
        Border black = BorderFactory.createLineBorder(Color.black);
        panel1.setBorder(border);
        GridLayout layout = new GridLayout(lenght,1);
        //panel1.setLayout(layout);
        BoxLayout box = new BoxLayout(panel1,BoxLayout.Y_AXIS);
        panel1.setLayout(box);

        for( int i =0 ; i < lenght ; i++) {
            jps[i] = new JPanel();
            String s1 = Hotels.get(i).getName() + " είναι ένα " + Hotels.get(i).getStars() + " αστέρων ξενοδοχείο, βρίσκεται στην " + Hotels.get(i).getLocation() + " και έχει τα εξής δωμάτια: ";
            jps[i].add(new JLabel(s1));
            JPanel[] jps_sub = new JPanel[Hotels.get(i).Rooms.size()];
            box = new BoxLayout(jps[i],BoxLayout.Y_AXIS);
            jps[i].setLayout(box);
            for(int j = 0 ; j < Hotels.get(i).Rooms.size();j++ ){
                jps_sub[j] = new JPanel();
                // jps_sub[i].setLayout(box);
                Hotel_room hr = Hotels.get(i).Rooms.get(j);
                String s2 = "Το δωμάτιο " + hr.getName() +" είναι " + hr.getSqmeter() + " τετραγωνικά και είναι κατάλληλο για " + hr.getCapacity() + " άτομα.";
                String s3 = hr.hasBreakfast() + " Πρωινό\n " + hr.hasAc() + " Κλιματισμός\n " + hr.hasParking() + " Parking\n " + hr.hasWifi() + " Wifi\n " + hr.hasCleaningservice() + " Υπηρεσίες Καθαρισμού\n ";
                jps_sub[j].add(new JLabel(s2));
                jps_sub[j].add(new JLabel(s3));
                jps_sub[j].setBorder(black);
                jps[i].add(jps_sub[j]);
            }
            Border brd = BorderFactory.createLineBorder(Color.black);
            jps[i].setBorder(brd);
            panel1.add(jps[i]);
        }

        return panel1;
    }


    /**
     * Μέθοδος η οποία εμφανίζει ένα συγκεκριμένο ξενοδοχείο
     */
    public JPanel Hotels_Display(String name) {
        Hotel hot = null;
        for ( Hotel h : Hotels) {
            if (name.equals(h.getName())) {
                hot = h;
            }
        }
        Border brd = BorderFactory.createLineBorder(Color.black);
        JPanel main = new JPanel();
        String s1 = hot.getName() + " είναι ένα " + hot.getStars() + " αστέρων ξενοδοχείο, βρίσκεται στην " + hot.getLocation() + " και έχει τα εξής δωμάτια: ";
        main.add(new JLabel(s1));
        JPanel[] jps_sub = new JPanel[hot.Rooms.size()];
        BoxLayout box = new BoxLayout(main,BoxLayout.Y_AXIS);
        //jps[i].setLayout(box);
        for(int j = 0 ; j < hot.Rooms.size();j++ ){
            jps_sub[j] = new JPanel();
            // jps_sub[i].setLayout(box);
            Hotel_room hr = hot.Rooms.get(j);
            String s2 = "Το δωμάτιο " + hr.getName() +" είναι " + hr.getSqmeter() + " τετραγωνικά και είναι κατάλληλο για " + hr.getCapacity() + " άτομα.";
            String s3 = hr.hasBreakfast() + " Πρωινό\n " + hr.hasAc() + " Κλιματισμός\n " + hr.hasParking() + " Parking\n " + hr.hasWifi() + " Wifi\n " + hr.hasCleaningservice() + " Υπηρεσίες Καθαρισμού\n ";
            jps_sub[j].add(new JLabel(s2));
            jps_sub[j].add(new JLabel(s3));
            jps_sub[j].setBorder(brd);
            main.add(jps_sub[j]);
        }

        return main;

    }


    /**
     * Μέθοδος η οποία προσθέτει ένα καινούργιο ξενοδοχείο στη λίστα
     * @return
     */
    public JPanel Hotel_add() {
        JPanel[] main ={new JPanel()};
        final JTextField[] output = {new JTextField()};
        output[0].setEditable(false);
        JLabel Name = new JLabel("Όνομα");
        JLabel Location = new JLabel("Τοποθεσία");
        JLabel Stars = new JLabel("Αστέρια");
        JButton add_room = new JButton("Προσθήκη Δωματίων");




        JTextField NameT = new JTextField("");
        JTextField LocationT = new JTextField("");
        JTextField StarsT = new JTextField("");


        GridLayout layout = new GridLayout(3,1);
        main[0].setLayout(layout);
        main[0].add(Name);
        main[0].add(NameT);
        main[0].add(Location);
        main[0].add(LocationT);
        main[0].add(Stars);
        main[0].add(StarsT);

        JButton add = new JButton("Αποθήκευση");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Pattern p = Pattern.compile("[1-5]");
                if(!StarsT.getText().matches(p.pattern()))
                    output[0].setText("Λάθος Αριθμός Αστερίων");
                else
                    output[0].setText("Αποθηκεύτηκαν");
                if(!output[0].getText().equals("Αποθηκεύτηκαν")){
                    return;
                }
                Hotel temp = new Hotel(NameT.getText(),LocationT.getText(),StarsT.getText());
                Hotels.add(temp);

                try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){//
                    buffer.write("Ξενοδοχείο: " + temp.getName() +" - "+ "Στην τοποθεσία: " + temp.getLocation() + " - " + "Αστέρια Ξενοδοχείου: "
                            + temp.getStars() + " - " + "Με τα εξής δωμάτια: " );
                    buffer.newLine();
                }catch (IOException ex){
                    ex.printStackTrace();
                }//

                NameT.setText("");
                LocationT.setText("");
                StarsT.setText("");
                if(!Hotels.isEmpty())
                    main[0].add(add_room);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }
        });



        add_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main[0].removeAll();
                JPanel temp = Hotel_Room_add();
                temp.setLocation(0,0);
                main[0].setLayout(new BorderLayout());
                main[0].add(temp);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }
        });

        if(!Hotels.isEmpty())
            main[0].add(add_room);

        main[0].add(add);
        main[0].add(output[0]);
        return main[0];
    }

    private JPanel Hotel_Room_add() {
        JPanel[] main ={new JPanel()};
        final JTextField[] output = {new JTextField()};
        output[0].setEditable(false);
        JComboBox<String> list = new JComboBox<>();

        for(Hotel h : Hotels)
            list.addItem(h.getName());

        for(Hotel h : Hotels)
            if(list.getSelectedItem().equals(h.getName()))
                hotel = h ;


        JLabel Price = new JLabel("Τιμή");
        JLabel Square_meters = new JLabel("Τετραγωνικά Μέτρα");
        JLabel Capacity = new JLabel("Χωρητικότητα");
        JLabel Breakfast = new JLabel("Πρωινό");
        JLabel Wifi = new JLabel("Wifi");
        JLabel Parking = new JLabel("Parking");
        JLabel Ac = new JLabel("Κλιματισμός");
        JLabel Cleaning_Service = new JLabel("Υπηρεσία Καθαρισμού");
        JLabel Name = new JLabel("Όνομα");


        JTextField NameT = new JTextField("");
        JTextField PriceT = new JTextField("");
        JTextField Square_metersT = new JTextField("");
        JTextField CapacityT = new JTextField("");
        JCheckBox BreakfastT = new JCheckBox("");
        JCheckBox WifiT = new JCheckBox();
        JCheckBox ParkingT = new JCheckBox();
        JCheckBox AcT = new JCheckBox();
        JCheckBox Cleaning_ServiceT = new JCheckBox();



        GridLayout layout = new GridLayout(10,1);
        main[0].setLayout(layout);
        main[0].add(Name);
        main[0].add(NameT);
        main[0].add(Square_meters);
        main[0].add(Square_metersT);
        main[0].add(Price);
        main[0].add(PriceT);
        main[0].add(Capacity);
        main[0].add(CapacityT);
        main[0].add(Breakfast);
        main[0].add(BreakfastT);
        main[0].add(Wifi);
        main[0].add(WifiT);
        main[0].add(Parking);
        main[0].add(ParkingT);
        main[0].add(Ac);
        main[0].add(AcT);
        main[0].add(Cleaning_Service);
        main[0].add(Cleaning_ServiceT);
        main[0].add(list);

        JButton add = new JButton("Αποθήκευση");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output[0].setText(input_check(PriceT.getText(),Square_metersT.getText(),CapacityT.getText()));
                if(!output[0].getText().equals("Αποθηκεύτηκαν")){
                    return;
                }
                for(Hotel h : Hotels)
                    if(list.getSelectedItem().equals(h.getName()))
                        hotel = h ;

                String start= "Ξενοδοχείο: "+hotel.getName();//
                String rooms="            ";

                BufferedReader reader = null;
                try {
                    reader = new BufferedReader(new FileReader("accommodations.txt"));
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
                        if(currentLine.contains(start)|| currentLine.contains(rooms)) continue;
                        writer.write(currentLine);
                        writer.newLine();
                    }
                } catch (IOException ex) {
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
                    Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }//

                Hotel_room temp = new Hotel_room(NameT.getText(),PriceT.getText(),CapacityT.getText(),BreakfastT.isSelected(), WifiT.isSelected(),AcT.isSelected(),ParkingT.isSelected(),Cleaning_ServiceT.isSelected(),Square_metersT.getText());
                hotel.Rooms.add(temp);

                try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){//
                    buffer.write("Ξενοδοχείο: " + hotel.getName() +" - "+ "Στην τοποθεσία: " + hotel.getLocation() + " - " + "Αστέρια Ξενοδοχείου: "
                            + hotel.getStars() + " - " + "Με τα εξής δωμάτια: " );
                    buffer.newLine();

                    for(Hotel_room r: hotel.Rooms){
                        buffer.write("            ");
                        buffer.write("Δωμάτιο: " + r.getName() + " - "+"Τιμή ανα βράδυ: " + r.getPrice() + "$" + " - " + "Τετραγωνικά δωματίου: "
                                + r.getSqmeter() + " - " + "Χωρητικότητα Δωματίου: " + r.getCapacity() +"άτομα" + " - " + "Το κατάλυμα προσφέρει: ");

                        if(r.isAc()){
                            buffer.write("Κλιματισμό ");
                        }
                        if(r.isBreakfast()){
                            buffer.write("Πρωινό ");
                        }
                        if (r.isCleaning_services()){
                            buffer.write("Υπηρεσίες Καθαρισμού ");
                        }
                        if (r.isParking()){
                            buffer.write("Parking ");
                        }
                        if(r.isWifi()){
                            buffer.write("Wifi.");
                        }
                        buffer.newLine();
                    }
                }catch (IOException ex){
                    ex.printStackTrace();
                }//

                NameT.setText("");
                PriceT.setText("");
                Square_metersT.setText("");
                CapacityT.setText("");
                BreakfastT.setSelected(false);
                WifiT.setSelected(false);
                ParkingT.setSelected(false);
                AcT.setSelected(false);
                Cleaning_ServiceT.setSelected(false);
            }
        });


        main[0].add(add);
        main[0].add(output[0]);
        return main[0];
    }



    /**
     * Μέθοδος η οποία φέρει ένα ξενοδοχείο από τη λίστα
     * @return
     */
    public JPanel Hotel_delete() {
        final boolean[] press = new boolean[1];
        press[0] = false;
        final JPanel[] main = {new JPanel()};
        JComboBox<String> list1 = new JComboBox<>();
        JComboBox<String> list2 = new JComboBox<>();
        final JComboBox<String>[] finalList = new JComboBox[]{list2};
        final JPanel[] info = {new JPanel()};
        if(Hotels.isEmpty()){
            main[0].add(new JLabel("Δεν Υπάρχουν Καταχωρημένα Ξενοδοχεία"));
            return main[0];
        }

        for (Hotel hotel : Hotels) {
            list1.addItem(hotel.getName());
        }
        finalList[0] = create_list((String) list1.getSelectedItem());
        main[0].add(list1);
        main[0].add(finalList[0]);


        JButton delete = new JButton("Διαγραφή");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                press[0] = true;
                if (finalList[0].getSelectedItem().equals("Ολόκληρο το Ξενοδοχείο")) {
                    String sele = String.valueOf(list1.getSelectedItem());
                    Hotel h = null;
                    for (int i = 0; i < list1.getItemCount(); i++) {
                        if (Hotels.get(i).getName().equals(sele)) {
                            h = Hotels.get(i);
                        }
                    }
                    Hotels.remove(h);

                    String start= "Ξενοδοχείο: "+h.getName();//
                    String rooms="            ";

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("accommodations.txt"));
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
                            if(currentLine.contains(start)|| currentLine.contains(rooms)) continue;
                                 writer.write(currentLine);
                                 writer.newLine();
                        }
                    } catch (IOException ex) {
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
                        Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }//

                    list1.removeAllItems();
                    for (Hotel hotel : Hotels) {
                        list1.addItem(hotel.getName());
                    }
                    if (Hotels.isEmpty()) {
                        main[0].remove(list1);
                        main[0].remove(finalList[0]);
                        main[0].remove(delete);
                        main[0].remove(info[0]);
                        main[0].add(new JLabel("Δεν Υπάρχουν Καταχωρημένα Ξενοδοχεία"));

                    } else {
                        main[0].remove(list1);
                        main[0].remove(finalList[0]);
                        main[0].remove(delete);
                        main[0].remove(info[0]);
                        SwingUtilities.updateComponentTreeUI(main[0]);
                        SwingUtilities.updateComponentTreeUI(list1);
                        main[0].add(list1);
                        main[0].add(finalList[0]);
                        main[0].add(delete);
                        info[0] = Hotels_Display((String) list1.getSelectedItem());
                        main[0].add(info[0]);
                    }

                }
                else{
                    String sele = String.valueOf(list1.getSelectedItem());
                    Hotel h = null;
                    Hotel_room temp = new Hotel_room();

                    for (int i = 0; i < list1.getItemCount(); i++)
                        if (Hotels.get(i).getName().equals(sele))
                            h = Hotels.get(i);

                    for(Hotel_room hr : h.Rooms)
                        if(hr.getName().equals(finalList[0].getSelectedItem()))
                            temp = hr ;

                    String start= "Δωμάτιο: "+temp.getName();//
                    String rooms="            ";

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("accommodations.txt"));
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
                            if(currentLine.contains(start) && currentLine.contains(rooms)) continue;
                            writer.write(currentLine);
                            writer.newLine();
                        }
                    } catch (IOException ex) {
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
                        Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }//

                    h.Rooms.remove(temp);
                    main[0].remove(finalList[0]);
                    main[0].remove(delete);
                    main[0].remove(info[0]);
                    finalList[0].removeAllItems();
                    finalList[0] = create_list(h.getName());
                    SwingUtilities.updateComponentTreeUI(main[0]);
                    SwingUtilities.updateComponentTreeUI(list1);
                    main[0].add(finalList[0]);
                    main[0].add(delete);
                    info[0] = Hotels_Display((String) list1.getSelectedItem());
                    main[0].add(info[0]);
                }
                SwingUtilities.updateComponentTreeUI(main[0]);

                press[0] = false;
            }
        });



        list1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( press[0])
                    return;
                main[0].remove(info[0]);
                main[0].remove(delete);
                main[0].remove(finalList[0]);
                finalList[0] = create_list( (String) list1.getSelectedItem());
                SwingUtilities.updateComponentTreeUI(main[0]);
                info[0] = Hotels_Display( (String) list1.getSelectedItem());
                main[0].add(finalList[0]);
                main[0].add(delete);
                main[0].add(info[0]);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }

        });


        main[0].add(delete);
        info[0] = Hotels_Display( (String) list1.getSelectedItem());
        main[0].add(info[0]);
        return main[0];
    }

    private JComboBox<String> create_list(String selectedItem) {
        Hotel hotel = new Hotel() ;
        JComboBox<String> list = new JComboBox<>();
        list.addItem("Ολόκληρο το Ξενοδοχείο");
        for(Hotel h : Hotels)
            if(h.getName().equals(selectedItem))
                hotel = h ;
        for(Hotel_room hr : hotel.Rooms)
            list.addItem(hr.getName());
        return list;
    }


    /**
     * Μέθοδος επεξεργασίας ενός ξενοδοχείου
     * @return
     */
    public JPanel Hotel_edit() {

        JComboBox<String> list = new JComboBox<>();
        JButton edit_room = new JButton("Επεξεργασία Δωματίου του συγκεκριμένου Ξενοδοχείου");
        JPanel[] main = {new JPanel()};
        final JTextField[] output = {new JTextField()};
        output[0].setEditable(false);
        JLabel Name = new JLabel("Όνομα");
        JLabel Location = new JLabel("Τοποθεσία");
        JLabel Stars = new JLabel("Αστέρια");
        if(Hotels.isEmpty()){
            JLabel er = new JLabel("Δεν Υπάρχουν Καταχωρημένα Δωμάτια");
            main[0].add(er);
            return main[0];
        }

        JTextField NameT = new JTextField(Hotels.get(0).getName());
        JTextField LocationT = new JTextField(Hotels.get(0).getLocation());
        JTextField StarsT = new JTextField(Hotels.get(0).getStars());

        GridLayout layout = new GridLayout(3,2);
        main[0].setLayout(layout);
        main[0].add(Name);
        main[0].add(NameT);
        main[0].add(Location);
        main[0].add(LocationT);
        main[0].add(Stars);
        main[0].add(StarsT);

        for (Hotel hotel : Hotels) {
            list.addItem(hotel.getName());
        }

        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = String.valueOf(list.getSelectedItem());
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(Hotels.get(i).getName().equals(sele)){
                        NameT.setText(Hotels.get(i).getName());
                        LocationT.setText(Hotels.get(i).getLocation());
                        StarsT.setText(Hotels.get(i).getStars());
                    }
                }
            }
        });

        JButton Save = new JButton("Αποθήκευση");
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = String.valueOf(list.getSelectedItem());
                Pattern p = Pattern.compile("[1-5]");
                if(!StarsT.getText().matches(p.pattern()))
                    output[0].setText("Λάθος Αριθμός Αστεριών");
                else
                    output[0].setText("Αποθηκεύτηκαν");
                if(!output[0].getText().equals("Αποθηκεύτηκαν")){
                    return;
                }
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(Hotels.get(i).getName().equals(sele)){

                        String start= "Ξενοδοχείο: "+Hotels.get(i).getName();//
                        String rooms="            ";

                        BufferedReader reader = null;
                        try {
                            reader = new BufferedReader(new FileReader("accommodations.txt"));
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
                            while ((currentLine = reader.readLine()) != null) {
                                if (currentLine.contains(start) || currentLine.contains(rooms)) continue;
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
                            Files.move(oldFile, oldFile.resolveSibling("accommodations.txt"), StandardCopyOption.REPLACE_EXISTING);
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                        }//

                        Hotels.get(i).setName(NameT.getText());
                        Hotels.get(i).setLocation(LocationT.getText());
                        Hotels.get(i).setStars(StarsT.getText());

                        try(BufferedWriter buffer= new BufferedWriter(new FileWriter("accommodations.txt",true))){//
                            buffer.write("Ξενοδοχείο: " + Hotels.get(i).getName() +" - "+ "Στην τοποθεσία: " + Hotels.get(i).getLocation() + " - " + "Αστέρια Ξενοδοχείου: "
                                    + Hotels.get(i).getStars() + " - " + "Με τα εξής δωμάτια: " );
                            buffer.newLine();

                            for(Hotel_room r: hotel.Rooms){
                                buffer.write("            ");
                                buffer.write("Δωμάτιο: " + r.getName() + " - "+"Τιμή ανα βράδυ: " + r.getPrice() + "$" + " - " + "Τετραγωνικά δωματίου: "
                                        + r.getSqmeter() + " - " + "Χωρητικότητα Δωματίου: " + r.getCapacity() +"άτομα" + " - " + "Το κατάλυμα προσφέρει: ");

                                if(r.isAc()){
                                    buffer.write("Κλιματισμό ");
                                }
                                if(r.isBreakfast()){
                                    buffer.write("Πρωινό ");
                                }
                                if (r.isCleaning_services()){
                                    buffer.write("Υπηρεσίες Καθαρισμού ");
                                }
                                if (r.isParking()){
                                    buffer.write("Parking ");
                                }
                                if(r.isWifi()){
                                    buffer.write("Wifi.");
                                }
                                buffer.newLine();
                            }
                        }catch (IOException ex){
                            ex.printStackTrace();
                        }//
                    }
                }
                main[0].remove(list);
                main[0].remove(Save);
                main[0].remove(output[0]);
                main[0].remove(edit_room);
                list.removeAllItems();
                for (Hotel hotel : Hotels) {
                    list.addItem(hotel.getName());
                }
                SwingUtilities.updateComponentTreeUI(main[0]);
                SwingUtilities.updateComponentTreeUI(list);
                main[0].add(list);
                main[0].add(Save);
                main[0].add(output[0]);
                main[0].add(edit_room);

            }
        });
        edit_room.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main[0].removeAll();
                JPanel temp = Hotel_Room_edit((String) list.getSelectedItem());
                temp.setLocation(0,0);
                main[0].setLayout(new BorderLayout());
                main[0].add(temp);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }
        });

        main[0].add(list);
        main[0].add(Save);
        main[0].add(output[0]);
        main[0].add(edit_room);
        return main[0];
    }

    private JPanel Hotel_Room_edit(String name) {
        Hotel[] selected = {new Hotel()};
        for(Hotel h : Hotels)
            if(name.equals(h.getName()))
                selected[0] = h ;
        JComboBox<String> list = new JComboBox<>();
        JPanel main = new JPanel();
        final JTextField[] output = {new JTextField()};
        output[0].setEditable(false);
        JLabel Name = new JLabel("Όνομα");
        JLabel Price = new JLabel("Τιμή");
        JLabel Square_meters = new JLabel("Τετραγωνικά Μέτρα");
        JLabel Capacity = new JLabel("Χωρητικότητα");
        JLabel Breakfast = new JLabel("Πρωινό");
        JLabel Wifi = new JLabel("Wifi");
        JLabel Parking = new JLabel("Parking");
        JLabel Ac = new JLabel("Κλιματισμός");
        JLabel Cleaning_Service = new JLabel("Υπηρεσία Καθαρισμού");
        if(selected[0].Rooms.isEmpty()){
            JLabel er = new JLabel("Δεν υπάρχουν Καταχωρημένα Δωμάτια");
            main.add(er);
            return main;
        }



        JTextField NameT = new JTextField(selected[0].Rooms.get(0).getName());
        JTextField PriceT = new JTextField(selected[0].Rooms.get(0).getPrice());
        JTextField Square_metersT = new JTextField(selected[0].Rooms.get(0).getSqmeter());
        JTextField CapacityT = new JTextField(selected[0].Rooms.get(0).getCapacity());
        JCheckBox BreakfastT = new JCheckBox();
        JCheckBox WifiT = new JCheckBox();
        JCheckBox ParkingT = new JCheckBox();
        JCheckBox AcT = new JCheckBox();
        JCheckBox Cleaning_ServiceT = new JCheckBox();

        BreakfastT.setSelected(selected[0].Rooms.get(0).isBreakfast());
        WifiT.setSelected(selected[0].Rooms.get(0).isWifi());
        ParkingT.setSelected(selected[0].Rooms.get(0).isParking());
        AcT.setSelected(selected[0].Rooms.get(0).isAc());
        Cleaning_ServiceT.setSelected(selected[0].Rooms.get(0).isCleaning_services());

        GridLayout layout = new GridLayout(10,2);
        main.setLayout(layout);
        main.add(Name);
        main.add(NameT);
        main.add(Square_meters);
        main.add(Square_metersT);
        main.add(Price);
        main.add(PriceT);
        main.add(Capacity);
        main.add(CapacityT);
        main.add(Breakfast);
        main.add(BreakfastT);
        main.add(Wifi);
        main.add(WifiT);
        main.add(Parking);
        main.add(ParkingT);
        main.add(Ac);
        main.add(AcT);
        main.add(Cleaning_Service);
        main.add(Cleaning_ServiceT);

        for (Hotel_room hr : selected[0].Rooms) {
            list.addItem(hr.getName());
        }
        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = String.valueOf(list.getSelectedItem());
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(selected[0].Rooms.get(i).getName().equals(sele)){
                        NameT.setText(selected[0].Rooms.get(i).getName());
                        PriceT.setText(selected[0].Rooms.get(i).getPrice());
                        Square_metersT.setText(selected[0].Rooms.get(i).getSqmeter());
                        CapacityT.setText(selected[0].Rooms.get(i).getCapacity());
                        BreakfastT.setSelected(selected[0].Rooms.get(i).isBreakfast());
                        WifiT.setSelected(selected[0].Rooms.get(i).isWifi());
                        ParkingT.setSelected(selected[0].Rooms.get(i).isParking());
                        AcT.setSelected(selected[0].Rooms.get(i).isAc());
                        Cleaning_ServiceT.setSelected(selected[0].Rooms.get(i).isCleaning_services());

                    }
                }

            }
        });
        JButton Save = new JButton("Αποθήκευσε");
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = String.valueOf(list.getSelectedItem());
                output[0].setText(input_check(PriceT.getText(),Square_metersT.getText(),CapacityT.getText()));
                if(!output[0].getText().equals("Αποθηκεύτηκαν")){
                    return;
                }
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(selected[0].Rooms.get(i).getName().equals(sele)){
                        selected[0].Rooms.get(i).setName(NameT.getText());
                        selected[0].Rooms.get(i).setPrice(PriceT.getText());
                        selected[0].Rooms.get(i).setSqmeter(Square_metersT.getText());
                        selected[0].Rooms.get(i).setCapacity(CapacityT.getText());
                        selected[0].Rooms.get(i).setBreakfast(BreakfastT.isSelected());
                        selected[0].Rooms.get(i).setAc(AcT.isSelected());
                        selected[0].Rooms.get(i).setParking(ParkingT.isSelected());
                        selected[0].Rooms.get(i).setWifi(WifiT.isSelected());
                        selected[0].Rooms.get(i).setCleaning_services(Cleaning_ServiceT.isSelected());

                    }
                }
                main.remove(list);
                main.remove(Save);
                main.remove(output[0]);
                list.removeAllItems();
                for (Hotel_room hr : selected[0].Rooms) {
                    list.addItem(hr.getName());
                }
                SwingUtilities.updateComponentTreeUI(main);
                SwingUtilities.updateComponentTreeUI(list);
                main.add(list);
                main.add(Save);
                main.add(output[0]);
            }
        });

        main.add(list);
        main.add(Save);
        main.add(output[0]);
        return main;

    }


    /**
     * Μέθοδος προβολής συνολικών κρατήσεων σε όλα τα ξενοδοχεία
     * @return
     */
    public JPanel sum_resv() {
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
        main.add(cancs);


        ArrayList<Hotel> non_empty = new ArrayList<>();
        for(Hotel h : Hotels)
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
                    Reservations x = non_empty2.get(k).hotelroomreservations.get(l);
                    JLabel date = new JLabel("Ημερομηνία") , customer = new JLabel("Πελάτης");
                    temp.add(date);
                    temp.add(customer);
                    JLabel b1 = new JLabel(x.getStart().toString() + " / " +x.getEnd().toString());
                    JLabel b2 = new JLabel(non_empty2.get(k).hotelroomreservations.get(l).getCustomer().getName());
                    temp.add(b1);
                    temp.add(b2);
                }
            }
        }



        ArrayList<Hotel> non_emptyc = new ArrayList<>();
        for(Hotel h : Hotels)
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
            titledBorder = BorderFactory.createTitledBorder(non_empty.get(i).getName());
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
                    Reservations x = non_empty2c.get(k).hotelroomcancellations.get(l);
                    JLabel date = new JLabel("Ημερομηνία") , customer = new JLabel("Πελάτης");
                    temp.add(date);
                    temp.add(customer);
                    JLabel b1 = new JLabel(x.getStart().toString() + " / " +x.getEnd().toString());
                    JLabel b2 = new JLabel(non_empty2c.get(k).hotelroomcancellations.get(l).getCustomer().getName());
                    temp.add(b1);
                    temp.add(b2);
                }
            }
        }



        return main;
    }


    private String input_check(String price, String sqmtrs, String capacity) {
        String x = "";
        p = Pattern.compile(".*[0-9]");
        if(!price.matches(p.pattern()))
            x = x + "Λάθος Τιμή/";
        if(!capacity.matches(p.pattern()))
            x = x + "Λάθος Χωρητικότητα/";
        if(!sqmtrs.matches(p.pattern()))
            x = x + "Λάθος Τετραγωνικά/";
        if(!x.equals(""))
            return x ;
        return "Αποθηκεύτηκαν";
    }
}