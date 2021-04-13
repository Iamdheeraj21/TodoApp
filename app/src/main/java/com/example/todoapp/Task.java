package com.example.todoapp;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "MyTodo")
public class Task implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name="taskname")
    private String taskname;

    @ColumnInfo(name="taskdesc")
    private String taskdesc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskname() {
        return taskname;
    }

    public void setTaskname(String taskname) {
        this.taskname = taskname;
    }

    public String getTaskdesc() {
        return taskdesc;
    }

    public void setTaskdesc(String taskdesc) {
        this.taskdesc = taskdesc;
    }
}
