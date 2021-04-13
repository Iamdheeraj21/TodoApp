package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTaskActivity extends AppCompatActivity
{
    EditText editText1,editText2;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        initviews();

        if(editText1.getText().toString().equals("")){
            editText1.setError("Fill the blank");
        }else if(editText2.getText().toString().equals("")){
            editText1.setError("Fill the blank");
        }

        btn1.setOnClickListener(v -> {
            addTheTask();
        });

    }

    private void addTheTask()
    {
        final String sTask = editText1.getText().toString().trim();
        final String sDesc = editText2.getText().toString().trim();

        if (sTask.isEmpty()) {
            editText1.setError("Task required");
            editText1.requestFocus();
        }
        if (sDesc.isEmpty()) {
            editText2.setError("Desc required");
            editText2.requestFocus();
        }

        class SaveTask extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Task task = new Task();
                task.setTaskname(sTask);
                task.setTaskdesc(sDesc);


                //adding to database
                TaskDatabase.getInstance(getApplicationContext()).getAppDatabase()
                        .taskDao()
                        .insertTask(task);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }

        SaveTask st = new SaveTask();
        st.execute();
    }

    private void initviews()
    {
        editText1=findViewById(R.id.editTextTextPersonName);
        editText2=findViewById(R.id.editTextTextPersonName2);
        btn1=findViewById(R.id.button);
    }
}