package com.example.android.mvvmexample;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    //deletes all notes
    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    //gets all notes from table by priority
    @Query("SELECT * FROM note_table ORDER BY priority_column DESC")
    LiveData<List<Note>> getAllNotes();



}
