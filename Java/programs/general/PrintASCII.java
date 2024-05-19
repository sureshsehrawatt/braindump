public class PrintASCII {
    static void print() {
        for (int i = 0; i < 256; i++)
            System.out.println("The ASCII value of " + (char) i + "  =  " + i);
    }

    public static void main(String[] args) {
        PrintASCII.print();
    }
}
