package map;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class HashMapPrgm {
    public static void main(String[] args) {
        // HashMap example
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);

        // Hashtable example
        Hashtable<String, Integer> hashTable = new Hashtable<>();
        hashTable.put("One", 1);
        hashTable.put("Two", 2);
        hashTable.put("Three", 3);

        // Null value handling in HashMap
        hashMap.put(null, null);
        // hashTable.put(null, null);
        System.out.println("HashMap: " + hashMap);
        System.out.println("Hashtable: " + hashTable);

        // Null value handling in Hashtable (throws NullPointerException)
        // hashTable.put(null, null); // Uncommenting this line will throw NullPointerException

        // Thread-safe operations with Hashtable
        // Hashtable is synchronized, so it is thread-safe by default
    }
}
