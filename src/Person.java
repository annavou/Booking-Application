/**
 * Αυτή η κλάση αναπαριστά ένα χρήστη με τα χαρακτηριστικά του, ο οποίος μπορεί να δει τα στοιχεία του, τα μηνύματα που έχει και
 * να αναζητήσει ένα κατάλυμα ή ξενοδοχείο
 */

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.regex.Pattern;
import javax.swing.*;

public class Person implements java.io.Serializable {

    private String Name;
    private String Home_ground;
    private String Phone_number;
    private String Email;
    private boolean Activated;


    ArrayList<messages> messages = new ArrayList<>();
    int messages_count;


    @Serial
    private static final long serialVersionUID = 6529686788267757690L;


    /**
     * Ο προκαθορισμένος κατασκευαστής
     */
    Person() {
        Name = null;
        Home_ground = null;
        Phone_number = null;
        Email = null;
        Activated = false;
    }

    /**
     * Κατασκευαστής που αρχικοποιεί τις παραμέτρους της κλάσης
     */
    Person(String aname, String ahome_ground, String aphone_number, String aemail) {
        Name = aname;
        Home_ground = ahome_ground;
        Phone_number = aphone_number;
        Email = aemail;
        messages.add(new messages("app","Welcome","Καλωσόρισες " + getName() + "!!"));
        messages_count = 0;
        Activated = false;
    }

    public boolean isActivated() {
        return Activated;
    }

    public void setActivated(boolean activated) {
        Activated = activated;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getHome_ground() {
        return Home_ground;
    }

    public void setHome_ground(String home_ground) {
        Home_ground = home_ground;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    /**
     * μέθοδος με την οποία ένας χρήστης μπορεί να συντάξει και να στείλει ένα μήνυμα
     * @param acc_list λίστα με τους χρήστες
     * @param myname το ονομα του αποστολέα
     */
    public void message_send(Collection<Person> acc_list,String myname) {
        JFrame main = new JFrame();
        JLabel to = new JLabel("Παραλήπτης:");
        JLabel topic = new JLabel("Θέμα:");
        JLabel text = new JLabel("Μήνυμα:");
        JTextField To = new JTextField(),Topic = new JTextField(),Text = new JTextField();
        GridLayout layout = new GridLayout(4,3);
        main.add(to);
        main.add(To);
        main.add(topic);
        main.add(Topic);
        main.add(text);
        main.add(Text);
        main.setLayout(layout);
        main.setSize(500,500);
        main.setVisible(true);
        JButton send = new JButton();
        send.setText("Αποστολή");
        send.addActionListener(e -> {
            Person temp = new Person();
            if(To.getText().equals("") | Topic.getText().equals("") | Text.getText().equals("")){
                System.out.println("Δεν Υπάρχουν Μηνύματα");
            }
            boolean ex = false ;
            for ( Person p : acc_list){
                if(p.getName().equals(To.getText())) {
                   ex = true;
                   temp = p ;
                }
            }
            if(!ex){
                System.out.println("Οχι");
            }
            if(ex){
                messages mess = new messages(myname,Topic.getText(),Text.getText());
                temp.messages.add(mess);
                main.setVisible(false);
            }

        });
        JButton back = new JButton();
        back.setText("Πίσω");
        back.addActionListener(e -> main.setVisible(false));


      main.add(send);
      main.add(back);
    }

    /**
     * μέθοδος με την οποία ο χρήστης αποφασίζει την αποστολή η την προβολή και διαγραφή μηνύματος
     * @param acc_list λίστα με τα μηνύματα
     * @param myname το όνομα του αποστολέα
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    public JPanel message(Collection<Person> acc_list,String myname) {
        final JPanel[] main = {new JPanel()};
        GridLayout layout = new GridLayout(2,2);
        main[0].setLayout(layout);

        JButton view = new JButton();
        view.setText("Προβολή/Διαγραφή Μηνυμάτων");
        view.addActionListener(e -> {
             main[0].removeAll();
             JPanel temp = messages_view();
             temp.setLocation(0,0);
             main[0].setLayout(new BorderLayout());
             main[0].add(temp);
             SwingUtilities.updateComponentTreeUI(main[0]);
        });
        JButton send = new JButton();
        send.setText("Στείλτε Μήνυμα");
        send.addActionListener(e -> message_send(acc_list,myname));


        if(view.isSelected()) {
            return main[0];
        }
        main[0].add(view);
        main[0].add(send);
        return main[0];
    }



    /**
     * μέθοδος για την προβολή και διαγραφή των μηνυμάτων
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     */
    private JPanel messages_view() {

        final boolean[] press = new boolean[1];
        //press[0] = false;
        final JPanel[] main = {new JPanel()};
        JComboBox<String> list = new JComboBox<>();
        final JPanel[] info = {new JPanel()};
        messages mess ;
        String sele ;


        if(messages.isEmpty()){
            main[0].add(new JLabel("Δεν έχετε Μηνύματα"));
            return main[0];
        }

        int i = 1 ;
        for (messages mes : messages) {
            list.addItem(i + ") " + mes.getFrom() + " : " + mes.getTopic());
            i++;
        }
        main[0].add(list);

        JLabel from = new JLabel("Αποστολέας:");
        JLabel topic = new JLabel("Θέμα:");
        JLabel text = new JLabel("Μήνυμα:");
        JTextField From = new JTextField(messages.get(0).getFrom()),Topic = new JTextField(messages.get(0).getTopic()),Text = new JTextField(messages.get(0).getText());
        GridLayout layout = new GridLayout(3,2);
        info[0].add(from);
        info[0].add(From);
        info[0].add(topic);
        info[0].add(Topic);
        info[0].add(text);
        info[0].add(Text);
        info[0].setLayout(layout);


        JButton delete = new JButton("Διαγραφή");
        delete.addActionListener(e -> {
            messages mess1;
            String sele1;
            press[0] = true;
            sele1 = String.valueOf(list.getSelectedItem());
            //char c = sele1.charAt(0);
            //int in = (int) c ;
            mess1 = messages.get(Character.getNumericValue(sele1.charAt(0))-1);
            messages.remove(mess1);
            list.removeAllItems();
            int i1 = 1 ;
            for (messages mes : messages) {
                list.addItem(i1 + ") " + mess1.getFrom() + " : " + mess1.getTopic());
                i1++;
            }
            if(messages.isEmpty()){
                main[0].remove(list);
                main[0].remove(delete);
                main[0].remove(info[0]);
                main[0].add(new JLabel("Δεν Έχετε Μηνύματα"));

            }
            else {
                main[0].remove(list);
                main[0].remove(delete);
                main[0].remove(info[0]);
                SwingUtilities.updateComponentTreeUI(main[0]);
                SwingUtilities.updateComponentTreeUI(list);
                main[0].add(list);
                main[0].add(delete);
                sele1 = String.valueOf(list.getSelectedItem());
                mess1 = messages.get(Character.getNumericValue(sele1.charAt(0))-1);
                From.setText(mess1.getFrom());
                Topic.setText(mess1.getTopic());
                Text.setText(mess1.getText());
                main[0].add(info[0]);
            }
            SwingUtilities.updateComponentTreeUI(main[0]);

            press[0] = false;
        });


        list.addActionListener(e -> {
            messages mess12;
            String sele12;
            if( press[0])
                return;
            main[0].remove(info[0]);
            SwingUtilities.updateComponentTreeUI(main[0]);
            sele12 = String.valueOf(list.getSelectedItem());
            mess12 = messages.get(Character.getNumericValue(sele12.charAt(0))-1);
            From.setText(mess12.getFrom());
            Topic.setText(mess12.getTopic());
            Text.setText(mess12.getText());
            main[0].add(info[0]);
            SwingUtilities.updateComponentTreeUI(main[0]);
        });


        main[0].add(delete);
        sele = String.valueOf(list.getSelectedItem());
        mess = messages.get(Character.getNumericValue(sele.charAt(0))-1);
        From.setText(mess.getFrom());
        Topic.setText(mess.getTopic());
        Text.setText(mess.getText());
        main[0].add(info[0]);
        return main[0];
    }

    /**
     * μέθοδος για την επεξεργασία και την αλλαγή των στοιχείων του χρήστη
     * @return Το JPanel με που θα εμφανιστεί στην οθόνη
     * @param acc_list Η λίστα με τους χρήστες που αποθηκεύεται στο αρχείο
     */
    public JPanel info_edit(HashMap<Credentials, Person> acc_list) {
        JPanel main = new JPanel();
        final JTextField[] output = {new JTextField("Επεξεργασία Στοιχείων")};
        output[0].setEditable(false);
        final boolean[] switch_f = {false};
        GridLayout layout = new GridLayout(5,2);
        main.setLayout(layout);
        final Person[] temp = {new Person()};

        JLabel namel= new JLabel ("Ονοματεπώνυμο");
        JLabel  phonel = new JLabel ("Αριθμός Τηλεφώνου");
        JLabel home_groundl = new JLabel ("Έδρα");
        JLabel  emaill= new JLabel ("email");
        JTextField Name = new JTextField(this.getName());
        JTextField Location = new JTextField(this.getHome_ground());
        JTextField email = new JTextField(this.getEmail());
        JTextField phone_number = new JTextField(this.getPhone_number());
        Pattern p = Pattern.compile(".*[0-9]{10}");
        JButton Save = new JButton("Αποθήκευση");
        Save.addActionListener(e -> {
        if(!phone_number.getText().matches(p.pattern())){
            output[0].setText("Λάθος Αριθμός! (τουλάχιστον 10 ψηφία από το 0-9)");
             return;
         }
         else{
             switch_f[0] = true ;
             temp[0] = new Person(Name.getText(),Location.getText(),phone_number.getText(),email.getText());
             output[0].setText("Αποθηκεύτηκαν!!!");
             updater(temp[0]);
            try{ FileOutputStream fos = new FileOutputStream("pls.bin");
                ObjectOutputStream os = new ObjectOutputStream(fos);
                os.writeObject(acc_list);
            }catch (IOException ex){
                ex.printStackTrace();
            }

        }
        });



        main.add(namel);
        main.add(Name);
        main.add(home_groundl);
        main.add(Location);
        main.add(phonel);
        main.add(phone_number);
        main.add(emaill);
        main.add(email);
        main.add(Save);
        main.add(output[0]);
        return main;

    }


    /**
     * μέθοδος που ενημερώνει τα στοιχεία του χρήστη
     * @param person προσωρινός person με τις επιθυμητές αλλαγές
     */
    private void updater(Person person) {
        this.setEmail(person.getEmail());
        this.setName(person.getName());
        this.setHome_ground(person.getHome_ground());
        this.setPhone_number(person.getPhone_number());
    }


}
