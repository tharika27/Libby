package com.example.libby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;


public class Register extends AppCompatActivity {

    private TextInputEditText editTextEmail, editTextPassword;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    private FirebaseUser user;
    private static final String TAG = "RegisterActivity";

    /*public void onBackPressed() {
        Intent intent = new Intent(Register.this, MainActivity.class);
        startActivity(intent);
    }

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance(); // Initialize FirebaseAuth instance

        // Find views
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        Button buttonReg = findViewById(R.id.btn_Register);
        progressBar = findViewById(R.id.progressBar);
        Button loginBtn = findViewById(R.id.btn_loginNow);

        // Set click listener for "Login Now" TextView
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });


        // Set click listener for registration button
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Objects.requireNonNull(editTextEmail.getText()).toString().trim();
                String password = Objects.requireNonNull(editTextPassword.getText()).toString().trim();

                // Check if email or password is empty
                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this,"Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this,"Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                // Create user with email and password
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                // Handle registration completion
                                if (task.isSuccessful()) {
                                    Toast.makeText(Register.this, "Account created.", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();

                                   FirebaseUser user = mAuth.getCurrentUser();

                                } else {
                                    // If registration fails, display a message to the user.
                                    Toast.makeText(Register.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }

        });


    }

    /*@Override
    public void onStart() {
        super.onStart();
        // Check if mAuth is null before accessing currentUser
        if (mAuth != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if(currentUser != null){
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        } else {
            Log.e(TAG, "FirebaseAuth instance is null");
            Toast.makeText(this, "Authentication error. Please try again later.", Toast.LENGTH_SHORT).show();
        }
    }*/


}