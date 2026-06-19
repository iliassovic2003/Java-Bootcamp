import java.util.Scanner;

public class Program {
    public static void main( String[] args ) {
        
        int number = 0;

        Scanner s = new Scanner(System.in);
        number = s.nextInt();

        try {
            if (number <= 1)
                throw new IllegalArgumentException("");

            int i, rep = 1;
            for (i = 2; i < Math.sqrt(number); i++) {
                if (number % i == 0) {
                    System.out.printf("false %d\n", rep);
                    return;
                }
                rep++;
            }
            System.out.printf("true %d\n", rep);
            return;

        } catch (Exception e) {
            System.out.println("IllegalArgument");
        }
    }
}
