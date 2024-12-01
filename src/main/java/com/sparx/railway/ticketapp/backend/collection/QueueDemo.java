package com.sparx.railway.ticketapp.backend.collection;

import java.util.Arrays;
import java.util.Iterator;
import java.util.PriorityQueue;

public class QueueDemo {
    public static void main(String [] args){
        queueOperations();
    }
    public static void queueOperations(){
        PriorityQueue<String> q1=new PriorityQueue<String>(Arrays.asList("one","two","three","four","five"));
        /*
        Displaying the head of the queue and this method throws an exception when the queue is empty
         */
        System.out.println("Head element using element method:"+q1.element());
        /*
        displayng the head elemet using the peek method return nul if the queue is empty
         */
        System.out.println("displaying the head element using the peek method"+q1.peek());
        /*
        displaying the elemnt in the queue
         */
        System.out.println("displaying the element before the removal of the element");
        Iterator itr =q1.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        q1.remove();
        System.out.println("displaying the element of the array after the removal of the element "+q1.toString());
        q1.poll();
        Iterator itr2=q1.iterator();
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }


    }
}
