import java.util.*;

/*
 *  @author : zhanGTao
 *  @version: 1.0
 */

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BigInt num1 = new BigInt(scanner.next());
        BigInt num2 = new BigInt(scanner.next());
        String flag = scanner.next();
        if (flag.equals("+"))System.out.println(BigInt.Add(num1,num2));
        else System.out.println(BigInt.div(num1,num2));
//        System.out.println("They are : ");
//        System.out.println(num1.toString() + " ");
//        System.out.println(num2.toString());
//        System.out.println("Add they : ");
//        System.out.println(BigInt.Add(num1,num2));
//        System.out.println("Div they : ");
//        System.out.println(BigInt.div(num1,num2));
    }
}
