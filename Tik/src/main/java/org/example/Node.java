package org.example;

import java.util.Objects;

public class Node implements Comparable<Node> {
    Character character;
    int probability;
    Node left;
    Node right;

    public Node(Character character, int probability) {
        this.character = character;
        this.probability = probability;
    }

    public Node(Character character, int probability, Node left, Node right) {
        this.character = character;
        this.probability = probability;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node o) {
        return o.probability - probability;
    }

    public String getCodeForCharacter(Character ch, String parentPath) {
        if (character == ch) {
            return parentPath;
        } else {
            if (left != null) {
                String path = left.getCodeForCharacter(ch, parentPath + "0");
                if (path != null) {
                    return path;
                }
            }
            if (right != null) {
                String path = right.getCodeForCharacter(ch, parentPath + "1");
                if (path != null) {
                    return path;
                }
            }
        }
        return null;
    }

}
