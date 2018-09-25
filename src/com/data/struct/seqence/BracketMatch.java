package com.data.struct.seqence;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class BracketMatch {

    private static final String LEFT_ONE = "{";

    private static final String LEFT_TWO = "[";

    private static final String LEFT_THREE = "(";

    private static final String RIGHT_ONE = "}";

    private static final String RIGHT_TWO = "]";

    private static final String RIGHT_THREE = ")";

    private static Map<String, String> bracketMap = new HashMap<String, String>() {
        {
            put(LEFT_ONE, RIGHT_ONE);
            put(LEFT_TWO, RIGHT_TWO);
            put(LEFT_THREE, RIGHT_THREE);
        }
    };

    public static void main(String[] args) {
        // {([()])}
        try {
            String[] brackets = new String[] {"{", "(", "[", "(", ")", "]", ")", "}"};
            matchBracket(brackets);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void matchBracket(String[] brackets) throws Exception {
        int len = brackets.length;
        int i = 0;
        Stack<String> stack = new Stack<>();
        boolean isStart = false;
        while (!stack.isEmpty() || !isStart) {
            isStart = true;
            String bracket = brackets[i];
            if (isInLeft(bracket)) {
                stack.push(bracket);
                System.out.println(bracket);
            } else if (isInRight(bracket)) {
                if (stack.empty()) {
                    throw new Exception("目前栈内元素为空，无法匹配右括号。");
                }
                String beforeBracket = stack.peek();
                if (bracketMap.get(beforeBracket).equals(bracket)) {
                    stack.pop();
                    System.out.println(bracket);
                } else {
                    throw new Exception("栈顶元素不匹配，无法匹配右括号。");
                }
            } else {
                throw new Exception(String.format(":s不是括号。", bracket));
            }
            i++;
        }
    }

    public static boolean isInLeft(String bracket) {
        return LEFT_ONE.equals(bracket) || LEFT_TWO.equals(bracket) || LEFT_THREE.equals(bracket);
    }

    public static boolean isInRight(String bracket) {
        return RIGHT_ONE.equals(bracket) || RIGHT_TWO.equals(bracket) || RIGHT_THREE.equals(bracket);
    }

}
