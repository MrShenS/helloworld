package ThinkInJava.ProcessControl_3.EquivalenceExercise;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.ProcessControl_3.EquivalenceExercise
 * @ClassName: Test
 * @Author: DELL
 * @Description:
 * @Date: 2019/10/9 23:00
 * @Version: 1.0
 */
public class Test {
    public void search(int x){
        System.out.println("int");
    }
    private Test getTest(){
        return this;
    }
    public static void main(String[] args) {
        System.out.println("helloWorld");
        short x=12;
        Test t = new Test();
        Test t1 = new Test();
        t.search(x);
        t1.search(x);

    }
}
