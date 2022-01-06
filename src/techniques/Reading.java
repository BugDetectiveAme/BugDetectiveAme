package techniques;

import java.util.Scanner;
import java.io.*;

public class Reading {
    public static void main(String[] args) throws Exception {
        //Mr Lund's way
        FileReader file = new FileReader("resources/test.txt");
        BufferedReader buff = new BufferedReader(file);
        Scanner scan0 = new Scanner(buff);
        while (scan0.hasNextLine()) {
            System.out.println(scan0.nextLine());
        }
        scan0.close();
        //My way
        Scanner scan = new Scanner(new File("resources/test.txt"));
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
        }
        scan.close();
    }
}
