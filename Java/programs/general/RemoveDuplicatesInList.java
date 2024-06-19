import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RemoveDuplicatesInList {
    public static void removeDuplicates(List<Integer> list){
        Set<Integer> set = new HashSet<>(list);
        System.out.println(set.toString());
    }
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(6);
        list.add(2);
        list.add(7);
        list.add(4);
        list.add(6);
        list.add(7);
        RemoveDuplicatesInList.removeDuplicates(list);
    }
}
