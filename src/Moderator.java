import java.util.Collection;
import java.util.HashMap;
//ΣΥΝΔΕΣΗ ΜΕ ΚΡΑΤΗΣΕΙΣ
public class Moderator extends Person{


    Moderator(){}


    Moderator(String aname , String ahome_ground, double aphone_number, String aemail){
        super(aname,ahome_ground,aphone_number,aname);
        super.setActivated(true);
    }

    public void see_all_accommodations(Collection<Person> persons){
        for (Person p : persons ){
            if (p instanceof Accommodation_Provider AP){
                AP.Accomodations_Display_All();
            }
            if(p instanceof Hotel_Provider HP){
                HP.Hotels_Display_All();
            }
        }
    }

    public void see_all_users(Collection<Person> persons){
        for (Person p : persons ){
            p.show_person();
        }
    }

    public void account_manage(Collection<Person> people) {
        for (Person p : people){
            if (!p.isActivated()){
                p.show_person();
            }
        }
        System.out.println("autoi den einai active   thes na kaneis kapoion");
        next_string = sc.next();
        while (next_string.equals("nai")){
            System.out.println("poion?");
            next_string = sc.next();
            for (Person p : people){
                if (next_string.equals(p.getName())){
                    p.setActivated(true);
                }
            }
            System.out.println("thes kai allon?");
            next_string = sc.next();
        }

    }
}
