public class Palindrome {
    static void isPalindrome(String str) {
        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - i - 1)) {
                System.out.println("false");
                return;
            }
        }
        System.out.println("true");
    }

    public static void main(String args[]) {
        Palindrome.isPalindrome("aman");
    }
}
