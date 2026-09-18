import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Program {
    private static final int    READ_SIZE = 1024;
    private static final int    MAX_RETRIES = 3;

    private static int          lastIndex = 0;
    private static String[]     linkArr;

    private static final Object sharedLock = new Object();
    private static int          threadsCount;

    static class MyThread implements Runnable {
        private final String[]  array;
        private final int       id;

        public MyThread(int identifier, String[] array) {
            this.id     = identifier + 1;
            this.array  = array;
        }

        @Override
        public void run() {
            while (true) {
                int                 currentIndex;

                synchronized (sharedLock) {
                    if (lastIndex >= array.length)
                        break;
                    currentIndex = lastIndex;
                    lastIndex++;
                }

                String              fileUrl = this.array[currentIndex];
                int                 dotIndex = fileUrl.lastIndexOf('.');
                String              extension = (dotIndex != -1) ? fileUrl.substring(dotIndex) : "";
                
                String              fileName = "IZ_FILE_" + (currentIndex + 1) + extension;

                boolean             success = false;
                int                 retries = 0;

                while (!success && retries < MAX_RETRIES) {
                    try (
                        BufferedInputStream in = new BufferedInputStream(new URL(fileUrl).openStream());
                        FileOutputStream    out = new FileOutputStream(fileName)
                    ) {
                        byte[]  dataBuffer = new byte[READ_SIZE];
                        int     bytesRead = 0;

                        System.out.println("Thread-" + this.id + " start download " + fileName);

                        while ((bytesRead = in.read(dataBuffer, 0, READ_SIZE)) != -1)
                            out.write(dataBuffer, 0, bytesRead);

                        System.out.println("Thread-" + this.id + " finish download " + fileName);
                        success = true;

                    } catch (Exception e) {
                        retries++;
                        System.out.println("Thread-" + this.id + " failed to download " + fileName + ". Retry " + retries);
                    }
                }
            }
        }

    }

    private static boolean ValidateArgs(String args[]) {
        boolean isValid = false;

        if (args.length == 1 && args[0].startsWith("--threadsCount=")) {
            String[] parts = args[0].split("=");

            if (parts.length == 2) {
                try {
                    threadsCount = Integer.parseInt(parts[1]);
                    isValid = true;
                } catch (Exception e) {
                    System.out.println("Error: The count must be a valid integer.");
                }
            } else
                System.out.println("Error: You must provide a value, like --threadsCount=3");
        } else
            System.out.println("Usage: Java Program --threadsCount=<numbers>");

        return(isValid);
    }

    private static boolean LoadLinks() {
        try {
            List<String> lines = Files.readAllLines(Paths.get("files_urls.txt"));
            linkArr = lines.toArray(new String[0]);
            
            if (linkArr.length == 0) {
                System.out.println("Error: files_urls.txt is empty.");
                return false;
            }
            return true;

        } catch (Exception e) {
            System.out.println("Error reading files_urls.txt: " + e.getMessage());
            return false;
        }
    }

    public static void main(String args[]) {
        if (!ValidateArgs(args) || !LoadLinks())
            return;

        try {
            Thread[] threads = new Thread[threadsCount];
            for (int i = 0; i < threadsCount; i++) {
                threads[i] = new Thread(new MyThread(i, linkArr));
                threads[i].start();
            }

            for (int i = 0; i < threadsCount; i++)
                threads[i].join();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}