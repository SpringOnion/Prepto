package infs3605.prepto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
    String correctAnswer;
    String[] responses;
    int score;
    int totalAnswered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        responses = new String[10];

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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered], Toast.LENGTH_LONG);
                toast.show();
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered], Toast.LENGTH_LONG);
                toast.show();
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered], Toast.LENGTH_LONG);
                toast.show();
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered], Toast.LENGTH_LONG);
                toast.show();
                totalAnswered++;
            }
        });
    }
}
