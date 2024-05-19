public class ContainsVowel {
    static boolean containVowel(String str) {
        return str.toLowerCase().matches(".*[aeiou].*");
    }

    public static void main(String artgs[]) {
        boolean check = ContainsVowel.containVowel("Hello");
        System.out.println(check);
    }
}
