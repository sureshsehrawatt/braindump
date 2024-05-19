import java.util.ArrayList;

public class ReverseString {
    static String reverse(String str) {
        String rev = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            rev += str.charAt(i);
        }
        return rev;
    }

    static String reverseStringWithSpaces(String str) {
        String[] arr = str.split(" ");
        String rev = "";
        for (String s : arr) {
            rev += reverse(s) + " ";
        }
        return rev.trim();
    }

    static String reverseStringWithFixSpaces(String str) {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == ' ')
                indexes.add(i);

        String res = "";
        int index = 0;
        for (int i = str.length() - 1; i >= 0; i--) {
            if (indexes.size() > 0 && index == indexes.get(0)) {
                res += " ";
                indexes.remove(0);
                i++;
            } else if (str.charAt(i) != ' ') {
                res += str.charAt(i);
            }
            index++;
        }
        return res;
    }

    public static void main(String[] args) {
        String result = ReverseString.reverse("Mayank");
        System.out.println(result);

        String res = ReverseString.reverseStringWithSpaces("Mayank Jaiswal");
        System.out.println(res);

        String res2 = ReverseString.reverseStringWithFixSpaces("I am Mayank Jaiswal");
        System.out.println(res2);

    }
}
