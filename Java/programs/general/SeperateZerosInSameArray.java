public class SeperateZerosInSameArray {
    public static void seperate(int[] arr) {
        int length = arr.length;
        int rev = length - 1;
        for (int i = 0; i < length; i++) {
            if (i >= rev)
                break;
            if (arr[i] == 0) {
                while (arr[rev] == 0)
                    rev--;
                arr[i] = arr[rev];
                arr[rev] = 0;
            }
        }
        for (int i : arr)
            System.out.print(i + " ");
    }

    public static void main(String[] args) {
        int[] arr = { 1, 4, 7, 0, 3, 6, 0, 7, 4, 0, 8, 6, 0, 9, 0, 0, 3, 6, 1 };
        SeperateZerosInSameArray.seperate(arr);
    }
}
