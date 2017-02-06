package com.carcag_k.todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.List;

import static android.provider.AlarmClock.EXTRA_MESSAGE;
import static java.lang.System.exit;

/**
 * Created by carcag_k on 22/01/17.
 */

public class createNewTask extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_new_task);

        Intent intent = getIntent();
    }

    public void addTaskToDB(View view) {
        Intent intent = new Intent(this, NewWindow.class);
        List<String> InfoList = new LinkedList<String>();
        EditText editText = (EditText) findViewById(R.id.editName);
        InfoList.add(editText.getText().toString());
        editText = (EditText) findViewById(R.id.editDescription);
        InfoList.add(editText.getText().toString());
        editText = (EditText) findViewById(R.id.editDate);
        InfoList.add(editText.getText().toString());
        String[] finalString = new String[InfoList.size()];
        finalString = InfoList.toArray(finalString);
        intent.putExtra(EXTRA_MESSAGE, finalString);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //setResult(Activity.RESULT_OK, intent);
        finish();
    }
}