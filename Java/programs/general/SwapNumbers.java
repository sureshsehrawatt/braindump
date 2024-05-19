public class SwapNumbers {
    static void swap(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        System.out.println("a = " + a + "\nb = " + b);
    }

    static void swap2(int a, int b) {
        b = (a+b) - (a=b);
    }

    public static void main(String args[]) {
        SwapNumbers.swap(5, 10);
    }
}
