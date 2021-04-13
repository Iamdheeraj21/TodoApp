package com.example.todoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateTaskActivity extends AppCompatActivity
{
    EditText editText1,editText2;
    Button btn1,btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_task);
        initViews();


        Task task=(Task)getIntent().getSerializableExtra("task");
        loadTask(task);
        btn1.setOnClickListener(v -> {
            UpdateTask(task);
            Toast.makeText(this, "Updated...", Toast.LENGTH_SHORT).show();
        });
        btn2.setOnClickListener(v -> {
            AlertDialog.Builder alertDialog=new AlertDialog.Builder(this);
            alertDialog.setTitle("You want to delete this task?");
            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which)
                {
                    DeleteTask(task);
                    Toast.makeText(UpdateTaskActivity.this, "Task deleted successfully...", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("No",null);
            alertDialog.show();
        });

    }

    private void DeleteTask(Task task)
    {
        class DeleteTask extends AsyncTask<Void,Void,Void>
        {
            @Override
            protected Void doInBackground(Void... voids)
            {
                TaskDatabase.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .DeleteTask(task);
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }
        DeleteTask deleteTask=new DeleteTask();
        deleteTask.execute();
    }

    private void loadTask(Task task)
    {
        editText1.setText(task.getTaskname());
        editText2.setText(task.getTaskdesc());
    }
    private void UpdateTask(Task task)
    {
        final String sTask = editText1.getText().toString().trim();
        final String sDesc = editText2.getText().toString().trim();

        if (sTask.isEmpty()) {
            editText1.setError("Task required");
        }
        if (sDesc.isEmpty()) {
            editText2.setError("Desc required");
        }

        class UpdateTask extends AsyncTask<Void,Void,Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                task.setTaskname(sTask);
                task.setTaskdesc(sDesc);
                TaskDatabase.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .UpdateTask(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateTaskActivity.this, MainActivity.class));
            }
        }
        UpdateTask ut = new UpdateTask();
        ut.execute();
        }
    private void initViews()
    {
        editText1=findViewById(R.id.Update_editText1);
        editText2=findViewById(R.id.Update_editText2);
        btn1=findViewById(R.id.Update);
        btn2=findViewById(R.id.DeleteTask);
    }
}