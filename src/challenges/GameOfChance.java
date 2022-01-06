package challenges;

import classes.Useful;
import java.util.Scanner;
import java.util.Random;

public class GameOfChance {
    public static void main(String[] args) throws Exception {
        int bal = 100;
        while (bal > 0) {
            System.out.printf("Balance: £%d\n",bal);
            Scanner scan = new Scanner(System.in);
            System.out.print("Choose a number between 0 and 30: ");
            int bet = Integer.parseInt(scan.nextLine());
            int betAmount = 0;
            while (!(betAmount > 0 && betAmount <= 10)) {
                System.out.print("Choose an amount to bet: ");
                betAmount = Integer.parseInt(scan.nextLine());
                if (!(betAmount > 0 && betAmount <= 10)) {
                    System.out.println("Bet must be between £1 and £10");
                }
            }
            bal -= betAmount;
            System.out.printf("Balance: £%d\n",bal);
            int answer = new Random().nextInt(30)+1;
            int mult = 1;
            System.out.printf("Correct number: %d\n",answer);
            if (bet == answer)  {
                if (bet % 2 == 0) {
                    mult *= 2;
                } if (bet % 3 == 0) {
                    mult *= 3;
                } if (bet % 10 == 0) {
                    mult *= 3;
                } if (Useful.isPrime(bet)) {
                    mult *= 5;
                } if (bet < 5) {
                    mult *=2;
                }
            }
            System.out.printf("Payout: £%d\n",mult != 1 ? betAmount*mult : 0);
            bal += mult != 1 ? betAmount*mult : 0;
            if (bal <= 0) {
                System.out.println("Insufficient money to continue playing");
            }
        }
        System.exit(200);
    }
}
