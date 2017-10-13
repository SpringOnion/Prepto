package infs3605.prepto;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static infs3605.prepto.DatabaseHelper.COLUMN_EMAIL;
import static infs3605.prepto.DatabaseHelper.COLUMN_ID;
import static infs3605.prepto.DatabaseHelper.COLUMN_NAME;
import static infs3605.prepto.DatabaseHelper.COLUMN_PASS;
import static infs3605.prepto.DatabaseHelper.COLUMN_USERNAME;
import static infs3605.prepto.DatabaseHelper.TABLE_NAME;

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

    public void insertContact(Contact c) {
        SQLiteDatabase db = DatabaseHelper.getInstance(SignUp.this).getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASS, c.getPass());

        db.insert(TABLE_NAME, null, values);
        cursor.close();
        db.close();
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
                insertContact(contact);
                Toast.makeText(SignUp.this, "Signup complete, " + usernamestr, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SignUp.this, LoginPage.class);
                startActivity(intent);
            }
        }
    }
}