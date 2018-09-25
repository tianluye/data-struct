package com.data.struct.seqence;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 深度优先： https://blog.csdn.net/autfish/article/details/52447805
 * 广度优先： https://blog.csdn.net/autfish/article/details/52627005
 */
public class ThroughMaze {

    /**
     * 定义迷宫
     * false: 不可通过
     * true : 可通过
     */
    private static boolean[][] mazeArr = {
        {false, false, false, false, false, false, false, false, false, false},
        {false, true,  true,  false, true,  true,  true,  false, true,  false},
        {false, true,  true,  false, true,  true,  true,  false, true,  false},
        {false, true,  true,  true,  true,  false, false, true,  true,  false},
        {false, true,  false, false, false, true,  true,  true,  true,  false},
        {false, true,  true,  true,  false, true,  true,  true,  true,  false},
        {false, true,  false, true,  true,  true,  false, true,  true,  false},
        {false, true,  false, false, false, true,  false, false, true,  false},
        {false, false, true,  true,  true,  true,  true,  true,  true,  false},
        {false, false, false, false, false, false, false, false, false, false}
    };

    /**
     * 迷宫起始的 X坐标
     */
    private static int srcX = 1;

    /**
     * 迷宫起始的 Y坐标
     */
    private static int srcY = 1;

    /**
     * 迷宫目标的 X坐标
     */
    private static int destX = 8;

    /**
     * 迷宫目标的 Y坐标
     */
    private static int destY = 8;

    /**
     * 探索迷宫的移到路径
     */
    private static Stack<Maze> stack = new Stack<>();

    /**
     * 探索迷宫的方向，依次为：右、下、左、上
     */
    private static int[][] next = {
        {1, 0}, {0, 1}, {-1, 0}, {0, -1}
    };

    public static void main(String[] args) {
        int random = (int)(Math.random() * 10);
        if (random <= 5) {
            System.out.println("深度优先......");
            Maze initCurrMaze = new Maze(srcX, srcY);
            stack.push(initCurrMaze);
            explore(srcX, srcY);
        } else {
            System.out.println("广度优先......");
            exploreBfs();
        }
    }

    /**
     * 深度优先
     * 在迷宫 (x, y)处向 4个方向探索
     * @param x
     * @param y
     */
    private static void explore(int x, int y) {
        // 本次探索，已经探索过的路径集合
        List<Maze> directList = new ArrayList<>();
        // 右、下、左、上
        for (int i = 0; i < next.length; i++) {
            if (mazeArr[y + next[i][1]][x + next[i][0]]) {
                // 是否在当前的移动队列里
                Maze stackMaze = new Maze(x + next[i][0], y + next[i][1]);
                int search = stack.search(stackMaze);
                if (search == -1) { // 不在
                    // Maze.dict取值 1 2 3 4 分别对应右、下、左、上 4个方向
                    Maze maze = new Maze(x, y, i + 1);
                    // 是否已经走过
                    if (!directList.contains(maze)) {
                        stack.push(stackMaze); // 将当前探索的路径加入到队列里
                        directList.add(maze); // 记录当前探索的路径及方向加入到列表中
                        // 是否到达目的地
                        if ((x + next[i][0]) == destX && (y + next[i][1]) == destY) {
                            outputStack(); // 打印探索成功的消息
                            stack.pop(); // 目的地出栈，进行其他方向的探索
                        } else {
                            // 递归调用下一次探索
                            explore(x + next[i][0], y + next[i][1]);
                        }
                    }
                }
            }
        }
        // 所在路径四个方向都已经探索完毕
        Maze topMaze = stack.peek();
        // 没找到出口，说明是错误步骤，出栈取消本次的探索
        if (topMaze.getPosX() != destX || topMaze.getPosY() != destY) {
            stack.pop();
        }
    }

    /**
     * 广度优先
     */
    private static void exploreBfs() {
        int head = 0; // 将要进行探索的迷宫节点
        int tail = 1; // 待探索标号最大的迷宫节点
        int nextX, nextY;
        List<Trace> traces = new ArrayList<>();
        boolean[][] markArr = new boolean[10][10];
        markArr[srcX][srcY] = true;
        traces.add(new Trace(srcX, srcY, -1, 0));
        boolean flag = false;
        while (head < tail) {
            for (int i = 0; i < next.length; i++) {
                nextX = traces.get(head).getX() + next[i][0];
                nextY = traces.get(head).getY() + next[i][1];
                // 判断是否合法，是否可以通过，是否已经探索过
                if (!mazeArr[nextY][nextX] || markArr[nextX][nextY]) {
                    continue;
                }
                markArr[nextX][nextY] = true;
                traces.add(tail, new Trace(nextX, nextY, head, traces.get(head).getStep() + 1));
                tail++;
                if (nextX == destX && nextY == destY) {
                    flag = true;
                    break;
                }
            }
            // 若找到目标，则退出探索
            if (flag) {
                break;
            }
            head++;
        }
        Trace end = traces.get(tail - 1);
        System.out.println("共" + end.getStep() + "步");
        Stack<Maze> mazeStack = new Stack<>();
        while (true) {
            Maze maze = new Maze(end.getX(), end.getY());
            mazeStack.push(maze);
            int father = end.getFather();
            if (father == -1) {
                break;
            }
            end = traces.get(father);
        }
        while (!mazeStack.isEmpty()) {
            stack.push(mazeStack.pop());
        }
        outputStack();
    }

    private static void outputStack() {
        String[][] mazeArrStr = {
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"},
                {"*", "*", "*", "*", "*", "*", "*", "*", "*", "*"}
        };
        List<Maze> mazeList = stack.subList(0, stack.size());
        int num = 0;
        for (Maze maze : mazeList) {
            mazeArrStr[maze.getPosY()][maze.getPosX()] = String.valueOf(num);
            num++;
            System.out.println(maze);
        }
        for (String[] mazes : mazeArrStr) {
            for (String step : mazes) {
                if (step.length() == 1) {
                    step = step + " ";
                }
                System.out.print(step);
            }
            System.out.println();
        }
        System.out.println();
    }

    static class Maze {
        private int posX;
        private int posY;
        private int dict;

        public int getPosX() {
            return posX;
        }

        public void setPosX(int posX) {
            this.posX = posX;
        }

        public int getPosY() {
            return posY;
        }

        public void setPosY(int posY) {
            this.posY = posY;
        }

        public int getDict() {
            return dict;
        }

        public void setDict(int dict) {
            this.dict = dict;
        }

        public Maze() {
        }

        public Maze(int posX, int posY) {
            this(posX, posY, 0);
        }

        public Maze(int posX, int posY, int dict) {
            this.posX = posX;
            this.posY = posY;
            this.dict = dict;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Maze maze = (Maze) o;

            if (posX != maze.posX) return false;
            if (posY != maze.posY) return false;
            return dict == maze.dict;
        }

        @Override
        public int hashCode() {
            int result = posX;
            result = 31 * result + posY;
            result = 31 * result + dict;
            return result;
        }

        @Override
        public String toString() {
            return "Maze{" +
                    "posX=" + posX +
                    ", posY=" + posY +
                    ", dict=" + dict +
                    '}';
        }
    }

    static class Trace {

        public Trace(int x, int y, int father, int step) {
            this.x = x;
            this.y = y;
            this.father = father;
            this.step = step;
        }

        private int x;
        private int y;
        private int father;
        private int step;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getFather() {
            return father;
        }

        public void setFather(int father) {
            this.father = father;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }
    }

}