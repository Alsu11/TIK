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

        writeToFile(stackOfBooks(resultBwt.get(0)), resultBwt.get(1));

        List<Character> alph = getAlphabet(resultBwt.get(0));
        for (int i = 0; i < alph.size(); i++) {
            System.out.print(alph.get(i) + " ");
        }
        System.out.println();


        System.out.println(stackOfBooks(resultBwt.get(0)));
        System.out.println(resultBwt.get(1));

    }

    private static void writeToFile(StringBuilder stackOfBooks, StringBuilder bwtNumber) {
        File file = new File("C:\\ProjectsJava\\Tik\\Tik\\src\\main\\resources\\result.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);

            fr.write(stackOfBooks.toString());
            fr.write("\n");
            fr.write(bwtNumber.toString());

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