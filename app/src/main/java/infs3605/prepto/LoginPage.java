package infs3605.prepto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static infs3605.prepto.R.id.BLogin;
import static infs3605.prepto.R.id.TFpassword;

public class LoginPage extends AppCompatActivity {

    public Button v;
    public Button signup;
    DatabaseHelper helper = new DatabaseHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        signup = (Button) findViewById(R.id.Bsignup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginPage.this, SignUp.class);
                startActivity(intent);

            }
        });
        v = (Button) findViewById(R.id.button_login);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText a = (EditText) findViewById(R.id.TFuname);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(TFpassword);
                String pass = b.getText().toString();

                String password = helper.searchPass(str);
                if (pass.equals(password)) {
                    Intent i = new Intent(LoginPage.this, StudentDashboard.class);
                    //Intent i = new Intent(LoginPage.this, TeacherDashboard.class);
                    i.putExtra("Username", str);
                    startActivity(i);
                }
            }
        });
    }

        public void onButtonClick(View v)
    {
        if(v.getId() == BLogin)
        {
            EditText a = (EditText) findViewById(R.id.TFuname);
            String str = a.getText().toString();
            EditText b = (EditText)findViewById(TFpassword);
            String pass = b.getText().toString();

            Toast.makeText(LoginPage.this, str, Toast.LENGTH_SHORT).show();
            String password = helper.searchPass(str);
            if(pass.equals(password))
            {
                Toast.makeText(LoginPage.this, "Everything's working! Yay", Toast.LENGTH_SHORT).show();
                Intent i = new Intent (LoginPage.this, StudentDashboard.class);
                i.putExtra("Username", str);
                startActivity(i);
            }
            if(v.getId() == R.id.Bsignup)
            {
            Intent i = new Intent(LoginPage.this, SignUp.class);
                startActivity(i);

        }
            else {
                Toast temp = Toast.makeText(LoginPage.this, "Username and password dont match!", Toast.LENGTH_SHORT);
                temp.show();
            }

        }
    }
}
