package ThinkInJava.InitializationAndCleanup_4.Init;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.InitializationAndCleanup_4.Init
 * @ClassName: xxx
 * @Author: DELL
 * @Description:
 * @Date: 2019/10/16 22:14
 * @Version: 1.0
 */
class Mug {
    Mug(int marker) {
        System.out.println("Mug(" + marker + ")");
    }
    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}
public class Mugs {
    Mug c1;
    Mug c2= new Mug(2);
    {
        c1 = new Mug(1);
//        c2 = new Mug(2);
        System.out.println("c1 & c2 initialized");
    }
    Mugs() {
        System.out.println("Mugs()");
    }
    public static void main(String[] args) {
        System.out.println("Inside main()");
        Mugs x = new Mugs();
    }
}
