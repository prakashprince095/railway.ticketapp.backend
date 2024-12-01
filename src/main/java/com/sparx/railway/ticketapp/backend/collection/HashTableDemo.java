package com.sparx.railway.ticketapp.backend.collection;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class HashTableDemo {
    public static void main(String [] args){
//        differentWaysToCreateHashTable();
//        differentBasicMethodsOfHashTable();
        insertingClassInHashtable();
    }
    public static void differentWaysToCreateHashTable(){
        Hashtable<Integer,String> ht = new Hashtable<Integer,String>();
        ht.put(1,"Virat");
        ht.put(2,"Rohit");
        ht.put(3,"Shami");
        System.out.println("Printing the hashTable created with empty constructor");
        for(Object key : ht.keySet()){
            System.out.println(key +" "+ ht.get(key));
        }
        System.out.println("Printing the hashTable created with initial capacity ");

        Hashtable<Integer,String> ht2 = new Hashtable<Integer,String>(10);
        ht2.put(1,"kohli");
        ht2.put(2,"sharma");
        ht2.put(3,"ahmad");
        ht2.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() +" "+ entry.getValue());
        });
        System.out.println("Printing the hashTable created with load factor ");
        Hashtable<String , Integer> ht3 = new Hashtable<>(10,0.5f);
        ht3.put("India",1);
        ht3.put("Australia",2);
        ht3.put("South Africa",3);
        Iterator itr =ht3.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry entry = (Map.Entry) itr.next();
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        Map<Integer,String> map1 = new HashMap<Integer,String>();
        map1.put(1,"Dhoni");
        map1.put(2,"Kohli");
        map1.put(3,"Rohit");
        Hashtable<Integer,String> ht4=new Hashtable<Integer,String>(map1);
        System.out.println("iterating over the hashtable created with the inital map value");
        for(Map.Entry<Integer,String> map : ht4.entrySet()){
            System.out.println(map.getKey()+"  value :"+map.getValue());
        }


    }
    public static void differentBasicMethodsOfHashTable(){
        Map<Integer,String> map1 = new HashMap<Integer,String>();
        map1.put(1,"Dhoni");
        map1.put(2,"Kohli");
        map1.put(3,"Rohit");
        Hashtable<Integer,String> ht4=new Hashtable<Integer,String>(map1);
        System.out.println("iterating over the hashtable created with the inital map value"+ht4);
//        for(Map.Entry<Integer,String> map : ht4.entrySet()){
//            System.out.println(map.getKey()+"  value :"+map.getValue());
//        }
        ht4.remove(1);
        System.out.println("After removing key 1 from the hashtable"+ht4);
        System.out.println("getting the value of the key 4"+ht4.getOrDefault(4,"No Found Element with this key"));
        System.out.println("checking if the key 2 is present in the hashtable :"+ht4.getOrDefault(2,"No Found Element with this key"));

        System.out.println("putting the value in the hashtable when the key 1 is not present"+ht4.putIfAbsent(1,"shrama"));
        System.out.println("After putting the value in the hashtable when the key 2 is not present"+ht4.putIfAbsent(2,"shrama"));

    }
    public static void insertingClassInHashtable(){
        Map<Integer ,Book> map=new Hashtable<Integer ,Book>();
        //Creating Books
        Book b1=new Book(101,"Let us C","Yashwant Kanetkar","BPB",8);
        Book b2=new Book(102,"Data Communications & Networking","Forouzan","Mc Graw Hill",4);
        Book b3=new Book(103,"Operating System","Galvin","Wiley",6);
        //Adding Books to map
        map.put(1,b1);
        map.put(2,b2);
        map.put(3,b3);
        //Traversing map
        for(Map.Entry<Integer , Book> entry:map.entrySet()){
            int key=entry.getKey();
            Book b=entry.getValue();
            System.out.println(key+" Details:");
            System.out.println(b.id+" "+b.name+" "+b.author+" "+b.publisher+" "+b.quantity);
        }
    }
}
