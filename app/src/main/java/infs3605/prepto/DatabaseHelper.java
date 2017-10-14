package infs3605.prepto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amarkashyap on 17/09/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASS = "pass";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, " + "" +
            "name text not null, email text not null, username text not null, pass text not null)";
    private static final String TABLE_CREATE_QUIZ = "CREATE TABLE QUESTIONS (ID integer primary key AUTOINCREMENT not null, " +
            "question text not null, answerA text not null, answerB text not null, " +
            "answerC text not null, answerD text not null, correctanswer text not null, " +
            "quiz integer not null); ";
    private static final String TABLE_CREATE_RESULTS = "create table results (ID integer primary key AUTOINCREMENT not null, " +
            "questionID integer, quiz integer not null, correctanswer text not null, " +
            "result text not null, student text not null); ";

    private static DatabaseHelper instance;
    private SQLiteDatabase db;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_QUIZ);
        db.execSQL(TABLE_CREATE_RESULTS);
        this.db = db;
        addBaseContacts();
        addBaseQuestions();
        addBaseResults();
    }

    public void addBaseContacts() {
        ContentValues values = new ContentValues();
        values.put("id", "1");
        values.put("name", "Michael Cahallane");
        values.put("email", "z1111111@ad.unsw.edu.au");
        values.put("username", "z1111111");
        values.put("pass", "teacher1");
        db.insert("contacts", null, values);
        ContentValues second = new ContentValues();
        second.put("id", "2");
        second.put("name", "Vineet Suri");
        second.put("email", "z1234567@ad.unsw.edu.au");
        second.put("username", "z1234567");
        second.put("pass", "student1");
        db.insert("contacts", null, second);
    }

    public void addBaseResults() {
        Result[] results = new Result[100];
        for (Result r : results) {
            r = new Result();
            r.setQuiz(1);
            r.setStudent("z1234567");
        }
        int check = 0;
        String[] choices = new String[]{"A", "B", "C", "D"};
        int choiceCheck = 0;
        for (int i = 0; i < 100; i++) {
            results[i].setQuestionID((i % 10) + 1);
            results[i].setCorrectAnswer(choices[choiceCheck]);
            if (choiceCheck >= 3) {
                choiceCheck = 0;
            } else {
                choiceCheck++;
            }
            switch (check % 10) {
                case 0:
                case 4:
                case 8:
                    results[i].setResult("A");
                    break;
                case 1:
                case 5:
                case 9:
                    results[i].setResult("B");
                    break;
                case 2:
                case 6:
                    results[i].setResult("C");
                    break;
                case 3:
                case 7:
                    results[i].setResult("D");
                    break;
                default:
                    results[i].setResult("A");
                    break;
            }
            check++;
        }
    }

    public void addBaseQuestions() {
        Question[] questions = new Question[10];
        for (int i = 0; i < 10; i++) {
            questions[i] = new Question();
            questions[i].setId(i);
        }

        questions[0].setQuestion("");
        questions[1].setQuestion("");
        questions[2].setQuestion("");
        questions[3].setQuestion("");
        questions[4].setQuestion("");
        questions[5].setQuestion("");
        questions[6].setQuestion("");
        questions[7].setQuestion("");
        questions[8].setQuestion("");
        questions[9].setQuestion("");


        questions[0].setAnswerA("");
        questions[1].setAnswerA("");
        questions[2].setAnswerA("");
        questions[3].setAnswerA("");
        questions[4].setAnswerA("");
        questions[5].setAnswerA("");
        questions[6].setAnswerA("");
        questions[7].setAnswerA("");
        questions[8].setAnswerA("");
        questions[9].setAnswerA("");

        questions[0].setAnswerB("");
        questions[1].setAnswerB("");
        questions[2].setAnswerB("");
        questions[3].setAnswerB("");
        questions[4].setAnswerB("");
        questions[5].setAnswerB("");
        questions[6].setAnswerB("");
        questions[7].setAnswerB("");
        questions[8].setAnswerB("");
        questions[9].setAnswerB("");

        questions[0].setAnswerC("");
        questions[1].setAnswerC("");
        questions[2].setAnswerC("");
        questions[3].setAnswerC("");
        questions[4].setAnswerC("");
        questions[5].setAnswerC("");
        questions[6].setAnswerC("");
        questions[7].setAnswerC("");
        questions[8].setAnswerC("");
        questions[9].setAnswerC("");

        questions[0].setAnswerD("");
        questions[1].setAnswerD("");
        questions[2].setAnswerD("");
        questions[3].setAnswerD("");
        questions[4].setAnswerD("");
        questions[5].setAnswerD("");
        questions[6].setAnswerD("");
        questions[7].setAnswerD("");
        questions[8].setAnswerD("");
        questions[9].setAnswerD("");

        questions[0].setCorrectAnswer("");
        questions[1].setCorrectAnswer("");
        questions[2].setCorrectAnswer("");
        questions[3].setCorrectAnswer("");
        questions[4].setCorrectAnswer("");
        questions[5].setCorrectAnswer("");
        questions[6].setCorrectAnswer("");
        questions[7].setCorrectAnswer("");
        questions[8].setCorrectAnswer("");
        questions[9].setCorrectAnswer("");

        questions[0].setQuiz(2);
        questions[1].setQuiz(2);
        questions[2].setQuiz(2);
        questions[3].setQuiz(2);
        questions[4].setQuiz(2);
        questions[5].setQuiz(2);
        questions[6].setQuiz(2);
        questions[7].setQuiz(2);
        questions[8].setQuiz(2);
        questions[9].setQuiz(2);

        for (int j = 0; j < 10; j++) {
            ContentValues values = new ContentValues();
            String query = "SELECT * FROM QUESTIONS";

            Cursor cursor = db.rawQuery(query, null);
            int count = cursor.getCount();
            values.put("ID", count);
            values.put("question", questions[j].getQuestion());
            values.put("answera", questions[j].getAnswerA());
            values.put("answerb", questions[j].getAnswerB());
            values.put("answerc", questions[j].getAnswerC());
            values.put("answerd", questions[j].getAnswerD());
            values.put("correctanswer", questions[j].getCorrectAnswer());
            values.put("quiz", questions[j].getQuiz());

            db.insert("QUESTIONS", null, values);
            cursor.close();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}

