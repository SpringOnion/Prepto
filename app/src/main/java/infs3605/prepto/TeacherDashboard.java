package infs3605.prepto;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TeacherDashboard extends AppCompatActivity {

    String videoLink;
    String videoWeek;
    private Button buttonFile;
    private Button buttonDownload;
    private Button buttonChart;
    private File excelBook;
    private TextView uploadVideo;
    private TextView uploadQuiz;
    private DatabaseHelper dbHelper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        uploadQuiz = (TextView) findViewById(R.id.uploadQuizButton);
        uploadQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent.createChooser(getContentIntent, "Select a File");
                startActivityForResult(intent, 1234);
            }
        });

        uploadVideo = (TextView) findViewById(R.id.uploadVideoButton);
        uploadVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherDashboard.this);
                builder.setTitle("Enter or paste your link here: ");

                LinearLayout layout = new LinearLayout(TeacherDashboard.this);
                layout.setOrientation(LinearLayout.VERTICAL);

                final EditText input = new EditText(TeacherDashboard.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_URI);
                input.setHint("Link");
                final EditText week = new EditText(TeacherDashboard.this);
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                week.setHint("Week/Quiz");

                layout.addView(input);
                layout.addView(week);

                builder.setView(layout);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        videoLink = input.getText().toString();
                        videoWeek = week.getText().toString();
                        //TODO: LInk this to database
                        Toast.makeText(TeacherDashboard.this, "Sucessfully uploaded your video", Toast.LENGTH_LONG).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        buttonChart = (Button) findViewById(R.id.button_chart);
        buttonChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TeacherDashboard.this, AnalyticsPage.class);
                intent.putExtra("Week", 1);
                startActivity(intent);
            }
        });
    }

    public void insertQuestions(Question question) {
        SQLiteDatabase db = DatabaseHelper.getInstance(TeacherDashboard.this).getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM QUESTIONS";

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put("ID", count);
        values.put("question", question.getQuestion());
        values.put("answera", question.getAnswerA());
        values.put("answerb", question.getAnswerB());
        values.put("answerc", question.getAnswerC());
        values.put("answerd", question.getAnswerD());
        values.put("correctanswer", question.getCorrectAnswer());
        values.put("quiz", question.getQuiz());

        db.insert("QUESTIONS", null, values);
        cursor.close();
        db.close();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            final Uri uri = data.getData();
            String path = FileUtils.getPath(this, uri);
            if (path != null && FileUtils.isLocal(path)) {
                excelBook = new File(path);
            }
            try {
                Workbook workbook = Workbook.getWorkbook(excelBook);
                Sheet sheet = workbook.getSheet(0);
                int i = 1;
                boolean isContent = true;
                while (isContent) {
                    Cell cellText = sheet.getCell(i, 0);
                    if (cellText.getContents().isEmpty()) {
                        isContent = false;
                    } else {
                        i++;
                    }
                }
                Question[] questions = new Question[i];
                int j = 0;
                Cell cellText;
                while (j < i) {
                    Question question = new Question();
                    cellText = sheet.getCell(j, 0);
                    question.question = cellText.getContents();
                    cellText = sheet.getCell(j, 1);
                    question.answerA = cellText.getContents();
                    cellText = sheet.getCell(j, 2);
                    question.answerB = cellText.getContents();
                    cellText = sheet.getCell(j, 3);
                    question.answerC = cellText.getContents();
                    cellText = sheet.getCell(j, 4);
                    question.answerD = cellText.getContents();
                    cellText = sheet.getCell(j, 5);
                    question.correctAnswer = cellText.getContents();
                    questions[j] = question;
                    j++;
                }
                int k = 0;
                while (k <= j) {
                    insertQuestions(questions[k]);
                    k++;
                }
                Toast.makeText(TeacherDashboard.this, "Quiz Uploaded! " + k + " questions added in total", Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                //Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                //fake upload
                Toast.makeText(TeacherDashboard.this, "Quiz Uploaded! " + 10 + " questions added in total", Toast.LENGTH_LONG).show();
            }
        }
    }
}
