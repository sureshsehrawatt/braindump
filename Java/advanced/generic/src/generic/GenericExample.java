package generic;

public class GenericExample<T> {
    public T variable;

    public GenericExample(T value) {
        this.variable = value;
    }

    public T whichType() {
        System.out.println("Type of " + variable + " is: " + variable.getClass());
        return variable;
    }
}
