import java.util.Arrays;

public class SecondLargestNum {
    static int find(int arr[]) {
        Arrays.sort(arr);
        return arr[arr.length - 2];
    }

    public static void main(String[] args) {
        int[] arr = { 4, 2, 6, 2, 7, 3, 9, 1 };
        System.out.println(SecondLargestNum.find(arr));
    }
}
