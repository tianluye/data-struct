package com.data.struct.tree;

import java.util.*;

public class HuffmanTree {

    private Node root;

    /**
     * 树节点定义
     */
    class Node implements Comparable<Node> {
        // 父节点
        private Node parentNode;

        // 左孩子节点
        private Node leftNode;

        // 右孩子节点
        private Node rightNode;

        // 节点数据
        private Integer data;

        // 是否是虚拟节点
        private boolean isVirtual;

        @Override
        public int compareTo(Node o) {
            // 如果与新生成的节点 data值一致，需要排序的时候放在后面
            if (this.data.intValue() == o.data) {
                return this.isVirtual ? 0 : -1;
            }
            return this.data - o.data;
        }

    }

    public Node getRoot() {
        return this.root;
    }

    /**
     * 将最小的两颗树作为左右子树构建一个新的二叉树，将其构造的父节点加入到列表中继续循环处理，直至列表中就剩下一个节点为止。
     *
     * @param nodeData <br></>
     */
    public void createHuffmanTree(Integer[] nodeData) {
        List<Node> nodeList = new ArrayList<>();
        for (Integer data : nodeData) {
            Node node = new Node();
            node.data = data;
            node.isVirtual = false;
            nodeList.add(node);
        }
        Collections.sort(nodeList);
        while (nodeList.size() > 1) {
            Node one = nodeList.remove(0);
            Node two = nodeList.remove(0);
            Integer com = Integer.sum(one.data, two.data);
            Node node = new Node();
            node.data = com;
            node.isVirtual = true;
            node.leftNode = one;
            node.rightNode = two;
            one.parentNode = node;
            two.parentNode = node;
            nodeList.add(node);
            Collections.sort(nodeList);
        }
        this.root = nodeList.get(0);
    }

}
