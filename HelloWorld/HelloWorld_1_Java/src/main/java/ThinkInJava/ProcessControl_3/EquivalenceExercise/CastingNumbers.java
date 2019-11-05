package ThinkInJava.ProcessControl_3.EquivalenceExercise;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.ProcessControl_3
 * @ClassName: CastingNumbers
 * @Author: DELL
 * @Description: 数制转换
 * @Date: 2019/10/9 22:11
 * @Version: 1.0
 */
public class CastingNumbers {
    public static void main(String[] args) {
        double
                above = 0.7,
                below = 0.4;
        System.out.println("above: " + above);
        System.out.println("below: " + below);
        System.out.println(
                "(int)above: " + (int)above);
        System.out.println(
                "(int)below: " + (int)below);
        System.out.println(
                "(char)('a' + above): " +
                        (char)('a' + above));
        System.out.println(
                "(char)('a' + below): " +
                        (char)('a' + below));
    }
}

