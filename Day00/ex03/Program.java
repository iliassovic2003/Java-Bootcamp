import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Program {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            int     weekIndex = 1;
            
            Map<Integer, Integer> map = new HashMap<>();

            while (true) {
                String  temp = s.next();

                if ("42".equals(temp))
                    break;

                if (!"Week".equals(temp))
                    throw new IllegalArgumentException("Must Write Week");
                
                int weekNum = s.nextInt();

                if (weekIndex != weekNum)
                    throw new IllegalArgumentException("Order Error");

                if (weekIndex > 18)
                    throw new IllegalArgumentException("Max Weeks Is 18");

                int min = 9;
                for (int k = 0; k < 5; k++) {
                    int tmp = s.nextInt();

                    if (tmp < 1 || tmp > 9)
                        throw new IllegalArgumentException("Number must between 1 and 9");

                    min = (min < tmp) ? min : tmp;
                }

                map.put(weekIndex, min);
                weekIndex++;
                s.close();
            }

            for (int i = 1; i < weekIndex; i++) {
                StringBuilder   sb = new StringBuilder();
                int             minGrade = map.get(i);

                for (int j = 0; j < minGrade; j++)
                    sb.append("=");
                sb.append(">");

                System.out.printf("Week %d %s\n", i, sb.toString());
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Input: " + e.getMessage());
            System.exit(-1);
        } catch (Exception e) {
            System.err.println("Unknown Error");
            System.exit(-1);
        }
        
        System.exit(0);
    }
}