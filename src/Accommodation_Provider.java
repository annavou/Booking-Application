import java.util.ArrayList;

public class Accommodation_Provider extends Person{


    ArrayList<Accommodation> Accommodations = new ArrayList<>();
    Accommodation acc ;


    Accommodation_Provider(){}

    Accommodation_Provider(String aname , String ahome_ground, double aphone_number, String aemail){
        super(aname,ahome_ground,aphone_number,aname);
    }

    public void Accomodations_Display_All(){  //safes
        for(Accommodation acc : Accommodations){
            acc.show_accommodation();
        }
    }

    public void Accomodations_Display(){ //safes
        System.out.println("poio");

        next_string = sc.next();
        Accommodation acc = search(next_string,Accommodations);
        acc.show_accommodation();

    }

    public void Accomodation_Edit(){
        System.out.println("poio thes");
        next_string = sc.next();

        acc = search(next_string,Accommodations);


        System.out.println("ti thes na allakseis");
        next_string = sc.next();

        switch (next_string){
            case "name" :
                System.out.println("dwse onoma");
                next_string = sc.next();
                acc.setName(next_string);
                System.out.println(acc.getName());
            case "location":


        }



    } // ta upolipa kai safes


    public void Accommodation_add() {
        System.out.println("dwse onoma tou spitiou");
        next_string = sc.next();
        Accommodation temp = new Accommodation(next_string,"kavala",23,44,2,"ff",3,4,true,true,true,true,true);
        Accommodations.add(temp);
    }

    public void Accommodation_delete() {
        System.out.println("dwse onoma tou spitiou");
        next_string = sc.next();
        acc = search(next_string,Accommodations);
        Accommodations.remove(acc);
    }
}

