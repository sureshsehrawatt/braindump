import java.util.HashSet;
import java.util.Set;

public class ArrayContainsSameElements {
    static boolean compareArrays(int arr1[], int arr2[]) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> set2 = new HashSet<>();

        for (int i : arr1)
            set1.add(i);
        for (int i : arr2)
            set2.add(i);

        if (set1.size() != set2.size())
            return false;

        for (int i : set1)
            if (!set2.contains(i))
                return false;
        return true;
    }

    public static void main(String[] args) {
        int[] arr1 = { 1, 2, 3, 4, 3, 2, 1 };
        int[] arr2 = { 4, 4, 3, 3, 2, 2, 1, 1, 5 };
        System.out.println(ArrayContainsSameElements.compareArrays(arr1, arr2));
    }
}
