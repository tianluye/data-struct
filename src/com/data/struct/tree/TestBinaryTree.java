package com.data.struct.tree;

import java.util.ArrayList;
import java.util.List;

public class TestBinaryTree {

    public static void main(String[] args) {
        BinaryTree<String> tree = new BinaryTree<>();
        List<String> data = new ArrayList<>();
        initTreeData(data);
        tree.createBinaryTree(data);
        System.out.println(tree);
        // ABDHIMEJNCFKG
        tree.preOrderTraverse(tree.getRootNode());
        System.out.println();

        // HDMIBJNEAFKCG
        tree.midOrderTraverse(tree.getRootNode());
        System.out.println();

        // HMIDNJEBKFGCA
        tree.postOrderTraverse(tree.getRootNode());
        System.out.println();
    }

    public static void initTreeData(List<String> data) {
        data.add("A");
        data.add("B");
        data.add("C");
        data.add("D");
        data.add("E");
        data.add("F");
        data.add("G");
        data.add("H");
        data.add("I");
        data.add("J");
        data.add(null);
        data.add(null);
        data.add("K");
        data.add(null);
        data.add(null);
        data.add(null);
        data.add(null);
        data.add("M");
        data.add(null);
        data.add(null);
        data.add("N");
    }

}
