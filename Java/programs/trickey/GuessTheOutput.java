public class GuessTheOutput {
    static {
        String a = "abc";
        String b = "abc";
        System.out.println("a is equal to b: " + a == b);
    }

    static {
        // flag = true or false;
        boolean flag = true;
        try {
            if (flag) {
                while (true) {
                }
            } else {
                System.exit(1);
            }
        } finally {
            System.out.println("In Finally");
        }
    }

    static {
        String x = "abc";
        String y = "abc";
        x.concat(y);
        System.out.print(x);
    }

    public static void foo(Object o) {
        System.out.println("Object argument");
    }

    public static void foo(String s) {
        System.out.println("String argument");
    }

    public static void foo(StringBuilder s) {
        System.out.println("StringBuilder argument");
    }

    public static void main(String[] args) {
        foo(null);
    }
}
