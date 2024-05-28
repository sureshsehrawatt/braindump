package types.hybrid;

interface Animal {
    void eat();
}

interface Pet {
    void play();
}

class Dog implements Animal, Pet {
    public void eat() {
        System.out.println("Eating...");
    }

    public void play() {
        System.out.println("playing...");
    }
}

class GuardDog extends Dog {
    void guard() {
        System.out.println("Gaurding...");
    }
}

public class Hybrid {
    public static void main(String[] args) {
        GuardDog guardDog = new GuardDog();
        guardDog.eat();
        guardDog.play();
        guardDog.guard();
    }
}
