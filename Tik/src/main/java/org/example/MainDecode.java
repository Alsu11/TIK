package org.example;

import java.io.File;
import java.io.IOException;

import static org.example.Huffman.huffmanDecode;

public class MainDecode {
    public static void main(String[] args) throws IOException {
        huffmanDecode( new File("C:\\TIK\\Tik\\src\\main\\resources\\decode.txt"));
    }
}
