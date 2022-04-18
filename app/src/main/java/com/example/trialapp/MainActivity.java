package com.example.trialapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {
        Button button1 , button2;
        FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        auth = FirebaseAuth.getInstance();

        button1 = findViewById(R.id.login);
        button2 = findViewById(R.id.register);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignActivity();
            }
        });
//
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        if (auth.getCurrentUser()!=null)
        {
            Intent intent = new Intent(MainActivity.this,Home.class);
            startActivity(intent);
            finish();
        }

    }

    public void signUp(){
        Intent intent = new Intent(this,SignUp_Activity.class);
        startActivity(intent);
    }

    public void SignActivity(){
        Intent intent = new Intent(this,SignIn_Activity.class);
        startActivity(intent);
        finish();
    }




}