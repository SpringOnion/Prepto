package infs3605.prepto;

/**
 * Created by amarkashyap on 1/10/2017.
 */

public class Contact {
    int id;
    String name, email, username, pass;

    public void setID(int id) {
        this.id = id;
    }
    public int getId()
    {
        return this.id;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return this.name;
    }
    public void setEmail(String Email)
    {
        this.email = email;
    }
    public String getEmail()
    {
        return this.email;
    }
    public void setUsername(String username)
    {
        this.username = username;
    }
    public String getUsername()
    {
        return this.username;
    }
    public void setPass(String pass)
    {
        this.pass = pass;
    }
    public String getPass()
    {
        return this.pass;
    }
}
