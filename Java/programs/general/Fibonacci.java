public class Fibonacci {

    static void printFibonacci(int num) {
        int a = 0, b = 1, c = 1;

        for (int i = 1; i <= num; i++) {
            System.out.print(a + " ");
            a = b;
            b = c;
            c = a + b;
        }
    }

    public static void main(String args[]) {
        Fibonacci.printFibonacci(10);
    }
}
