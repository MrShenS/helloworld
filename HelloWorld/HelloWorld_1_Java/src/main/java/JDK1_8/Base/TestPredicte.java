package JDK1_8.Base;

import java.util.function.Predicate;

/**
 * @ClassNameTestPredicte
 * @Description TODO Predicate为函数式接口，predicate的中文意思是“断定”，即判断的意思，判断某个东西是否满足某种条件； 因此它包含test方法，
 *               根据输入值来做逻辑判断，其结果为True或者False。它的使用方法示例如下：
 * @Author zhen.zhen
 * @Date 2019/12/2611:56
 * @Version 1.0
 */
public class TestPredicte {
    public static void main(String[] args) {
        Predicate<String> p1=s->s.equals("ssss");
        Predicate<String> p2=s->s.startsWith("ss");
        /*
         * @Author zhen.shen
         * @Description //TODO negate 方法对原来的及结果进行取反
         * @Date 12:08 2019/12/26
         * @Param 
         * @return 
         **/
        System.out.println(p1.negate().test("ssss"));

        /**
         * @Author zhen.shen
         * @Description //TODO and 效果等同于短路与
         * @Date 12:09 2019/12/26
         * @Param
         * @return
         **/
        System.out.println(p1.and(p2).test("ss"));//false


        /**
         * @Author zhen.shen
         * @Description //TODO or 效果相当与短路或
         * @Date 12:11 2019/12/26
         * @Param
         * @return
         **/

        System.out.println(p1.and(p2).test("ssss"));//true
    }
}
