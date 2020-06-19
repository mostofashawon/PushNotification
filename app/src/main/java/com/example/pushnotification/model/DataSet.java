package com.example.pushnotification.model;

public class DataSet {

    private String Time;
    private String Todo;
    private String notificationTime;

    private DataSet(){

    }

    public DataSet(String time, String todo,String notification) {
        Time = time;
        Todo = todo;
        notificationTime = notification;
    }

    public String getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(String notificationTime) {
        this.notificationTime = notificationTime;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTodo() {
        return Todo;
    }

    public void setTodo(String todo) {
        Todo = todo;
    }
}
