package com.sparx.railway.ticketapp.backend.collection;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDemo {
    public static void main(String[] args ){
        differentWaysTocreate();
    }

    public static void differentWaysTocreate(){

        LinkedList<String> list = new LinkedList<String>();
        list.add("virat");
        list.add("abd");
        list.add("babar");
        list.add("ishan");
        list.add("rohit");
        list.add("abd");
        list.add("virat");
        System.out.println("printing the list "+list.toString());
        list.addFirst("mahi");
        list.addLast("pant");
        System.out.println("printing the list  after modyfing the list "+list.toString());
        LinkedList<String> list2 = new LinkedList<String>();
        list2.add("steeve");
        list2.add("michal");
        list2.add("shane");
        list.addAll(list2);
        System.out.println("printing the list after adding list2 "+list.toString());
        // after removing the list
        list.removeAll(list2);
        System.out.println("after invoking the All method "+list.toString());
        list.removeFirst();
        System.out.println("after invoking the removeFirst method "+list.toString());
        list.removeLast();
        System.out.println("after invoking the removeLast method "+list.toString());
        list.remove("abd");
        System.out.println("after invoking the remove method "+list.toString());
        list.removeFirstOccurrence("virat");
        System.out.println("after invoking the removeFirstOccurrence method "+list.toString());
        list.removeLastOccurrence("abd");
        System.out.println("after invoking the removeLastOccurrence method "+list.toString());
        Iterator itr =list.descendingIterator();
        System.out.println("printing the list in descending order ");
        while(itr.hasNext()){
            System.out.print(itr.next()+" ");
        }
        System.out.println();
        System.out.println("the value of the list at 1:"+list.get(2));


    }
}
