package org.example;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Huffman {

    public static void huffmanDecode(File file) {

        TreeMap<Character, Integer> alphabetData = getDataAlphabet(file);
        String code = getCode(file);

        // генерируем список листов дерева
        ArrayList<Node> nodes = new ArrayList<>();
        for (Character c: alphabetData.keySet()) {
            nodes.add(new Node(c, alphabetData.get(c)));
        }

        // строим кодовое дерево с помощью алгоритма Хаффмана
        Node tree = getTree(nodes);

        // генерируем таблицу префиксных кодов для кодируемых символов с помощью кодового дерева
        TreeMap<Character, String> codes = new TreeMap<>();
        for (Character c: alphabetData.keySet()) {
            codes.put(c, tree.getCodeForCharacter(c, ""));
        }

        assert code != null;
        System.out.println(decode(code, tree));
    }

    private static Node getTree(ArrayList<Node> codeTreeNodes) {
        while (codeTreeNodes.size() > 1) {
            Collections.sort(codeTreeNodes);
            Node left = codeTreeNodes.remove(codeTreeNodes.size() - 1);
            Node right = codeTreeNodes.remove(codeTreeNodes.size() - 1);

            Node parent = new Node(null, right.weight + left.weight, left, right);
            codeTreeNodes.add(parent);
        }
        return  codeTreeNodes.get(0);
    }

    private static String decode(String encoded, Node tree) {
        StringBuilder decoded = new StringBuilder();

        Node node = tree;
        for (int i = 0; i < encoded.length(); i++) {
            node = encoded.charAt(i) == '0' ? node.left : node.right;
            if (node.content != null) {
                decoded.append(node.content);
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
                int pr = -10;
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
