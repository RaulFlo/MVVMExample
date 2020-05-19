package com.example.android.mvvmexample;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
class Note {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String title;

    private final String description;

    //to change the name in the table
    @ColumnInfo(name = "priority_column")
    private final int priority;


    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    String getDescription() {
        return description;
    }

    int getPriority() {
        return priority;
    }
}
