package interfaceUse;

public class Main {
    public static void insUse(){
        System.out.println("Interface use...");

        Animal obj = new Dog();
        obj.makeSound();
        obj.sleep();
    }
}