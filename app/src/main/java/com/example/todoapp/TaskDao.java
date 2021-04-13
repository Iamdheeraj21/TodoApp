package com.example.todoapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao
{
    @Query("SELECT * FROM MyTodo")
    List<Task> getAll();

    @Insert
    void insertTask(Task task);

    @Update
    void UpdateTask(Task task);

    @Delete
    void DeleteTask(Task task);
}
