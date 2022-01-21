/**
 * Αυτή η κλάση αναπαριστά ένα χρήστη με τα χαρακτηριστικά του, ο οποίος μπορεί να δει τα στοιχεία του, τα μηνύματα που έχει και
 * να αναζητήσει ένα κατάλυμα ή ξενοδοχείο
 */

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
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.border.Border;

public class Person {

    private String Name;
    private String Home_ground;
    private String Phone_number;
    private String Email;
    private boolean Activated;


    ArrayList<messages> messages = new ArrayList<>();
    int messages_count;

    Scanner sc = new Scanner(System.in);

    int next_int;
    boolean next_bool;
    String next_string;
    Pattern p;
    Checker ch = new Checker();

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
        messages.add(new messages("app","Welcome","Καλωσόρισες!!" + getName() + "!!"));
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
     * @param acc_list λίστα με τα μηνύματα
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
        send.setText("Στάλθηκε");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Person temp = new Person();
                if(To.getText().equals("") | Topic.getText().equals("") | Text.getText().equals("")){
                    System.out.println("Κενό");
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

                    try (BufferedWriter buffer=new BufferedWriter(new FileWriter("messages.txt",true))){//
                        buffer.write("Μήνυμα: " + mess.getTopic() + " - " + "Παραλήπτης: " + temp.getName() +" - " + "Αποστολέας: " + mess.getFrom());
                        buffer.newLine();
                        buffer.flush();
                    } catch (IOException exe) {
                        exe.printStackTrace();
                    }//

                    main.setVisible(false);
                }

            }
        });
        JButton back = new JButton();
        back.setText("Πίσω");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVisible(false);
            }
        });


        main.add(send);
        main.add(back);
    }

    /**
     * μέθοδος με την οποία ο χρήστης αποφασίζει την αποστολή, την προβολή, τη διαγραφή ή την έξοδο απο τα μηνύματα
     * @param acc_list λίστα με τα μηνύματα
     * @return
     */
    public JPanel message(Collection<Person> acc_list,String myname) {
        final JPanel[] main = {new JPanel()};
        GridLayout layout = new GridLayout(2,2);
        main[0].setLayout(layout);

        JButton view = new JButton();
        view.setText("Προβολή/Διαγραφή Μηνυμάτων");
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main[0].removeAll();
                JPanel temp = messages_view();
                temp.setLocation(0,0);
                main[0].setLayout(new BorderLayout());
                main[0].add(temp);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }
        });
        JButton send = new JButton();
        send.setText("Στείλε μήνυμα");
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                message_send(acc_list,myname);
            }
        });


        if(view.isSelected()) {
            return main[0];
        }
        main[0].add(view);
        main[0].add(send);
        return main[0];
    }

    /**
     * μέθοδος για την προβολή των μηνυμάτων
     * @return
     */
    private JPanel messages_view() {

        final boolean[] press = new boolean[1];
        press[0] = false;
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
        if (messages.size()==1){//
            try (BufferedWriter buffer=new BufferedWriter(new FileWriter("messages.txt",true))){
                buffer.write("Μήνυμα: " + "Καλωσόρισες " + getName() + "!!" + " - " + "Παραλήπτης: "
                        + Name +" - " + "Αποστολέας: app");
                buffer.newLine();
                buffer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }//
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
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messages mess ;
                String sele ;
                press[0] = true;
                sele = String.valueOf(list.getSelectedItem());
                char c = sele.charAt(0);
                int in = (int) c ;
                mess = messages.get(Character.getNumericValue(sele.charAt(0))-1);
                messages.remove(mess);

                String start= "Μήνυμα: "+ mess.getText();

                BufferedReader reader = null;//
                try {
                    reader = new BufferedReader(new FileReader("messages.txt"));
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
                        if (currentLine.contains(start)) continue;
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
                    Files.move(oldFile, oldFile.resolveSibling("messages.txt"), StandardCopyOption.REPLACE_EXISTING);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }

                list.removeAllItems();
                int i = 1 ;
                for (messages mes : messages) {
                    list.addItem(i + ") " + mess.getFrom() + " : " + mess.getTopic());
                    i++;
                }
                if(messages.isEmpty()){
                    main[0].remove(list);
                    main[0].remove(delete);
                    main[0].remove(info[0]);
                    main[0].add(new JLabel("Δεν έχετε Μηνύματα"));

                }
                else {
                    main[0].remove(list);
                    main[0].remove(delete);
                    main[0].remove(info[0]);
                    SwingUtilities.updateComponentTreeUI(main[0]);
                    SwingUtilities.updateComponentTreeUI(list);
                    main[0].add(list);
                    main[0].add(delete);
                    sele = String.valueOf(list.getSelectedItem());
                    mess = messages.get(Character.getNumericValue(sele.charAt(0))-1);
                    From.setText(mess.getFrom());
                    Topic.setText(mess.getTopic());
                    Text.setText(mess.getText());
                    main[0].add(info[0]);
                }
                SwingUtilities.updateComponentTreeUI(main[0]);

                press[0] = false;
            }

        });


        list.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                messages mess ;
                String sele ;
                if( press[0])
                    return;
                main[0].remove(info[0]);
                SwingUtilities.updateComponentTreeUI(main[0]);
                sele = String.valueOf(list.getSelectedItem());
                mess = messages.get(Character.getNumericValue(sele.charAt(0))-1);
                From.setText(mess.getFrom());
                Topic.setText(mess.getTopic());
                Text.setText(mess.getText());
                main[0].add(info[0]);
                SwingUtilities.updateComponentTreeUI(main[0]);
            }

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
     * @return
     */
    public JPanel info_edit(HashMap<Credentials,Person> people,String s1) {
        JPanel main = new JPanel();
        final JTextField[] output = {new JTextField("Επεξεργασία Στοιχείων")};
        output[0].setEditable(false);
        final boolean[] switch_f = {false};
        GridLayout layout = new GridLayout(5,2);
        main.setLayout(layout);
        final Person[] temp = {new Person()};

        Credentials c  = new Credentials();
        for(Credentials j : people.keySet()){
            if(people.get(j).equals(this))
                c = j ;
        }

        JLabel namel= new JLabel ("Ονοματεπώνυμο");
        JLabel  phonel = new JLabel ("Αριθμός Τηλεφώνου");
        JLabel home_groundl = new JLabel ("Έδρα");
        JLabel  emaill= new JLabel ("email");
        JTextField Name = new JTextField(this.getName());
        JTextField Location = new JTextField(this.getHome_ground());
        JTextField email = new JTextField(this.getEmail());
        JTextField phone_number = new JTextField(this.getPhone_number());
        p = Pattern.compile(".*[0-9]{10}");
        JButton Save = new JButton("Αποθήκευση");
        Credentials finalC = c;
        Save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!phone_number.getText().matches(p.pattern())){
                    output[0].setText("Λάθος Αριθμός! (τουλάχιστον 10 ψηφία από το 0-9)");
                    return;
                }
                else{
                    switch_f[0] = true ;
                    temp[0] = new Person(Name.getText(),Location.getText(),phone_number.getText(),email.getText());
                    output[0].setText("Αποθηκεύτηκαν!!!");
                    updater(temp[0]);

                    String start= "Username:"+ finalC.getUsername();//

                    BufferedReader reader = null;
                    try {
                        reader = new BufferedReader(new FileReader("users.txt"));
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
                            if (currentLine.contains(start)) continue;
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
                        Files.move(oldFile, oldFile.resolveSibling("users.txt"), StandardCopyOption.REPLACE_EXISTING);
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                    }

                    try(BufferedWriter buffer=new BufferedWriter(new FileWriter("users.txt",true))) {
                        buffer.write("Username" + ":" + finalC.getUsername()
                                +" - " + "Κωδικός" + ":" + finalC.getPassword() + " - " + s1 +":" + Name.getText()  + " - " + "Τόπος Κατοικίας: " + Location.getText()
                                + " - " + "Email: " + email.getText() + " - " + "Τηλέφωνο Επικοινωνίας: " + phone_number.getText());

                        buffer.newLine();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }//

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

    private void updater(Person person) {
        this.setEmail(person.getEmail());
        this.setName(person.getName());
        this.setHome_ground(person.getHome_ground());
        this.setPhone_number(person.getPhone_number());
    }


}
