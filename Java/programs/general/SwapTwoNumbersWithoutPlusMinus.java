public class SwapTwoNumbersWithoutPlusMinus {
    static void calculate(int a, int b) {
        a = a * b; 
        b = a / b; 
        a = a / b;
        System.out.println("a: "+a+" b: "+b);
    }
    public static void main(String[] args) {
        SwapTwoNumbersWithoutPlusMinus.calculate(2, 3);
    }
}
