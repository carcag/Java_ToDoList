package com.carcag_k.todolist;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import static android.R.attr.id;
import static android.R.id.message;
import static android.provider.AlarmClock.EXTRA_MESSAGE;


/**
 * Created by carcag_k on 29/01/17.
 */

public class editNewTask extends AppCompatActivity{

    public int id, done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_new_task);

        int idOfTask = getIntent().getIntExtra(EXTRA_MESSAGE, 0);
        taskBDDHandler taskBDD = new taskBDDHandler(this);
        taskBDD.open();

        taskCreator task =  taskBDD.getTaskWithid(idOfTask);

        EditText nameEdit = (EditText) findViewById(R.id.editName);
        nameEdit.setText(task.getName());


        EditText descEdit = (EditText) findViewById(R.id.editDescription);
        descEdit.setText(task.getDescription());


        EditText dateEdit = (EditText) findViewById(R.id.editDate);
        dateEdit.setText(task.getDate());

        this.id = task.getId();
        this.done = task.getDone();
        taskBDD.close();
    }
    public void addEditTaskToDB(View view) {
        taskBDDHandler taskBDD = new taskBDDHandler(this);
        taskBDD.open();


        Intent intent = new Intent(this, NewWindow.class);

        taskCreator task = new taskCreator();
        EditText editText = (EditText) findViewById(R.id.editName);
        task.setName(editText.getText().toString());
        editText = (EditText) findViewById(R.id.editDescription);
        task.setDescription(editText.getText().toString());
        editText = (EditText) findViewById(R.id.editDate);
        task.setDate(editText.getText().toString());
        task.setId(this.id);
        task.setDone(this.done);
        taskBDD.updateTask(this.id, task);


        String[] finalString = {null};

        intent.putExtra(EXTRA_MESSAGE, finalString);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();
    }

    public void deleteTask(View view) {
        taskBDDHandler taskBDDHandler = new taskBDDHandler(this);
        taskBDDHandler.open();

        taskBDDHandler.removeTaskWithID(this.id);


        String[] finalString = {null};

        Intent intent = new Intent(this, NewWindow.class);
        intent.putExtra(EXTRA_MESSAGE, finalString);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        taskBDDHandler.close();

        finish();
    }

    public void archiveTask(View view) {
        taskBDDHandler taskBDD = new taskBDDHandler(this);
        taskBDD.open();


        Intent intent = new Intent(this, NewWindow.class);

        taskCreator task = new taskCreator();
        EditText editText = (EditText) findViewById(R.id.editName);
        task.setName(editText.getText().toString());
        editText = (EditText) findViewById(R.id.editDescription);
        task.setDescription(editText.getText().toString());
        editText = (EditText) findViewById(R.id.editDate);
        task.setDate(editText.getText().toString());
        task.setId(this.id);
        task.setDone(1);
        taskBDD.updateTask(this.id, task);


        String[] finalString = {null};

        intent.putExtra(EXTRA_MESSAGE, finalString);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        finish();

    }

}
