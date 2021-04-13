package com.example.todoapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Task.class,exportSchema = false,version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();
}
