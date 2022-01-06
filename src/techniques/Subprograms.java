package techniques;

import techniques.printing.*;

public class Subprograms {
    public static void main(String[] args) {
        funny("Jess");
        funny("Will");
        funny();
        funny(String.valueOf(addition(5,10)));
        techniques.printing.main(new String[]{"", ""});
    }
    //Procedure
    public static void funny(String name) {
        System.out.printf("%s mama!\n",name);
    }
    public static void funny() {
        System.out.println("Joe mama!");
    }

    //Functions
    public static int addition(int num1,int num2) {
        return num1 + num2;
    }
}
