package com.data.struct.tree;

public class TestHuffmanTree {

    public static void main(String[] args) {
        Integer[] nodesArr = {23, 11, 5, 3, 29, 14, 7, 8};
        HuffmanTree tree = new HuffmanTree();
        tree.createHuffmanTree(nodesArr);
        System.out.println(tree);
    }

}
