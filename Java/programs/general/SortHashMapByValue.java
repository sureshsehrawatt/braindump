import java.util.HashMap;
import java.util.Map;

public class SortHashMapByValue {
    static void sort(Map<String, Integer> hashMap) {
        int[] arr = new int[hashMap.size()];
        int i = 0;
        Map<Integer, String> hashMap2 = new HashMap<>();
        Map<Integer, String> hashMap3 = new HashMap<>();

        for (Map.Entry<String, Integer> entry : hashMap.entrySet()) {
            arr[i] = entry.getValue();
            hashMap2.put(entry.getValue(), entry.getKey());
            i++;
        }

        for (int j : arr) {
            hashMap3.put(j, hashMap2.get(j));
        }
        System.out.println(hashMap3.toString());
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Mayank", 98);
        map.put("Jai", 97);
        map.put("Gourav", 100);
        map.put("Madhur", 96);
        SortHashMapByValue.sort(map);
    }
}
