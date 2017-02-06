package com.carcag_k.todolist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by carcag_k on 21/01/17.
 */

public class DBCreator extends SQLiteOpenHelper{
    private static final String TABLE_TASK = "table_task";
    private static final String COL_ID = "ID";
    private static final String COL_DONE = "Done";
    private static final String COL_NAME = "Name";
    private static final String COL_DESCRIPTION= "Description";
    private static final String COL_DATE = "Date";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_TASK + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_DONE + " INTEGER, "
            + COL_NAME + " TEXT NOT NULL," + COL_DESCRIPTION   + " TEXT NOT NULL,"
            + COL_DATE + " TEXT NOT NULL);";

    public DBCreator(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
        db.execSQL("INSERT INTO table_task (ID, Done, Name, Description, Date)" +
                "VALUES (0, 0, 'TestTask', 'description of the TestTask', '0000');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_TASK + ";");
        onCreate(db);
    }

    /*public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from table_task;", null );
        return res;
    }*/
}