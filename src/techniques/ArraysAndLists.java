package techniques;

import java.util.Arrays;
import java.util.ArrayList;

public class ArraysAndLists {
    public static void main(String[] args) {
        //Arrays (I know this :))
        String[] tLundShopping = {"carrot","lettuce","sausage"};
        System.out.println(Arrays.toString(tLundShopping));
        tLundShopping[1] = "strawberry";
        System.out.println(Arrays.toString(tLundShopping));

        int[] numbers = new int[10];
        for (int i=0;i<10;++i) {
            numbers[i] = i + 10;
        }
        System.out.println(Arrays.toString(numbers));

        //List (new? :0)
        ArrayList<String> names = new ArrayList<>();
        names.add("TLund");
        names.add("Ellis");
        names.add("Will");
        names.add("Fin");
        names.add("Nick");
        System.out.println(names.get(1));
        names.remove(3);
        System.out.println(Arrays.toString(names.toArray()));
    }
}
