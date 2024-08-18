package com.example.libby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Stack;

public class BookHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_history);

        TextView textViewBookHistory = findViewById(R.id.textViewBookHistory);
        Button homeButton = findViewById(R.id.home_Button_press);

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookHistory.this, MainActivity.class);
            startActivity(intent);
        });

        // Create an instance of Library
        Library library = Library.getInstance();

        // Retrieve book history from the Library
        Stack<Book> bookHistory = library.getBookHistory();

        StringBuilder stringBuilder = new StringBuilder();

        // Iterate through the book history stack
        for (Book book : bookHistory) {
            // Append the details of each book to the StringBuilder
            stringBuilder.append("Title: ").append(book.getBookTitle()).append("\n")
                    .append("Author: ").append(book.getAuthor()).append("\n")
                    .append("ISBN: ").append(book.getIsbn()).append("\n")
                    .append("Format: ").append(book.getFormat()).append("\n")
                    .append("Review: ").append(book.getReview()).append("\n\n"); // Add review to the StringBuilder
        }

        // Display the book details in the TextView
        if (stringBuilder.length() > 0) {
            textViewBookHistory.setText(stringBuilder.toString());
        } else {
            textViewBookHistory.setText("No books available");
        }
    }

    /*public void onBackPressed () {
        Intent intent = new Intent(BookHistory.this, MainActivity.class);
        startActivity(intent);
    }

     */
}