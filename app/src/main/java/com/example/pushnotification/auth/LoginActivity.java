package com.example.pushnotification.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pushnotification.R;
import com.example.pushnotification.model.JoinActivity;
import com.example.pushnotification.view.TaskListActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Email,Pass;
    private Button Login;
    private TextView ForSignUp;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        Email =(EditText) findViewById(R.id.loginEmail);
        Pass =(EditText) findViewById(R.id.LoginPass);
        Login =(Button) findViewById(R.id.button);
        ForSignUp =(TextView) findViewById(R.id.textView3);
        progressBar = findViewById(R.id.progressBar);

        Login.setOnClickListener(this);
        ForSignUp.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        String first = Email.getText().toString().trim();
        String second = Pass.getText().toString().trim();


        if (v.getId() == R.id.button){

           doValidation(first,second);
           doSignUp(first,second);
        }

        if (v.getId() == R.id.textView3){

            Intent newIntent = new Intent(LoginActivity.this,SignUpActivity.class);
            startActivity(newIntent);

        }

    }

    private void doValidation(String first,String second){


        if(first.isEmpty() && second.isEmpty()){

            Email.setError("Enter Mail");
            Email.requestFocus();

            Pass.setError("Enter PassWord");
            Pass.requestFocus();

        }

        if (first.isEmpty()){
            Email.setError("Enter Mail");
            Email.requestFocus();
        }
        if (second.isEmpty()){

            Pass.setError("Enter PassWord");
            Pass.requestFocus();
        }

    }

    private void doSignUp(String email,String password){

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            if (mAuth.getCurrentUser().isEmailVerified()){

                                Intent LoginIntent = new Intent(LoginActivity.this, JoinActivity.class);
                                startActivity(LoginIntent);
                            }

                        } else {

                            Toast.makeText(LoginActivity.this,"Please verify Emaill",Toast.LENGTH_LONG).show();

                        }

                    }});

    }

}
