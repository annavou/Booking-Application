import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class Person {

    private String Name ;
    private String Home_ground ;
    private double Phone_number ;
    private String Email ;
    private boolean Activated ;


    ArrayList<String> messages = new ArrayList<>();
    int messages_count;

    Scanner sc = new Scanner(System.in);

    int next_int;
    boolean nex_bool;
    String next_string;



    Person(){
        Name = null;
        Home_ground = null ;
        Phone_number = 0 ;
        Email = null ;
        Activated = false ;
    }

    Person(String aname , String ahome_ground, double aphone_number, String aemail){
        Name = aname ;
        Home_ground = ahome_ground ;
        Phone_number = aphone_number ;
        Email = aemail ;
        messages.add("Welcome to the app " + getName());
        messages_count = 0;
        Activated = false ;
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

    public double getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(double phone_number) {
        Phone_number = phone_number;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public Accommodation search(String name, ArrayList<Accommodation> accommodations){
        for (Accommodation acc : accommodations){
            if(name.equals(acc.getName())){
                return acc;
            }
        }
        return null;
    }


    public void show_person(){
        System.out.println("The person "+getName());
    }

    public void messages_notifications() {
        int mess  = messages.size() - messages_count ;
        if( mess > 0 ){
            System.out.println("You have " + mess + " New mail(s)");
            messages_count = messages.size();
        }
    }


    public void message_send(Collection<Person> acc_list) {


        Person temp = new Person() ;
        System.out.println("se poion minima");
        next_string = sc.next();
        for (Person p : acc_list){
            if(p.getName().equals(next_string)){
                temp = p ;
            }
        }
        System.out.println("grapse to mnm:");
        next_string = sc.next();
        temp.messages.add(next_string);
    }

    public void message(Collection<Person> acc_list) {
        boolean flag = true ;
        System.out.println("steile h des h svise h fuge");
        while (flag) {


            next_string = sc.next();
            switch (next_string) {
                case "steile" -> message_send(acc_list);
                case "des" -> messages_view();
                case "svise" -> messages_delete();
                case "efuga" -> flag = false;
            }
            System.out.println("steile h des h svise h fuge");
        }
    }

    private void messages_delete() {
        messages_view();
        System.out.println("poio thes na sviseis");
        next_int = sc.nextInt();
        messages.remove(next_int-1);
    }

    private void messages_view() {
        for (int i = 0 ; i < messages.size() ; i++)
            System.out.println((i+1)+") " + messages.get(i));
    }
}

