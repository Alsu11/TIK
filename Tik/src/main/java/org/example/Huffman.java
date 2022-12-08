package org.example;

import java.io.*;
import java.util.*;

public class Huffman {

    public static void huffmanDecode(File file) {

        TreeMap<Character, Integer> alphabetData = getDataAlphabet(file);
        String code = getCode(file);

        ArrayList<Node> nodes = new ArrayList<>();   // генерируем список листов дерева
        for (Character c: alphabetData.keySet()) {
            nodes.add(new Node(c, alphabetData.get(c)));
        }

        Node tree = getTree(nodes);    // строим кодовое дерево с помощью алгоритма Хаффмана

        TreeMap<Character, String> codes = new TreeMap<>();   // генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
        for (Character c: alphabetData.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }

        for (Map.Entry<Character, String> m : codes.entrySet()) {
           System.out.println(m.getKey() + " : " + m.getValue());
        }

        assert code != null;
        System.out.println(decode(code, tree));
    }

    private static Node getTree(ArrayList<Node> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            codeTreeNodes.sort(Collections.reverseOrder());
            Node left = codeTreeNodes.remove(0);
            Node right = codeTreeNodes.remove(0);

            Node parent = new Node(null, right.probability + left.probability, left, right);
            codeTreeNodes.add(parent);
        }
        return  codeTreeNodes.get(0);
    }

    private static String decode(String encoded, Node tree) {
        StringBuilder decoded = new StringBuilder();

        Node node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            if (encoded.charAt(i) == '0') {
                node = node.left;
            } else {
                node = node.right;
            }
            if (node.character != null) {
                decoded.append(node.character);
                node = tree;
            }
        }
        return decoded.toString();
    }

    private static TreeMap<Character, Integer> getDataAlphabet(File file) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            List<String> alphabet = Arrays.asList(reader.readLine().split(" "));
            List<String> probabilities = Arrays.asList(reader.readLine().split(" "));

            for (int i = 0; i < alphabet.size(); i++) {
                int pr;
                if (probabilities.get(i).contains(",") || probabilities.get(i).contains(".")) {
                    pr = (int) (Float.parseFloat(probabilities.get(i).replace(",", ".")) * 1000000);
                } else {
                    pr = Integer.parseInt(probabilities.get(i));
                }
                map.put(alphabet.get(i).toCharArray()[0], pr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!!!");
        } catch (IOException e) {
            System.out.println("Некорректный файл!!!");
        }
        return map;
    }

    private static String getCode(File file) {
        try {
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);

            reader.readLine();
            reader.readLine();
            return reader.readLine();
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден!!!");
        } catch (IOException e) {
            System.out.println("Некорректный файл!!!");
        }
        return null;
    }
}
