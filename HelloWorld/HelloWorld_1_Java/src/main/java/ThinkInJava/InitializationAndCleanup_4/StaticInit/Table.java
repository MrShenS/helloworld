package ThinkInJava.InitializationAndCleanup_4.StaticInit;


/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.InitializationAndCleanup_4
 * @ClassName: Table
 * @Author: DELL
 * @Description:
 * @Date: 2019/10/16 22:00
 * @Version: 1.0
 */
class Table {
    static Bowl b1 = new Bowl(1);
    Table() {
        System.out.println("Table()");
        b2.f(1);
    }
    void f2(int marker) {
        System.out.println("f2(" + marker + ")");
    }
    static Bowl b2 = new Bowl(2);
}
