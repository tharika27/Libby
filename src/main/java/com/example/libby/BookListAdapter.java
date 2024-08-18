package com.example.libby;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.libby.Book;
import com.example.libby.R;

import java.util.List;

public class BookListAdapter extends ArrayAdapter<Book> {

    private Context mContext;
    private List<Book> mBooks;
    private Book mSelectedBook; // Variable to store the selected book

    public BookListAdapter(Context context, List<Book> books) {
        super(context, 0, books);
        mContext = context;
        mBooks = books;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.book_list_item, parent, false);
        }

        final Book currentBook = mBooks.get(position);

        TextView titleTextView = listItem.findViewById(R.id.titleTextView);
        titleTextView.setText(currentBook.getTitle());

        TextView authorTextView = listItem.findViewById(R.id.authorTextView);
        authorTextView.setText(currentBook.getAuthor());

        TextView isbnTextView = listItem.findViewById(R.id.isbnTextView);
        isbnTextView.setText(currentBook.getIsbn());

        TextView formatTextView = listItem.findViewById(R.id.formatTextView);
        formatTextView.setText(currentBook.getFormat());

        // CheckBox to select the book for review
        CheckBox selectCheckBox = listItem.findViewById(R.id.selectCheckBox);
        selectCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the selected book when the CheckBox is checked
                if (isChecked) {
                    mSelectedBook = currentBook;
                }
            }
        });

        return listItem;
    }

    // Method to retrieve the selected book
    public Book getSelectedBook() {
        return mSelectedBook;
    }
}
