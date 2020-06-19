package com.example.pushnotification.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserInfo")
public class UserInfo {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private String Time;
    @NonNull
    private String  ToDo;


    public UserInfo(){
    }

    public UserInfo(@NonNull String time, @NonNull String toDo) {
        Time = time;
        ToDo = toDo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getTime() {
        return Time;
    }

    public void setTime(@NonNull String time) {
        Time = time;
    }

    @NonNull
    public String getToDo() {
        return ToDo;
    }

    public void setToDo(@NonNull String toDo) {
        ToDo = toDo;
    }
}