import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Program {

    /* ---------------------------------------------------- */

    private static String readInput() {
        Scanner s = new Scanner(System.in);
        String text = s.nextLine();
        s.close();
        return text;
    }

    /* ---------------------------------------------------- */

    private static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : text.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);
        return map;
    }

    private static void extractAndValidate(Map<Character, Integer> map, char[] keys, int[] values) {
        int k = 0;
        for (Map.Entry<Character, Integer> pair : map.entrySet()) {
            keys[k] = pair.getKey();
            int count = pair.getValue();
            if (count > 999)
                throw new IllegalArgumentException("The maximum number of character occurrences is 999");
            values[k] = count;
            k++;
        }
    }

    /* ---------------------------------------------------- */

    private static void bubbleSort(char[] keys, int[] values) {
        int n = keys.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                boolean shouldSwap = values[j] < values[j + 1]
                        || (values[j] == values[j + 1] && keys[j] > keys[j + 1]);
                if (shouldSwap) {
                    char tempKey = keys[j];
                    keys[j] = keys[j + 1];
                    keys[j + 1] = tempKey;

                    int tempVal = values[j];
                    values[j] = values[j + 1];
                    values[j + 1] = tempVal;
                }
            }
        }
    }

    /* ---------------------------------------------------- */

    private static String buildChartRow(int row, char[] keys, int[] values, int topCount, int max) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < topCount; i++) {
            int height = (int) Math.floor((values[i] * 10.0) / max);
            if (i > 0) line.append(" ");
            if (row == height + 1) {
                if (values[i] < 10)  line.append(" ");
                if (values[i] < 100) line.append(" ");
                line.append(values[i]);
            } else if (height >= row) {
                line.append("  #");
            } else {
                line.append("  ");
            }
        }
        return line.toString().stripTrailing();
    }

    private static String buildAxisLabels(char[] keys, int topCount) {
        StringBuilder labels = new StringBuilder();
        for (int i = 0; i < topCount; i++) {
            if (i > 0) labels.append(" ");
            labels.append("  ").append(keys[i]);
        }
        return labels.toString();
    }

    private static String buildChart(char[] keys, int[] values, int topCount) {
        int max = values[0];
        StringBuilder output = new StringBuilder();
        for (int row = 11; row >= 1; row--) {
            String lineStr = buildChartRow(row, keys, values, topCount, max);
            output.append(lineStr.isEmpty() ? "" : lineStr).append("\n");
        }
        output.append(buildAxisLabels(keys, topCount)).append("\n");
        return output.toString();
    }

    /* ---------------------------------------------------- */

    public static void main(String[] args) {
        try {
            String text = readInput();
            Map<Character, Integer> frequencyMap = buildFrequencyMap(text);
            char[] keys = new char[frequencyMap.size()];
            int[] values = new int[frequencyMap.size()];
            extractAndValidate(frequencyMap, keys, values);
            bubbleSort(keys, values);
            int topCount = Math.min(keys.length, 10);
            String output = buildChart(keys, values, topCount);
            System.out.print(output);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}