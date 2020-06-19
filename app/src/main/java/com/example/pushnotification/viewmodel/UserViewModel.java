package com.example.pushnotification.viewmodel;

import android.app.Application;

import com.example.pushnotification.repository.UserRepository;
import com.example.pushnotification.roomdatabase.UserInfo;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class UserViewModel extends AndroidViewModel {

    private UserRepository userRepository;
    private LiveData<List<UserInfo>> listLiveData;


    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<List<UserInfo>> getData(){

        listLiveData =  userRepository.getData();
        return listLiveData;
    }
    public void Insert(UserInfo userInfo){

        userRepository.Insert(userInfo);
    }


}
