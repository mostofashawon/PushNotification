package com.example.pushnotification.repository;

import android.app.Application;
import android.os.AsyncTask;

import com.example.pushnotification.roomdatabase.UserDao;
import com.example.pushnotification.roomdatabase.UserInfo;
import com.example.pushnotification.roomdatabase.UserRoomDatabase;

import java.util.List;

import androidx.lifecycle.LiveData;

public class UserRepository {

    private UserDao userDao;

    private LiveData<List<UserInfo>> listLiveData;

   public UserRepository (Application application){

       UserRoomDatabase userRoomDatabase = UserRoomDatabase.getDatabase(application);

       userDao = userRoomDatabase.userDao();

       listLiveData = userDao.getInfo();

   }


    public void Insert(UserInfo userInfo){

        new InsertAysntask(userDao).execute(userInfo);
    }

    public LiveData<List<UserInfo>> getData(){

        return listLiveData;
    }


    public class InsertAysntask extends AsyncTask<UserInfo,Void,Void> {


        private UserDao userDao;

        public InsertAysntask(UserDao userDao){

            this.userDao = userDao;
        }


        @Override
        protected Void doInBackground(UserInfo... userInfo) {

            userDao.Insert(userInfo[0]);
            return null;
        }
    }


}
