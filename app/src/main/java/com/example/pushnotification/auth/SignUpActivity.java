package com.example.pushnotification.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pushnotification.R;
import com.example.pushnotification.model.JoinActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Email,Pass;
    private Button SignUp;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        Email =(EditText) findViewById(R.id.editText);
        Pass =(EditText) findViewById(R.id.editText2);
        SignUp =(Button) findViewById(R.id.button2);
        SignUp.setOnClickListener(this);
        progressBar = findViewById(R.id.progressBar2);
    }

    @Override
    public void onClick(View v) {

        String first = Email.getText().toString().trim();
        String second = Pass.getText().toString().trim();

        doValidation(first,second);
        startSignUp(first,second);

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

    private void startSignUp(String email,String password){
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(SignUpActivity.this,"Registration Successful,Verify Email" ,Toast.LENGTH_LONG).show();
                                      //  Intent SignUpIntent = new Intent(SignUpActivity.this, JoinActivity.class);
                                      //  startActivity(SignUpIntent);
                                    }
                                    else {
                                        progressBar.setVisibility(View.GONE);
                                        Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        } else {

                          //  if (task.getException() instanceof FirebaseAuthUserCollisionException){

                            //    progressBar.setVisibility(View.GONE);
                            //    Toast.makeText(SignUpActivity.this, "Already Registered", Toast.LENGTH_LONG).show();
                          //  } else {

                            //    progressBar.setVisibility(View.GONE);
                             //   Toast.makeText(SignUpActivity.this, "Error Occur", Toast.LENGTH_LONG).show();
                         //   }

                        }

                    }
                });

    }

}
