package infs3605.prepto;

import android.content.ContentValues;
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

    public void addBaseContacts() {
        SQLiteDatabase db = DatabaseHelper.getInstance(getApplicationContext()).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", "1");
        values.put("name", "Michael Cahallane");
        values.put("email", "z1111111@ad.unsw.edu.au");
        values.put("username", "z1111111");
        values.put("pass", "teacher1");
        db.insert("contacts", null, values);
        ContentValues second = new ContentValues();
        second.put("id", "2");
        second.put("name", "Vineet Suri");
        second.put("email", "z1234567@ad.unsw.edu.au");
        second.put("username", "z1234567");
        second.put("pass", "student1");
        db.insert("contacts", null, second);
        db.close();
    }

    public void addBaseQuestions() {
        SQLiteDatabase db = DatabaseHelper.getInstance(getApplicationContext()).getWritableDatabase();
        Question[] questions = new Question[10];
        for (int i = 0; i < 10; i++) {
            questions[i] = new Question();
        }
        questions[0].setQuestion("");
        questions[1].setQuestion("");
        questions[2].setQuestion("");
        questions[3].setQuestion("");
        questions[4].setQuestion("");
        questions[5].setQuestion("");
        questions[6].setQuestion("");
        questions[7].setQuestion("");
        questions[8].setQuestion("");
        questions[9].setQuestion("");


        questions[0].setAnswerA("");
        questions[1].setAnswerA("");
        questions[2].setAnswerA("");
        questions[3].setAnswerA("");
        questions[4].setAnswerA("");
        questions[5].setAnswerA("");
        questions[6].setAnswerA("");
        questions[7].setAnswerA("");
        questions[8].setAnswerA("");
        questions[9].setAnswerA("");

        questions[0].setAnswerB("");
        questions[1].setAnswerB("");
        questions[2].setAnswerB("");
        questions[3].setAnswerB("");
        questions[4].setAnswerB("");
        questions[5].setAnswerB("");
        questions[6].setAnswerB("");
        questions[7].setAnswerB("");
        questions[8].setAnswerB("");
        questions[9].setAnswerB("");

        questions[0].setAnswerC("");
        questions[1].setAnswerC("");
        questions[2].setAnswerC("");
        questions[3].setAnswerC("");
        questions[4].setAnswerC("");
        questions[5].setAnswerC("");
        questions[6].setAnswerC("");
        questions[7].setAnswerC("");
        questions[8].setAnswerC("");
        questions[9].setAnswerC("");

        questions[0].setAnswerD("");
        questions[1].setAnswerD("");
        questions[2].setAnswerD("");
        questions[3].setAnswerD("");
        questions[4].setAnswerD("");
        questions[5].setAnswerD("");
        questions[6].setAnswerD("");
        questions[7].setAnswerD("");
        questions[8].setAnswerD("");
        questions[9].setAnswerD("");

        questions[0].setCorrectAnswer("");
        questions[1].setCorrectAnswer("");
        questions[2].setCorrectAnswer("");
        questions[3].setCorrectAnswer("");
        questions[4].setCorrectAnswer("");
        questions[5].setCorrectAnswer("");
        questions[6].setCorrectAnswer("");
        questions[7].setCorrectAnswer("");
        questions[8].setCorrectAnswer("");
        questions[9].setCorrectAnswer("");

        questions[0].setQuiz(1);
        questions[1].setQuiz(1);
        questions[2].setQuiz(1);
        questions[3].setQuiz(1);
        questions[4].setQuiz(1);
        questions[5].setQuiz(1);
        questions[6].setQuiz(1);
        questions[7].setQuiz(1);
        questions[8].setQuiz(1);
        questions[9].setQuiz(1);

        TeacherDashboard td = new TeacherDashboard();
        for (int j = 0; j < 10; j++) {
            td.insertQuestions(questions[j]);
        }
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
