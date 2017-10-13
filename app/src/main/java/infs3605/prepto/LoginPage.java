package infs3605.prepto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static infs3605.prepto.DatabaseHelper.TABLE_NAME;
import static infs3605.prepto.R.id.BLogin;

public class LoginPage extends AppCompatActivity {

    public Button v;
    public Button signup;

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
                EditText b = (EditText) findViewById(R.id.TFpassword);
                String pass = b.getText().toString();

                Intent i;
                String password = searchPass(str);
                if (pass.equals(password)) {
                    if (password.equals("student1")) {
                        i = new Intent(LoginPage.this, StudentDashboard.class);
                    } else {
                        i = new Intent(LoginPage.this, TeacherDashboard.class);
                    }
                    i.putExtra("Username", str);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Error: could not log you in", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public String searchPass(String username) {
        SQLiteDatabase db = DatabaseHelper.getInstance(LoginPage.this).getWritableDatabase();
        String query = "select username, pass from " + TABLE_NAME + " WHERE username ='" + username + "';";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return b;
    }


    public void onButtonClick(View v)
    {
        if(v.getId() == BLogin)
        {
            EditText a = (EditText) findViewById(R.id.TFuname);
            String str = a.getText().toString();
            EditText b = (EditText) findViewById(R.id.TFpassword);
            String pass = b.getText().toString();

            Toast.makeText(LoginPage.this, str, Toast.LENGTH_SHORT).show();
            String password = searchPass(str);
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
