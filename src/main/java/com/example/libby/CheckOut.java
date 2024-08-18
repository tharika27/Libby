package com.example.libby;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;




import androidx.appcompat.app.AppCompatActivity;

public class CheckOut extends AppCompatActivity {


    private EditText editTextTitle;
    private EditText editTextAuthor;
    private EditText editTextISBN;
    private RadioGroup radioGroupFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        // Initialize UI elements
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextAuthor = findViewById(R.id.editTextAuthor);
        editTextISBN = findViewById(R.id.editTextISBN);
        radioGroupFormat = findViewById(R.id.radioGroupFormat);
        Button homeButton = findViewById(R.id.home_Button_press);

        // Find the checkout button and set its click listener
        Button checkoutButton = findViewById(R.id.check_out_button);

        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent to perform the checkout
                performCheckout();
            }
        });

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(CheckOut.this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void performCheckout() {
        // Retrieve values from UI elements when checkout is initiated
        String title = editTextTitle.getText().toString();
        String author = editTextAuthor.getText().toString();
        String isbn = editTextISBN.getText().toString();

        // Handle radio button selection for book format
        int selectedRadioButtonId = radioGroupFormat.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(selectedRadioButtonId);
        radioButton.getText().toString();
        String format;

        // Check if any field is empty
        if (title.isEmpty() || author.isEmpty() || isbn.isEmpty()) {
            // Show error message if any field is empty
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        //reassign format value into string
        if (selectedRadioButtonId == R.id.radioButtonEbook) {
            format = "Ebook";
        } else if (selectedRadioButtonId == R.id.radioButtonPhysical) {
            format = "Physical";
        } else {
            // Default value if no radio button is selected
            format = "None selected";
        }

        // Create a new book object
        Book book = new Book(title, author, isbn, format);

        // Add the book to the library
        Library library = Library.getInstance();
        library.addBook(book);
        library.checkOutBook(book);

        // Show success message
        Toast.makeText(this, "Checked Out Successfully", Toast.LENGTH_SHORT).show();
    }

    /*@Override
    public void onBackPressed () {
        Intent intent = new Intent(CheckOut.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

     */


}



