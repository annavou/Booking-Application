public class Reservations {
    Date start;
    Date end;

    public Reservations(){
        start = null;
        end = null ;
    }

    public Reservations(Date a, Date b){
        start = a ;
        end = b ;
    }


    public void show(){
        System.out.println(start.getDay()+"/"+start.getMonth()+"/"+start.getYear()+" - "+end.getDay()+"/"+end.getMonth()+"/"+end.getYear());
    }
}
