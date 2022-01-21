import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class GUI extends JFrame {
    JTextField username;
    JTextField password;
    HashMap<Credentials, Person> acc_list = new HashMap<>();
    Checker ch = new Checker();
    JRadioButton accom = new JRadioButton("Accommodation Provider");
    JRadioButton hotel = new JRadioButton("Hotel Provider");
    JRadioButton mod = new JRadioButton("Moderator");
    JRadioButton customer = new JRadioButton("Customer");

    Person user;
    Accommodation_Provider user_acc;
    Hotel_Provider user_hot;
    Moderator moder;
    Customer custom;

    GUI() throws IOException {
        initialize();
        start();
    }

    /**
     * Μέθοδος που αναλαμβάνει την αρχικοποίηση χρηστών κάθε κατηγορίας καθώς και καταλυμάτων ξενοδοχείων των συνθηματικων τους και κρατήσεων
     */
    private void initialize() throws IOException {


      /*  Customer p4 = new Customer("Γιώργος", "Θεσσαλονίκη", "4354", "v");
        Credentials c4 = new Credentials("Γιώργος", "123");
        acc_list.put(c4, p4);

        Hotel_Provider p1 = new Hotel_Provider("Γιάννης Παπαδόπουλος", "Ελλάδα", "6955", "giannhs@gmail.com");
        Credentials c1 = new Credentials("Γιάννης", "12345");
        Accommodation_Provider p2 = new Accommodation_Provider("Maria McArthour", "USA", "335", "...");
        Credentials c2 = new Credentials("Maria", "67890");
        acc_list.put(c2, p2);
        acc_list.put(c1, p1);
        Accommodation j = new Accommodation("Αθηνά", "Ύδρα", "300", "50", "5", "4", "10", true, false, false, false, false);
        Accommodation k = new Accommodation("Θέα", "Καβάλα", "500", "68", "3", "4", "8", true, false, true, false, true);
        LocalDate s = LocalDate.of(2021, 1, 2);
        LocalDate e = LocalDate.of(2021, 1, 5);
        p2.Accommodations.add(k);
        p2.Accommodations.add(j);
        Reservations r1 = new Reservations(s, e, p4, k, null, null);
        Reservations r2 = new Reservations(s, e, p4, j, null, null);
        k.reservations.add(r1);
        j.reservations.add(r2);


        Moderator p3 = new Moderator("Ouzi", "Germany", "5467", ",,");
        Credentials c3 = new Credentials("Ouzi", "10032002");
        acc_list.put(c3, p3);


        Hotel b = new Hotel("Galaxy", "Καβάλα", "4");
        Hotel_room d1 = new Hotel_room("Τρίκλινο", "3", "40", true, true, true, true, false, "50");
        Hotel_room d2 = new Hotel_room("Δίκλινο", "2", "30", true, true, true, false, false, "35");
        b.Rooms.add(d1);
        b.Rooms.add(d2);
        p1.Hotels.add(b);
        Hotel r = new Hotel("Ocean", "Καβάλα", "5");
        Hotel_room hr1 = new Hotel_room("Τρίκλινο", "3", "40", true, true, true, true, false, "50");
        Hotel_room hr2 = new Hotel_room("Δίκλινο", "2", "30", true, true, true, false, false, "35");
        r.Rooms.add(hr1);
        r.Rooms.add(hr2);
        p1.Hotels.add(r);
        Reservations r3 = new Reservations(s, e, p4, null, hr1, r);
        hr1.hotelroomreservations.add(r3);
        Reservations r4 = new Reservations(s, e, p4, null, d2, b);
        d2.hotelroomreservations.add(r4);
        p4.My_Acc_Bookings.put(r1, k);
        p4.My_Acc_Bookings.put(r2, j);
        p4.My_Hot_Bookings.put(r3, hr1);
        p4.My_Hot_Bookings.put(r4, d2);
        LocalDate s2 = LocalDate.of(2022, 1, 2);
        LocalDate e2 = LocalDate.of(2022, 1, 5);
        Reservations r5 = new Reservations(s2, e2, p4, k, null, null);
        p4.My_Acc_Bookings.put(r5, k);
        k.reservations.add(r5);



      try{ FileOutputStream fos = new FileOutputStream("pls.bin");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(acc_list);
        }catch (FileNotFoundException ex){
            ex.printStackTrace();
        }*/

      try { FileInputStream fis  = new FileInputStream("pls.bin");
            ObjectInputStream is = new ObjectInputStream(fis);
            acc_list = (HashMap<Credentials, Person>) is.readObject();
        }catch (FileNotFoundException | ClassNotFoundException ex){
            ex.printStackTrace();
        }




}


    /**
     *Μέθοδος στην οποία ο χρήστης διαλέγει αν θέλει να κάνει σύνδεση η εγγραφή και έπειτα καλεί την μέθοδο με τις επιλογές ανάλογα το είδος του χρήστη
     */
    public void start(){

        setTitle("syndesh");
        setLocationRelativeTo(null);
        setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel1 = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder(" Στοιχεία Δανείου ");
        panel1.setBorder(border);
        GridLayout layout = new GridLayout(2,2);
        panel1.setLayout(layout);

        JButton b1 = new JButton();
        b1.setText("reg");
        b1.addActionListener(e -> register());

        JLabel usernamel = new JLabel ("username");
        JLabel  passwordl = new JLabel ("password");
        username = new JTextField("Γιώργος");
        password = new JTextField("123");
        panel1.add(usernamel);
        panel1.add(username);
        panel1.add(passwordl);
        panel1.add(password);

        add(panel1, BorderLayout.CENTER);

        JPanel panel2 = new JPanel();
        JButton buttonCalculate = new JButton("syndesh");
        buttonCalculate.addActionListener(e -> login());
        panel2.add(buttonCalculate);
        add(panel2, BorderLayout.PAGE_END);
        panel2.add(b1);
        pack();
        setVisible(true);

    }

    /**
     *Μέθοδος στην οποία ο χρήστης δημιουργεί ένα καινούργιο λογαριασμό εισάγοντας κωδικό,
     *,όνομα και την κατηγορία λογαριασμού που θέλετε δημιουργήσει καθως και τα στοιχεία του
     */
    private void register(){
        //setVisible(false);
        JFrame tempor = new JFrame();
        JTextField username;
        JTextField password;
        JTextField Name;
        JTextField Location;
        JTextField email;
        JTextField phone_number;

        tempor.setTitle("Register");
        tempor.setSize(500, 500);
        tempor.setLocationRelativeTo(null);
        tempor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tempor.setResizable(true);




        Set<Credentials> c = acc_list.keySet();
        Object[] a = c.toArray();

        JPanel panel1 = new JPanel();
        TitledBorder border = BorderFactory.createTitledBorder(" Στοιχεία Δανείου ");
        panel1.setBorder(border);


        JLabel usernamel = new JLabel ("username");
        JLabel  passwordl = new JLabel ("password");
        username = new JTextField("akis");
        password = new JTextField("123");

        ButtonGroup bg = new ButtonGroup();
        bg.add(accom);
        bg.add(hotel);
        bg.add(mod);
        bg.add(customer);




        panel1.add(usernamel);
        panel1.add(username);
        panel1.add(passwordl);
        panel1.add(password);


        JLabel namel= new JLabel ("Full name");
        JLabel  phonel = new JLabel ("Phone Number");
        JLabel home_groundl = new JLabel ("Home Ground");
        JLabel  emaill= new JLabel ("email");
        Name = new JTextField("akis");
        Name.setColumns(4);
        Location = new JTextField("ss");
        phone_number = new JTextField("6945436967");
        email = new JTextField("akisspa@gmail.com");

        panel1.add(namel);
        panel1.add(Name);
        panel1.add(home_groundl);
        panel1.add(Location);

        panel1.add(phonel);
        panel1.add(phone_number);
        panel1.add(emaill);
        panel1.add(email);

        tempor.add(panel1, BorderLayout.CENTER);

        panel1.add(accom);
        panel1.add(hotel);
        panel1.add(mod);
        panel1.add(customer);



        JPanel panel2 = new JPanel();
        JButton buttonnext = new JButton("register");
        buttonnext.addActionListener(e -> {
            final boolean[] flag = {true};
            for (Object o : a) {
                Credentials c1 = (Credentials) o;
                if (c1.getUsername().equals(username.getText())) {
                    //error("idio user");
                    flag[0] = false;
                    break;
                }
            }
            if(flag[0]){
                user = register_check(username.getText(),password.getText(),Location.getText(),phone_number.getText(),email.getText(),Name.getText());
                if( user != null) {
                    tempor.setVisible(false);
                    setVisible(true);
                    System.out.println(acc_list);
                }
            }
        });
        panel2.add(buttonnext);
        tempor.add(panel2, BorderLayout.PAGE_END);
        tempor.setVisible(true);
        JButton back = new JButton("Back");
        back.addActionListener(e -> {
            tempor.setVisible(false);
            setVisible(true);
        });
        panel2.add(back);

    }

    /**
     * Μέθοδος η οποιά ελεγχεί αν τα στοιχεία που εδωσε ο χρήστης γαι την εγραφή ειναι σωστά
     * @param username Username
     * @param password Κωδικός
     * @param Location Εδρα
     * @param phone_number Τηλέφωνο
     * @param email email
     * @param Name Ονοματεπώνυμο
     * @return Ο person που δημιουργήθηκε
     */
    private Person register_check(String username,String password, String Location, String phone_number, String email,String Name){
        Pattern p ;

        p = Pattern.compile("[0-9]{10}");
        if(!ch.valid(phone_number,p)) {
            System.out.println("kati lathos");
            return null;
        }

        p = Pattern.compile(".*@+[a-zA-Z]+[.]+[a-zA-Z]+$");
        if(!ch.valid(email,p)){
           // error("kati lathos");
            return null;
        }
        if(accom.isSelected())
           user =  new Accommodation_Provider(Name,Location,phone_number,email);
        if(hotel.isSelected())
            user = new Hotel_Provider(Name,Location,phone_number,email);
        if(mod.isSelected())
            user = new Moderator(Name,Location,phone_number,email);
        if(customer.isSelected())
            user = new Customer(Name,Location,phone_number,email);
        Credentials temp = new Credentials(username,password);
        acc_list.put(temp, user);
        try{ FileOutputStream fos = new FileOutputStream("pls.bin");
            ObjectOutputStream os = new ObjectOutputStream(fos);
            os.writeObject(acc_list);
        }catch (IOException ex){
            ex.printStackTrace();
        }
        return user;
    }


    /**
     * Μέθοδος με την οποία εκτελείται η σύνδεση του χρήστη
     */
    private void login() {
        Credentials temp = new Credentials(username.getText(),password.getText());

        for (Credentials c : acc_list.keySet()) {
            if (c.equal(temp)) {
                temp = c;
                break;
            }
        }
        user = acc_list.get(temp);

         if (user!= null ) {
             if(!user.isActivated()){
                 JTextField error = new JTextField("den eisai active");
                 error.setEditable(false);
                 JDialog dialog = new JDialog();
                 dialog.add(error);
                 dialog.setLocationRelativeTo(null);
                 dialog.setVisible(true);
                 return;
             }
             if(user instanceof Accommodation_Provider) {
                 user_acc = (Accommodation_Provider) this.user;
                 accommodation_prov();
             }
             if(user instanceof Hotel_Provider) {
                 user_hot = (Hotel_Provider) this.user;
                 Hotel_prov();
             }
             if(user instanceof Moderator) {
                 moder = (Moderator) this.user;
                 modder();
             }
             if(user instanceof Customer) {
                 custom = (Customer) this.user;
                 cust();
             }
         }
         else{
             JTextField error = new JTextField("lathos");
             error.setEditable(false);
             JDialog dialog = new JDialog();
             dialog.add(error);
             dialog.setLocationRelativeTo(null);
             dialog.setVisible(true);

         }


    }

    /**
     * Μέθοδος την οποία ο χρήστης ως πελάτης διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void cust() {
        setVisible(false);
        JFrame menu = new JFrame();
        menu.setTitle("menu");
        menu.setLocationRelativeTo(null);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel buttons = new JPanel();
        final JPanel[] screen = {new JPanel()};
        TitledBorder border = BorderFactory.createTitledBorder(" Στοιχεία Δανείου ");
        buttons.setBorder(border);
        GridLayout layout = new GridLayout(2,2);
        buttons.setLayout(layout);

        menu.setLayout(layout);

        JButton logout = new JButton();
        logout.setText("logout");
        logout.addActionListener(e -> {
            menu.setVisible(false);
            setVisible(true);
            user = null;
            username.setText("");
            password.setText("");
        });

        JButton info = new JButton();
        info.setText("Personal Info");
        info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = custom.info_edit(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton messages = new JButton();
        messages.setText("Messages");
        messages.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = custom.message(acc_list.values(),custom.getName());
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton resv_canc = new JButton();
        resv_canc.setText("See All Reservations/Cancellations");
        resv_canc.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = custom.resv_canc_view();
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Cancel = new JButton();
        Cancel.setText("Cancel a Reservation");
        Cancel.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = custom.Cancel(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton resv = new JButton();
        resv.setText("Make a Reservation");
        resv.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = custom.Reserv(acc_list.values(),acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });




        buttons.add(logout);
        buttons.add(resv_canc);
        buttons.add(Cancel);
        buttons.add(resv);
        buttons.add(info);
        buttons.add(messages);
        menu.add(buttons);
        menu.add(screen[0]);
        menu.setVisible(true);
    }

    /**
     * Στην οποία ο χρήστης ως moderator διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void modder() {
        setVisible(false);
        JFrame menu = new JFrame();
        menu.setTitle("menu");
        menu.setLocationRelativeTo(null);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel buttons = new JPanel();
        final JPanel[] screen = {new JPanel()};
        TitledBorder border = BorderFactory.createTitledBorder(" Στοιχεία Δανείου ");
        buttons.setBorder(border);
        GridLayout layout = new GridLayout(2,2);
        buttons.setLayout(layout);

        menu.setLayout(layout);

        JButton logout = new JButton();
        logout.setText("logout");
        logout.addActionListener(e -> {
            menu.setVisible(false);
            setVisible(true);
            user = null;
            username.setText("");
            password.setText("");
        });
        JButton info = new JButton();
        info.setText("Personal Info");
        info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.info_edit(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton messages = new JButton();
        messages.setText("Messages");
        messages.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.message(acc_list.values(),moder.getName());
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton view_users = new JButton();
        view_users.setText("View All Users");
        view_users.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.see_all_users(acc_list.values());
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton view_prop = new JButton();
        view_prop.setText("View All Properties");
        view_prop.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.see_all_accommodations(acc_list.values());
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton manage = new JButton();
        manage.setText("Manage User Activation");
        manage.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.account_manage(acc_list.values(),acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton resv_info = new JButton();
        resv_info.setText("See All Reservations/Cancellations");
        resv_info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.see_all_resv(acc_list.values(),true);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Cancel_resv = new JButton();
        Cancel_resv.setText("Cancel a Reservation");
        Cancel_resv.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = moder.resv_canc(acc_list.values(),acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });

        buttons.add(logout);
        buttons.add(view_users);
        buttons.add(view_prop);
        buttons.add(manage);
        buttons.add(resv_info);
        buttons.add(Cancel_resv);
        buttons.add(info);
        buttons.add(messages);
        menu.add(buttons);
        menu.add(screen[0]);
        menu.setVisible(true);
    }

    /**
     * Μέθοδος στην οποία ο χρήστης ως accommodation provider διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void accommodation_prov() {
        setVisible(false);
        JFrame menu = new JFrame();
        menu.setTitle("menu");
        menu.setLocationRelativeTo(null);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel buttons = new JPanel();
        final JPanel[] screen = {new JPanel()};
        TitledBorder border = BorderFactory.createTitledBorder(" Στοιχεία Δανείου ");
        buttons.setBorder(border);
        GridLayout layout = new GridLayout(2,2);
        buttons.setLayout(layout);

        menu.setLayout(layout);

        JButton logout = new JButton();
        logout.setText("logout");
        logout.addActionListener(e -> {
            menu.setVisible(false);
            setVisible(true);
            user = null;
            username.setText("");
            password.setText("");
        });
        JButton display_all = new JButton();
        display_all.setText("Display accommodations");
        display_all.addActionListener(e -> {
               menu.remove(screen[0]);
               screen[0] = user_acc.Accomodations_Display_All();
               menu.add(screen[0]);
               SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Resv_info = new JButton();
        Resv_info.setText("Reservations/Cancellations info");
        Resv_info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_acc.sum_resv();
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Accommodation_add = new JButton();
        Accommodation_add.setText("Add an Accommodation");
        Accommodation_add.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_acc.Accommodation_add(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton accommodation_Edit = new JButton();
        accommodation_Edit.setText("Edit an Accommodation");
        accommodation_Edit.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_acc.Accomodation_Edit(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Accommodation_delete = new JButton();
        Accommodation_delete.setText("Delete an Accommodation");
        Accommodation_delete.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_acc.Accommodation_delete(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton info = new JButton();
        info.setText("Personal Info");
        info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_acc.info_edit(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton messages = new JButton();
        messages.setText("Messages");
        messages.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_acc.message(acc_list.values(),user_acc.getName());
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });

        buttons.add(logout);
        buttons.add(display_all);
        buttons.add(Resv_info);
        buttons.add(Accommodation_add);
        buttons.add(accommodation_Edit);
        buttons.add(Accommodation_delete);
        buttons.add(info);
        buttons.add(messages);
        menu.add(buttons);
        menu.add(screen[0]);
        menu.setVisible(true);
    }

    /**
     * Μέθοδος στην οποία ο χρήστης ως hotel provider διαλέγει ποια λειτουργία θέλει να εκτελέσει
     */
    private void Hotel_prov() {
        setVisible(false);
        JFrame menu = new JFrame();
        menu.setTitle("menu");
        menu.setLocationRelativeTo(null);
        menu.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        JPanel buttons = new JPanel();
        final JPanel[] screen = {new JPanel()};
        TitledBorder border = BorderFactory.createTitledBorder("Hotel Provider Options");
        buttons.setBorder(border);
        GridLayout layout = new GridLayout(2,2);
        buttons.setLayout(layout);

        menu.setLayout(layout);


        JButton logout = new JButton();
        logout.setText("logout");
        logout.addActionListener(e -> {
            menu.setVisible(false);
            setVisible(true);
            user = null;
            username.setText("");
            password.setText("");
        });
       JButton display_all = new JButton();
        display_all.setText("Display accommodations");
        display_all.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.Hotels_Display_All();
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Hotel_add = new JButton();
        Hotel_add.setText("Add a Hotel/Hotel Room");
        Hotel_add.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.Hotel_add(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Hotel_Edit = new JButton();
        Hotel_Edit.setText("Edit a Hotel/Hotel Room");
        Hotel_Edit.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.Hotel_edit(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Hotel_delete = new JButton();
        Hotel_delete.setText("Delete a Hotel/Hotel room");
        Hotel_delete.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.Hotel_delete(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton Resv_info = new JButton();
        Resv_info.setText("Reservations/Cancellations info");
        Resv_info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.sum_resv();
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton info = new JButton();
        info.setText("Personal Info");
        info.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.info_edit(acc_list);
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });
        JButton messages = new JButton();
        messages.setText("Messages");
        messages.addActionListener(e -> {
            menu.remove(screen[0]);
            screen[0] = user_hot.message(acc_list.values(),user_hot.getName());
            menu.add(screen[0]);
            SwingUtilities.updateComponentTreeUI(menu);
        });

        buttons.add(logout);
        buttons.add(display_all);
        buttons.add(Hotel_add);
        buttons.add(Hotel_Edit);
        buttons.add(Hotel_delete);
        buttons.add(Resv_info);
        buttons.add(info);
        buttons.add(messages);
        menu.add(buttons);
        menu.add(screen[0]);
        menu.setVisible(true);

    }

}
