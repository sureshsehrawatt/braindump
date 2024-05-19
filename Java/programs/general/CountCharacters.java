import java.util.HashMap;
import java.util.Map;

public class CountCharacters {
    static void countChars(String str) {
        Map<Character, Integer> map = new HashMap<>();

        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        System.out.println(map);
    }

    public static void main(String[] args) {
        CountCharacters.countChars("hdegJHSGbSbBBSkSJbhSjhbSDjbWS");
    }
}
