package techniques;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Navi {
    public static void main(String[] args) throws Exception {
        String[] navi = {"You!","Hello!","Listen!","Hey, listen!"};
        for (int i=0;i<300;++i) {
            int x = new Random().nextInt(4);
            System.out.println(navi[x]);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}