/**
 * Αυτή η κλάση αναπαριστά έναν πάροχο καταλύματος με τα χαρακτηριστηκά του, ο οποίος μπορεί να προσθέσει το κατάλυμα του,
 * να δεί τα καταλύματα του, να κάνει αλλαγές αν επιθυμεί σε κάποιο, να δεί τις κρατήσεις του καθώς και τις ακυρώσεις του.
 */

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Accommodation_Provider extends Person{


    ArrayList<Accommodation> Accommodations = new ArrayList<>();



    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Accommodation_Provider(){}

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    Accommodation_Provider(String aname , String ahome_ground, String aphone_number, String aemail){
        super(aname,ahome_ground,aphone_number,aemail);
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει όλα τα καταλύματα που έχει καταχωρήσει
     */
    public JPanel Accomodations_Display_All(){
        int lenght = Accommodations.size();
        JPanel[] jps = new JPanel[lenght];

        JPanel panel1 = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder("Accom_displ");
        panel1.setBorder(border);
        GridLayout layout = new GridLayout(lenght,2);
        panel1.setLayout(layout);

        for( int i =0 ; i < lenght ; i++) {
            jps[i] = new JPanel();
            String s1 = Accommodations.get(i).getName() + " is a " + Accommodations.get(i).getStars() + " stars accommodation , located at " + Accommodations.get(i).getLocation() + " and is at " + Accommodations.get(i).getPrice() + "$ per night." ;
            String s2 = " It has an area of " + Accommodations.get(i).getSqmeter() + "m2 with " + Accommodations.get(i).getRooms() + " rooms and is suitable for up to " + Accommodations.get(i).getCapacity() + " people.";
            String s3 = Accommodations.get(i).hasBreakfast() + " Breakfast\n " + Accommodations.get(i).hasAc() + " Ac\n " + Accommodations.get(i).hasParking() + " Parking\n " + Accommodations.get(i).hasWifi() + " Wifi\n " + Accommodations.get(i).hasCleaningservice() + " Cleaning Service\n ";
            jps[i].add(new JLabel(s1));
            jps[i].add(new JLabel(s2));
            jps[i].add(new JLabel(s3));
            GridLayout layout1 = new GridLayout(3,2);
            jps[i].setLayout(layout1);
            Border brd = BorderFactory.createLineBorder(Color.black);
            jps[i].setBorder(brd);
            panel1.add(jps[i]);
        }

            return panel1 ;
    }

    public JPanel Accomodations_Display(String name){
            Accommodation acc = null;
        for (Accommodation accommodation : Accommodations) {
            if (name.equals(accommodation.getName())) {
                acc = accommodation;
            }
        }


            JPanel panel = new JPanel();
            String s1 = acc.getName() + " is a " + acc.getStars() + " stars accommodation , located at " + acc.getLocation() + " and is at " + acc.getPrice() + "$ per night." ;
            String s2 = " It has an area of " + acc.getSqmeter() + "m2 with " + acc.getRooms() + " rooms and is suitable for up to " + acc.getCapacity() + " people.";
            String s3 = acc.hasBreakfast() + " Breakfast\n " + acc.hasAc() + " Ac\n " + acc.hasParking() + " Parking\n " + acc.hasWifi() + " Wifi\n " + acc.hasCleaningservice() + " Cleaning Service\n ";
            panel.add(new JLabel(s1));
            panel.add(new JLabel(s2));
            panel.add(new JLabel(s3));
            GridLayout layout1 = new GridLayout(3,2);
            panel.setLayout(layout1);
            Border brd = BorderFactory.createLineBorder(Color.black);
            panel.setBorder(brd);


        return panel ;
    }



    /**
     * μέθοδος με την οποία ο πάροχος κάνει επεξεργασία ένα κατάλυμα
     * @return
     */
    public JPanel Accomodation_Edit() {
         JComboBox<String> list = new JComboBox<>();

        JPanel main = new JPanel();
        final JTextField[] output = {new JTextField()};
        output[0].setEditable(false);
        JLabel Name = new JLabel("Name");
        JLabel Location = new JLabel("Location");
        JLabel Stars = new JLabel("Stars");
        JLabel Price = new JLabel("Price");
        JLabel Square_meters = new JLabel("Square Meters");
        JLabel Rooms = new JLabel("Rooms");
        JLabel Capacity = new JLabel("Capacity");
        JLabel Breakfast = new JLabel("Breakfast");
        JLabel Wifi = new JLabel("Wifi");
        JLabel Parking = new JLabel("Parking");
        JLabel Ac = new JLabel("Ac");
        JLabel Cleaning_Service = new JLabel("Cleaning Service");
        if(Accommodations.isEmpty()){
            JLabel er = new JLabel("Den exeis");
            main.add(er);
            return main;
        }



        JTextField NameT = new JTextField(Accommodations.get(0).getName());
        JTextField LocationT = new JTextField(Accommodations.get(0).getLocation());
        JTextField StarsT = new JTextField(Accommodations.get(0).getStars());
        JTextField PriceT = new JTextField(Accommodations.get(0).getPrice());
        JTextField Square_metersT = new JTextField(Accommodations.get(0).getSqmeter());
        JTextField RoomsT = new JTextField(Accommodations.get(0).getRooms());
        JTextField CapacityT = new JTextField(Accommodations.get(0).getCapacity());
        JCheckBox BreakfastT = new JCheckBox();
        JCheckBox WifiT = new JCheckBox();
        JCheckBox ParkingT = new JCheckBox();
        JCheckBox AcT = new JCheckBox();
        JCheckBox Cleaning_ServiceT = new JCheckBox();

        BreakfastT.setSelected(Accommodations.get(0).isBreakfast());
        WifiT.setSelected(Accommodations.get(0).isWifi());
        ParkingT.setSelected(Accommodations.get(0).isParking());
        AcT.setSelected(Accommodations.get(0).isAc());
        Cleaning_ServiceT.setSelected(Accommodations.get(0).isCleaning_services());

        GridLayout layout = new GridLayout(13,2);
        main.setLayout(layout);
        main.add(Name);
        main.add(NameT);
        main.add(Location);
        main.add(LocationT);
        main.add(Stars);
        main.add(StarsT);
        main.add(Square_meters);
        main.add(Square_metersT);
        main.add(Price);
        main.add(PriceT);
        main.add(Rooms);
        main.add(RoomsT);
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


        for (Accommodation accommodation : Accommodations) {
            list.addItem(accommodation.getName());
        }

        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = String.valueOf(list.getSelectedItem());
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(Accommodations.get(i).getName().equals(sele)){
                         NameT.setText(Accommodations.get(i).getName());
                         LocationT.setText(Accommodations.get(i).getLocation());
                         StarsT.setText(Accommodations.get(i).getStars());
                         PriceT.setText(Accommodations.get(i).getPrice());
                         Square_metersT.setText(Accommodations.get(i).getSqmeter());
                         RoomsT.setText(Accommodations.get(i).getRooms());
                         CapacityT.setText(Accommodations.get(i).getCapacity());
                        BreakfastT.setSelected(Accommodations.get(i).isBreakfast());
                        WifiT.setSelected(Accommodations.get(i).isWifi());
                        ParkingT.setSelected(Accommodations.get(i).isParking());
                        AcT.setSelected(Accommodations.get(i).isAc());
                        Cleaning_ServiceT.setSelected(Accommodations.get(i).isCleaning_services());

                    }
                }

            }
        });
        JButton Save = new JButton("Save");
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sele = String.valueOf(list.getSelectedItem());
                output[0].setText(input_check(PriceT.getText(),Square_metersT.getText(),StarsT.getText(),RoomsT.getText(),CapacityT.getText()));
                if(!output[0].getText().equals("Done")){
                    return;
                }
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(Accommodations.get(i).getName().equals(sele)){
                        Accommodations.get(i).setName(NameT.getText());
                        Accommodations.get(i).setLocation(LocationT.getText());
                        Accommodations.get(i).setPrice(PriceT.getText());
                        Accommodations.get(i).setStars(StarsT.getText());
                        Accommodations.get(i).setSqmeter(Square_metersT.getText());
                        Accommodations.get(i).setCapacity(CapacityT.getText());
                        Accommodations.get(i).setRooms(RoomsT.getText());
                        Accommodations.get(i).setBreakfast(BreakfastT.isSelected());
                        Accommodations.get(i).setAc(AcT.isSelected());
                        Accommodations.get(i).setParking(ParkingT.isSelected());
                        Accommodations.get(i).setWifi(WifiT.isSelected());
                        Accommodations.get(i).setCleaning_services(Cleaning_ServiceT.isSelected());

                    }
                }
                main.remove(list);
                main.remove(Save);
                main.remove(output[0]);
                list.removeAllItems();
                for (Accommodation accommodation : Accommodations) {
                    list.addItem(accommodation.getName());
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
     * μέθοδος με την οποία ο πάροχος προσθέτει ενα κατάλυμα
     * @return
     */
    public JPanel Accommodation_add() {
        JPanel main = new JPanel();
        final JTextField[] output = {new JTextField()};
        output[0].setEditable(false);
        JLabel Name = new JLabel("Name");
        JLabel Location = new JLabel("Location");
        JLabel Stars = new JLabel("Stars");
        JLabel Price = new JLabel("Price");
        JLabel Square_meters = new JLabel("Square Meters");
        JLabel Rooms = new JLabel("Rooms");
        JLabel Capacity = new JLabel("Capacity");
        JLabel Breakfast = new JLabel("Breakfast");
        JLabel Wifi = new JLabel("Wifi");
        JLabel Parking = new JLabel("Parking");
        JLabel Ac = new JLabel("Ac");
        JLabel Cleaning_Service = new JLabel("Cleaning Service");


        JTextField NameT = new JTextField("");
        JTextField LocationT = new JTextField("");
        JTextField StarsT = new JTextField("");
        JTextField PriceT = new JTextField("");
        JTextField Square_metersT = new JTextField("");
        JTextField RoomsT = new JTextField("");
        JTextField CapacityT = new JTextField("");
        JCheckBox BreakfastT = new JCheckBox("");
        JCheckBox WifiT = new JCheckBox();
        JCheckBox ParkingT = new JCheckBox();
        JCheckBox AcT = new JCheckBox();
        JCheckBox Cleaning_ServiceT = new JCheckBox();

        GridLayout layout = new GridLayout(13,2);
        main.setLayout(layout);
        main.add(Name);
        main.add(NameT);
        main.add(Location);
        main.add(LocationT);
        main.add(Stars);
        main.add(StarsT);
        main.add(Square_meters);
        main.add(Square_metersT);
        main.add(Price);
        main.add(PriceT);
        main.add(Rooms);
        main.add(RoomsT);
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




        JButton add = new JButton("Save");
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               output[0].setText(input_check(PriceT.getText(),Square_metersT.getText(),StarsT.getText(),RoomsT.getText(),CapacityT.getText()));
                if(!output[0].getText().equals("Done")){
                    return;
                }
                Accommodation temp = new Accommodation(NameT.getText(),LocationT.getText(),PriceT.getText(),Square_metersT.getText(),StarsT.getText(),RoomsT.getText(),CapacityT.getText(),BreakfastT.isSelected(), WifiT.isSelected(),AcT.isSelected(),ParkingT.isSelected(),Cleaning_ServiceT.isSelected());
                Accommodations.add(temp);
                System.out.println(temp.getName()+temp.hasAc()+temp.getPrice());
                NameT.setText("");
                LocationT.setText("");
                StarsT.setText("");
                PriceT.setText("");
                Square_metersT.setText("");
                RoomsT.setText("");
                CapacityT.setText("");
                BreakfastT.setSelected(false);
                WifiT.setSelected(false);
                ParkingT.setSelected(false);
                AcT.setSelected(false);
                Cleaning_ServiceT.setSelected(false);
            }
        });


        main.add(add);
        main.add(output[0]);
        return main;
    }

    private String input_check(String price, String sqmtrs, String stars, String rooms, String capacity) {
        String x = "";
        p = Pattern.compile(".*[0-9]");
        if(!price.matches(p.pattern()))
            x = x + "Wrong Price/";
        if(!rooms.matches(p.pattern()))
            x = x + "Wrong Rooms/";
        if(!capacity.matches(p.pattern()))
            x = x + "Wrong Capacity/";
        if(!sqmtrs.matches(p.pattern()))
            x = x + "Wrong Square meters/";
        p = Pattern.compile("[1-5]");
        if(!stars.matches(p.pattern()))
            x = x + "Wrong Stars/";
        if(!x.equals(""))
            return x ;
        return "Done";
    }

    /**
     * μέθοδος με την οποία ο πάροχος διγράφει ένα απο τα καταλύματα του
     * @return
     */
    public JPanel Accommodation_delete() {
        final boolean[] press = new boolean[1];
        press[0] = false;
        final JPanel[] main = {new JPanel()};
        JComboBox<String> list = new JComboBox<>();
        final JPanel[] info = {new JPanel()};
        if(Accommodations.isEmpty()){
            main[0].add(new JLabel("geia"));
            return main[0];
        }

        for (Accommodation accommodation : Accommodations) {
            list.addItem(accommodation.getName());
        }
        main[0].add(list);

        JButton delete = new JButton("Delete");
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                press[0] = true;
                String sele = String.valueOf(list.getSelectedItem());
                Accommodation acc = null;
                for(int i = 0 ; i < list.getItemCount(); i++){
                    if(Accommodations.get(i).getName().equals(sele)) {
                        acc = Accommodations.get(i);
                    }
                }
                Accommodations.remove(acc);
                list.removeAllItems();
                for (Accommodation accommodation : Accommodations) {
                    list.addItem(accommodation.getName());
                }
                if(Accommodations.isEmpty()){
                    main[0].remove(list);
                    main[0].remove(delete);
                    main[0].remove(info[0]);
                    main[0].add(new JLabel("geia"));

                }
                else {
                    main[0].remove(list);
                    main[0].remove(delete);
                    main[0].remove(info[0]);
                    SwingUtilities.updateComponentTreeUI(main[0]);
                    SwingUtilities.updateComponentTreeUI(list);
                    main[0].add(list);
                    main[0].add(delete);
                    info[0] = Accomodations_Display( (String) list.getSelectedItem());
                    main[0].add(info[0]);
                }
                SwingUtilities.updateComponentTreeUI(main[0]);

              press[0] = false;
            }

        });


        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if( press[0])
                    return;
                main[0].remove(info[0]);
                SwingUtilities.updateComponentTreeUI(main[0]);
                info[0] = Accomodations_Display( (String) list.getSelectedItem());
                main[0].add(info[0]);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }

        });


        main[0].add(delete);
        info[0] = Accomodations_Display( (String) list.getSelectedItem());
        main[0].add(info[0]);
        return main[0];
    }

    /**
     * μέθοδος με την οποία ο πάροχος βλέπει τις κρατήσεις των καταλυμάτων του
     */
    public JPanel sum_resv() {
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
        main.add(cancs);


        ArrayList<Accommodation> non_empty = new ArrayList<>();
        for(Accommodation acc : Accommodations)
            if(!acc.reservations.isEmpty())
                non_empty.add(acc);
        int n = non_empty.size();

        GridLayout gridLayout = new GridLayout(n,2);
        resvs.setLayout(gridLayout);

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
            int m = non_empty.get(i).reservations.size();
            for(int j = 0 ; j < m ;j++){
                Reservations temp = non_empty.get(i).reservations.get(j);
                JLabel date = new JLabel("DATE") , customer = new JLabel("CUSTOMER");
                sub1[i].add(date);
                sub1[i].add(customer);
                JLabel b1 = new JLabel(temp.getStart().toString() + " / " +temp.getEnd().toString());
                JLabel b2 = new JLabel(non_empty.get(i).reservations.get(j).getCustomer_name());
                sub1[i].add(b1);
                sub1[i].add(b2);

            }
        }


        ArrayList<Accommodation> non_empty2 = new ArrayList<>();
        for(Accommodation acc : Accommodations)
            if(!acc.cancellations.isEmpty())
                non_empty2.add(acc);
        int n2 = non_empty2.size();
        GridLayout gridLayout2 = new GridLayout(n2,2);
        cancs.setLayout(gridLayout);
        JPanel[] sub2 = new JPanel[n2];

        for(int i = 0 ; i < n2 ; i++){
            sub2[i] = new JPanel();
            GridLayout layout = new GridLayout(2,2);
            sub2[i].setLayout(layout);
            sub2[i].setBorder(border);
            titledBorder = BorderFactory.createTitledBorder(non_empty2.get(i).getName());
            sub2[i].setBorder(titledBorder);
            cancs.add(sub2[i]);

        }

        for(int i = 0 ; i < n2 ; i++){
            int m2 = non_empty2.get(i).cancellations.size();
            for(int j = 0 ; j < m2 ;j++){
                Reservations temp = non_empty2.get(i).cancellations.get(j);
                JLabel date = new JLabel("DATE") , customer = new JLabel("CUSTOMER");
                sub2[i].add(date);
                sub2[i].add(customer);
                JLabel b1 = new JLabel(temp.getStart().toString() + " / " +temp.getEnd().toString());
                JLabel b2 = new JLabel(temp.getCustomer_name());
                sub2[i].add(b1);
                sub2[i].add(b2);

            }
        }





        return main;
    }

}
