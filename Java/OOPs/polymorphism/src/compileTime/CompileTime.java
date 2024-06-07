package compileTime;

class Calculator {
    void sum(int a, int b) {
        System.out.println(a+b);
    }
    
    void sum(int a, int b, int c) {
        System.out.println(a+b+c);
    }
}

public class CompileTime {
    public static void main(String[] args) {
        Calculator obj=new Calculator();
        obj.sum(1, 2);
        obj.sum(1, 2, 3);
    }
}
