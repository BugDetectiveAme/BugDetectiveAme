package techniques;

import java.util.Random;

public class RandomNums {
    public static void main(String[] args) {
        for (int i=0;i<10;++i) {
            System.out.println(new Random().nextInt(6)+1);
        }
    }
}
