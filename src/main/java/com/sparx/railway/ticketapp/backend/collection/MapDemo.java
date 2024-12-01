package com.sparx.railway.ticketapp.backend.collection;

import java.util.*;

public class MapDemo {
    public static void main(String[] args){
//        hashMapBasicMethods();
//        basicMethodOfLinkedHashMap();
        basicMethodOfTreeSet();
    }
    public static void hashMapBasicMethods(){
        HashMap<Integer,String> map1=new HashMap<Integer,String>();
        map1.put(1,"virat");
        map1.put(2,"rohit");
        map1.put(4,"shami");
        map1.put(3,"mahi");
        System.out.println("printing the data using lambda function");
        map1.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });
        Set set1=map1.entrySet();
        System.out.println("printing the hashmap using entrySet()");
        for(Object obj:set1){
            Map.Entry entry=(Map.Entry)obj;
            System.out.println(entry.getKey()+" "+entry.getValue());
        }
        Iterator itr =set1.iterator();
        System.out.println("printing the hashmap using iterator()");
        while(itr.hasNext()){
            System.out.println(itr.next());
        }
        System.out.println("printing the key-value pairs using for-each loop");
        for(Map.Entry m:map1.entrySet()){
            // Printing the key-value pairs
            System.out.println(m.getKey()+" "+m.getValue());
        }
        System.out.println("Accessing the map in the sorted order in ascending order");
        map1.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });
        System.out.println("Accessing the map in the descending order");
        map1.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEach(entry->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });

        /*
        We can seach the specific value by giving key in the hashset by using the get() method
         */
        String seachedEntry=map1.get(2);
        System.out.println("Search result for key 2 : "+seachedEntry);
       /*
       We can also check whether the value does exist or not by the key in the hashMap
        */
        boolean isFound=map1.containsKey(5);
        System.out.println("the key 3 does found in the map :"+((isFound)?" yes":" no"));
    }
    public static void basicMethodOfLinkedHashMap(){
        LinkedHashMap<Integer,String> linkedHashMap=new LinkedHashMap<Integer,String>();
        linkedHashMap.put(1,"Aman");
        linkedHashMap.put(2,"Rahul");
        linkedHashMap.put(4,"Rajat");
        linkedHashMap.put(3,"Shubham");
        System.out.println("the initial value of the linked HashMap table ");
        linkedHashMap.entrySet().forEach(entry ->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });
    }

    public static void basicMethodOfTreeSet(){
        TreeMap<Integer,String> treeMap = new TreeMap<>();
        treeMap.put(1,"ravi");
        treeMap.put(2,"pawan");
        treeMap.put(4,"kunal");
        treeMap.put(3,"rohit");
        treeMap.entrySet().forEach(entry->{
            System.out.println(entry.getKey()+" "+entry.getValue());
        });

    }
}
