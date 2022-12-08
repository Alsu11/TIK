package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackOfBooks {

    static StringBuilder stackOfBooks(StringBuilder sbBwt) {
        return getSequence(sbBwt, getAlphabet(sbBwt));
    }

    private static StringBuilder getSequence(StringBuilder sbBwt, List<Character> alphabet) {
        StringBuilder sequence = new StringBuilder("");
        for (int i = 0; i < sbBwt.length(); i++) {

            for (int j = 0; j < alphabet.size(); j++) {
                if (sbBwt.charAt(i) == alphabet.get(j)) {
                    sequence.append(Integer.toBinaryString(j));
                    sequence.append(" ");

                    if (j > 0) {
                        flipAlphabet(alphabet, sbBwt.charAt(i), j);
                        j = alphabet.size();
                    }
                }
            }
        }
        return sequence;
    }

    private static void flipAlphabet(List<Character> alphabet, Character t, int index) {
        for (int i = index; i >= 0; i--) {
            if (i == 0) {
                alphabet.set(i, t);
            } else {
                alphabet.set(i, alphabet.get(i - 1));
            }
        }
    }

    static List<Character> getAlphabet(StringBuilder sbBwt) {
        List<Character> alphabet = new ArrayList<>();
        for (int i = 0; i < sbBwt.length(); i++) {
            if (!alphabet.contains(sbBwt.charAt(i))) {
                alphabet.add(sbBwt.charAt(i));
            }
        }
        Collections.sort(alphabet);
        return alphabet;
    }
}
