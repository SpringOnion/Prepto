package infs3605.prepto;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class QuizReviewActivity extends AppCompatActivity {

    int week;
    String student;
    TextView header;
    TextView question;
    TextView answerA;
    TextView answerB;
    TextView answerC;
    TextView answerD;
    TextView completion;
    Question[] questions;
    Result[] results;
    String hint;
    Button buttonNext;
    Button buttonPrev;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        week = getIntent().getExtras().getInt("Week");
        student = getIntent().getExtras().getString("Student");
        final int count = getCount(week);

        questions = new Question[count];
        for (int i = 0; i < count; i++) {
            questions[i] = new Question();
        }

        results = new Result[count];
        for (int i = 0; i < count; i++) {
            results[i] = new Result();
        }

        questions = getQuestions(week).clone();
        //results = getResults(week, student).clone();

        header = (TextView) findViewById(R.id.text_quiz_title);
        header.setText("Week " + week + " REVIEW");
        question = (TextView) findViewById(R.id.text_question);
        completion = (TextView) findViewById(R.id.test_completion);
        completion.setText("1 / " + count);
        answerA = (TextView) findViewById(R.id.textcardA);
        answerB = (TextView) findViewById(R.id.textcardB);
        answerC = (TextView) findViewById(R.id.textcardC);
        answerD = (TextView) findViewById(R.id.textcardD);

        buttonNext = (Button) findViewById(R.id.button_result_next);
        buttonPrev = (Button) findViewById(R.id.button_result_back);
        header = (TextView) findViewById(R.id.text_quiz_title);
        header.setText("Week " + week + " REVIEW");
        question = (TextView) findViewById(R.id.text_question);
        completion = (TextView) findViewById(R.id.test_completion);
        completion.setText("1 / " + count);
        answerA = (TextView) findViewById(R.id.textcardA);
        answerB = (TextView) findViewById(R.id.textcardB);
        answerC = (TextView) findViewById(R.id.textcardC);
        answerD = (TextView) findViewById(R.id.textcardD);

        buttonNext = (Button) findViewById(R.id.button_result_next);
        buttonPrev = (Button) findViewById(R.id.button_result_back);
        buttonNext.setVisibility(View.VISIBLE);
        buttonPrev.setVisibility(View.VISIBLE);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress++;
                if (progress < count) {
                    setAllText(questions[progress], results[progress]);
                } else {
                    progress = 0;
                    setAllText(questions[progress], results[progress]);
                }
            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress--;
                if (progress >= 0) {
                    setAllText(questions[progress], results[progress]);
                } else {
                    progress = 0;
                }
            }
        });
        hint = questions[0].getHint();
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizReviewActivity.this);
                builder.setMessage(hint);
                builder.setTitle("Question " + (progress + 1));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizReviewActivity.this);
                builder.setMessage(hint);
                builder.setTitle("Question " + (progress + 1));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizReviewActivity.this);
                builder.setMessage(hint);
                builder.setTitle("Question " + (progress + 1));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        answerD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(QuizReviewActivity.this);
                builder.setMessage(hint);
                builder.setTitle("Question " + (progress + 1));
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        setAllText(questions[0], results[0]);
    }

    private String setAllText(Question questionBank, Result result) {
        String isCorrect = "";
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
        switch (result.getCorrectAnswer()) {
            case "A":
                answerA.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                answerB.setBackgroundColor(getResources().getColor(R.color.White));
                answerC.setBackgroundColor(getResources().getColor(R.color.White));
                answerD.setBackgroundColor(getResources().getColor(R.color.White));
                isCorrect = "A";
                break;
            case "B":
                answerA.setBackgroundColor(getResources().getColor(R.color.White));
                answerB.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                answerC.setBackgroundColor(getResources().getColor(R.color.White));
                answerD.setBackgroundColor(getResources().getColor(R.color.White));
                isCorrect = "B";
                break;
            case "C":
                answerA.setBackgroundColor(getResources().getColor(R.color.White));
                answerB.setBackgroundColor(getResources().getColor(R.color.White));
                answerC.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                answerD.setBackgroundColor(getResources().getColor(R.color.White));
                isCorrect = "C";
                break;
            case "D":
                answerA.setBackgroundColor(getResources().getColor(R.color.White));
                answerB.setBackgroundColor(getResources().getColor(R.color.White));
                answerC.setBackgroundColor(getResources().getColor(R.color.White));
                answerD.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                isCorrect = "D";
                break;
        }
        switch (result.getResult()) {
            case "A":
                if (isCorrect.equals("A")) {
                    break;
                } else {
                    answerA.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    break;
                }
            case "B":
                if (isCorrect.equals("B")) {
                    break;
                } else {
                    answerB.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    break;
                }
            case "C":
                if (isCorrect.equals("C")) {
                    break;
                } else {
                    answerC.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    break;
                }
            case "D":
                if (isCorrect.equals("D")) {
                    break;
                } else {
                    answerD.setBackgroundColor(getResources().getColor(R.color.colorRed));
                    break;
                }
        }
        hint = questionBank.getHint();
        return isCorrect;
    }

    private Result[] getResults(int quiz, String username) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizReviewActivity.this).getReadableDatabase();
        String query = "SELECT * FROM results WHERE quiz = " + quiz + " AND STUDENT ='" + username + "'; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        Result[] results = new Result[count];
        for (int i = 0; i < count; i++) {
            results[i] = new Result();
        }
        int j = 0;
        cursor.moveToFirst();
        while (j < count) {
        }
        //cursor.close();
        //db.close();
        return results;
    }

    public Question[] getQuestions(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizReviewActivity.this).getReadableDatabase();
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
        //cursor.close();
        //db.close();
        String result = "SELECT * FROM results WHERE quiz = " + quiz + " AND STUDENT ='" + student + "'; ";
        Cursor second = db.rawQuery(result, null);
        second.moveToFirst();
        for (int j = 0; j < count && !second.isAfterLast(); j++) {
            results[j].setCorrectAnswer(second.getString(second.getColumnIndex("correctanswer")));
            results[j].setResult(second.getString(second.getColumnIndex("result")));
            results[j].setQuestionText(second.getString(second.getColumnIndex("questiontext")));
            second.moveToNext();
        }
        //second.close();
        //db.close();

        return questions;
    }

    public int getCount(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(QuizReviewActivity.this).getReadableDatabase();
        String query = "SELECT quiz FROM questions WHERE quiz= " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        //cursor.close();
        //db.close();
        return count;
    }
}
