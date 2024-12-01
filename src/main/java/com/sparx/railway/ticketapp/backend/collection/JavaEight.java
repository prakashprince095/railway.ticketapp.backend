package com.sparx.railway.ticketapp.backend.collection;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@FunctionalInterface
interface Converter<F , T> {
    T convert(F from);
}
interface MessageDisplay {
    // Static method to display a static greeting message.
    static void showStaticMessage() {
        // Print a static greeting message to the console.
        System.out.println("Static Greeting: Welcome!");
    }
    // Abstract method to be implemented by classes for executing a custom action with a string input.
    void executeCustomAction(String input);
}
class ActionExecutor implements MessageDisplay{
    public  static void main(String[] args) {
        MessageDisplay.showStaticMessage();
        new ActionExecutor().executeCustomAction("Hello World!");
    }
    @Override
    public void executeCustomAction(String input) {
        System.out.println("Executing custom action: " + input);
    }
}
public class JavaEight {
    public static void main (String [] args){
//        lambdaFunction();
//        methodRefernece();
//        functionalInterface();
//        optionalDemo();
//        forEachDemo();
//        basicDateTimeMethods();
//          stringJoiner();
//        collectorsExample();
//        streamAndFilterDemo();
//        encodingDecodingDemo();
        parallelSortArrayDemo();
    }
    public static void lambdaFunction(){
        List<String> languages = Arrays.asList("Java", "Python", "JavaScript", "C++","Php");
        System.out.println("languages starts with J");
        languages.stream().filter(res-> res.startsWith("P")).forEach(res-> System.out.println("reslts are "+res));

        filter(languages,(res-> res.startsWith("J")));

    }
    public static void filter(List<String> names, Predicate<String> condition) {
        List<String> result = new ArrayList<>();
        for (String name : names) {
            if (condition.test(name)) {
                result.add(name);
            }
        }
        System.out.println(result);

    }

    public static void methodRefernece(){
        // static method  refernce
        List<String> sList=Arrays.asList("Java", "Python", "JavaScript", "C++","Php");
        sList.stream().map(String::toUpperCase).collect(Collectors.toList()).forEach(res->{
            System.out.println("results are "+res);
        });
        // instance method refernce
        System.out.println("printing the list of results");
        sList.forEach(System.out::println);

        // constructor refrence : creating new refrence

        Supplier<List<String>> listSupplier = ArrayList::new; // constructor reference
        List<String> newList=listSupplier.get();
        newList.addAll(sList);
        System.out.println("iterating the list created using constructor referene");
        newList.forEach(System.out::println);

        // Additional Example: Using Function Interface for Constructor Reference
        Function<String, Integer> stringToInteger = Integer::new; // constructor reference
        Integer number = stringToInteger.apply("100");
        System.out.println("String to Integer: " + number);

    }
    public static void functionalInterface(){
        // Using the Converter functional interface with a lambda expression
        Converter<String , Integer> stringToInteger = Integer::valueOf;
        // Applying the converter to convert a string to an integer
        int convertedValue = stringToInteger.convert("123");
        System.out.println("Converted Value: " + convertedValue);
        // Another example, converting case of a string
        Converter<String , String> upperCaseConverter = String::toUpperCase;
        String convertedString = upperCaseConverter.convert("java");
        System.out.println("Converted String: " + convertedString);
    }
    /*
        used to avoid null pointer exception
     */
    public static void optionalDemo(){
        String[] strArray=new String[10];
        strArray[5]="Hello World";
        Optional<String> checkNull= Optional.ofNullable(strArray[6]);
        if(checkNull.isPresent()){
            String word=strArray[5];
            System.out.println("Value is: "+word.toUpperCase());
        }else{
            System.out.println("Value is null");
        }
    }
    public static void forEachDemo(){
        List<String> list=Arrays.asList("virat","rohit","mahi","test","dhoni");
        list.forEach(res->{
            System.out.println("results are "+res);
        });

    }
    public static void basicDateTimeMethods(){
        LocalDate currentDate = LocalDate.now();
        System.out.println("todays date is  "+currentDate);
        System.out.println("Printing the future date"+currentDate.plusDays(1));
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Printing the date in the DD/MM/YY format :"+formatter.format(currentDate));
        //Parsing a date to the format
        String dateToBeParsed="25/10/2023";
        LocalDate parsedDate= LocalDate.parse(dateToBeParsed, formatter);
        System.out.println("Parsed date :"+parsedDate);

    }
    public static void stringJoiner(){
        StringJoiner joiner=new StringJoiner(",","[","]");
        joiner.add("Java").add("Python").add("JavaScript");
        System.out.println(joiner.toString());
    }
    public static void collectorsExample(){
       List<String> list= Arrays.asList("one","two","three","four","five","six");
       List list1=list.stream().map(item -> item+" m").collect(Collectors.toList());
       list1.stream().forEach(res ->{
           System.out.println(res);
       });
    }
    public static void streamAndFilterDemo(){
        List<String> sList=Arrays.asList("virat","vvs","rohit","vinay");
        sList.stream().forEach(str->
                System.out.println("printing the value of the list using the stream "+str)
                );
        sList.stream().filter(str->(
            str.startsWith("v")
        )).forEach(str -> System.out.println("Printing the value after the modifications : "+str));

    }
    public static void encodingDecodingDemo(){
        String orignalString ="Hello, world this message is for encoding the large String representation so that break can be seen eaisly " ;
        String encryptedData= Base64.getEncoder().encodeToString(orignalString.getBytes(StandardCharsets.UTF_8));
//        String encryptedData = Base64.getEncoder().encodeToString(originalString.getBytes(StandardCharsets.UTF_8));
        System.out.println("Encrypted Data: "+encryptedData);
        byte[] decodedBytes=Base64.getDecoder().decode(encryptedData);
        String decodedString=new String(decodedBytes, StandardCharsets.UTF_8);
        System.out.println("the value in the decrpted form"+decodedString);

        String encodedUrl= Base64.getUrlEncoder().encodeToString(orignalString.getBytes());
        System.out.println("printing the encoded String (URL) :"+encodedUrl);
        String mimeEncodeUrl=Base64.getMimeEncoder().encodeToString(orignalString.getBytes());
        System.out.println("Printing the value of the encoded String "+mimeEncodeUrl);


    }
    public static void parallelSortArrayDemo(){
        int [] arr={10,3,2,2,3,41,10,11};
        System.out.println("Before sorting :"+Arrays.toString(arr));
        Arrays.parallelSort(arr);
        System.out.println("After sorting :"+Arrays.toString(arr));
        String [] namedArray={"virat","rohit","vivek","pawan"};
        System.out.println("Arrays before sorting"+Arrays.toString(namedArray));
        Arrays.parallelSort(namedArray);
        System.out.println("Arrays after sorting"+Arrays.toString(namedArray));
        int [] arr2={10,3,23,45,65,6,7,8,9,10,8};
        System.out.println("Before sorting :"+Arrays.toString(arr2));
        Arrays.sort(arr2);
        System.out.println("After sorting :"+Arrays.toString(arr2));
        String [] namedArray2={"one","two","three","four","five","six","seven","eight"};
        System.out.println("Arrays before sorting"+Arrays.toString(namedArray2));
        Arrays.sort(namedArray2);
        System.out.println("Arrays after sorting"+Arrays.toString(namedArray2));
    }




    }
