package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;


public class SignIn_Activity extends AppCompatActivity {


   TextView textView;
   EditText signinEmail ,signinPassword;
   Button Signin;
   FirebaseAuth auth;
   ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().hide();
//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );

        textView = findViewById(R.id.tvSignUp);
        signinEmail = findViewById(R.id.signinEmail);
        signinPassword = findViewById(R.id.signinPassword);
        Signin = findViewById(R.id.btnSignin);

        auth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(SignIn_Activity.this);
        progressDialog.setTitle("Login Account");
        progressDialog.setMessage("Logging Into your account ");





        textView.setOnClickListener(view -> {
            Intent intent = new Intent(SignIn_Activity.this,SignUp_Activity.class);
            startActivity(intent);
                finish();

        });


        Signin.setOnClickListener(view -> {
            progressDialog.show();
            auth.signInWithEmailAndPassword(signinEmail.getText().toString(),
                    signinPassword.getText().toString()).addOnCompleteListener(task -> {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Intent intent = new Intent(SignIn_Activity.this, Home.class);
                    startActivity(intent);
                    finish();


                } else
                    Toast.makeText(SignIn_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                    });

        });
    }
}