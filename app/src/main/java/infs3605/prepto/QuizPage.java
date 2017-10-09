package infs3605.prepto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

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

    File excelSheet;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                if (resultCode == RESULT_OK) {
                    final Uri uri = data.getData();
                    String path = FileUtils.getPath(this, uri);
                    if (path != null && FileUtils.isLocal(path)) {
                        excelSheet = new File(path);
                    }
                    try {
                        Workbook workbook = Workbook.getWorkbook(excelSheet);
                        Sheet sheet = workbook.getSheet(0);
                        Cell cellText = sheet.getCell(0, 1);
                        question.setText(cellText.getContents());
                        cellText = sheet.getCell(1, 1);
                        answerA.setText(cellText.getContents());
                        cellText = sheet.getCell(2, 1);
                        answerB.setText(cellText.getContents());
                        cellText = sheet.getCell(3, 1);
                        answerC.setText(cellText.getContents());
                        cellText = sheet.getCell(4, 1);
                        answerD.setText(cellText.getContents());
                        cellText = sheet.getCell(5, 1);
                        correctAnswer = "answer" + cellText.getContents();
                    } catch (Exception ex) {
                        Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                    }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent getContentIntent = FileUtils.createGetContentIntent();
        Intent intent = Intent.createChooser(getContentIntent, "Select a File");
        startActivityForResult(intent, 1234);

        responses = new String[10];

        question = (TextView) findViewById(R.id.text_question);
        question.setText("This is testing purposes");
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
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
                Toast toast = Toast.makeText(getApplicationContext(), "Answer " + totalAnswered + ": " + responses[totalAnswered] + ", Score = " + score, Toast.LENGTH_LONG);
                toast.show();
                totalAnswered++;
            }
        });


    }
}
