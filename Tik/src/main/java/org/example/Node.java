package org.example;

public class Node implements Comparable<Node> {
    Character content;
    int weight;
    Node left;
    Node right;

    public Node(Character content, int weight) {
        this.content = content;
        this.weight = weight;
    }

    public Node(Character content, int weight, Node left, Node right) {
        this.content = content;
        this.weight = weight;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return o.weight - weight;
    }

    // извлечение кода для символа
    public String getCodeForCharacter(Character ch, String parentPath) {
        if (content == ch) {
            return  parentPath;
        } else {
            if (left != null) {
                String path = left.getCodeForCharacter(ch, parentPath + 0);
                if (path != null) {
                    return path;
                }
            }
            if (right != null) {
                String path = right.getCodeForCharacter(ch, parentPath + 1);
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }
}
