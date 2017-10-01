package infs3605.prepto;

import android.content.Context;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.Sampler;

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
    private static final String TABLE_CREATE = "create table contacts (id integer primary key not null auto increment, " + "" +
            "name text not null, email text not null, username text not null, pass text not null)";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME , c.getName() );
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_USERNAME , c.getUsername());
        values.put(COLUMN_PASS , c.getPass());

        db.insert(TABLE_NAME , null, values);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}

