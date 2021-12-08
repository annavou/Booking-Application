import java.util.ArrayList;

public class Hotel_Provider extends  Person{

    ArrayList<Hotel> Hotels = new ArrayList<>();
    Hotel hotel ;


    Hotel_Provider(){

    }

    Hotel_Provider(String aname , String ahome_ground, double aphone_number, String aemail){
        super(aname,ahome_ground,aphone_number,aemail);
    }


    public void Hotels_Display_All(){  //safes
        for(Hotel h : Hotels){
            h.show_Hotel();
        }
    }

    public void Hotels_Display() {
        System.out.println("poio");

        next_string = sc.next();
        hotel = search_hot(next_string,Hotels);
        hotel.show_Hotel();

    }

    public void Hotel_add() {
        System.out.println("dwse onoma tou spitiou");
        next_string = sc.next();
        Hotel temp = new Hotel(next_string,"kavala",4);
        Hotels.add(temp);
    }


    public void Hotel_delete() {
        System.out.println("dwse onoma tou spitiou");
        next_string = sc.next();
        hotel = search_hot(next_string,Hotels);
        Hotels.remove(hotel);
    }


}