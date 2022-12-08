package org.example;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import static org.example.BWT.bwt;
import static org.example.StackOfBooks.getAlphabet;
import static org.example.StackOfBooks.stackOfBooks;

public class MainCode {
    public static void main(String[] args) {
        //стопка книг + БУ
        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        List<StringBuilder> resultBwt = bwt(word);

        List<Character> alph = getAlphabet(resultBwt.get(0));
        for (int i = 0; i < alph.size(); i++) {
            System.out.print(alph.get(i));
        }
        System.out.println();

        System.out.println(stackOfBooks(resultBwt.get(0)));
        System.out.println(Integer.toBinaryString(Integer.parseInt(resultBwt.get(1).toString())));

        writeToFile(alph, stackOfBooks(resultBwt.get(0)), resultBwt.get(1));
    }

    private static void writeToFile(List<Character> alph, StringBuilder stackOfBooks, StringBuilder bwtNumber) {
        File file = new File("C:\\TIK\\Tik\\src\\main\\resources\\code.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);

            for (Character character : alph) {
                fr.write(character);
            }

            fr.write("\n");

            fr.write(stackOfBooks.toString());
            fr.write("\n");
            
            fr.write(Integer.toBinaryString(Integer.parseInt(bwtNumber.toString())));

        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            try {
                assert fr != null;
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}