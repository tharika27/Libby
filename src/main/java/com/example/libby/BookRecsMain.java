
package com.example.libby;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class BookRecsMain extends AppCompatActivity {

    private BookRecommendations bookRecommendations;
    private RadioGroup genreRadioGroup;
    private RadioGroup pageLengthRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_recs);

        // Initialize views
        genreRadioGroup = findViewById(R.id.radioGroupGenre);
        pageLengthRadioGroup = findViewById(R.id.radioGroupLength);
        Button recommendButton = findViewById(R.id.buttonGenerateRecommendation);
        Button homeButton = findViewById(R.id.home_Button_press);

        // Initialize BookRecommendations
        bookRecommendations = new BookRecommendations();
        List<Book> books = BookGenerator.generateBooks();
        for (Book book : books) {
            bookRecommendations.insert(book);
        }

        homeButton.setOnClickListener(v -> {
            Intent intent = new Intent(BookRecsMain.this, MainActivity.class);
            startActivity(intent);
        });


        // Set up recommend button click listener
        recommendButton.setOnClickListener(v -> {
            Log.d("BookRecsMain", "Recommend button clicked");
            String selectedGenre = getSelectedGenre();
            Log.d("BookRecsMain", "Selected genre: " + selectedGenre);
            int selectedPageLength = getSelectedPageLength();
            Log.d("BookRecsMain", "Selected page length: " + selectedPageLength);

            Book recommendedBook = bookRecommendations.recommendBook(selectedGenre, selectedPageLength);
            if (recommendedBook != null) {
                showRecommendationToast(recommendedBook);
            } else {
                showNoRecommendationDialog();
            }
        });
    }

    private String getSelectedGenre() {
        if (genreRadioGroup == null) {
            Log.e("BookRecsMain", "genreRadioGroup is null");
            return "";
        }
        int selectedRadioButtonId = genreRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            Log.e("BookRecsMain", "No radio button selected for genre");
            return "";
        }
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        if (selectedRadioButton == null) {
            Log.e("BookRecsMain", "Selected radio button is null");
            return "";
        }
        return selectedRadioButton.getText().toString();
    }

    private int getSelectedPageLength() {
        if (pageLengthRadioGroup == null) {
            Log.e("BookRecsMain", "pageLengthRadioGroup is null");
            return 0;
        }
        int selectedRadioButtonId = pageLengthRadioGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            Log.e("BookRecsMain", "No radio button selected for page length");
            return 0;
        }
        RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);
        if (selectedRadioButton == null) {
            Log.e("BookRecsMain", "Selected radio button is null");
            return 0;
        }
        String pageLengthString = selectedRadioButton.getText().toString();
        // Extract the page length from the radio button text (assuming it's in the format "Page Length: XXX")
        String[] parts = pageLengthString.split(": ");
        return Integer.parseInt(parts[1]);
    }
    private void showRecommendationToast(Book recommendedBook) {
        String message = "Title: " + recommendedBook.getBookTitle() + "\n" +
                "Author: " + recommendedBook.getAuthor() + "\n" +
                "Page Length: " + recommendedBook.getPageLength() + "\n" +
                "Genre: " + recommendedBook.getGenre();
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private void showNoRecommendationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No Recommendation");
        builder.setMessage("Sorry, no book matches your criteria.");
        builder.setPositiveButton("OK", null);
        builder.show();
    }


    /*public void onBackPressed() {
        super.onBackPressed();
        }




 */

}

