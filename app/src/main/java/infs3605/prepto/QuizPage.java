package infs3605.prepto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;

/**
 * Created by chenz on 30/09/2017.
 */

public class QuizPage extends AppCompatActivity {

    TextView question;
    TextView answerA;
    TextView answerB;
    TextView answerC;
    TextView answerD;
    String correctAnswer;
    String[] responses;
    int score;
    int totalAnswered = 0;
    Question[] questions;

    File excelSheet;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        int week = getIntent().getExtras().getInt("Week");
        questions = db.getQuestions(week);
        setAllText(questions[totalAnswered]);

        responses = new String[questions.length];

        question = (TextView) findViewById(R.id.text_question);
        correctAnswer = "answerA";
        answerA = (TextView) findViewById(R.id.textcardA);
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctAnswer.equals("answerA")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerA";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;

            }
        });
        answerB = (TextView) findViewById(R.id.textcardB);
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctAnswer.equals("answerB")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerB";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
            }
        });
        answerC = (TextView) findViewById(R.id.textcardC);
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctAnswer.equals("answerC")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerC";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
            }
        });
        answerD = (TextView) findViewById(R.id.textcardD);
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (correctAnswer.equals("answerD")) {
                    score++;
                } else {
                }
                responses[totalAnswered] = "answerD";
                //Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                //toast.show();
                setAllText(questions[totalAnswered]);
                totalAnswered++;
            }
        });

    }

    private void setAllText(Question questionBank) {
        question.setText(questionBank.question);
        answerA.setText(questionBank.answerA);
        answerB.setText(questionBank.answerB);
        answerC.setText(questionBank.answerC);
        answerD.setText(questionBank.answerD);
        correctAnswer = "answer" + questionBank.correctAnswer;
    }
}
