package infs3605.prepto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ipaulpro.afilechooser.utils.FileUtils;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TeacherDashboard extends AppCompatActivity {

    private Button buttonFile;
    private File excelBook;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        buttonFile = (Button) findViewById(R.id.button_file);
        buttonFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getContentIntent = FileUtils.createGetContentIntent();
                Intent intent = Intent.createChooser(getContentIntent, "Select a File");
                startActivityForResult(intent, 1234);
            }
        });
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
                    dbHelper.insertQuestions(questions[k]);
                    k++;
                }
                Toast.makeText(TeacherDashboard.this, "Quiz Uploaded! " + k + " questions added in total", Toast.LENGTH_LONG).show();

            } catch (Exception ex) {
                Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
