package com.carcag_k.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * Created by carcag_k on 30/01/17.
 */

public class archivePage extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archive_list);

        taskBDDHandler taskBDD = new taskBDDHandler(this);
        RecyclerView taskListView = (RecyclerView) findViewById(R.id.afflist);
        taskBDD.open();


        //taskBDD.cleanDB();

        this.displayAllArchiveTask();
    }

    protected void displayAllArchiveTask() {
        taskBDDHandler taskBDD = new taskBDDHandler(this);

        taskBDD.open();

        final ArrayList<taskCreator> taskList = taskBDD.getAllArchivedData();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.afflist);

        TaskAdapter mAdapter = new TaskAdapter(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        final ArrayList<taskCreator> taskList2 = taskBDD.getAllArchivedData();
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(),
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                taskCreator task = taskList2.get(position);
                //editTask(task.getId());
            }
            @Override
            public void onLongClick(View view, int position){

            }

        }));
    }

    public void mainPage(View view) {
        taskBDDHandler taskBDDHandler = new taskBDDHandler(this);
        taskBDDHandler.open();


        String[] finalString = {null};

        Intent intent = new Intent(this, NewWindow.class);
        intent.putExtra(EXTRA_MESSAGE, finalString);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

        taskBDDHandler.close();
        finish();

    }
}