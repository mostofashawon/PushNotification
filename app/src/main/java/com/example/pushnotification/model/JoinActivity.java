package com.example.pushnotification.model;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pushnotification.R;
import com.example.pushnotification.roomdatabase.UserInfo;
import com.example.pushnotification.view.TaskListActivity;
import com.example.pushnotification.viewmodel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class JoinActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText date,task;
    private Button join,show;
    private Spinner spinner;
    private Intent joinIntent;
    private UserViewModel userViewModel;
    private String SubscriptionTopic;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        databaseReference = FirebaseDatabase.getInstance().getReference("User");

        date =(EditText) findViewById(R.id.editText3);
        task =(EditText) findViewById(R.id.editText4);
        join =(Button) findViewById(R.id.button3);
        show = findViewById(R.id.button4);
        spinner =  findViewById(R.id.spinner2);

        SubscriptionTopic = spinner.getSelectedItem().toString();

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        join.setOnClickListener(this);
        show.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        String first = date.getText().toString().trim();
        String second =  task.getText().toString().trim();
        SubscriptionTopic = spinner.getSelectedItem().toString();

        if (v.getId() == R.id.button3){

            doValidation(first,second);
            storeData(first,second,SubscriptionTopic);
            saveData(first,second);
            Toast.makeText(JoinActivity.this,"Task Added",Toast.LENGTH_LONG).show();
        }

        if (v.getId()==R.id.button4){
            joinIntent = new Intent(JoinActivity.this, TaskListActivity.class);
            startActivity(joinIntent);
        }

    }

    private void doValidation(String first,String second){

        if(first.isEmpty() && second.isEmpty()){

            date.setError("Enter Name");
            date.requestFocus();

            task.setError("Enter PassWord");
            task.requestFocus();

        }

        if (first.isEmpty()){
            date.setError("Enter Name");
            date.requestFocus();
        }
        if (second.isEmpty()){

            task.setError("Enter PassWord");
            task.requestFocus();
        }

    }


    public void saveData(String date,String task){
        UserInfo userInfo = new UserInfo(date,task);
        userViewModel.Insert(userInfo);
    }


    public void storeData(String date,String task,String time){

        DataSet dataSet = new DataSet(date,task,time);

        String key = databaseReference.push().getKey();

        databaseReference.child(key).setValue(dataSet);


    }


}
