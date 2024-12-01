package com.sparx.railway.ticketapp.backend.collection;

import java.util.Comparator;

public class Book implements Comparator<Book>, Comparable<Book> {
    int id;
    String name, author, publisher;
    int quantity;

    public Book(int id, String name, String author, String publisher, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

    // Implement the compareTo method from Comparable interface
    @Override
    public int compareTo(Book o) {
        // Compare by 'id' (or another field if desired)
        return Integer.compare(this.id, o.id);
    }

    @Override
    public int compare(Book o1, Book o2) {
        return Integer.compare(o1.id, o2.id);  // Comparator logic
    }
}
