package com.example.mynotes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLDBHelper extends SQLiteOpenHelper
{
    // Creating a table name.
    public static final String TABLE_NAME = "NOTES";

    // Creating the columns from the table.
    public static final String _ID = "_id";
    public static final String TITLE = "subject";
    public static final String CONTENT = "content";

    // Creating the Database information
    static final String DB_NAME = "MY_NOTES_APP.DB";

    // Setting the Database Version.
    static final int DB_VERSION = 1;

    // Creating the Table Query.
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TITLE + " TEXT NOT NULL, " + CONTENT + " TEXT);";

    // Creating the constructor for the Database helper
    public SQLDBHelper(Context context)
    {
        super(context, DB_NAME,null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Executing the Query.
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
