package com.javarush.task.task22.task2207;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(System.in))) {
            String fileName = bf.readLine();
            bf.close();

            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            StringBuilder sb = new StringBuilder();
            lines.forEach(l -> sb.append(l).append(" "));
            String[] words = sb.toString().split("\\s");
            Set<String> wordsToCheck = new HashSet<>(Arrays.asList(words));

            for (int i = 0; i < words.length; i++) {
                StringBuilder wordReversed = new StringBuilder(words[i]).reverse();
                for (String word : wordsToCheck) {
                    if (word.equals(wordsToCheck.toString())) {
                        Pair pair = new Pair();
                        pair.first = words[i];
                        pair.second = word;

                        wordsToCheck.remove(word);

                        result.add(pair);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
       System.out.println(result.size());
       result.forEach(o -> System.out.println(o.toString()));
    }


    public static class Pair {
        String first;
        String second;

        public Pair() {
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return first == null && second == null ? "" :
                    first == null ? second :
                            second == null ? first :
                                    first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
