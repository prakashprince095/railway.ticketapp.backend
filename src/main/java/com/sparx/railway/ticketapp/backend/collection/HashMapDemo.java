package com.sparx.railway.ticketapp.backend.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HashMapDemo {
    public static void main(String[] args){
//        differetWayToCreateHashMap();
        basicHashMapMethod();
    }

    public static void differetWayToCreateHashMap(){
        HashMap<Integer,String>  hashMap1 = new HashMap<Integer,String>();
        hashMap1.put(1,"aman");
        hashMap1.put(2,"prakash");
        hashMap1.put(3,"rahul");
        System.out.println("Printing the hashMap created with empty constructor");
        for(Map.Entry entry : hashMap1.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        System.out.println("Printing the hashMap created with inital Capacity");

        HashMap<Integer,String> hashMap2 = new HashMap<>(5);
        hashMap2.put(1,"raj");
        hashMap2.put(2,"thakur");
        hashMap2.put(3,"singh");
        hashMap2.entrySet().forEach(entry ->{
            System.out.println(entry.getKey() +" "+ entry.getValue());
        });
        System.out.println("Printing the hashMap created with inital Capacity and load factor");
        HashMap<Integer,String> hashMap3 = new HashMap(12,0.8f);
        hashMap3.put(1,"apple");
        hashMap3.put(2,"banana");
        hashMap3.put(3,"mango");
        Iterator itr =hashMap3.entrySet().iterator();
        while (itr.hasNext()){
            Map.Entry entry = (Map.Entry) itr.next();
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }

    }

    public static void basicHashMapMethod(){
        HashMap<Integer,String>  hashMap1 = new HashMap<Integer,String>();
        hashMap1.put(1,"aman");
        hashMap1.put(2,"prakash");
        hashMap1.put(3,"rahul");
        hashMap1.putIfAbsent(4,"rahul raj");
        hashMap1.entrySet().forEach(entry->{
            System.out.println(entry.getKey() +" "+ entry.getValue());
        });
        System.out.println("the printing the hashMap after performing PutALL");
        HashMap<Integer,String> hashMap2 = new HashMap<>(5);
        hashMap2.put(5,"raj");
        hashMap2.put(6,"thakur");
        hashMap2.put(7,"singh");
        hashMap1.putAll(hashMap2);
        for(Map.Entry entry: hashMap1.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        hashMap1.remove(7);
        System.out.println("the printing the hashMap after performing remove on the key 7");
        for(Map.Entry entry: hashMap1.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        hashMap1.remove(6,"thakur");
        System.out.println("the printing the hashMap after performing remove on the key 6 with value 'thakur'");
        for(Map.Entry entry: hashMap1.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        System.out.println("the printing the hashMap after performing replace on the key 2");
        hashMap1.replace(2, "Gaurav");
        for(Map.Entry entry: hashMap1.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }
        System.out.println("the printing the hashMap after performing replace on the key 3 with new value 'virat'");
        hashMap1.replace(3, "rahul","virat");
        for(Map.Entry entry: hashMap1.entrySet()){
            System.out.println(entry.getKey() +" "+ entry.getValue());
        }

    }


}
