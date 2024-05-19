public class PrimeNumber {
    static void isPrimeNumber(int a) {
        if (a > 1) {
            if (a == 2) {
                System.out.println("true");
                return;
            }
            for (int i = 2; i < a / 2; i++) {
                if (a % i == 0) {
                    System.out.println("false");
                    return;
                }
            }
        } else {
            System.out.println("false");
            return;
        }
        System.out.println("true");
    }

    public static void main(String args[]) {
        PrimeNumber.isPrimeNumber(2);
    }
}
