package com.example.libby;

import java.util.ArrayList;
import java.util.List;

public class BookGenerator {

    public static List<Book> generateBooks() {
        List<Book> books = new ArrayList<>();

        // Generate books for each genre
        books.addAll(generateContemporaryFiction());
        books.addAll(generateFantasy());
        books.addAll(generateMysteryAdventure());
        books.addAll(generateScienceFiction());
        books.addAll(generateHistoricalFiction());

        return books;
    }

    private static List<Book> generateContemporaryFiction() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Lemonade War", "Jacqueline Davies", 160));
        books.add(new Book("Wonder", "R.J. Palacio", 320));
        books.add(new Book("Matilda", "Roald Dahl", 240));
        return books;
    }

    private static List<Book> generateFantasy() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 320));
        books.add(new Book("Percy Jackson and the Olympians: The Lightning Thief", "Rick Riordan", 384));
        books.add(new Book("The Lion, the Witch and the Wardrobe", "C.S. Lewis", 208));
        return books;
    }

    private static List<Book> generateMysteryAdventure() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("The Boxcar Children", "Gertrude Chandler Warner", 160));
        books.add(new Book("Nancy Drew: The Secret of the Old Clock", "Carolyn Keene", 180));
        books.add(new Book("The Hardy Boys: The Tower Treasure", "Franklin W. Dixon", 192));
        return books;
    }

    private static List<Book> generateScienceFiction() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("A Wrinkle in Time", "Madeleine L'Engle", 256));
        books.add(new Book("The City of Ember", "Jeanne DuPrau", 288));
        books.add(new Book("Escape from Mr. Lemoncello's Library", "Chris Grabenstein", 304));
        return books;
    }

    private static List<Book> generateHistoricalFiction() {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Number the Stars", "Lois Lowry", 160));
        books.add(new Book("I Survived the Sinking of the Titanic, 1912", "Lauren Tarshis", 112));
        books.add(new Book("The War That Saved My Life", "Kimberly Brubaker Bradley", 336));
        return books;
    }
}
