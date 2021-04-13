package com.example.todoapp;


import android.content.Context;

import androidx.room.Room;

public class TaskDatabase
{
    private static final String Database_name="My_TodoList";
    private static TaskDatabase instance;
    Context context;

    private final AppDatabase appDatabase;

    private TaskDatabase(Context mCtx) {
        this.context = mCtx;

        //creating the app database with Room database builder
        //MyToDos is the name of the database
        appDatabase = Room.databaseBuilder(mCtx, AppDatabase.class, Database_name).build();
    }

    public static synchronized TaskDatabase getInstance(Context mCtx) {
        if (instance == null) {
            instance = new TaskDatabase(mCtx);
        }
        return instance;
    }

    public AppDatabase getAppDatabase() {
        return appDatabase;
    }
}
