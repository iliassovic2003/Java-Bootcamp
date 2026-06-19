import java.util.Scanner;

public class Program {

    /* ---------------------------------------------------- */

    public static boolean isPrime(int number) {
        if (number <= 1)
            return false;

        for (int i = 2; i < Math.sqrt(number); i++)
            if (number % i == 0) 
                return false;
        return true;
    }

    public static int numSum(int number) {
        number = Math.abs(number);
        short sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }

    /* ---------------------------------------------------- */

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int number, count = 0;

        while (true) {
            number = s.nextInt();
            if (number == 42)
                break;
            if (isPrime(numSum(number)))
                count++;
        }

        System.out.printf("Count of coffee-request : %d\n", count);
        s.close();
        return;
    }

}