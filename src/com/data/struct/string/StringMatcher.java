package com.data.struct.string;

public class StringMatcher {


    public static void main(String[] args) {
        String srcStr = "abcdefgh";
        String destStr = "ef";
        int pos = 2;
        int index = findSubStrOne(srcStr, destStr, pos);
        System.out.println(index);
    }

    private static int findSubStrOne(String srcStr, String destStr, int pos) {
        int len = srcStr.length();
        if (pos >= len) {
            return -1;
        }
        if (len < destStr.length()) {
            return -1;
        }
        if (pos + destStr.length() >= len) {
            return -1;
        }
        len = destStr.length();
        for (int i = 0; i < len; i++) {
            if (srcStr.charAt(pos + i) != destStr.charAt(i)) {
                return findSubStrOne(srcStr, destStr, pos + 1);
            }
        }
        return pos;
    }

}
