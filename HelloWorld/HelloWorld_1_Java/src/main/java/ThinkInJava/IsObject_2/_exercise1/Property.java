package ThinkInJava.IsObject_2._exercise1;

import java.util.Date;
import java.util.Properties;

public class Property {
    public static void main(String[] args) {
        System.out.println(new Date());
        Properties p = System.getProperties();
        p.list(System.out);
        System.out.println("---Memory Usage");
        Runtime rt = Runtime.getRuntime();
        System.out.println("Total Memory = "+rt.totalMemory()+"free Memory = "+rt.freeMemory());
    }
}
