package techniques;

public class selection {
    public static void main(String[] args) throws Exception {
        //Still not commenting, I know java
        int speed = 72;
        int day = 8;
        String fullday = "";
        switch (day) {
            case 1:
                fullday = "Monday";System.out.println(fullday);
                break;
            case 2:
                fullday = "Tuesday";System.out.println(fullday);
                break;
            case 3:
                fullday = "Wednesday";System.out.println(fullday);
                break;
            case 4:
                fullday = "Thursday";System.out.println(fullday);
                break;
            case 5:
                fullday = "Friday";System.out.println(fullday);
                break;
            case 6:
                fullday = "Saturday";System.out.println(fullday);
                break;
            case 7:
                fullday = "Sunday";System.out.println(fullday);
                break;
            default:
                throw new Exception(day + " is not a day in the week");
        }
        if (speed >= 77 && !fullday.equalsIgnoreCase("saturday")) {
            System.out.println("Issue speeding ticket");
        } else if (speed >= 70) {
            System.out.println("Issue speed warning");
        }  else {
            System.out.println("No action");
        }
    }
}
