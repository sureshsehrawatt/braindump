public class FirstUniqueString {
    
    public static void main(String[] args) {
        String[] arr = {"Apple", "Computer", "Apple", "Bags"};
        System.out.println(FirstUniqueString.firstUnique(arr));
    }

    private static String firstUnique(String[] arr) {
        for(int i = 1; i<arr.length; i++){
            String str = arr[i];
            int count = 1;
            for(int j = i+1; j<arr.length; j++){
                if(str.equals(arr[j])){
                    count++;
                    break;
                }
            }
            if(count == 1)
                return str;
        }
        return "";
    }
}
