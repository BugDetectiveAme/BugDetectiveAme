package challenges;

import java.util.Random;
import java.util.Scanner;

public class ArithmeticTest {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scan.nextLine();
        int score = 0;
        for (int i=0;i<10;++i) {
            int x = new Random().nextInt(99)+1;
            int y = new Random().nextInt(99)+1;
            int z = new Random().nextInt(4);
            double ans;
            switch (z) {
                case 0:
                    System.out.printf("\nQuestion: %d + %d",x,y);
                    ans = x+y;
                    break;
                case 1:
                    System.out.printf("\nQuestion: %d - %d",x,y);
                    ans = x-y;
                    break;
                case 2:
                    System.out.printf("\nQuestion: %d x %d",x,y);
                    ans = x*y;
                    break;
                default:
                    System.out.printf("\nQuestion: %d / %d",x,y);
                    ans = x/y;
            }
            System.out.print("\nEnter answer: ");
            String answer0 = scan.nextLine();
            double answer = Double.parseDouble(answer0);
            if (answer == ans) {
                System.out.println("Correct!");
                ++score;
            } else {
                System.out.println("Incorrect");
                System.out.printf("Correct answer: %.0f\n",Math.floor(ans*100)/100);
            }
        }
        System.out.printf("\n%s got %d points",name,score);
        System.exit(200);
    }
}
