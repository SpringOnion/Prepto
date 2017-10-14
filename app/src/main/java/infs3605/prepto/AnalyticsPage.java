package infs3605.prepto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class AnalyticsPage extends AppCompatActivity {

    BarChart barchart;
    Result[] results;
    ArrayList<BarEntry> entries;
    ArrayList<String> xAxis;
    int week;
    int length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_analytics);
        week = getIntent().getExtras().getInt("Week");

        barchart = (BarChart) findViewById(R.id.bar_chart);
        length = getQuizCount(week);
        results = new Result[length];
        for (Result r : results) {
            r = new Result();
        }
        results = getResultsByQuiz(week);
        setupInitialChart(results);

    }

    private void setupInitialChart(Result[] results) {

        int lowestQuestion = -72;
        for (int i = 0; i < length; i++) {
            if (results[i].getQuestionID() < lowestQuestion || lowestQuestion == -72) {
                lowestQuestion = results[i].getQuestionID();
            }
        }
        int[] nums = new int[length];
        for (int score : nums) {
            score = 0;
        }

        for (int j = 0; j < length; j++) {
            for (int k = 0; k < length; k++) {
                if (results[k].getQuestionID() == lowestQuestion) {
                    if (results[k].getCorrectAnswer().equals(results[k].getResult())) {
                        nums[j]++;
                    }
                }
            }
            entries.add(new BarEntry(nums[j], j));
            xAxis.add((String) nums[j]))
            lowestQuestion++;
        }
        BarDataSet dataset = new BarDataSet(entries, "Results for quiz " + week);
    }

    private int getQuizCount(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(AnalyticsPage.this).getReadableDatabase();
        String query = "SELECT quiz FROM results WHERE quiz= " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        cursor.close();
        db.close();
        return count;
    }

    private Result[] getResultsByQuiz(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(AnalyticsPage.this).getReadableDatabase();
        String query = "select * from results WHERE quiz=" + quiz;
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();
        int length = cursor.getCount();
        Result[] result = new Result[length];
        for (int i = 0; i < length; i++) {
            result[i].setQuiz(cursor.getInt(cursor.getColumnIndex("quiz")));
            result[i].setQuestionID(cursor.getInt(cursor.getColumnIndex("questionID")));
            result[i].setCorrectAnswer(cursor.getString(cursor.getColumnIndex("correctanswer")));
            result[i].setResult(cursor.getString(cursor.getColumnIndex("result")));
            result[i].setStudent(cursor.getString(cursor.getColumnIndex("student")));
            cursor.moveToNext();
        }
        cursor.close();
        db.close();
        return result;
    }

}
