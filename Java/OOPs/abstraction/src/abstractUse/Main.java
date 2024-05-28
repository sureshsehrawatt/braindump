package abstractUse;

public class Main {
    public static void absUse(){
        System.out.println("Abstract class use...");

        Dog obj = new Dog();
        obj.makeSound();
        obj.sleep();
    }
}
