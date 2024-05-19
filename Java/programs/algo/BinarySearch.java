public class BinarySearch {
    static int binarySearch(int[] array, int key) {
        int low = 0, high = array.length - 1;
        int mid = (low + high) / 2;
        while (low <= high) {
            if (array[mid] == key) {
                return mid;
            } else if (array[mid] < key) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = (low + high) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] array = { 2, 4, 6, 8, 10, 12, 14, 16, 18, 20 };
        System.out.println(BinarySearch.binarySearch(array, 18));
    }
}
