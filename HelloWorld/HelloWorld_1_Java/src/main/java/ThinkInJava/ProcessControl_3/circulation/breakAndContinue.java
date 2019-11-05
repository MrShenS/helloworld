package ThinkInJava.ProcessControl_3.circulation;

/**
 * @ProjectName: HelloWorld
 * @Package: ThinkInJava.ProcessControl_3.circulation
 * @ClassName: breakAndContinue
 * @Author: DELL
 * @Description: 终止和跳出
 * @Date: 2019/10/9 20:00
 * @Version: 1.0
 */
public class breakAndContinue {
    public static void main(String[] args) {
    h: for (int i=0;i<10;i++){
      k:  for (int j=0;j<10;j++){
           if (j==8){
               System.out.println("走了");
//               continue h;
               break h;
           }
          System.out.println(i+" "+j);
        }
     }
    }
}
