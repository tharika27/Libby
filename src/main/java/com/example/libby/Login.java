package com.example.libby;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword;
    Button buttonLogin;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView textView;
    //String currentUserId;


  /*  @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
       // FirebaseUser currentUser = mAuth.getCurrentUser();
        //if(currentUser != null){
          //  currentUser.reload();
      //  }
    }

   */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FirebaseApp.initializeApp(this);


        // Initialize FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        Log.d("LoginActivity", "FirebaseAuth instance: " + mAuth);

        // Initialize UI elements
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        buttonLogin = findViewById(R.id.btn_login);
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.RegisterNow);

        // Set click listener for Register text view
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Register activity when Register text view is clicked
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        // Set click listener for Login button
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Call loginUser() method to perform login
                loginUser();
            }
        });

        // Check if user is still logged in
        checkCurrentUser();
    }

    private void loginUser() {
        progressBar.setVisibility(View.VISIBLE);

        String email = Objects.requireNonNull(editTextEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(editTextPassword.getText()).toString().trim();

        // Check if email or password is empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.GONE);
            return;
        }

        // Signing in existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Login successful, start MainActivity
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // Login failed, display error message
                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Check if user is still logged in
        checkCurrentUser();
    }

    private void checkCurrentUser() {
        // Check if the user is already signed in
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
    /*

    public void onBackPressed () {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
    }

     */
}
