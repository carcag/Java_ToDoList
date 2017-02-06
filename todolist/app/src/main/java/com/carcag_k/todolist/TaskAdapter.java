package com.carcag_k.todolist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by carcag_k on 25/01/17.
 */

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {
    private List<taskCreator> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, desc;
        public String id;
        public int done;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
            desc = (TextView) view.findViewById(R.id.desc);
        }
    }

    public TaskAdapter (List<taskCreator> taskList) {
        this.taskList = taskList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        taskCreator task = taskList.get(position);
        holder.name.setText(task.getName());
        holder.date.setText(task.getDate());
        holder.desc.setText(task.getDescription());
        holder.id  = String.valueOf(task.getId());
        holder.done = task.getDone();
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}