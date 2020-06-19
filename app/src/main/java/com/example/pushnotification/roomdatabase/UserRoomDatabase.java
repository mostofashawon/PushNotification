package com.example.pushnotification.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserInfo.class},version = 1,exportSchema = false)
public abstract class UserRoomDatabase extends RoomDatabase {

    public static UserRoomDatabase instance;

    public abstract UserDao userDao();

    public static synchronized UserRoomDatabase getDatabase(final Context context){

        if (instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),UserRoomDatabase.class,"UserInfo_Database")
                    .fallbackToDestructiveMigration()
                    .build();

        }
        return instance;

    }


}
