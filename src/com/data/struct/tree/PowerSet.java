package com.data.struct.tree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Pattern;

public class PowerSet {

    public static void main(String[] args) {
        String[] sets = {"a", "b", "c", "d"};
        System.out.println("********************************************");
        String[] powerSetsOne = powerSetOne(sets);
        for (String powerSet : powerSetsOne) {
            if (Pattern.matches("[a-z]+Φ$", powerSet)) {
                powerSet = powerSet.substring(0, powerSet.length() - 1);
            }
            System.out.println(powerSet);
        }
        System.out.println("********************************************");
        powerSetTwo(sets);
        System.out.println("********************************************");
        powerSetThree(sets);
    }

    /**
     * 分治法求解幂集
     * 集合 A的幂集就等于其子集 B和 C的交叉集合
     *
     * @param sets 集合
     * @return 幂集
     */
    public static String[] powerSetOne(String[] sets) {
        int setLen = sets.length;
        if (setLen == 1) {
            return new String[] {sets[0], "Φ"};
        }
        String curr = sets[0];
        String[] subSets = Arrays.copyOfRange(sets, 1, setLen);
        String[] powerSets = powerSetOne(subSets);
        return matchSubPowerSet(curr, powerSets);
    }

    private static String[] matchSubPowerSet(String curr, String[] sets) {
        int powerSetsLen = sets.length * 2;
        String[] powerSets = new String[powerSetsLen];
        for (int i = 0; i < sets.length; i++) {
            powerSets[2 * i] = sets[i];
            powerSets[2 * i + 1] = curr + sets[i];
        }
        return powerSets;
    }

    /**
     * 集合 A对应的幂集就是：
     * 0 - 其元素数量 len转换为二进制后，0对应不存在，1对应存在。
     * 设集合 A = {a, b, c}, len = 3
     * 000 - 111: 010: b; 111: abc
     *
     * @param sets 初始集合
     */
    public static void powerSetTwo(String[] sets) {
        int setLen = sets.length;
        int powerSetLen = (int) Math.pow(2, setLen);
        int i, j;
        for (i = 0; i < powerSetLen; i++) {
            for (j = 0; j < setLen; j++) {
                // 001要和 001 010 100进行 &计算，用作判断其哪一位可以被输出
                if ((i & (1 << j)) > 0) { // 1左移 0 1 2位
                    System.out.print(sets[j]);
                }
            }
            System.out.println();
        }
    }

    /**
     * 构造一棵树，非叶子节点的孩子节点是集合元素里的一个元素和空元素
     *      root
     *     a       ""
     *   b   ""   b  ""
     *
     * @param sets 初始集合
     */
    public static void powerSetThree(String[] sets) {
        int setLen = sets.length;
        Node root = new Node(0, null, "");
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        for (int i = 0; i < setLen; i++) {
            int queueSize = nodeQueue.size();
            int j;
            for (j = 0; j < queueSize; j++) {
                Node parent = nodeQueue.poll();
                Node a = new Node(i + 1, parent, sets[i]);
                Node b = new Node(i + 1, parent, "");
                if (i == 3) {
                    printNode(a);
                    printNode(b);
                } else {
                    nodeQueue.add(a);
                    nodeQueue.add(b);
                }
            }
        }
    }

    public static void printNode(Node node) {
        while (node.parent != null) {
            System.out.print(node.data);
            node = node.parent;
        }
        System.out.println();
    }

    public static class Node {
        private int deep;
        private Node parent;
        private String data;

        public Node(int deep, Node parent, String data) {
            this.deep = deep;
            this.parent = parent;
            this.data = data;
        }

    }

}
