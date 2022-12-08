package org.example;

import java.util.Arrays;
import java.util.List;

public class BWT {
    static List<StringBuilder> bwt(String word) {
        int len = word.length();

        String[] strings = new String[len];
        strings[0] = word;
        permutation(len, strings);
        sort(strings);
        return findWord(len, strings, word);
    }

    private static List<StringBuilder> findWord(int len, String[] strings, String word) {
        StringBuilder st = new StringBuilder();
        int t = -10;
        for (int i = 0; i < len; i++) {
            if (strings[i].equals(word)) {
                t = i;
            }
            st.append(strings[i].charAt(len - 1));
        }
        StringBuilder num = new StringBuilder();
        num.append(t);
        return Arrays.asList(st, num);
    }

    private static void permutation(int len, String[] strings) {
        for (int i = 1; i < len; i++) {
            StringBuilder t = new StringBuilder();
            for (int j = 0; j < len; j++) {
                if (j == len - 1) {
                    t.append(strings[i - 1].charAt(0));
                } else {
                    t.append(strings[i - 1].charAt(j + 1));
                }
            }
            strings[i] = t.toString();
        }
    }

    private static void sort(String[] strings) {
        Arrays.sort(strings);
    }
}
