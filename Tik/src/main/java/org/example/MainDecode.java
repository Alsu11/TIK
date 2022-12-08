package org.example;

import java.io.File;

import static org.example.Huffman.huffmanDecode;

public class MainDecode {
    public static void main(String[] args) {
        huffmanDecode( new File("C:\\ProjectsJava\\Tik\\Tik\\src\\main\\resources\\result.txt"));
    }
}
