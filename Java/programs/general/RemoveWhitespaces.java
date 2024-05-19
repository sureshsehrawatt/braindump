public class RemoveWhitespaces {
    static String removeSpaces(String str) {
        StringBuilder res = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String res = RemoveWhitespaces.removeSpaces("I am Mayank");
        System.out.println(res);
    }
}
