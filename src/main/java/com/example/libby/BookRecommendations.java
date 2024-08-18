package com.example.libby;

public class BookRecommendations {
    // Node class for the binary search tree
    private static class Node {
        Book data;
        Node left;
        Node right;

        Node(Book data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    // Constructor
    public BookRecommendations() {
        root = null;
    }

    // Method to insert a book into the binary search tree
    public void insert(Book data) {
        root = insertRecursive(root, data);
    }

    private Node insertRecursive(Node node, Book data) {
        if (data == null) {
            return node; // Return the current node if the data is null
        }

        if (node == null) {
            return new Node(data);
        }

        // Compare the book data to decide the direction of insertion
        String nodeTitle = node.data.getTitle() != null ? node.data.getTitle().toString() : "";
        String dataTitle = data.getTitle() != null ? data.getTitle().toString() : "";
        if (dataTitle.compareToIgnoreCase(nodeTitle) < 0) {
            node.left = insertRecursive(node.left, data);
        } else {
            node.right = insertRecursive(node.right, data);
        }

        return node;
    }

    // Method to recommend a book based on user preferences
    public Book recommendBook(String genre, int pageLength) {
        return recommendBookHelper(root, genre, pageLength);
    }

    // Recursive helper method to find a recommended book
    private Book recommendBookHelper(Node node, String genre, int pageLength) {
        if (node == null) {
            return null; // No matching book found
        }
        // If the book matches the genre and page length, return it
        if (node.data.getGenre().equalsIgnoreCase(genre) && node.data.getPageLength() <= pageLength) {
            return node.data;
        }
        // If the book's genre is before the desired genre alphabetically, search right subtree
        if (node.data.getGenre().compareToIgnoreCase(genre) < 0) {
            return recommendBookHelper(node.right, genre, pageLength);
        }
        // If the book's genre is after the desired genre alphabetically, search left subtree
        return recommendBookHelper(node.left, genre, pageLength);
    }
}

