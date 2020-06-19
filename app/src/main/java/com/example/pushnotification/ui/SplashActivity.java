package com.example.pushnotification.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.PendingIntent;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pushnotification.R;
import com.example.pushnotification.auth.LoginActivity;
import com.example.pushnotification.model.JoinActivity;
import com.example.pushnotification.view.TaskListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {


    private Intent SplashIntent;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        TextView splash_text = (TextView) findViewById(R.id.textView);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        GetStart();
    }


    private void GetStart() {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    Thread.sleep(2000);

                    if (user!= null && user.isEmailVerified()){

                        SplashIntent = new Intent(SplashActivity.this, TaskListActivity.class);
                        startActivity(SplashIntent);
                        finish();
                    }

                    else {

                        SplashIntent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(SplashIntent);
                        finish();

                    }
                }
                catch (Exception e) {

                    Toast.makeText(getApplicationContext(), "The Exception" + e, Toast.LENGTH_LONG).show();
                }
            }
        });

        thread.start();

    }
}
