import java.util.Objects;

//ΕΤΟΙΜΟ
public class Credentials {


    private String Password ;
    private String Username ;

    Credentials(){}

    Credentials(String a,String b ){
        Username = a ;
        Password = b ;
    }

    public void Display(){
        System.out.println(Username + "  " + Password);
    }


    public boolean equal(Credentials a){
        return this.Username.equals(a.Username) && Objects.equals(this.Password, a.Password);
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}