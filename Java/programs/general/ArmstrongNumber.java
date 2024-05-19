public class ArmstrongNumber {
    static boolean isArmstrong(int num) {
        int orignalNum = num;
        int pow = String.valueOf(num).length();
        int sum = 0;

        while (num > 0) {
            int temp = num % 10;
            num /= 10;
            sum += Math.pow(temp, pow);
        }

        System.out.println(orignalNum == sum);
        return orignalNum == sum;
    }

    public static void main(String[] args) {
        ArmstrongNumber.isArmstrong(153);
    }
}
