package ThinkInJava.InitializationAndCleanup_4.StaticInit;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.InitializationAndCleanup_4
 * @ClassName: Cunboard
 * @Author: DELL
 * @Description:
 * @Date: 2019/10/16 22:01
 * @Version: 1.0
 */
class Cupboard {
    Bowl b3 = new Bowl(3);
    static Bowl b4 = new Bowl(4);
    Cupboard() {
        System.out.println("Cupboard()");
        b4.f(2);
    }
    void f3(int marker) {
        System.out.println("f3(" + marker + ")");
    }
    static Bowl b5 = new Bowl(5);
}
