package infs3605.prepto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by chenz on 30/09/2017.
 */

public class QuizPage extends AppCompatActivity {

    TextView question;
    TextView answerA;
    TextView answerB;
    TextView answerC;
    TextView answerD;
    ProgressBar quizProg;
    String[] responses;
    Question[] questions;
    int score;
    int totalAnswered = 0;
    int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        int week = getIntent().getExtras().getInt("Week");


        /*
        int count = getCount(week);
        questions = new Question[count];
        for (int i = 0; i < count; i++) {
            questions[i] = new Question();
        }
        questions = getQuestions(week);
        */
        questions = new Question[10];
        for (int i = 0; i < 10; i++) {
            questions[i] = new Question();
        }
        questions = getQuestions(2).clone();

        quizProg = (ProgressBar) findViewById(R.id.progressBar);
        //responses = new String[questions.length];
        responses = new String[10];

        question = (TextView) findViewById(R.id.text_question);
        answerA = (TextView) findViewById(R.id.textcardA);
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("answerA")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerA";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                checkCompletion(totalAnswered);
                quizProg.incrementProgressBy(10);
            }
        });
        answerB = (TextView) findViewById(R.id.textcardB);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("answerB")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerB";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                checkCompletion(totalAnswered);
                quizProg.incrementProgressBy(10);
            }
        });
        answerC = (TextView) findViewById(R.id.textcardC);
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("answerC")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerC";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                checkCompletion(totalAnswered);
                quizProg.incrementProgressBy(10);
            }
        });
        answerD = (TextView) findViewById(R.id.textcardD);
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("answerD")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerD";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                checkCompletion(totalAnswered);
                quizProg.incrementProgressBy(10);
            }
        });

        setAllText(questions[0]);
    }

    private void checkCompletion(int progress) {
        if (progress >= count) {
            Intent intent = new Intent(QuizPage.this, StudentDashboard.class);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Quiz Complete!", Toast.LENGTH_LONG).show();
        }
    }

    public int getCount(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizPage.this).getReadableDatabase();
        String query = "SELECT quiz FROM questions WHERE quiz= " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    public Question[] getQuestions(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizPage.this).getReadableDatabase();
        String query = "SELECT * FROM QUESTIONS WHERE quiz = " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        Question[] questions = new Question[count];
        for (int j = 0; j < count; j++) {
            questions[j] = new Question();
        }
        int i = 0;
        cursor.moveToFirst();
        while (i < count) {
            questions[i].question = cursor.getString(cursor.getColumnIndex("question"));
            questions[i].answerA = cursor.getString(cursor.getColumnIndex("answerA"));
            questions[i].answerB = cursor.getString(cursor.getColumnIndex("answerB"));
            questions[i].answerC = cursor.getString(cursor.getColumnIndex("answerC"));
            questions[i].answerD = cursor.getString(cursor.getColumnIndex("answerD"));
            questions[i].correctAnswer = cursor.getString(cursor.getColumnIndex("correctanswer"));
            questions[i].quiz = cursor.getInt(cursor.getColumnIndex("quiz"));
            cursor.moveToNext();
            i++;
        }
        cursor.close();
        db.close();
        return questions;
    }

    private void setAllText(Question questionBank) {
        String q = (questionBank.getQuestion());
        question.setText(q);
        String a = (questionBank.getAnswerA());
        answerA.setText(a);
        String b = (questionBank.getAnswerB());
        answerB.setText(b);
        String c = (questionBank.getAnswerC());
        answerC.setText(c);
        String d = (questionBank.getAnswerD());
        answerD.setText(d);
    }
}
