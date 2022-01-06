package challenges;

import java.util.Scanner;

public class CardValidator {
    public static void main(String[] args) {
        String num = "";
        Scanner scan = new Scanner(System.in);
        num = scan.nextLine();
        String[] vals = num.substring(0,num.length()-1).split("");
        for (int i=0;i<vals.length;++i) {
            if (i%2 == 0) {
                vals[i] = String.valueOf(Integer.parseInt(vals[i])*2);
                if (Integer.valueOf(vals[i]) >= 10) {
                    vals[i] = String.valueOf(Integer.parseInt(vals[i].split("")[0]) + Integer.parseInt(vals[i].split("")[1]));
                }
            }
        }
        int x = 0;
        for (String i : vals) {
            x += Integer.parseInt(i);
        }
        x += Integer.valueOf(num.split("")[num.length()-1]);
        if (x%10 == 0) {
            System.out.println("Valid");
        } else {
            System.out.println("Invalid");
        }
        System.exit(200);
    }
}