public class ReplaceCharsWithAsterisk {
    public static void main(String[] args) {
        String input = "Hello World 123";
        String output = replaceChars(input);
        System.out.println(output);
    }

    public static String replaceChars(String input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (Character.isWhitespace(ch)) {
                sb.append(ch); // Keep whitespace as it is
            } else {
                sb.append('*'); // Replace other characters with '*'
            }
        }

        return sb.toString();
    }
}
