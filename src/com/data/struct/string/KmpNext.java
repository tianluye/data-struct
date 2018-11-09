package com.data.struct.string;

public class KmpNext {

    private static int[] next;

    /**
     * 初始化串 destStr的转移函数
     *
     * @param destStr
     */
    public static void initNext(String destStr) {
        int len = destStr.length();
        // next的长度要与匹配串的长度一致
        next = new int[len];
        // 目标串的第一位对应的 next值只能是 -1
        next[0] = -1;
        int k = -1;
        for (int p = 1; p < len; p++) {
            // k > -1 意味着在前一个子串有相同的前后缀。
            // destStr.charAt(p) 第(p + 1)个位置的元素 不等于 destStr.charAt(k + 1) 上一个子串的最长的前后缀长度 + 1位置的元素
            /*
            分析：abac, p = 3, k = 0
            上一个子串是 aba, 其最长的相同的前后缀是 a。所以判断的是上一个子串的前缀的后一位和本串的最后一位，也就是上一个子串的后缀的后一位元素
            如果不相等，回退到上一个子串对应的相同前后缀长度值 k
             */
            while (k > -1 && destStr.charAt(p) != destStr.charAt(k + 1)) {
                k = next[k];
            }
            // 相等匹配到
            if (destStr.charAt(p) == destStr.charAt(k + 1)) {
                k = k + 1;
            }
            next[p] = k;
        }
    }

    public static int kmpMatcher(String srcStr, String destStr) {
        int sLen = srcStr.length();
        int dLen = destStr.length();
        int j = -1;
        for (int i = 0; i < sLen - dLen; i++) {
            // 不等，则获取对应匹配串的前 j个元素的 next函数值，用来右划
            while (j > -1 && destStr.charAt(j + 1) != srcStr.charAt(i)) {
                j = next[j];
            }
            // 对应位置元素相等，j这里就可以表示匹配串有几个元素和目标串匹配上了
            if (destStr.charAt(j + 1) == srcStr.charAt(i)) {
                j++;
            }
            // 成功匹配到
            if (j == dLen - 1) {
                return i - j;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String destStr = "ababaca";
        String srcStr =  "bacbababadababacambabacaddababacasdsd";
        initNext(destStr);
        for (int n : next) {
            System.out.println(n);
        }
        int index = kmpMatcher(srcStr, destStr);
        System.out.println(index);
    }

}
