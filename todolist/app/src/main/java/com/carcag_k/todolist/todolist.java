package com.carcag_k.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.lang.System.exit;

public class todolist extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);

        taskBDDHandler taskBDD = new taskBDDHandler(this);
        RecyclerView taskListView = (RecyclerView) findViewById(R.id.afflist);
        taskBDD.open();


        //taskBDD.cleanDB();

        this.displayAllTask();
    }

    public void createATask(String name, String description, String date) {
        taskBDDHandler taskBDD = new taskBDDHandler(this);

        taskBDD.open();
        taskCreator task1 = new taskCreator(2, 0, name, description, date);
        taskBDD.insertTask(task1);
    }

    public void addTask(View view) {
        Intent intent = new Intent(this, createNewTask.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String[] result = data.getStringArrayExtra(EXTRA_MESSAGE);
                createATask(result[0], result[1], result[2]);
                displayAllTask();
            } else if (resultCode == Activity.RESULT_CANCELED) {
                //error
            }
        } else {
            exit(22);
        }
    }

    public void editTask(int idtask) {
        Intent intent = new Intent(this, editNewTask.class);
        intent.putExtra(EXTRA_MESSAGE, idtask);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void displayAllTask() {
        taskBDDHandler taskBDD = new taskBDDHandler(this);

        taskBDD.open();

        final ArrayList<taskCreator> taskList = taskBDD.getAllData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.afflist);

        TaskAdapter mAdapter = new TaskAdapter(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        final ArrayList<taskCreator> taskList2 = taskBDD.getAllData();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                taskCreator task = taskList2.get(position);
                editTask(task.getId());
            }
            @Override
            public void onLongClick(View view, int position){

            }

        }));
    }

    public void archivePage(View view) {
        Intent intent = new Intent(this, archivePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}