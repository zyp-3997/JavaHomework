package Advance.week01;

public class Main {
    public static void main(String[] args) {
        int a=1;
        int b=2;
        int c=methosAdd(a,b);
        System.out.println(c);
    }

    private static int methosAdd(int a, int b) {
        if (a==1){
            return a+b;
        }
        else return a+b+1;
    }
}
