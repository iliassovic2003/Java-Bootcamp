import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Program {

    static Map<String, byte[]> loadSignatures(String File) throws IOException {
        Map<String, byte[]> signatures = new LinkedHashMap<>();

        FileInputStream     file = new FileInputStream(File);

        byte[]              buffer = new byte[1024];
        int                 bytesRead;

        StringBuilder       content = new StringBuilder();

        while ((bytesRead = file.read(buffer)) != -1)
            content.append(new String(buffer, 0, bytesRead));
        file.close();

        for (String line : content.toString().split("\n")) {
            line = line.trim();
            if (line.isEmpty())
                continue;

            String[]        parts = line.split(",", 2);
            String[]        hexValues = parts[1].trim().split(" ");
            String          type = parts[0].trim();

            byte[]          magic = new byte[hexValues.length];
            
            for (int i = 0; i < hexValues.length; i++)
                magic[i] = (byte)Integer.parseInt(hexValues[i], 16);

            signatures.put(type, magic);
        }

        return signatures;
    }

    static String detectType(String path, Map<String, byte[]> signatures) throws IOException {
        int                 maxLen = 0;
        for (byte[] signature : signatures.values())
            if (signature.length > maxLen)
                maxLen = signature.length;

        FileInputStream     file = new FileInputStream(path);
        byte[]              header = new byte[maxLen];
        int                 bytesRead = file.read(header);
        file.close();

        for (Map.Entry<String, byte[]> entry : signatures.entrySet()) {
            
            byte[]          magic = entry.getValue();
            if (bytesRead < magic.length)
                continue;

            boolean match = true;
            for (int i = 0; i < magic.length; i++)
            {
                if (header[i] != magic[i])
                {
                    match = false;
                    break;
                }
            }

            if (match)
                return entry.getKey();
        }

        return null;
    }

    public static void main (String[] args) {

        try {

            Map<String, byte[]> signatures = loadSignatures("signatures.txt");
            List<String>        results = new ArrayList<>();

            InputStream         input   = System.in;

            byte[]              buffer  = new byte[1024];
            int                 bytesReaded = 0;

            StringBuilder       path    = new StringBuilder();
            boolean             done    = false;

            while (!done && (bytesReaded = input.read(buffer)) != -1)
            {
                path.append(new String(buffer, 0, bytesReaded));

                while (path.toString().contains("\n"))
                {
                    int         newline = path.indexOf("\n");
                    String      filePath = path.substring(0, newline).trim();
                    path.delete(0, newline + 1);

                    if (filePath.equals("42")) {
                        done = true;
                        break;
                    }

                    String type = detectType(filePath, signatures);
                    if (type != null)
                        results.add(type);

                    System.out.println("PROCESSED");
                }
            }

            OutputStream        file = new FileOutputStream("result.txt");
            for (String result : results) {
                file.write(result.getBytes());
                file.write('\n');
            }
            file.close();

        } catch (Exception e) {
            System.out.println("->  Exception: " + e.getMessage());
        }
    }
}