package com.example.mynotes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager
{
    private SQLDBHelper sqldbHelper;
    private Context context;
    private SQLiteDatabase database;

    // Class constructor.
    public DBManager(Context contxt)
    {
        context = contxt;
    }

    // Method to open database.
    public DBManager open() throws SQLException
    {
        sqldbHelper = new SQLDBHelper(context);
        database = sqldbHelper.getWritableDatabase();
        return this;
    }

    // Method to close database.
    public void close()
    {
        sqldbHelper.close();
    }

    // Method to insert entry to database.
    public void insert(String title, String content)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLDBHelper.TITLE, title);
        contentValues.put(SQLDBHelper.CONTENT, content);
        database.insert(SQLDBHelper.TABLE_NAME,null, contentValues);
    }

    //  Method to get data from database.
    public Cursor fetch()
    {
        String[] columns = new String[] {SQLDBHelper._ID, SQLDBHelper.TITLE, SQLDBHelper.CONTENT};
        Cursor cursor = database.query(SQLDBHelper.TABLE_NAME, columns, null, null, null, null, null);

        if(cursor != null)
        {
            cursor.moveToFirst();
        }
        return  cursor;
    }

    // Method to update existing entry in database.
    public int update(long _id, String title, String content)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(SQLDBHelper.TITLE, title);
        contentValues.put(SQLDBHelper.CONTENT, content);

        int i = database.update(SQLDBHelper.TABLE_NAME, contentValues, SQLDBHelper._ID + " = " + _id, null);

        return  i;
    }

    // Method to delete existing entry in database.
    public void delete(long _id)
    {
        database.delete(SQLDBHelper.TABLE_NAME, SQLDBHelper._ID + " = " + _id, null);
    }
}
