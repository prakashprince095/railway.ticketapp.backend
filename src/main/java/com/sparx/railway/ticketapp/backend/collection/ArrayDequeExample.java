package com.sparx.railway.ticketapp.backend.collection;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;


class BookItem{
    int id;
    String name,author,publisher;
    int quantity;
    public BookItem(int id, String name, String author, String publisher, int quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.quantity = quantity;
    }

}
public class ArrayDequeExample {
    public static void main(String [] args){
//        methodsOfArrayDeque();
        storeObject();
    }
    public static  void storeObject(){
        Deque<BookItem> set=new ArrayDeque<BookItem>();
        //Creating Books
        BookItem b1=new BookItem(101,"Let us C","Yashwant Kanetkar","BPB",8);
        BookItem b2=new BookItem(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
        BookItem b3=new BookItem(103,"Operating System","Galvin","Wiley",6);
        //Adding Books to Deque
        set.add(b1);
        set.add(b2);
        set.add(b3);
        //Traversing ArrayDeque
        for(BookItem b:set){
            System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);
        }
    }

    public static void methodsOfArrayDeque(){
        Deque<String> dqueue = new ArrayDeque<String>(Arrays.asList("one", "two", "three","four","five"));
        System.out.println("printing the iniatal element of the DQueue"+dqueue.toString());
        dqueue.addFirst("minus_one");
        dqueue.addLast("six");
        Iterator itr =dqueue.iterator();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }
        /*
        we do have some special ArrayDeque related method thats are offerFirst and offerLast and pollFirst and poll Last
         */
        dqueue.offerFirst("minus-two");
        dqueue.offerLast("seven");
        System.out.println("After invoking offerFirst and offerLast methods ");
         Iterator itr2 =dqueue.iterator();
        while (itr2.hasNext()){
            String element= (String) itr2.next();
            System.out.println(element);
            if(element.equals("six")){
                itr2.remove();
            }
        }
        System.out.println("After invoking remove method in the iterator  "+dqueue.toString());
        dqueue.pollFirst();
        dqueue.pollLast();
        System.out.println("After invoking pollFirst and pollLast methods ");
        for(String str: dqueue){
            System.out.println(str);
        }
    }
}

