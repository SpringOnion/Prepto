package infs3605.prepto;

/**
 * Created by amarkashyap on 1/10/2017.
 */

public class Contact {
    int id;
    String name, email, username, pass;

    public Contact(String Name, String Email, String Username, String Password) {
        name = Name;
        email = Email;
        username = Username;
        pass = Password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
