package techniques;

import java.util.Random;

public class iteration {
    public static void main(String[] args) {
        System.out.println(new Random().nextInt());
        String[] items = {"carrot","apple","banana","aubergine","beans"};
        for (String i : items) {
            System.out.print(i + ", ");
        }
        System.out.println("\nfor int");
        for (int i=0;i<10;++i) {
            System.out.print(i + ", ");
        }
        int x = 0;
        System.out.println("\nwhile");
        while (x < 2) {
            System.out.print(x + ", ");
            ++x;
        }
        System.out.println("\ndo while");
        do {
            System.out.print(x + ", ");
            ++x;
        } while (x < 4);
    }
}
