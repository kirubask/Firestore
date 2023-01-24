package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {


    EditText emailEditTxt;
    EditText passWordEditTxt;
    Button loginBtn;
    TextView donTSignUp;

    FirebaseAuth firebaseAuth ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();

        firebaseAuth = FirebaseAuth.getInstance();
        emailEditTxt = findViewById(R.id.login_email);
        passWordEditTxt = findViewById(R.id.login_password);
        donTSignUp = findViewById(R.id.dontAccSignUp);
        loginBtn = findViewById(R.id.loginBtn);

       loginBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String email = emailEditTxt.getText().toString();
               String passWord = passWordEditTxt.getText().toString();

               if (email.isEmpty()){
                   emailEditTxt.setError("Please Enter the Email Id");
                   emailEditTxt.requestFocus();
                   return;
               }
               if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   emailEditTxt.setError("Please Enter Valid Email Id");
               }

               if (passWord.isEmpty()){
                   passWordEditTxt.setError("Please Enter the Password");
                   passWordEditTxt.requestFocus();
                   return;
               }
               if (passWord.length()<6){
                   passWordEditTxt.setError("Length of password should more than 6");
                   passWordEditTxt.requestFocus();
                   return;
               }else {
                   firebaseAuth.signInWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                           if (task.isSuccessful()){

                               Toast.makeText(Signup.this, "Login Success-full ", Toast.LENGTH_SHORT).show();
                               Intent login = new Intent(Signup.this,FirstPage.class);
                               startActivity(login);
                           }
                           else {
                               Toast.makeText(Signup.this, "Login Failed", Toast.LENGTH_SHORT).show();
                           }



                       }
                   });
               }

           }
       });


        donTSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dontSignUp = new Intent(Signup.this,MainActivity.class);
                startActivity(dontSignUp);
            }
        });
    }



}