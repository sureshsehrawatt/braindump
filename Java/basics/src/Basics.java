public class Basics {

    // Data types - Primitive
    boolean a = true; // true(1) or false(0), 1 bit, default value:false
    byte b = 127; // -128 to 127 [0-255], 8 bits, Default value: 0
    char c = 'a'; // 0 to 65535, 16 bits, Default value: '\u0000'
    char d = 97; // prints 'a'
    short e = 32767; // -32768 to 32767, 16 bits, Default value: 0
    int f = 2147483647; // -2147483648 to 2147483647, 32 bits, Default value: 0
    float g = 7.1234567f; // 7 digits after decimal, 32 bits, Default value: 0.0f (suffix 'f' indicating k
                          // float literal)
    double h = 9.123456789012345d; // 15 digits after decimal, 64 bits, Default value: 0.0
    long i = 8234567890123456789l; // -9223372036854775808L to 9223372036854775807L, 64 bits, Default value: 0L
                                   // (suffix 'L')

    // Non-primitive
    String j = "Hello World!"; // To-Do

    // Types of variables
    boolean check = false; // 1 - instance variable
    static int DIVIDEBY = 2; // 2 - Class variable

    boolean isEven(int a) { // 3 - Method Argument
        int c = a % Basics.DIVIDEBY; // 4 - Local variable (inside method body)
        return c != 1;
    }

    // Operators- Arithmetic Operators
    int k = 10, l = 3;
    int sum = k + l; // 13, Addition
    int difference = k - l; // 7, Subtraction
    int product = k * l; // 30, Multiplication
    int quotient = k / l; // 3, Division
    int remainder = k % l; // 1, Modulus

    // Unary Operators
    int x = 5;
    int preIncrement = ++x; // 6, Pre-increment
    int postIncrement = x++; // 6, Post-increment
    int preDecrement = --x; // 6, Pre-decrement
    int postDecrement = x--; // 6, Post-decrement

    // Relational Operators
    boolean isEqual = (k == l); // false, Equal to
    boolean isNotEqual = (k != l); // true, Not equal to
    boolean isLessThan = (k < l); // false, Less than
    boolean isGreaterThan = (k > l); // true, Greater than
    boolean isLessThanOrEqual = (k <= l); // false, Less than or equal to
    boolean isGreaterThanOrEqual = (k >= l); // true, Greater than or equal to

    // Logical Operators
    boolean logicalNot = !isEqual; // true, Logical NOT
    boolean logicalAnd = (isEqual && isNotEqual); // false Logical AND
    boolean logicalOr = (isEqual || isNotEqual); // true Logical OR

    // Assignment Operators
    int assignedValue = 42;
    {
        assignedValue += 5; // 47, Equivalent to: assignedValue = assignedValue + 5
        assignedValue -= 3; // 44 Equivalent to: assignedValue = assignedValue - 3
        assignedValue *= 2; // 88, Equivalent to: assignedValue = assignedValue * 2
        assignedValue /= 4; // 22, Equivalent to: assignedValue = assignedValue / 4
        assignedValue %= 3; // 1, Equivalent to: assignedValue = assignedValue % 3
    }

    // Bitwise Operators- if k=5, l=3
    int bitwiseAnd = k & l; // Bitwise AND: 1, 0101 & 0011 = 0001 = 1
    int bitwiseOr = k | l; // Bitwise OR: 7, 0101 | 0011 = 0111 = 7
    int bitwiseXor = k ^ l; // Bitwise XOR: 6, 0101 ^ 0011 = 0110 = 6
    int bitwiseNot = ~k; // Bitwise NOT: -6, ~0101 = 1010 = -6
    int leftShift = k << 1; // Left Shift: 10, 0101 << 1 = 1010 = 10
    int rightShift = k >> 1; // Right Shift: 2, 0101 >> 1 = 0010 = 2
    int zeroFillRightShift = k >>> 1; // Zero-fill Right Shift: 2, 0101 >>> 1 = 0010 = 2

    // Conditional (Ternary) Operator
    int result = (k > l) ? k : l; // k:5

    // Other Operators
    Object result1 = 42; // An Integer object
    int instanceOfCheck = (result1 instanceof Integer) ? 1 : 0; // 1

    // String Concatenation using '+'
    String str1 = "Hello";
    String str2 = "World";
    String concatenatedString = str1 + " " + str2; // "Hello World"

    // Types of comment - 1. single line comment

    /*
     * 2. multi-line comment
     */

    /**
     * 3. Document comment
     * 
     * @author Jai Sehrawat
     * @param args
     */

    // Block statement
    {
        // a group of statements related to a specific task
        int x = 5;
        int y = 10;
        int result = x + y;
        System.out.println(result);
    }

    static {
        // Static block statement
    }

    // Java is default pass-by-value
    static void changeValue(int aCopy) {
        aCopy = 10;
    }

    static void changeReference(Animal animalObjCopy) {
        animalObjCopy.age = 10;
    }

    public static void main(String[] args) {
        int a = 5;
        changeValue(a);
        System.out.println("Value of a " + a);

        Animal obj = new Animal();
        obj.age = 5;
        changeReference(obj);
        System.out.println("Value of animal age " + obj.age);

        // JVM shutdown hook - Shutdown hooks provide a way to gracefully shut down and
        // perform cleanup tasks in Java applications.
        // Create and register a shutdown hook
        Thread shutdownHook = new Thread(() -> {
            System.out.println("Shutdown hook is running...");
            // Perform cleanup operations or other tasks
            System.out.println("Shutdown hook completed.");
        });

        Runtime.getRuntime().addShutdownHook(shutdownHook);

        // Some application code
        System.out.println("Application is running...");

        // Simulate a delay or user interaction
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Application is exiting
        System.out.println("Application is exiting...");
    }
}

class Animal {
    int age;
}