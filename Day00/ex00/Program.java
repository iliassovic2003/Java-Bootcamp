public class Program {
    public static void main( String[] args ) {
        long number = 479598;

        number = Math.abs(number);
        short sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        System.out.println(sum);
    }
}