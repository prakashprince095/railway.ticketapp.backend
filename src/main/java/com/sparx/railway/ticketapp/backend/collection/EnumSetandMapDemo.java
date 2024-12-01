package com.sparx.railway.ticketapp.backend.collection;

import java.util.*;

enum days {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
}
 enum Days {
    Monday, Tuesday, Wednesday, Thursday
};
enum Key{
    One, Two, Three
};

public class EnumSetandMapDemo {

    public static void main(String [] args){
//        differentWaysToCreateEnumSet();
        differentBasicMethodsOfEnumMap();
    }
    public static void differentWaysToCreateEnumSet(){
     Set<days> set= EnumSet.of(days.FRIDAY,days.MONDAY,days.FRIDAY);
        Iterator<days> itr =set.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("creating EnumSet using EnumSet.allOf()");
        Set<days> set2=EnumSet.allOf(days.class);
        Iterator<days> itr2=set2.iterator();
        while(itr2.hasNext()){
            System.out.println(itr2.next());
        }
    }
    public static void differentBasicMethodsOfEnumMap(){
        EnumMap<Days,String> map1=new EnumMap<>(Days.class);
        map1.put(Days.Monday,"1");
        map1.put(Days.Tuesday,"2");
        map1.put(Days.Wednesday,"3");
        map1.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+"  value :"+entry.getValue());
        });
        System.out.println("iterating the map Set where class is being stored as a value");
        EnumMap<Key, Book> map = new EnumMap<Key, Book>(Key.class);
        // Creating Books
        Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8);
        Book b2=new Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
        Book b3=new Book(103,"Operating System","Galvin","Wiley",6);
        // Adding Books to Map
        map.put(Key.One, b1);
        map.put(Key.Two, b2);
        map.put(Key.Three, b3);
        // Traversing EnumMap
        for(Map.Entry<Key, Book> entry:map.entrySet()){
            Book b=entry.getValue();
            System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);
        }

    }
}
