package com.sparx.railway.ticketapp.backend.collection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        differentWaysToCreateHashSet();
        differentMethodsOfLinkedHashSet();
    }
    public static void differentWaysToCreateHashSet() throws IllegalAccessException, NoSuchFieldException {
//        HashSet<String> lhs=new LinkedHashSet<>();
//        Set<String> lhs2=new LinkedHashSet<>();
        LinkedHashSet<String> lhs1=new LinkedHashSet<>();
        lhs1.add("virat");
        lhs1.add("rohit");
        lhs1.add("dhoni");
        System.out.println("printing the initail value of the linked hashSet");
        for(String str : lhs1){
            System.out.println(str);
        }
        System.out.println("creating the linked hashset with the inital element of the collection ");
        LinkedHashSet<String> lhs2 = new LinkedHashSet<String>(Arrays.asList("vivek","ajay","ritesh"));
        for(String str : lhs2){
            System.out.println(str);
        }
        LinkedHashSet<String > lhs3=new LinkedHashSet<String>(5);
        lhs3.add("john");
        lhs3.add("joe");
        lhs3.add("peter");
        lhs3.add("peter");
        System.out.println("printing the value of the linked hashset that is being created with the fixed size");
        for(String str : lhs3){
            System.out.println(str);
        }
        System.out.println("printing the value of the linked hashset created with the fixed size amd the loading factor ");
        LinkedHashSet<String> lhs4=new LinkedHashSet<String>(5,0.5f);
        lhs4.addAll(lhs1);
        lhs4.addAll(lhs2);
        for(String str : lhs4){
            System.out.println(str);
        }
//        // Use reflection to access the "threshold" field (internally, the capacity threshold)
//        Field thresholdField = LinkedHashSet.class.getDeclaredField("threshold");
//        thresholdField.setAccessible(true);  // Make the private field accessible
//        int threshold = thresholdField.getInt(lhs4);
//
//        System.out.println("Threshold (estimated capacity): " + threshold);



    }
    public static void differentMethodsOfLinkedHashSet(){
        LinkedHashSet<String> lhs1=new LinkedHashSet<>(Arrays.asList("one","two","three","four","five","six","seven","eight"));
        lhs1.remove("three");
        System.out.println("After invoking the remove method");
        for(String str : lhs1){
            System.out.println(str);
        }
        lhs1.removeFirst();
        System.out.println("After removing the first element the linkedHashSet will be");
        for (String str : lhs1){
            System.out.println(str);
        }
        lhs1.removeLast();
        System.out.println("After removing the last element the linkedHashSet will be");
        for (String str : lhs1){
            System.out.println(str);
        }
        lhs1.clear();
        System.out.println("After invoking the clear method the linkedHashSet will be"+lhs1.toString());

    }
}
