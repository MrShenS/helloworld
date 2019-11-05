package ThinkInJava.IsObject_2._exercise1;

import java.math.BigInteger;

public class StringObject {
    static String s;
    public static void main(String[] args) {
        StringObject so = new StringObject();
        so.print();
    }
    public void print(){
        System.out.println(s);
    }
}
