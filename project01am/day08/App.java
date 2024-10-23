package day08;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws IOException {

        List<Product> products = new ArrayList<>();
        
        LocalDate ldCreated = LocalDate.of(2024, 10, 21);
        Date createDt = Date.from(ldCreated.atStartOfDay(ZoneId.systemDefault()).toInstant());

        products.add(new Product(1L, "mouse", "mouse input", "computer", 99.0F, createDt));
        products.add(new Product(2L, "keyboard", "keyboard input", "computer", 235.5F, createDt));
        products.add(new Product(3L, "15.6 inch monitor", "display panel", "computer", 157.0F, createDt));
        products.add(new Product(4L, "Huawei Pura 70 Ultra", "Huawei Phone", "mobile", 900.0F, createDt));
        products.add(new Product(5L, "Huawei Mate X50 Pro", "Huawei Phone", "mobile", 1200.0F, createDt));
        products.add(new Product(6L, "iPhone 16 Pro", "iPhone", "mobile", 2000.0F, createDt));
        products.add(new Product(7L, "iPhone 14 Pro", "iPhone", "mobile", 1800.0F, createDt));

        // for (Product p : products) {
        //     System.out.println(p); // Implicitly calls toString() and prints each product on a new line
        // }

        // to filter something from a list:
        List<Product> filteredProducts = new ArrayList<>(); // 1. create the filtered list by instantiating it
        filteredProducts = products.stream().filter(product -> "mobile".equals(product.getProdCat()) && product.getPrice() > 1500).collect(Collectors.toList()); // 2. use the stream->filter->collect approach to collect list of filtered items
        filteredProducts.forEach(System.out::println); // 3. print a line for each filtered product; the system.out::println is req when u use forEach() as it expects a Consumer

        // to write the list of filtered objects to a file
        FileWriter fileWriter = new FileWriter(args[0], false); // args[0] refers to the 1st arg passed to Java application when run it from command line; false to overwrite while true to append to file
        BufferedWriter writer = new BufferedWriter(fileWriter); // fr is like writing a single letter then mailing it straight while br is writing a few letters and compiling them in a box to mail tgt, so br makes it more efficient to write to a file.
        
        try (writer) {
            for (Product product : filteredProducts) {  // for-each loop

                writer.write(product.toString());  // writer is like the pen and .write is telling it to write
                writer.newLine();  // Adding a new line after each product
            }
            
            System.out.println("Filtered products written to " + args[0]);
        }
        
        catch (IOException e) {
            e.printStackTrace();
        }

        // using comparator to perform sorting (single column comparison)
        Comparator<Product> comparator1 = Comparator.comparing(Product::getPrice);
        products.sort(comparator1); // to sort in ascending which is the default
        products.forEach(System.out::println);

        Comparator<Product> comparator2 = Comparator.comparing(Product::getPrice);
        products.sort(comparator2.reversed()); // .reversed() to sort in descending
        products.forEach(System.out::println);

        // sort an array of string "Bernard" "Zachary" "Alpha" "Theophilis" "Sammy" "Christopher"
        
        // sort ascending and print
        String arr[] = { "Bernard", "Zachary", "Alpha", "Theophilis", "Sammy", "Christopher" };
        Arrays.sort(arr);
        System.out.println("Names in ascending order: " + Arrays.toString(arr));
        // note: arr is just a REFERENCE to an array object that holds the names and not the array obj itself so arr.toString() only prints the memory addr instead of its contents.
        //       Arrays.toString(arr) is a special method from the Arrays class that knows how to handle arrays specifically.
        
        // sort descending and print
        Arrays.sort(arr, Collections.reverseOrder());
        System.out.println("Names in descending order: " + Arrays.toString(arr));
        // Collections is a utility class that provides various static methods for operating on collections (like lists, sets, and maps).
        // can sort/search/shuffle/fill/reverse/modify collections.

        // creating and sorting map objects
        Map<String, Integer> mapObjects = new HashMap<>();
        mapObjects.put("Adam", 45);
        mapObjects.put("Barry", 67);
        mapObjects.put("Charlie", 76);
        mapObjects.put("Danny", 88);
        mapObjects.put("Ethan", 92);
        mapObjects.put("Fred", 55);
        mapObjects.forEach((k,v) -> System.out.println(k + ":" + v));

        List<Entry<String, Integer>> mapList = new ArrayList<>(mapObjects.entrySet());
        mapList.sort(Entry.comparingByValue());
        mapList.forEach(System.out::println);

        Map<String, Integer> sortedMap = mapObjects.entrySet().stream().sorted(Entry.comparingByValue()).collect(Collectors.toMap(Entry::getKey,Entry::getValue,(e1, e2) -> e1,LinkedHashMap::new));
        // .entrySet() gives you a set of all the entries in the map where each entry is a pairing of a key and value.
        // .stream() converts the collection of entries into a flow of data that allows you to perform various operations in a more readable way.
        // .sorted(Entry.comparingByKey()) to sort based on keys/values
        // .collect() takes all the sorted entries and puts them back into a new map with the same key-value pair but sorted by keys/values.
        // (e1, e2) -> e1 is req to avoid duplicates and LinkedHashMap::new is req to ensure sorting is produced.
        sortedMap.forEach((k,v) -> System.out.println(k + ": " + v));

        // Threading recap
        MyImplementation myImpl01 = new MyImplementation();
        MyImplementation myImpl02 = new MyImplementation();
        MyImplementation myImpl03 = new MyImplementation();

        ExecutorService es = Executors.newFixedThreadPool(3);
        es.execute(myImpl01);
        es.execute(myImpl02);
        es.execute(myImpl03);
        es.shutdown();

        // calling functional interface method through the class
        ArithmaticOperation ao = new ArithmaticOperation();
        System.out.println("Addition Results: " + ao.AddOperation(3, 5));
        System.out.println("Multiplication Results: " + ao.MultiplyOperation(3, 5));
        System.out.println("Subtraction Results: " + ao.SubtractOperation(3, 5));














    }
}
