import java.util.Random;
import java.util.Arrays;

public class Program {
    private static long         threadSum = 0;
    private static final Object lock = new Object();

    static class MyThread implements Runnable {
        private final int[] array;
        private final int   start;
        private final int   end;
        private final int   id;

        public MyThread(int identifier, int[] array, int start, int end) {
            this.id     = identifier + 1;
            this.array  = array;
            this.start  = start;
            this.end    = end;
        }

        @Override
        public void run() {
            long localSum = 0;

            for (int i = start; i <= end; i++)
                localSum += array[i];

            System.out.println("Thread " + this.id + " from " + start + " to " + end + " sum is " + localSum);
            
            synchronized (lock) {
                threadSum += localSum;
            }
        }
    }

    public static void main(String args[]) {
        int     threadCount = 0; 
        int     arraySize = 0;
        boolean isValid = false;

        if (args.length == 2
            && args[0].startsWith("--arraySize=")
            && args[1].startsWith("--threadsCount="))
        {
            try {
                String[] parts = args[0].split("=");
                arraySize = Integer.parseInt(parts[1]);

                if (arraySize > 2000000 || arraySize <= 0) {
                    System.out.println("Error: arraySize must be between 1 and 2.000.000");
                    return;
                }

                parts = args[1].split("=");
                threadCount = Integer.parseInt(parts[1]);

                if (threadCount >= arraySize || threadCount <= 0) {
                    System.out.println("Error: threadCount must not be greater than arraySize");
                    return;
                }

                isValid = true;
            } catch (Exception e) {
                System.out.println("Error: The count must be a valid integer.");
            }
        }

        if (!isValid) {
            System.out.println("Usage: java Program  --arraySize=<arraySize> --threadsCount=<threadsCount>");
            return;
        }

        int[]   array = new int[arraySize];
        Random random = new Random();
        long    standardSum = 0;

        for (int i = 0; i < arraySize; i++) {
            int randomNumber = random.nextInt(2001) - 1000;

            array[i] = randomNumber;
            standardSum += randomNumber;
        }
        System.out.println("Sum: " + standardSum);

        int         batchSize = (int) Math.ceil((double) arraySize / threadCount);
        Thread[]    threads = new Thread[threadCount];
        
        for (int i = 0; i < threadCount; i++) {
            int start = i * batchSize;
            int end = start + batchSize - 1;
            
            if (end >= arraySize)
                end = arraySize - 1;

            threads[i] = new Thread(new MyThread(i, array, start, end));
            threads[i].start();
        }

        for (int i = 0; i < threadCount; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                System.out.println("Error: Thread interrupted.");
            }
        }

        System.out.println("Sum by threads: " + threadSum);
    }
}