import  java.time.LocalDate;
public class Reservations {
    LocalDate start;
    LocalDate end;

    public Reservations(){
        start = null;
        end = null ;
    }

    public Reservations(LocalDate a, LocalDate b){
        start = a ;
        end = b ;
    }


    public void show(){
        System.out.println(start+"-"+end);
    }
}
