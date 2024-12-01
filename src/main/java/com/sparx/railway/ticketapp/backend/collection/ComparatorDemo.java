package com.sparx.railway.ticketapp.backend.collection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
class StudentTest implements Comparator<StudentTest>{
    int rollno;
    String name;
    int age;
    StudentTest(int rollno,String name,int age){
        this.rollno=rollno;
        this.name=name;
        this.age=age;
    }

    public int getRollno() {
        return rollno;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compare(StudentTest o1, StudentTest o2) {
        if(o1.age > o2.age){
            return 1;
        }else if(o1.age < o2.age){
            return -1;
        }else if(o1.age == o2.age){
            return 0;
        }
        return 0;
    }
}
class AgeComparator implements Comparator {
    public int compare(Object o1,Object o2){
        StudentTest s1=(StudentTest)o1;
        StudentTest s2=(StudentTest)o2;

        if(s1.age==s2.age)
            return 0;
        else if(s1.age>s2.age)
            return 1;
        else
            return -1;
    }
}
class NameComparator implements Comparator{
    public int compare(Object o1,Object o2){
        StudentTest s1=(StudentTest)o1;
        StudentTest s2=(StudentTest)o2;

        return s1.name.compareTo(s2.name);
    }
}
class RoleComaprator implements Comparator{
    public int compare(Object o1,Object o2){
        StudentTest s1=(StudentTest)o1;
        StudentTest s2=(StudentTest)o2;

        if(s1.rollno==s2.rollno)
            return 0;
        else if(s1.rollno>s2.rollno)
            return 1;
        else
            return -1;
    }
}


public class ComparatorDemo {
    public static void main(String[] args) throws IOException {
//        basicMethodOfComporator();
//        ComporatorDemo();
//        basicMethodOfPropertiesClass();
        differentProperties();
    }
    public static void basicMethodOfComporator(){
        ArrayList<StudentTest> al=new ArrayList<StudentTest>();
        al.add(new StudentTest(1,"Virat",20));
        al.add(new StudentTest(2,"Rohit",21));
        al.add(new StudentTest(3,"David",20));
        System.out.println("printing the element of the List");
        al.stream().forEach(st->{
            System.out.println(st.rollno+" "+st.name+" "+st.age);
        });
        System.out.println("printing the element of the List in sorted mananer by Age");
        Collections.sort(al,new AgeComparator());
        al.stream().forEach(st->{
            System.out.println(st.rollno+" "+st.name+" "+st.age);
        });
        System.out.println("printing the element of the List in sorted mananer by Name");
        Collections.sort(al,new NameComparator());
        al.stream().forEach(st->{
            System.out.println(st.rollno+" "+st.name+" "+st.age);
        });
        System.out.println("printing the element of the List in sorted mananer by Roll No");
        Collections.sort(al,new RoleComaprator());
        al.stream().forEach(st->{
            System.out.println(st.rollno+" "+st.name+" "+st.age);
        });

    }
    public static void ComporatorDemo(){
        ArrayList<StudentTest> al=new ArrayList<StudentTest>();
        al.add(new StudentTest(101,"Vijay",23));
        al.add(new StudentTest(106,"Ajay",27));
        al.add(new StudentTest(105,"Jai",21));
        //Sorting elements on the basis of name
        Comparator<StudentTest> sTest=Comparator.comparing(StudentTest::getName);
        Collections.sort(al,sTest);
        al.stream().forEach(res ->{
            System.out.println(res.rollno+" "+res.name+" "+res.age);
        });


        System.out.println("Iterating the list sorted by the name where null should be the first element ");
        ArrayList<StudentTest> al2=new ArrayList<StudentTest>();
        al2.add(new StudentTest(101,"Vijay",23));
        al2.add(new StudentTest(106,"Ajay",27));
        al2.add(new StudentTest(105,null,21));
        Comparator<StudentTest> comp2=Comparator.comparing(StudentTest::getName,Comparator.nullsFirst(String::compareTo));
        Collections.sort(al2,comp2);
        al2.stream().forEach(str ->{
            System.out.println(str.rollno+" "+str.name+" "+str.age);
        });
        Comparator<StudentTest> comp3=Comparator.comparing(StudentTest::getName,Comparator.nullsLast(String::compareTo));
        Collections.sort(al2,comp3);
        System.out.println("iterating the list according to the order of name and the null should be the last eleemnt");
        al2.stream().forEach(str ->{
            System.out.println(str.rollno+" "+str.name+" "+str.age);
        });

    }
    public static void basicMethodOfPropertiesClass(){
        Properties p=System.getProperties();
        Set set=p.entrySet();

        Iterator itr=set.iterator();
        while(itr.hasNext()){
            Map.Entry entry=(Map.Entry)itr.next();
            System.out.println(entry.getKey()+" = "+entry.getValue());
        }

    }
    public static void differentProperties() throws IOException {
        FileReader reader=new FileReader("/home/spxlpt171/Downloads/railway.ticketapp.backend/src/main/resources/application.properties");

        Properties p=new Properties();
        p.load(reader);

        System.out.println(p.getProperty("spring.application.name"));
        p.entrySet().stream().forEach(entry ->{
            System.out.println(entry.getKey()+" = "+entry.getValue());
        });
//        System.out.println(p.getProperty("password"));

    }

}
