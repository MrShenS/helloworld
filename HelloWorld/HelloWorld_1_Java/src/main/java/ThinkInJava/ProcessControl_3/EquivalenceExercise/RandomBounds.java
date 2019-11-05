package ThinkInJava.ProcessControl_3.EquivalenceExercise;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.ProcessControl_3.EquivalenceExercise
 * @ClassName: RandomBounds
 * @Author: DELL
 * @Description: 随机数的生成
 * @Date: 2019/10/9 22:16
 * @Version: 1.0
 */
public class RandomBounds {
    static void usage() {
        System.err.println("Usage: \n\t" +
        "RandomBounds lower\n\t" +
                "RandomBounds upper");
        System.exit(1);
    }
    public static void main(String[] args) {
        if(args.length != 1) usage();
        if(args[0].equals("lower")) {
            while(Math.random() != 0.0) ; // Keep trying
            System.out.println("Produced 0.0!");
        }
        else if(args[0].equals("upper")) {
            while(Math.random() != 1.0)
                ; // Keep trying
            System.out.println("Produced 1.0!");
        }
        else
            usage();
    }
}
