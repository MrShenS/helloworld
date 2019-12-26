package JDK1_8.Base;

import java.util.function.Consumer;

/**
 * @ClassNameFirst
 * @Description TODO
 * @Author zhen.zhen
 * @Date 2019/12/2512:13
 * @Version 1.0
 */
public class TestConsumer {


    public static void main(String[] args) {
        Consumer consumer = new Consumer() {
            @Override
            public void accept(Object o) {
                System.out.println("jDK8真牛");
            }
        };
        StringBuffer s = new StringBuffer(new String("ssss"));
        Consumer c=(o)->{
            System.out.println(o);
        };
        testConsumer();
//        consumerTest();

    }
/**
 * @Author zhen.shen
 * @Description //TODO
 * @Date 12:54 2019/12/25
 * @Param 
 * @return 
 **/
    public static void testConsumer(){
        Consumer<String> c1=System.out::print;
        Consumer<String> c2= n-> System.out.println(n);
        System.out.println(c1.andThen(c2).getClass().getName());
        c1.andThen(c2).accept("自己测试+===================================");
        //c1.andThen(c2)返回的是个Consumer对象 该对象里连续执行的c1 和c2的accept方法
        Consumer<String> stringConsumer = c1.andThen(c2);
        System.out.println(c1.equals(stringConsumer));
        stringConsumer.accept("===");
    }

    public static void consumerTest() {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");
        //执行完F后再执行F2的Accept方法
        f.andThen(f2).accept("test");
        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }
}
