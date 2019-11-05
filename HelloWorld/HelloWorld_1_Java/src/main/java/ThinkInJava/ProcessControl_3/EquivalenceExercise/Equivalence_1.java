package ThinkInJava.ProcessControl_3.EquivalenceExercise;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.ProcessControl_3.EquivalenceExercise
 * @ClassName: Equivalence_1
 * @Author: DELL
 * @Description: wu1
 * @Date: 2019/10/9 19:11
 * @Version: 1.0
 */
public class Equivalence_1 {
    /**
     *==比较的是对象地址（句柄对象） （一般用来比较基本数据类型）
     * equals比较的是实际值（equals在object中被实现 由于Object是所有类的父类 所以所有的类都可以继承到Object
     * 类的equals方法 由于object里面的实现方式是采用==运算符 所以默认的equals和==运算符作用相同
     * 不过equals方法可以被重写 比较的规则由方法内部的实现逻辑决定）
     * Integer的equals：
     *    public boolean equals(Object obj) {
     *         if (obj instanceof Integer) {
     *             return value == ((Integer)obj).intValue();
     *         }
     *         return false;
     *     }
     * 先比较二者是否是同一类（Integer）的实例在比较对象的值
     * */
    public static void main(String[] args) {
        Integer i1 = new Integer(12);
        Integer i2 = new Integer(12);
        System.out.println(i1==i2);//false
        System.out.println(i1!=i2);//true
        System.out.println(i1.equals(i2));//true
    }
}
