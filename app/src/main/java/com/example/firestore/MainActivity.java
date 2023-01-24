package com.example.firestore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    EditText nameEdiTXT;
    EditText emailEdiTXT;
    EditText passwordEdiTXT;
    EditText rePasswordEdiTXT;
    Button registerBtn;
    TextView doUAcc;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        getSupportActionBar().hide();

        nameEdiTXT = findViewById(R.id.et_name);
        emailEdiTXT = findViewById(R.id.et_email);
        passwordEdiTXT = findViewById(R.id.et_password);
        rePasswordEdiTXT = findViewById(R.id.et_repassword);
        registerBtn = findViewById(R.id.btn_register);
        doUAcc = findViewById(R.id.doUSignIn);
        firebaseAuth = FirebaseAuth.getInstance();

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEdiTXT.getText().toString().trim();
                String email = emailEdiTXT.getText().toString().trim();
                String password = passwordEdiTXT.getText().toString().trim();
                String rePassword = rePasswordEdiTXT.getText().toString().trim();
                if (name.isEmpty()){
                    nameEdiTXT.setError("Please Enter The Name");
                    nameEdiTXT.requestFocus();
                    return;
                }
                if (email.isEmpty()){
                    emailEdiTXT.setError("Please Enter The Email Id");
                    emailEdiTXT.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    emailEdiTXT.setError("Please Enter The Valid Email Id");
                    emailEdiTXT.requestFocus();
                    return;
                }
                if (password.isEmpty() && rePassword.isEmpty() ){

                    passwordEdiTXT.setError("Please Enter The Password");
                    passwordEdiTXT.requestFocus();

                    rePasswordEdiTXT.setError("Please Enter The Password Again");
                    rePasswordEdiTXT.requestFocus();
                    return;
                }
                if (!password.equals(rePassword)){
                    Toast.makeText(MainActivity.this, "Password & RePassword are not matching", Toast.LENGTH_SHORT).show();

                }
                if (password.length()<6){
                    passwordEdiTXT.setError("Length of password should more than 6");
                    passwordEdiTXT.requestFocus();
                    return;
                }
                else {

                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {


                            if (task.isSuccessful()){
                                System.out.println("checking....testcase1");


                                Toast.makeText(MainActivity.this, "Registration Success-full", Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(MainActivity.this,Signup.class);
//                                startActivity(intent);
                            }
                            else {
                                System.out.println("checking....testcase2");
                                Toast.makeText(MainActivity.this, "You are not Registered try again! ", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                }

            }
        });


        doUAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this,Signup.class);
                startActivity(login);
            }
        });










    }
}