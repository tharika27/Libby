package com.example.libby;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
//import com.example.libby.MyReceiver;



public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    Button button;
    TextView textView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //textView = findViewById(R.id.user_details);
       user = mAuth.getCurrentUser();

        //receiver = new MyReceiver();

        Button bookHistoryBtn = findViewById(R.id.book_history);
        Button checkOutBtn = findViewById(R.id.check_out_books_button);
        Button checkInBtn = findViewById(R.id.check_in_books_button);
        Button logoutBtn = findViewById(R.id.logout_button);
        Button bookRecBtn = findViewById(R.id.rec_books_btn);

        bookHistoryBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookHistory.class);
            startActivity(intent);
        });
            bookRecBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, BookRecsMain.class);
            startActivity(intent);
        });


        checkInBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CheckIn.class);
            startActivity(intent);
        });

        checkOutBtn.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CheckOut.class);
            startActivity(intent);
        });

        logoutBtn.setOnClickListener(v -> {
            try {
                Intent intent = new Intent(MainActivity.this, Register.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        /*if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        } else {
            textView.setText(user.getEmail());
        }

         */

        /*logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        });

         */




    }
}