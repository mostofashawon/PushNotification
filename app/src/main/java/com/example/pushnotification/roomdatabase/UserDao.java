package com.example.pushnotification.roomdatabase;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void Insert(UserInfo userInfo);

    @Query("SELECT * FROM UserInfo")
    LiveData<List<UserInfo>> getInfo();
}
