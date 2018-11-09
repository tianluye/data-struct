package com.data.struct.seqence;

public class HanoiTower {

    /**
     * 将编号为 n的托盘从 x位置处移动到 z处
     * @param x 位置信息
     * @param n 托盘编号
     * @param z 位置信息
     */
    private static void move(char x, int n, char z) {
        System.out.println(String.format("将编号为：%s的托盘从 %s移到 %s上。", n, x, z));
    }

    /**
     * 汉诺塔移动过程
     * @param n 托盘的总数
     * @param x 起始位置
     * @param y 调度位置
     * @param z 目标位置
     */
    private static void hanoi(int n, char x, char y, char z) {
        if (n == 1) {
            // 只有一个托盘的时候，将其从 x位置移动到 z位置
            move(x, 1, z);
        } else {
            // 将 编号 1~n-1的托盘从 x位置移动到 y位置，z作为调度位置
            hanoi(n - 1, x, z, y);
            // 将托盘编号为 n的托盘移动到 z上
            move(x, n, z);
            // 将 编号 1~n-1的托盘从 y位置移动到 z位置，x作为调度位置
            hanoi(n - 1, y, x, z);
        }
    }

    public static void main(String[] args) {
        hanoi(3, 'X', 'Y', 'Z');
    }

}
