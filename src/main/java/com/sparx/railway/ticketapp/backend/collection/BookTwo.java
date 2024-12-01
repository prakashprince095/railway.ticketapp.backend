package com.sparx.railway.ticketapp.backend.collection;

import java.util.PriorityQueue;
import java.util.Queue;

public class BookTwo implements Comparable<BookTwo> {
    int id;
    String name, author, publisher;
    int quantity;
    // Constructor to initialize Book object
    public BookTwo(int id, String name, String author, String publisher, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }
    // compareTo method to compare Books based on 'id'


    @Override
    public int compareTo(BookTwo b) {
        if (id > b.id) {
            return 1;
        } else if (id < b.id) {
            return -1;
        } else {
            return 0;
        }
    }
}
 class LinkedListExample {
    public static void main(String[] args) {
        // Creating a PriorityQueue to store Book objects
        Queue<BookTwo> queue = new PriorityQueue<BookTwo>();
        // Creating Book objects
        Queue<BookTwo> queue1 = new PriorityQueue<BookTwo>();
        BookTwo b1 = new BookTwo(121, "Let us C", "Yashwant Kanetkar", "BPB", 8);
        BookTwo b2 = new BookTwo(233, "Operating System", "Galvin", "Wiley", 6);
        BookTwo b3 = new BookTwo(101, "Data Communications & Networking", "Forouzan", "Mc Graw Hill", 4);
        // Adding Book objects to the queue
        queue1.add(b1);
        queue1.add(b2);
        queue1.add(b3);
        // Displaying the elements of the queue
        System.out.println("Traversing the queue elements:");
        for (BookTwo b : queue1) {
            System.out.println(b.id + " " + b.name + " " + b.author + " " + b.publisher + " " + b.quantity);
        }
        // Removing the head of the queue
        queue1.remove();
        // Displaying the elements of the queue after removing one element
        System.out.println("After removing one book record:");
        for (BookTwo b : queue1) {
            System.out.println(b.id + " " + b.name + " " + b.author + " " + b.publisher + " " + b.quantity);
        }
    }
}

