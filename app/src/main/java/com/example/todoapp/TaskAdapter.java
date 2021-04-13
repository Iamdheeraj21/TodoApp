package com.example.todoapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TasksViewHolder> {
    private Context mCtx;
    private List<Task> taskList;

    public TasksAdapter(Context mCtx, List<Task> taskList) {
        this.mCtx = mCtx;
        this.taskList = taskList;
    }

    @Override
    public TasksViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.task_list, parent, false);
        return new TasksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TasksViewHolder holder, int position) {
        Task t = taskList.get(position);
        holder.textViewTask.setText(t.getTaskname());
        holder.textViewDesc.setText(t.getTaskdesc());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    class TasksViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewStatus, textViewTask, textViewDesc, textViewFinishBy;

        public TasksViewHolder(View itemView) {
            super(itemView);

            textViewTask = itemView.findViewById(R.id.textView1);
            textViewDesc = itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            Intent intent=new Intent(mCtx,UpdateTaskActivity.class);
            Task task= taskList.get(getAdapterPosition());
            intent.putExtra("task",task);
            mCtx.startActivity(intent);
        }
    }
}
