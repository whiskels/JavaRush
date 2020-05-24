package com.javarush.task.task20.task2027;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* 
Кроссворд
*/
public class Solution {
    public static void main(String[] args) {
        int[][] crossword = new int[][]{
                {'f', 'd', 'e', 'r', 'l', 'k'},
                {'u', 's', 'a', 'm', 'e', 'o'},
                {'l', 'n', 'g', 'r', 'o', 'v'},
                {'m', 'l', 'e', 'm', 'o', 'h'},
                {'p', 'o', 'e', 'e', 'j', 'j'}
        };

        for (Word word : detectAllWords(crossword, "home", "same"))
            System.out.println(word);

        /*
Ожидаемый результат
home - (5, 3) - (2, 0)
same - (1, 1) - (4, 1)
         */
    }

    public static List<Word> detectAllWords(int[][] crossword, String... words) {
        ArrayList<Word> result = new ArrayList<Word>();


        for (String name : words) {

            for (int y = 0; y < crossword.length; y++) {
                for (int x = 0; x < crossword[y].length; x++) {
                    if (crossword[y][x] == name.charAt(0)) {
                        if (name.length() == 1) {
                            addFoundWord(result, name, x, y, x, y);
                        } else {
                            foundLetter(crossword, name, y, x, result);
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void foundLetter(int[][] crossword, String name, int startY, int startX, List<Word> wordsList) {
        for (int a = startY - 1; a <= (startY + 1); a++) {
            for (int b = startX - 1; b <= (startX + 1); b++) {
                if (a == startY && b == startX) {
                    continue;
                } else if (checkBorders(crossword, b, a)) {
                    continue;
                } else if (crossword[a][b] == name.charAt(1)) {
                    if (name.length() == 2) {
                        addFoundWord(wordsList, name, startX, startY, a, b);
                    } else {
                        int dX = b - startX;
                        int dY = a - startY;
                        int endY = a;
                        int endX = b;

                        for (int i = 2; i < name.length(); i++) {
                            endY += dY;
                            endX += dX;
                            if (checkBorders(crossword, endX, endY)) {
                                break;
                            } else if (crossword[endY][endX] == name.charAt(i)) {
                                if (i == name.length() - 1) {
                                    addFoundWord(wordsList, name, startX, startY, endX, endY);
                                }
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    public static void addFoundWord(List<Word> words, String name, int startX, int startY, int endX, int endY) {
        Word word = new Word(name);
        word.setStartPoint(startX, startY);
        word.setEndPoint(endX, endY);
        words.add(word);
    }

    public static boolean checkBorders(int[][] crossword, int X, int Y) {
        boolean isOutsideOfBorders = false;
        if (X < 0 || Y < 0 || Y >= crossword.length || X >= crossword[0].length) {
            isOutsideOfBorders = true;
        }
        return isOutsideOfBorders;
    }




    public static class Word {
        private String text;
        private int startX;
        private int startY;
        private int endX;
        private int endY;

        public Word(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        public void setStartPoint(int i, int j) {
            startX = i;
            startY = j;
        }

        public void setEndPoint(int i, int j) {
            endX = i;
            endY = j;
        }

        @Override
        public String toString() {
            return String.format("%s - (%d, %d) - (%d, %d)", text, startX, startY, endX, endY);
        }
    }
}
