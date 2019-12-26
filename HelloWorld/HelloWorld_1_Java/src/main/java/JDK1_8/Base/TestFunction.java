package JDK1_8.Base;

import java.util.function.Function;

/**
 * @ClassNameTestFunction
 * @Description TODO 测试JDK8的Function
 * Function 有以下方法：apply未定义方法体 需要实现
 * @Author zhen.zhen
 * @Date 2019/12/2611:10
 * @Version 1.0
 */
public class TestFunction {
    public static void main(String[] args) {
        Function<Integer,Integer> f1=s->++s;
        Function<Integer,Integer> f2=s->s*9;
        System.out.println(f1.apply(12));
        System.out.println(f1.apply(23).getClass().getName());

    /*
     * 下面的的方法相当于是先执行f2.apply 再把前面的结果当做第二个方法的输入
     * integer i=f2.apply(2)
     * sout(f1.apply(i))
     * Compose 方法为反序 先执行参数对象的apply方法   在将返回值（输出）当做方法调用对象的apply方法的输入参数使用
     **/
        System.out.println(f1.compose(f2).apply(2));
        /*
         * @Author zhen.shen
         * @Description //TODO 下面的表达式是 先执行f1的方法 在执行f2的方法
         *                 integer i = f1.apply(2); sout(f2.apply(i))
         *  andThen方法返回一个Function 对象  对象内的方法 f2.apply(f1.apply()) 执行顺序为正序
         *
         * @Date 11:31 2019/12/26
         * @Param
         * @return
         **/
        System.out.println(f1.andThen(f2).apply(2));
        System.out.println("applyReturn:"+f1.andThen(f2).getClass().getName());
        
        
        /**
         * @Author zhen.shen
         * @Description //TODO 返回一个未处理的结果 结果类型会自动识别
         * @Date 11:36 2019/12/26
         * @Param 
         * @return 
         **/
        System.out.println(Function.identity().apply(2));
        System.out.println(Function.identity().apply(2).getClass().getName());
    }
}
