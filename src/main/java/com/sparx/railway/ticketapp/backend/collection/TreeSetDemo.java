package com.sparx.railway.ticketapp.backend.collection;

import java.util.*;


public class TreeSetDemo {
    public static void main(String[] args){
//      pattern1(8);
//        Scanner scanner = new Scanner(System.in);  // Scanner for input
//        differentWaysToCreateTretreeSet();
        storeClassInTreeSet();

    }
    public static void storeClassInTreeSet(){
        TreeSet<Book> books = new TreeSet<Book>();
        books.add(new Book(1, "Java", "Ken Thompson", "Oracle Press", 10));
        books.add(new Book(2, "C++", "Bjarne Stroustrup", "Addison-Wesley", 5));
        books.add(new Book(3, "Python", "Guido van Rossum", "O'Reilly Media", 8));
        books.add(new Book(4, "JavaScript", "Brendan Eich", "O'Reilly Media", 12));
        books.add(new Book(5, "Ruby", "Yukihiro Matsumoto", "Object Refinement Group", 7));
        Iterator itr= books.descendingIterator();
        while(itr.hasNext()){
            Book book = (Book) itr.next();
            System.out.println("the id is"+book.id+" name is "+book.name+" author "+book.author+" publisher "+book.publisher+" quantity"+book.quantity);
        }
//        for(Book book : books){
//            System.out.println("the id is"+book.id+"name is "+book.name+"author "+book.author+" publisher "+book.publisher+" quantity"+book.quantity);
//        }
    }




    public static void differentWaysToCreateTretreeSet(){
        TreeSet<String> treetSet1= new TreeSet<String>(Arrays.asList("virat", "rohit", "akash","kunal","deepak","pawan"));

        System.out.println("printing the treeset using iterator");
        treetSet1.forEach(res ->{
            System.out.println(res);
        });
       System.out.println("printing the value in the decending order");
       Iterator itr =treetSet1.descendingIterator();
       while(itr.hasNext()){
           System.out.println(itr.next());
       }
       /*
       Retreiving and removing the highest and the lowest elements
        */
        System.out.println("removing the top element"+treetSet1.pollFirst());
        System.out.println("removing the lowest element"+treetSet1.pollLast());
        System.out.println("the updated treeset value is "+treetSet1.toString());

    }

}
