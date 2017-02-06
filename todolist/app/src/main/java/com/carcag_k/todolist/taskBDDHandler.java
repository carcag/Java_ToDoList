package com.carcag_k.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by carcag_k on 21/01/17.
 */

public class taskBDDHandler {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "task.db";


    private static final String TABLE_TASK = "table_task";

    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;

    private static final String COL_DONE = "Done";
    private static final int NUM_COL_DONE = 1;

    private static final String COL_NAME = "Name";
    private static final int NUM_COL_NAME = 2;

    private static final String COL_DESCRIPTION= "Description";
    private static final int NUM_COL_DESCRIPTION= 3;

    private static final String COL_DATE = "Date";
    private static final int NUM_COL_DATE = 4;

    private SQLiteDatabase bdd;

    private DBCreator dbCreator;

    public taskBDDHandler(Context context) {
        dbCreator = new DBCreator(context, NOM_BDD, null, VERSION_BDD);
    }

    public void cleanDB(){
        dbCreator.onUpgrade(bdd, 1, 1);
    }

    public void open() {
        bdd = dbCreator.getWritableDatabase();
    }

    public void close() {
        bdd.close();
    }

    public SQLiteDatabase getBDD() {
        return (bdd);
    }

    public long insertTask(taskCreator task) {
        ContentValues values = new ContentValues();

        values.put(COL_DONE, task.getDone());
        values.put(COL_NAME, task.getName());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_DATE, task.getDate());

        return bdd.insert(TABLE_TASK, null, values);
    }

    public int updateTask(int id, taskCreator task) {
        ContentValues values = new ContentValues();
        values.put(COL_DONE, task.getDone());
        values.put(COL_NAME, task.getName());
        values.put(COL_DESCRIPTION, task.getDescription());
        values.put(COL_DATE, task.getDate());

        return bdd.update(TABLE_TASK, values, COL_ID + " = " +id, null);
    }

    private taskCreator cursorToTask(Cursor c) {
        if (c.getCount() == 0)
            return (null);

        c.moveToFirst();

        taskCreator task = new taskCreator();

        task.setId(c.getInt(NUM_COL_ID));
        task.setDone(c.getInt(NUM_COL_DONE));
        task.setName(c.getString(NUM_COL_NAME));
        task.setDescription(c.getString(NUM_COL_DESCRIPTION));
        task.setDate(c.getString(NUM_COL_DATE));

        c.close();

        return (task);
    }

    public int removeTaskWithID(int id) {
        return bdd.delete(TABLE_TASK, COL_ID + " = " +id, null);
    }

    public taskCreator getTaskWithid (int id) {

        Cursor c = bdd.query(TABLE_TASK, new String[] {COL_ID, COL_DONE,
                COL_NAME, COL_DESCRIPTION, COL_DATE}, COL_ID + " LIKE \"" + id + "\"",
                null, null, null, null);
        return (cursorToTask(c));
    }

    public ArrayList<taskCreator> getAllData() {
        Cursor c = bdd.query(TABLE_TASK, new String[] {COL_ID, COL_DONE,
                COL_NAME, COL_DESCRIPTION, COL_DATE}, COL_DONE + " LIKE \'0\'",
                null, null, null, null);

        if (c.getCount() == 0)
            return (null);

        c.moveToFirst();
        ArrayList<taskCreator> taskList = new ArrayList<>();

        while (c.moveToNext()) {
            taskCreator task = new taskCreator();

            task.setId(c.getInt(NUM_COL_ID));
            task.setDone(c.getInt(NUM_COL_DONE));
            task.setName(c.getString(NUM_COL_NAME));
            task.setDescription(c.getString(NUM_COL_DESCRIPTION));
            task.setDate(c.getString(NUM_COL_DATE));

            taskList.add(task);
        }
        c.close();
        return (taskList);
    }

    public ArrayList<taskCreator> getAllArchivedData() {
        Cursor c = bdd.query(TABLE_TASK, new String[] {COL_ID, COL_DONE,
                        COL_NAME, COL_DESCRIPTION, COL_DATE}, COL_DONE + " LIKE \'1\'",
                null, null, null, null);

        if (c.getCount() == 0)
            return (null);

        c.moveToFirst();
        ArrayList<taskCreator> taskList = new ArrayList<>();

        while (c.moveToNext()) {
            taskCreator task = new taskCreator();

            task.setId(c.getInt(NUM_COL_ID));
            task.setDone(c.getInt(NUM_COL_DONE));
            task.setName(c.getString(NUM_COL_NAME));
            task.setDescription(c.getString(NUM_COL_DESCRIPTION));
            task.setDate(c.getString(NUM_COL_DATE));

            taskList.add(task);
        }
        c.close();
        return (taskList);
    }
}