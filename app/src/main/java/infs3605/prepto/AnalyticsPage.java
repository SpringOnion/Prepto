package infs3605.prepto;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

public class AnalyticsPage extends AppCompatActivity implements IAxisValueFormatter {

    BarChart barchart;
    Result[] results;
    List<BarEntry> entries;
    ArrayList<String> xAxisValues;
    BarDataSet dataset;
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
        entries = new ArrayList<>();
        xAxisValues = new ArrayList<>();
        for (Result r : results) {
            r = new Result();
        }
        results = getResultsByQuiz(week);
        setupInitialChart(results);

        dataset = new BarDataSet(entries, "Results from Week " + week);
        BarData data = new BarData(dataset);
        barchart.setData(data);
        barchart.setContentDescription("Results for the quiz in week " + week + "showing aggregate scores.");
        Description desc = new Description();
        desc.setText("Results for the quiz in week " + week + "showing aggregate scores across the different questions. ");
        desc.setTextSize(16);
        barchart.setDescription(desc);
        XAxis xaxis = barchart.getXAxis();
        YAxis yaxis = barchart.getAxis(YAxis.AxisDependency.RIGHT);
        YAxis leftaxis = barchart.getAxis(YAxis.AxisDependency.LEFT);
        yaxis.setAxisMinimum(0);
        leftaxis.setAxisMinimum(0);
        yaxis.setDrawGridLines(false);
        xaxis.setDrawGridLines(false);
        yaxis.setValueFormatter(this);
        xaxis.setValueFormatter(this);
        leftaxis.setValueFormatter(this);


        barchart.invalidate();
    }

    private void setupInitialChart(Result[] results) {
        int lowestQuestion = -72;
        for (int i = 0; i < length; i++) {
            if (results[i].getQuestionID() < lowestQuestion || lowestQuestion == -72) {
                lowestQuestion = results[i].getQuestionID();
            }
        }
        int[] nums = new int[10];
        for (int score : nums) {
            score = 0;
        }

        for (int j = 0; j < 10; j++) {
            for (int k = 0; k < 100; k++) {
                if (results[k].getQuestionID() == lowestQuestion) {
                    if (results[k].getCorrectAnswer().equals(results[k].getResult())) {
                        nums[j]++;
                    }
                }
            }
            BarEntry bar = new BarEntry((float) j, (float) nums[j]);
            entries.add(bar);
            xAxisValues.add(Integer.toString(nums[j] + 1));
            lowestQuestion++;
        }

    }

    private int getQuizCount(int quiz) {
        SQLiteDatabase db = DatabaseHelper.getInstance(AnalyticsPage.this).getReadableDatabase();
        String query = "SELECT questionID FROM results WHERE quiz= " + quiz + "; ";
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
            result[i] = new Result();
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

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return "" + (int) value;
    }
}
