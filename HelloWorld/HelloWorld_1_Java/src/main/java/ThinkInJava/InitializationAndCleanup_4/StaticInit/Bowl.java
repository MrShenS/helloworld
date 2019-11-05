package ThinkInJava.InitializationAndCleanup_4.StaticInit;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.InitializationAndCleanup_4
 * @ClassName: Bowl
 * @Author: DELL
 * @Description:
 * @Date: 2019/10/16 21:59
 * @Version: 1.0
 */
class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");
    }
    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}
