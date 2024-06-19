import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnagramString {
    public static void main(String[] args) {
        String string1 = "heart";
        String string2 = "earth";
        AnagramString.isAnagram(string1, string2);
    }

    private static boolean isAnagram(String string1, String string2) {
        char[] arr = string1.toCharArray();
        List<Character> charList = new ArrayList<>();
        for(char ch : arr)
            charList.add(ch);
        for(char ch : string2.toCharArray())
            if (charList.contains(ch)) 
                charList.remove(Character.valueOf(ch));
        if (charList.isEmpty()) {
            System.out.println("true");
            return true;
        }
        return false;
    }
}
