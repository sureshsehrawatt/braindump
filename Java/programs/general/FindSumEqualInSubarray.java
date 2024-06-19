import java.util.ArrayList;
import java.util.List;

public class FindSumEqualInSubarray {
    public static boolean isSumPresentInSumArray(int[] arr, int num){
        for(int i=0; i<arr.length; i++){
            List<Integer> list = new ArrayList<>();
            int sum = 0;
            for(int j=i; j<arr.length; j++){
                list.add(arr[j]);
                sum += arr[j];
                if (sum == num) {
                    System.out.println(list.toString());
                    return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(FindSumEqualInSubarray.isSumPresentInSumArray(arr, 9));
    }
}
