package com.sparx.railway.ticketapp.backend.collection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class ArrayListDemo {
    public static void main(String [] args){
//        waysTODeclareTheArrayList();
//        gettingAndSettingValues();
//        SerizlationAndDesearlization();
//        addingAndRemoving();
        retainAndRemoveAll();
    }
    public static void waysTODeclareTheArrayList(){
        // to creata an empty arrayList
        List<Integer> list1=new ArrayList<Integer>();
        // to create an arrayList with initial values
        Integer[] arr={1,2,3,4};
        List<Integer> list2=Arrays.asList(arr);
        //or
        List<Integer> list3=new ArrayList<>(Arrays.asList(arr));
        // creating array with the initial capacity
        List<Integer> list4=new ArrayList<>(10);

        // adding elements to the list
        list4.add(1);
        list4.add(2);
        list4.add(3);
        list4.add(4);
        list4.add(5);

        // different ways to print the value from the list
        Iterator itr=list4.iterator();
        while(itr.hasNext()){
            System.out.println("printing the value using the iterator :"+itr.next());
        }
        for(int i : list4){
            System.out.println("Pinting the value using the ehanced for loop"+i);
        }
        ArrayList oldArrayListDef=new ArrayList();
        oldArrayListDef.add(10);
        oldArrayListDef.add("virat");
        oldArrayListDef.add(true);
        oldArrayListDef.add('c');
        System.out.println("diffent datatype stored in the same arrayList"+oldArrayListDef.toString());



    }
    public static void gettingAndSettingValues(){
        List<String > strArray=new ArrayList<String>();
        strArray.add("virat");
        strArray.add("rohit");
        strArray.add("dhoni");
        strArray.add("abd");
        System.out.println("getting the value of the 2 nd Index:"+strArray.get(2));
        strArray.set(2, "sachin");
        System.out.println("ArrayAfter Setting the value at index 2:"+strArray.toString());
        Collections.sort(strArray);
        System.out.println("Array after sorting:"+strArray.toString());
        // traversing the arrayList in the reverse order
        ListIterator<String> listIterator =strArray.listIterator(strArray.size());
        System.out.println("Traversing the array through the list iterator ");
        while(listIterator.hasPrevious()){
            System.out.println(listIterator.previous());
        }
        // accesing the list using the for loop
        System.out.println("travesing the arrayList using the for loop method ");

        for(int i=0;i<strArray.size();i++){
            System.out.println(strArray.get(i));
        }
        // accesing the arrayList using the for-Each method
        System.out.println("travesing the arrayList using the for-Each method");

        strArray.forEach( element ->{
            System.out.println(element);
        });

        // accesing  the element using the for-EachRemaining method
        System.out.println("travesing the arrayList using the for-EachRemaining method");
        Iterator listItr= strArray.iterator();
        listItr.forEachRemaining( element ->{
           System.out.println(element);
        });

    }
    public static void SerizlationAndDesearlization(){
        ArrayList<String> arr=new ArrayList<String>();
        arr.add("virat");
        arr.add("rohit");
        arr.add("mahi");
        System.out.println("the value of the arrayList is "+arr);
        try{
            // searlization
            FileOutputStream file=new FileOutputStream("file");
            ObjectOutputStream oos=new ObjectOutputStream(file);
            oos.writeObject(arr);
            file.close();
            oos.close();
            // deserilzation of the object
            FileInputStream fis=new FileInputStream("file");
            ObjectInputStream ois=new ObjectInputStream(fis);
            ArrayList<String> arrayList= (ArrayList<String>) ois.readObject();
            System.out.println("the value is being deserialized"+arrayList.toString());
            ois.close();
            fis.close();


        }catch (Exception ex){
            System.out.println("the error log "+ex.getMessage());

        }
    }
    public static void addingAndRemoving(){

        ArrayList<String> list=new ArrayList<>();
        list.add("virat");
        list.add("rohit");
        list.add("steeve");

        System.out.println("the initatial list is "+list.toString());
        ArrayList<String> list2=new ArrayList<>();
        list2.add("virat1");
        list2.add("rohit1");
        list2.add("steeve1");
        list.addAll(list2);
        System.out.println("after adding the lists2 : "+list.toString());
        // after removing the list2
        list.removeAll(list2);
        System.out.println("after removing the lists2 : "+list.toString());
        // adding the list 2 at the intial position
        list.addAll(0,list2);
        System.out.println("the value is being added at the initial position "+list.toString());
        // after removing the list 2 from the initial position
        list.remove(0);
        System.out.println("after removing the list2 from the initial position "+list.toString());
        // adding the list 2 at the end of the list
        list.addAll(list2);
        System.out.println("the value is being added at the end of the list "+list.toString());
        // after removing the list 2 from the end of the list
        list.remove(list2.size()-1);
        System.out.println("after removing the list2 from the end of the list "+list.toString());
        // after removing the element at the initial position
        list.remove(0);
        System.out.println("after removing the element at the initial position "+list.toString());
        // after removing the element at the end position
        list.remove(list.size()-1);
        System.out.println("after removing the element at the end position "+list.toString());
        // after removing the element
        list.remove("rohit");
        System.out.println("after removing the element 'virat' "+list.toString());
        // using removeif method
        list.removeIf(s -> s.equals("virat1"));
        System.out.println("after removing the element 'virat1' using removeIf method "+list.toString());



    }

    public static void retainAndRemoveAll(){
        List<String> list1=new ArrayList<>();
        list1.add("virat");
        list1.add("rohit");
        list1.add("dhoni");
        list1.add("pant");
        List<String> list2=new ArrayList<>();
        list2.add("virat");
        list2.add("rohit");
        list2.add("ishan");
        // after using the rerainAll
        list1.retainAll(list2);
        System.out.println("after using the retainAll method : "+list1.toString());
        list1.clear();
        System.out.println("the list after using the clear method "+list1.toString());

    }


}
