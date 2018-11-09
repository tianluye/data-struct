package com.data.struct.tree;

import java.util.*;

public class EightQueen {

    public static void main(String[] args) {
        solveEightQueen(8);
    }

    /**
     * 根节点，空棋盘
     */
    private static Node root = new Node();

    /**
     * 构造 n皇后在棋盘中的位置，其中要满足以下条件：
     * 1. 每两个皇后不能再同一列同一行出现
     * 2. 皇后不能全部处于对角线上
     *
     * @param queenNum 皇后数量
     */
    public static void solveEightQueen(Integer queenNum) {
        // 满足规则放置棋子的队列
        Queue<Node> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        while (nodeQueue.size() != 0) {
            Node queen = nodeQueue.poll();
            // 棋盘上的皇后数量要小于总皇后数量
            if (queen.deep < queenNum) {
                // Y坐标加一
                Integer posY = queen.deep + 1;
                // 因为皇后所处位置的特性，每一行每一列，必定存在皇后。尝试每一行中放入皇后
                for (int i = 1; i <= queenNum; i++) {
                    List<Integer> existedXList = new ArrayList<>();
                    existedXList.addAll(queen.existedXList);
                    List<Integer> existedYList = new ArrayList<>();
                    existedYList.addAll(queen.existedYList);
                    // 若尝试放的位置已经不满足条件，则进行下一个的位置尝试
                    if (existedXList.size() > 0 && existedYList.size() > 0) {
                        boolean isExisted = existedXList.contains(i) || existedYList.contains(posY);
                        if (isExisted) {
                            continue;
                        }
                    }
                    // 若满足，则将皇后放入该位置
                    Node nextQueen = new Node();
                    nextQueen.posX = i;
                    nextQueen.posY = posY;
                    existedXList.add(i);
                    existedYList.add(posY);
                    nextQueen.existedXList = existedXList;
                    nextQueen.existedYList = existedYList;
                    nextQueen.deep = posY;
                    // 若不是最后一个皇后，则将其加入到上面的队列里
                    if (posY != queenNum.intValue()) {
                        nodeQueue.add(nextQueen);
                    } else {
                        // 判断已经全部落下的皇后位置是否是在对角线上
                        // 其特征：existedXList和 existedYList要么相等，要么相反
                        if (!nextQueen.existedXList.equals(nextQueen.existedYList)) {
                            Collections.reverse(nextQueen.existedXList);
                            boolean flag = nextQueen.existedXList.equals(nextQueen.existedYList);
                            if (!flag) {
                                System.out.println(nextQueen);
                                printEightQueen(nextQueen);
                            }
                        }
                    }
                }
            }
        }
    }

    public static void printEightQueen(Node queen) {
        Integer deep = queen.deep;
        String[][] queenMap = new String[deep][deep];
        for (int i = 0; i < deep; i++) {
            for (int j = 0; j < deep; j++) {
                queenMap[i][j] = "□";
            }
        }
        for (int i = 0; i < deep; i++) {
            Integer x = queen.existedXList.get(i);
            Integer y = queen.existedYList.get(i);
            queenMap[x - 1][y - 1] = "♘";
        }
        for (int i = 0; i < deep; i++) {
            for (int j = 0; j < deep; j++) {
                System.out.print(queenMap[i][j]);
            }
            System.out.println();
        }
    }



    static class Node {
        /**
         * 棋子皇后所在棋盘的 X坐标
         */
        private Integer posX;

        /**
         * 棋子皇后所在棋盘的 Y坐标
         */
        private Integer posY;

        /**
         * 棋盘上落下第 deep个皇后棋子
         */
        private Integer deep;

        /**
         * 棋盘上落下皇后棋子后，棋盘被棋子所占的 X坐标集合
         */
        private List<Integer> existedXList;

        /**
         * 棋盘上落下皇后棋子后，棋盘被棋子所占的 Y坐标集合
         */
        private List<Integer> existedYList;

        public Node() {
            this.deep = 0;
            this.existedYList = new ArrayList<>();
            this.existedXList = new ArrayList<>();
            this.posX = 0;
            this.posY = 0;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "posX=" + posX +
                    ", posY=" + posY +
                    ", deep=" + deep +
                    ", existedXList=" + existedXList +
                    ", existedYList=" + existedYList +
                    '}';
        }
    }

}
