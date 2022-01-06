package techniques;

import java.util.Scanner;

public class Casting {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Input: ");
        String input = scan.nextLine();
        try {
            int num = Integer.parseInt(input);
            System.out.printf("\nYou entered number: %d",num);
        } catch (NumberFormatException e) {
            System.out.printf("\nInvalid input: %s is not integer",input);
        } catch (Exception e) {
            System.out.println("Unknown error encountered");
        }
    }
}
