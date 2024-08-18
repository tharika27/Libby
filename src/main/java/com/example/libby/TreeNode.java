package com.example.libby;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    private String genre;
    private List<Book> recommendations;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(String genre) {
        this.genre = genre;
        this.recommendations = new ArrayList<>();
        this.left = null;
        this.right = null;
    }

    public String getGenre() {
        return genre;
    }

    public List<Book> getRecommendations() {
        return recommendations;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}

