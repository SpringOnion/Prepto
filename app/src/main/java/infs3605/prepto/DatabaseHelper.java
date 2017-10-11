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
    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASS = "pass";
    private SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null, " + "" +
            "name text not null, email text not null, username text not null, pass text not null)";
    private static final String TABLE_CREATE_QUIZ = "CREATE TABLE QUESTIONS (ID integer primary key autoincrement, " +
            "question text not null, answerA text not null, answerB text not null, " +
            "answerC text not null, answerD text not null, correctanswer text not null, " +
            "quiz integer not null); ";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_QUIZ);
        this.db = db;
    }

    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME , c.getName() );
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_USERNAME , c.getUsername());
        values.put(COLUMN_PASS , c.getPass());

        db.insert(TABLE_NAME , null, values);
        cursor.close();
        db.close();

    }

    public void insertQuestions(Question question) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String query = "SELECT * FROM QUESTIONS";
        String getQuizCount = "SELECT quiz FROM QUESTIONS";
        Cursor quizCursor = db.rawQuery(getQuizCount, null);
        int quizCount;
        if (quizCursor.moveToLast()) {
            quizCount = quizCursor.getInt(0);
        } else {
            quizCount = 1;
        }

        quizCursor.close();

        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        values.put("ID", count);
        values.put("question", question.getQuestion());
        values.put("answera", question.getAnswerA());
        values.put("answerb", question.getAnswerB());
        values.put("answerc", question.getAnswerC());
        values.put("answerd", question.getAnswerD());
        values.put("quiz", quizCount);

        db.insert("QUESTIONS", null, values);
        cursor.close();
        db.close();
    }

    public Question[] getQuestions(int quiz) {
        db = this.getReadableDatabase();
        String query = "SELECT * FROM QUESTIONS WHERE quiz = " + quiz + "; ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();
        Question[] questions = new Question[count];
        int i = 0;
        while (i < count) {
            questions[i].question = cursor.getString(cursor.getColumnIndex("question"));
            questions[i].answerA = cursor.getString(cursor.getColumnIndex("answera"));
            questions[i].answerB = cursor.getString(cursor.getColumnIndex("answerb"));
            questions[i].answerC = cursor.getString(cursor.getColumnIndex("answerc"));
            questions[i].answerD = cursor.getString(cursor.getColumnIndex("anwerd"));
            questions[i].correctAnswer = cursor.getString(cursor.getColumnIndex("correctanswer"));
            questions[i].quiz = cursor.getInt(cursor.getColumnIndex("quiz"));
        }
        return questions;
    }

    public String searchPass(String username) {
        db = this.getReadableDatabase();
        String query = "select username, pass from " + TABLE_NAME + " WHERE username ='" + username + "';";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b = "not found";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(0);
                if (a.equals(username)) {
                    b = cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return b;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}

