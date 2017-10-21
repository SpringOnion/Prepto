package infs3605.prepto;

import android.content.ContentValues;
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

    TextView header;
    TextView question;
    TextView answerA;
    TextView answerB;
    TextView answerC;
    TextView answerD;
    TextView completion;
    ProgressBar quizProg;
    String[] responses;
    Question[] questions;
    int score;
    int totalAnswered = 0;
    int count = 10;
    int week;
    String student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        week = getIntent().getExtras().getInt("Week");
        student = getIntent().getExtras().getString("Student");

        int count = getCount(week);
        questions = new Question[count];
        for (int i = 0; i < count; i++) {
            questions[i] = new Question();
        }

        questions = getQuestions(week).clone();
        if (questions == null || questions.length == 0) {
            Intent intent = new Intent(QuizPage.this, StudentDashboard.class);
            startActivity(intent);
            Toast.makeText(this, "We couldn't find a quiz for you to complete.", Toast.LENGTH_LONG).show();
        }
        responses = new String[questions.length];

        header = (TextView) findViewById(R.id.text_quiz_title);
        header.setText("Week " + week + " QUIZ");
        completion = (TextView) findViewById(R.id.test_completion);
        completion.setText("1 / " + responses.length);
        question = (TextView) findViewById(R.id.text_question);
        answerA = (TextView) findViewById(R.id.textcardA);
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Correct Code, DO NOT TOUCH

                if (questions[totalAnswered].getCorrectAnswer().equals("A")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "A";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                completion.setText((totalAnswered + 1) + " / " + responses.length);
                checkCompletion(totalAnswered);

            }
        });
        answerB = (TextView) findViewById(R.id.textcardB);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("B")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "B";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                completion.setText((totalAnswered + 1) + " / " + responses.length);
                checkCompletion(totalAnswered);
            }
        });
        answerC = (TextView) findViewById(R.id.textcardC);
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("C")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "C";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                completion.setText((totalAnswered + 1) + " / " + responses.length);
                checkCompletion(totalAnswered);
            }
        });
        answerD = (TextView) findViewById(R.id.textcardD);
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (questions[totalAnswered].getCorrectAnswer().equals("D")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "D";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
                completion.setText((totalAnswered + 1) + " / " + responses.length);
                checkCompletion(totalAnswered);
            }
        });

        setAllText(questions[0]);
    }

    private void checkCompletion(int progress) {
        if (progress >= count) {
            Result[] result = new Result[count];
            for (int i = 0; i < count; i++) {
                result[i] = new Result();
                result[i].setQuiz(week);
                result[i].setCorrectAnswer(questions[i].getCorrectAnswer());
                result[i].setQuestionID(questions[i].getId());
                result[i].setResult(responses[i]);
                result[i].setStudent(student);
                result[i].setQuestionText(questions[i].getQuestion());
            }
            for (Result r : result) {
                uploadResults(r);
            }

            Intent intent = new Intent(QuizPage.this, StudentDashboard.class);
            intent.putExtra("Username", student);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), "Quiz Complete!", Toast.LENGTH_LONG).show();
        }
    }

    private void uploadResults(Result results) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizPage.this).getWritableDatabase();
        String query = "select * from results";
        Cursor cursor = db.rawQuery(query, null);
        ContentValues values = new ContentValues();
        values.put("quiz", results.getQuiz());
        values.put("correctanswer", results.getCorrectAnswer());
        values.put("questionID", results.getQuestionID());
        values.put("result", results.getResult());
        values.put("student", results.getStudent());
        values.put("questiontext", results.getQuestionText());
        db.insert("results", null, values);
        cursor.close();
        db.close();
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
            questions[i].id = cursor.getInt((cursor.getColumnIndex("ID")));
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
