package infs3605.prepto;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by amarkashyap on 1/10/2017.
 */

public class SignUp extends Activity {

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
    }
    public void onSignUpClick(View v)
    {


        if(v.getId() == R.id.Bsignupbutton)
        {
            EditText name = findViewById((R.id.TFname));
            EditText email = findViewById((R.id.TFemail));
            EditText username = findViewById((R.id.TFusername));
            EditText pass1 = findViewById((R.id.TFpass1));
            EditText pass2 = findViewById((R.id.TFpass2));

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String usernamestr = username.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            Contact contact = new Contact(namestr, emailstr, usernamestr, pass1str);

            if(!pass1str.equals(pass2str))
            {
                //popup msg
                Toast pass = Toast.makeText(SignUp.this , "Passwords dont match!", Toast.LENGTH_SHORT);
                pass.show();
            } else if (pass1str.equals(pass2str)) {
                helper.insertContact(contact);
                Toast.makeText(SignUp.this, namestr + " " + emailstr + " " + usernamestr, Toast.LENGTH_LONG).show();
            }
        }
    }
}