package com.example.libby;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class CheckIn extends AppCompatActivity {

    private ListView listViewCheckedOutBooks;
    private Button reviewButton;
    private Library library;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        // Initialize UI elements
        listViewCheckedOutBooks = findViewById(R.id.listViewCheckedOutBooks);
        reviewButton = findViewById(R.id.reviewButton);
        Button homeButton = findViewById(R.id.home_Button_press);
        // Create an instance of Library
        library = Library.getInstance();
        // Retrieve checked-out books from the Library
        Queue<Book> checkedOutBooks = library.getCheckedOutBooks();
        // Create an adapter to display checked-out books in the ListView
        BookListAdapter adapter = new BookListAdapter((Context) this, (List<Book>) checkedOutBooks);
        listViewCheckedOutBooks.setAdapter(adapter);

        // Set a click listener for the review button
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the review button click event
                handleReviewButtonClick();
            }
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(CheckIn.this, MainActivity.class);
            startActivity(intent);
        });
    }

    public void onBookSelectedForReview(Book selectedBook) {
        // Implement your logic to review the selected book here
        Toast.makeText(this, "Selected for review: " + selectedBook.getTitle(), Toast.LENGTH_SHORT).show();
        // You can open a review dialog or perform any other action here
    }

    private void handleReviewButtonClick() {
        // Get the adapter associated with the ListView
        BookListAdapter adapter = (BookListAdapter) listViewCheckedOutBooks.getAdapter();

        // Get the selected book from the adapter
        Book selectedBook = adapter.getSelectedBook();

        if (selectedBook == null) {
            Toast.makeText(this, "Please select a book to review", Toast.LENGTH_SHORT).show();
            return;
        }

        // Show a dialog for entering the review
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Review Book");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String review = input.getText().toString();
                // Update the selected book's review
                selectedBook.setReview(review);
                // Add the book to the book history
                library.addToBookHistory(selectedBook);
                // Remove the book from the checked-out books
                library.checkInBook(selectedBook);
                // Refresh the list view
                adapter.notifyDataSetChanged();
                Toast.makeText(CheckIn.this, "Review submitted", Toast.LENGTH_SHORT).show();
                Toast.makeText(CheckIn.this, "Book Checked in", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /*public void onBackPressed () {
        Intent intent = new Intent(CheckIn.this, MainActivity.class);
        startActivity(intent);
    }

     */
}

