import generic.GenericExample;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, Generic!");

        GenericExample<Integer> obj = new GenericExample<>(123);
        obj.whichType();
        
        GenericExample<String> obj1 = new GenericExample<>("123");
        obj1.whichType();
    }
}
