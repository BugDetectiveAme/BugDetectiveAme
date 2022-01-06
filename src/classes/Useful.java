package classes;

//Commenting this because it's stupid

public class Useful {
    public static boolean isPrime(int number) {
        String numAsString = String.valueOf(number); //Used for getting the last digit
        boolean prime = false;
        int lastDigit = Integer.parseInt(numAsString.substring(numAsString.length()-1)); //Gets the last digit as int
        if (lastDigit == 1 || lastDigit == 3 || lastDigit == 7 || lastDigit == 9 ) { //Prime numbers (ex. 2,5) always end in these digits
            if (number % 3 != 0 || number % 7 != 0 || number % 11 != 0 || number % 13 != 0) { //Checks primes not already covered by the last digit check
                if (number < 289) { //Previous check only covers numbers up to 289
                    prime = true;
                } else {
                    boolean temp = false;
                    for (int i=2;i<Math.sqrt(number);++i) { //This should only check primes < root(number) but idk how to do that, so checks every value < root(number)
                        if (number % i == 0) {
                            temp = true;
                            break;
                        }
                    }
                    if (!temp) { //If not divisible by any value < root(number), must be prime
                        prime = true;
                    }
                }
            }
        } else if (number == 2 || number == 3 || number == 5 || number == 7 || number == 11 || number == 13) { //Doesn't meet previous check conditions but is prime, so added exceptions
            prime = true;
        }
        return prime;
    }
}
