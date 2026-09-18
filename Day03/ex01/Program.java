public class Program {
    static class MyThread implements Runnable {
        private final String word;
        private final Object lock;
        private final int    count;

        public MyThread(String word, int count, Object lock) {
            this.word = word;
            this.count = count;
            this.lock = lock;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (lock) {
                        System.out.println(word);
                    }
                    Thread.sleep(1);
                }
            } catch (Exception e) {
                System.out.println("Error: Thread sleep failed.");
            }
        }
    }

    

    public static void main(String args[]) {
        int     msgNums = 0;
        boolean isValid = false;

        if (args.length == 1 && args[0].startsWith("--count=")) {
            String[] parts = args[0].split("=");

            if (parts.length == 2) {
                try {
                    msgNums = Integer.parseInt(parts[1]);
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("Error: The count must be a valid integer.");
                }
            } else {
                System.out.println("Error: You must provide a value, like --count=50");
            }
        } else {
            System.out.println("Usage: Java Program --count=<numbers>");
        }

        if (!isValid)
            return;

        Object sharedLock = new Object();

        Thread eggThread = new Thread(new MyThread("Egg", msgNums, sharedLock));
        Thread henThread = new Thread(new MyThread("Hen", msgNums, sharedLock));

        eggThread.start();
        henThread.start();

        try {
            eggThread.join();
            henThread.join();
        } catch (Exception e) {
            System.out.println("Error: Main thread interrupted.");
        }

        for (int i = 0; i < msgNums; i++)
            System.out.println("Human");
    }
}