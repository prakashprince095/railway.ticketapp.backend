package com.sparx.railway.ticketapp.backend.collection;

import java.util.*;

public class HashSetDemo {
    public static void main(String[] args)  {
//        differetWayToCreateHasgSet();
//        differentMethodofHashSet();
        exporingSomeMoreMethod();
    }
    public static void differetWayToCreateHasgSet(){

        Set<String> set1=new HashSet<>();
        set1.add("virat");
        set1.add("rohit");
        set1.add("dhoni");
        Iterator itr =set1.iterator();
        System.out.println("Iterating through the HashSet ");
        while(itr.hasNext()){
            System.out.print(itr.next()+" ");
        }
        System.out.println("Iterating the hashSet created with the initial value ");
        /*
        we can create the hashset with the initail value but it is not fixed it increase its size when 75% is filled
         */
        Set<String> set2 = new HashSet<String>(3);
        set2.add("michal");
        set2.add("shane");
        set2.add("steeve");
        set2.add("peter");
        Iterator itr2=set2.iterator();
        while(itr2.hasNext()){
            System.out.println(itr2.next()+" ");
        }

        Set<String> set3=new HashSet<>(5,0.5f);
        set3.add("messy");
        set3.add("ronaldo");
        set3.add("nemar");
        set3.add("sunil");
        set3.add("alberto");
        set3.add("aliano");
        System.out.println("Iterating the set that is created with the initial capaccity and loadfactor");
        for(String str : set3){
            System.out.println(str);
        }
        List<String> list=new ArrayList<String>(Arrays.asList("pawan", "ravi", "vivek"));
        System.out.println("Iterating the set created with the elements of the collection");
        Set<String> set4=new HashSet<String>(list);
        for(String str : set4){
           System.out.println(str);
        }
    }
    public static void differentMethodofHashSet(){
        Set<String> set1=new HashSet<>();
        set1.add("virat");
        set1.add("rohit");
        set1.add(null);
        set1.add("dhoni");
        set1.add("rohit");
        set1.add(null);
        System.out.println("printing the hashSet demonstarting the behaviour fro null and duplicate value");
        for(String str : set1){
            System.out.println(str);
        }
        set1.remove("dhoni");
        System.out.println("After invoking the remove method : "+set1.toString());
        Set<String> set2=new HashSet<String>();
        set2.add("rajat");
        set2.add("maxwell");
        set1.addAll(set2);
        System.out.println("After invoking the addAll method : "+set1.toString());
        set1.removeAll(set2);
        set1.remove(null);
        System.out.println("After invoking the removeAll method : "+set1.toString());
        set1.removeIf(res-> res.contains("rohit"));
        System.out.println("After invoking the removeIf method : "+set1.toString());
        set1.clear();
        System.out.println("After invoking the clear method"+set1.toString());

    }
    public static void exporingSomeMoreMethod(){
        List<String> list1=new ArrayList<String>(Arrays.asList("rohit","virat","mahi","tilak","hardik"));
        List<String> list2=new ArrayList<String>(Arrays.asList("david","tilak","steve"));

        Set<String> set1=new HashSet<>(list1);
        System.out.println("the element of set 1");
        for(String str :set1){
            System.out.println(str);
        }
        Set<String> set2=new HashSet<>(list2);
        System.out.println("the element stored in the set2");
        for(String str : set2){
            System.out.println(str);
        }
        /*
        performing the union operation on the set
         */
        set1.addAll(set2);
        System.out.println("after performing the union operation the value of the list ");
        for(String str: set1){
            System.out.println(str);
        }
        /*
        After performing finding the differnece between tow set
         */
        set1.removeAll(set2);
        System.out.println("after performing the difference operation the value of the list ");
        for(String str: set1){
            System.out.println(str);
        }

        List<String> list3=new ArrayList<String>(Arrays.asList("rohit","virat","mahi","tilak","hardik"));
        List<String> list4=new ArrayList<String>(Arrays.asList("david","tilak","steve","virat"));
        System.out.println("the value of the list 3 is "+list3.toString());
        System.out.println("the value of the list 4 is "+list4.toString());
        /*
        performing the intersection operation on the list
         */
        List<String> intersection=new ArrayList<>(list3);
        intersection.retainAll(list4);
        System.out.println("after performing the intersection operation the value of the list ");
        for(String str: intersection){
            System.out.println(str);
        }

        /*
        performing the symmetric difference operation on the list
         */
        List<String> symmetricDifference=new ArrayList<>(list3);
        symmetricDifference.addAll(list4);
        symmetricDifference.removeAll(intersection);
        System.out.println("after performing the symmetric difference operation the value of the list ");
        for(String str: symmetricDifference){
            System.out.println(str);
        }




    }


}
