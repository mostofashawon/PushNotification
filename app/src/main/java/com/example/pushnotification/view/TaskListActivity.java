package com.example.pushnotification.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.pushnotification.R;
import com.example.pushnotification.adapter.Adapter;
import com.example.pushnotification.auth.LoginActivity;
import com.example.pushnotification.model.JoinActivity;
import com.example.pushnotification.roomdatabase.UserInfo;
import com.example.pushnotification.viewmodel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private Adapter adapter;
    private UserViewModel userViewModel;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        mAuth = FirebaseAuth.getInstance();

        recyclerView = findViewById(R.id.RecyclerViewId);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getData().observe(this, new Observer<List<UserInfo>>() {
            @Override
            public void onChanged(List<UserInfo> userInfo) {
                if (!userInfo.isEmpty()){

                    adapter = new Adapter(TaskListActivity.this,userInfo);
                    adapter.notifyDataSetChanged();
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.option,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.signoutid){

            mAuth.signOut();
            Intent newIntent = new Intent(TaskListActivity.this, LoginActivity.class);
            startActivity(newIntent);
            finish();

        }

        if (item.getItemId() == R.id.Add){

            Intent anotherIntent = new Intent(TaskListActivity.this, JoinActivity.class);
            startActivity(anotherIntent);
        }

        return super.onOptionsItemSelected(item);
    }
}
