package infs3605.prepto;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by amarkashyap on 8/09/2017.
 */

public class DBHandler extends SQLiteOpenHelper {

//Database Version
private static final int DATABASE_VERSION = 1;
// Database Name
private static final String DATABASE_NAME = "PreptoDB";
// Table Name
private static final String TABLE_LOGIN_DETAIL = "loginDetails";
//Login Details Table Column Names
private static final String USERNAME = "id";
private static final String PASSWORD = "password";


public DBHandler(Context context) {super(context, DATABASE_NAME, null, DATABASE_VERSION);}

//creating tables
    @Override
public void onCreate(SQLiteDatabase db) {
String CREATE_LOGIN_DETAILS_TABLE = "CREATE TABLE " + TABLE_LOGIN_DETAIL + "("
+ USERNAME + " STRING PRIMARY KEY,"
+ PASSWORD + " TEST," + ")";

        db.execSQL(CREATE_LOGIN_DETAILS_TABLE);
    }

    @Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN_DETAIL);

        //Create tables again
        onCreate(db); }}
