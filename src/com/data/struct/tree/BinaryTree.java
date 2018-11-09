package com.data.struct.tree;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTree<T> {

    /**
     * 树的根节点
     */
    private Node root;

    /**
     * 根据数据列表组装树结构
     * A A1 A2 A11 A12 A21 A22... 根节点是 A，其左孩子节点是 A1, 右孩子节点是 A2
     * A1的左孩子节点是 A11，右孩子节点是 A12
     *
     * @param dataList 树的节点数据列表，顺序存储结构转变为链式存储
     */
    public void createBinaryTree(List<T> dataList) {
        Queue<Node> nodeQueue = new LinkedList<>();
        root = new Node();
        root.parentNode = null;
        root.data = dataList.get(0);
        nodeQueue.add(root);
        Node leftNode, rightNode;
        int len = dataList.size();
        int i = 1;
        while (i < len) {
            leftNode = new Node();
            rightNode = new Node();
            Node parent = nodeQueue.poll();
            leftNode.parentNode = parent;
            leftNode.data = dataList.get(i);
            rightNode.parentNode = parent;
            i++;
            if (i < len) {
                rightNode.data = dataList.get(i);
                parent.leftNode = leftNode;
                parent.rightNode = rightNode;
                i++;
            }
            nodeQueue.add(leftNode);
            nodeQueue.add(rightNode);
        }
    }

    /**
     * 获取树的根节点
     *
     * @return 树的跟节点
     */
    public Node getRootNode() {
        return root;
    }

    /**
     * 前序遍历树 (波兰表达式)
     * 父节点 -> 左孩子节点 -> 右孩子节点
     *
     * @param node 根节点
     */
    public void preOrderTraverse(Node node) {
        if (null != node.data) {
            System.out.print(node.data);
            if (null != node.leftNode && null != node.leftNode.data) {
                preOrderTraverse(node.leftNode);
            }
            if (null != node.rightNode && null != node.rightNode.data) {
                preOrderTraverse(node.rightNode);
            }
        }
    }

    /**
     * 中序遍历树
     * 左孩子节点 -> 父节点 -> 右孩子节点
     *
     * @param node 根节点
     */
    public void midOrderTraverse(Node node) {
        if (null != node.data) {
            if (null != node.leftNode && null != node.leftNode.data) {
                midOrderTraverse(node.leftNode);
            }
            System.out.print(node.data);
            if (null != node.rightNode && null != node.rightNode.data) {
                midOrderTraverse(node.rightNode);
            }
        }
    }

    /**
     * 后序遍历树 (逆波兰表达式)
     * 左孩子节点 -> 右孩子节点 -> 父节点
     *
     * @param node 根节点
     */
    public void postOrderTraverse(Node node) {
        if (null != node.data) {
            if (null != node.leftNode && null != node.leftNode.data) {
                postOrderTraverse(node.leftNode);
            }
            if (null != node.rightNode && null != node.rightNode.data) {
                postOrderTraverse(node.rightNode);
            }
            System.out.print(node.data);
        }
    }

    /**
     * 树节点定义
     */
    private class Node {
        // 父节点
        private Node parentNode;

        // 左孩子节点
        private Node leftNode;

        // 右孩子节点
        private Node rightNode;

        // 节点数据
        private T data;
    }

}
