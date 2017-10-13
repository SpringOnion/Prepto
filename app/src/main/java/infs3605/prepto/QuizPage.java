package infs3605.prepto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        int week = getIntent().getExtras().getInt("Week");
        db = new DatabaseHelper(QuizPage.this);

        int count = getCount(week);
        questions = new Question[count];
        for (int i = 0; i < count; i++) {
            questions[i] = new Question();
        }
        questions = getQuestions(week);
        setAllText(questions[totalAnswered]);

        quizProg = (ProgressBar) findViewById(R.id.progressBar);
        responses = new String[questions.length];
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
                quizProg.incrementProgressBy(10);
            }
        });

    }

    public int getCount(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizPage.this).getReadableDatabase();
        String query = "SELECT quiz FROM QUESTIONS WHERE quiz = " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        db.close();
        return count;
    }

    public Question[] getQuestions(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizPage.this).getReadableDatabase();
        String query = "SELECT * FROM QUESTIONS WHERE quiz = " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        Question[] questions = new Question[count];
        int i = 0;
        while (i < count) {
            questions[i].question = cursor.getString(cursor.getColumnIndex("question"));
            questions[i].answerA = cursor.getString(cursor.getColumnIndex("answera"));
            questions[i].answerB = cursor.getString(cursor.getColumnIndex("answerb"));
            questions[i].answerC = cursor.getString(cursor.getColumnIndex("answerc"));
            questions[i].answerD = cursor.getString(cursor.getColumnIndex("anwerd"));
            questions[i].correctAnswer = cursor.getString(cursor.getColumnIndex("correctanswer"));
            questions[i].quiz = cursor.getInt(cursor.getColumnIndex("quiz"));
        }
        db.close();
        return questions;
    }

    private void setAllText(Question questionBank) {
        question.setText(questionBank.question);
        answerA.setText(questionBank.answerA);
        answerB.setText(questionBank.answerB);
        answerC.setText(questionBank.answerC);
        answerD.setText(questionBank.answerD);
    }
}
