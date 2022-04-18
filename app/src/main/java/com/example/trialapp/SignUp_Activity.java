package com.example.trialapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trialapp.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp_Activity extends AppCompatActivity {

    TextView textView;
    EditText userName , signUpEmail ,signUpPassword;
    Button btnSignUp;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);


        getSupportActionBar().hide();
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );

        textView = findViewById(R.id.tvLogin);
        userName = findViewById(R.id.userName);
        signUpEmail = findViewById(R.id.signUpEmail);
        signUpPassword = findViewById(R.id.signUpPassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        auth = FirebaseAuth.getInstance();  //Mandatory
        database = FirebaseDatabase.getInstance();

        progressDialog = new ProgressDialog(SignUp_Activity.this);
        progressDialog.setTitle("Creating Account");
        progressDialog.setMessage("We are creating your account ");


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp_Activity.this,SignIn_Activity.class);
                startActivity(intent);
                finish();
            }
        });


   //SIMPLE EMAIL ID AND PASSWORD USING FIREBASE
      btnSignUp.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              progressDialog.show();
              auth.createUserWithEmailAndPassword(signUpEmail.getText().toString(),
                      signUpPassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      progressDialog.dismiss();
                      if(task.isSuccessful())
                        {
                            User user = new User(userName.getText().toString() ,
                                    signUpEmail.getText().toString() ,
                                    signUpPassword.getText().toString());

                            String id = task.getResult().getUser().getUid();
                            database.getReference().child("Users").child(id).setValue(user);

                            Intent intent = new Intent(SignUp_Activity.this,SignIn_Activity.class);
                            startActivity(intent);
                            finish();

                            Toast.makeText(SignUp_Activity.this, "User Created Succesfully", Toast.LENGTH_SHORT).show();
                        }
                      else {
                            Toast.makeText(SignUp_Activity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                  }
              });


          }
      });



    }

}