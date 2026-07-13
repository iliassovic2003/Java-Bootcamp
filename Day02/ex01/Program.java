import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;

import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.Set;
import java.util.Map;

public class Program {

    public static double calcDen(int[] vect1, int[] vect2) {
        double temp1 = 0;
        double temp2 = 0;

        for (int i = 0; i < vect1.length; i++)
            temp1 += vect1[i] * vect1[i];
        temp1 = Math.sqrt(temp1);

        for (int i = 0; i < vect1.length; i++)
            temp2 += vect2[i] * vect2[i];
        temp2 = Math.sqrt(temp2);

        return temp1 * temp2;
    }

    public static long calcNum(int[] vect1, int[] vect2) {
        long temp = 0;

        for (int i = 0; i < vect1.length; i++)
            temp += vect1[i] * vect2[i];

        return temp;
    }

    public static int[] buildVector(Set<String> dictionary, Map<String, Integer> freqMap) {
        
        int     i = 0;
        int[]   vector = new int[dictionary.size()];
        
        for (String word : dictionary) {
            vector[i] = freqMap.getOrDefault(word, 0);
            i++;
        }

        return vector;
    }

    public static void writeDictionary(Set<String> dictionary) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt")))
        {
            for (String word : dictionary) {
                writer.write(word);
                writer.newLine();
            }
        }
    }
    
    public static Map<String, Integer> buildFrequencyMap(String filename) throws IOException {
        
        Map<String, Integer>    map = new LinkedHashMap<>();

        BufferedReader          reader = new BufferedReader(new FileReader(filename));
        String                  line;

        while ((line = reader.readLine()) != null) {
            String[] words = line.toLowerCase().split("[^a-zA-Z']+");
            
            for (String word : words)
                if (!word.isEmpty()) 
                    map.put(word, map.getOrDefault(word, 0) + 1);
        }
        
        return map;
    }
    
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java <program_name> <file1> <file2>.");
            return;
        }
        
        try {
            Map<String, Integer>    map1 = buildFrequencyMap(args[0]);
            Map<String, Integer>    map2 = buildFrequencyMap(args[1]);

            Set<String>             dictionary = new TreeSet<>();
            dictionary.addAll(map1.keySet());
            dictionary.addAll(map2.keySet());
            writeDictionary(dictionary);

            int[]                   vect1 = buildVector(dictionary, map1);
            int[]                   vect2 = buildVector(dictionary, map2);

            long                    numerator;
            double                  denominator;

            numerator = calcNum(vect1, vect2);
            denominator = calcDen(vect1, vect2);

            double result;
            result = (denominator == 0) ? 0 : (double) numerator / denominator;

            System.out.printf("Similarity = %.2f%n", result);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}