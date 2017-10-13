package infs3605.prepto;

import android.content.Context;
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
    private static final String TABLE_CREATE_QUIZ = "CREATE TABLE QUESTIONS (ID integer primary key autoincrement, " +
            "question text not null, answerA text not null, answerB text not null, " +
            "answerC text not null, answerD text not null, correctanswer text not null, " +
            "quiz integer not null); ";
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
        this.db = db;
        LoginPage lg = new LoginPage();
        lg.addBaseContacts();
        lg.addBaseQuestions();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}

