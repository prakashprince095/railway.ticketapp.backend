package com.sparx.railway.ticketapp.backend.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
class Student implements Comparable<Student> {
    public String name;
    public Student(String name) {
        this.name = name;
    }
    public int compareTo(Student person) {
        return name.compareTo(person.name);

    }
}
*/
class Student implements Comparable<Student>{
    int rollno;
    String name;
    int age;
    Student(int rollno,String name,int age){
        this.rollno=rollno;
        this.name=name;
        this.age=age;
    }

    public int compareTo(Student st){
        if(age==st.age)
            return 0;
        else if(age>st.age)
            return 1;
        else
            return -1;
    }
}


public class CollectionDemo {
    public static void main(String[] args){
//        basicCollectionMethods();
        basicMethodOfComparable();
    }
    public static void basicCollectionMethods(){
        List<String> list=new ArrayList<String>(Arrays.asList("virat","rohit","david","steeve"));
        System.out.println("printing the initial value of the list"+list.toString());
        Collections.addAll(list,"babar","saheen","nasim");
        System.out.println("printing the initial value of the list"+list.toString());
        String[] strArr = {"C#", ".Net"};
        Collections.addAll(list, strArr);
        System.out.println("After adding array collection value:"+list);
        List<Integer> list2 = new ArrayList<Integer>();
        list2.add(46);
        list2.add(67);
        list2.add(24);
        list2.add(16);
        list2.add(8);
        list2.add(12);
        System.out.println("Value of maximum element from the collection: "+Collections.max(list2));
        System.out.println("Value of minimum element from the collection: "+Collections.min(list2));
        Collections.sort(list2);
        System.out.println("After sorting the list: "+list2);
        list2.forEach(item->{
            System.out.println(item);
        });
        /*
        ArrayList<Student> al=new ArrayList<Student>();
        al.add(new Student("Viru"));
        al.add(new Student("Saurav"));
        al.add(new Student("Mukesh"));
        al.add(new Student("Tahir"));
         System.out.println("printing the object in the sorted manner");
        Collections.sort(al);
        for (Student s : al) {
            System.out.println(s.name);
        }
        */



    }
    public static void basicMethodOfComparable(){
        ArrayList<Student> al=new ArrayList<Student>();
        al.add(new Student(101,"Vijay",23));
        al.add(new Student(106,"Ajay",27));
        al.add(new Student(105,"Jai",21));

        Collections.sort(al);
        for(Student st:al){
            System.out.println(st.rollno+" "+st.name+" "+st.age);
        }
    }
}
