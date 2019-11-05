package ThinkInJava.InitializationAndCleanup_4.StaticInit;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.InitializationAndCleanup_4
 * @ClassName: StaticInitialization
 * @Author: DELL
 * @Description:
 * @Date: 2019/10/16 22:02
 * @Version: 1.0
 */
public class StaticInitialization {
    public static void main(String[] args) {
        System.out.println(
                "Creating new Cupboard() in main");
        new Cupboard();
        System.out.println(
                "Creating new Cupboard() in main");
        new Cupboard();
        t2.f2(1);
        t3.f3(1);
    }
    static Table t2 = new Table();
    static Cupboard t3 = new Cupboard();
}
