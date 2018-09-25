package com.data.struct.seqence;

import java.util.Stack;

public class Conversion {

    public static void main(String[] args) {
        Integer number = 1348;
        convert(number);
    }

    public static void convert(Integer number) {
        Stack<String> stack = new Stack<>();
        if (0 == number) {
            return;
        }
        while (number != 0) {
            stack.push(String.valueOf(number % 8));
            number = number / 8;
        }
        System.out.println();
        while (!stack.empty()) {
            System.out.print(stack.pop());
        }
    }

}
