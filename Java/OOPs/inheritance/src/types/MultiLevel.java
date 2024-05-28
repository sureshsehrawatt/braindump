package types;

class Animal {
    void eat() {
        System.out.println("Eating...");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Barking...");
    }
}

class Puppy extends Dog {
    void play() {
        System.out.println("Playing...");
    }
}

public class MultiLevel {
    public static void main(String[] args) {
        Puppy mPuppy = new Puppy();
        mPuppy.eat();
        mPuppy.bark();
        mPuppy.play();
    }
}
