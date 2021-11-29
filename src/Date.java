public class Date {

    private int  day ;
    private int month ;
    private int year ;



    public Date(int a , int b , int c){
        day = a ;
        month = b ;
        year = c ;
    }

    public void setDay(int a){ day = a ; }
    public void setMonth(int b){month = b ; }
    public void setYear(int c){year = c ; }

    public int getDay(){return day;}
    public int getMonth(){return  month;}
    public int getYear(){return year;}

}
