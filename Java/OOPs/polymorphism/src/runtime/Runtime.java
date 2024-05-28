package Java.OOPs.polymorphism.src.runtime;

class Animal {
    void makeSound() {
        System.out.println("Animal making sound...");
    }
}

class Dog extends Animal {
    void makeSound() {
        System.out.println("Barking...");
    }
}
public class Runtime {
    public static void main(String[] args) {
        Animal obj = new Dog(); // upcasting
        obj.makeSound();
    }
}
