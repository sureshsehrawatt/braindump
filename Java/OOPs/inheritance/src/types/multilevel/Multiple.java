package types.multilevel;

interface Animal {
    void eat();
}

interface Pet {
    void playing();
}

class Dog implements Animal, Pet {
    public void eat() {
        System.out.println("Eating...");
    }

    public void playing() {
        System.out.println("Playing...");
    }
}

public class Multiple {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.eat();
        myDog.playing();
    }
}
