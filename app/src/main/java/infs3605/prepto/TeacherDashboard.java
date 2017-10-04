package infs3605.prepto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class TeacherDashboard extends AppCompatActivity {

    private Button buttonFile;
    private File excelBook = new File("assets/PreptoQuestions");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_dashboard);

        buttonFile = (Button) findViewById(R.id.button_file);
        buttonFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Workbook workbook = Workbook.getWorkbook(excelBook);
                    Sheet sheet = workbook.getSheet(1);
                    Cell questionCell = sheet.getCell(2, 1);
                    String question = questionCell.getContents();
                    Toast.makeText(TeacherDashboard.this, question, Toast.LENGTH_LONG).show();
                } catch (Exception ex) {
                    Toast.makeText(TeacherDashboard.this, "Error reading the workbook", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
