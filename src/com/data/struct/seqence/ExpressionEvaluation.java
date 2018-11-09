package com.data.struct.seqence;

import java.util.HashMap;
import java.util.Stack;

public class ExpressionEvaluation {

    /**
     * 存放操作符的优先级关系
     */
    private static HashMap<String, HashMap<String, Integer>> operatorDelimiter = new HashMap<>();

    /**
     * 运算符栈
     */
    private static Stack<String> opTr = new Stack<>();

    /**
     * 运算数栈
     */
    private static Stack<String> opNd = new Stack<>();

    /**
     * 初始化操作符优先级关系
     */
    private static void initOperatorDelimiter() {
        String[] operators = {"+", "-", "*", "/", "(", ")", "#"};
        Integer[][] results = {
                { 1,  1, -1, -1, -1,   1,    1},
                { 1,  1, -1, -1, -1,   1,    1},
                { 1,  1,  1,  1, -1,   1,    1},
                { 1,  1,  1,  1, -1,   1,    1},
                {-1, -1, -1, -1, -1,   0,    null},
                { 1,  1,  1,  1, null, 1,    1},
                {-1, -1, -1, -1, -1,   null, 0}
        };
        int len = operators.length;
        for(int i = 0; i < len; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = 0; j < len; j++) {
                map.put(operators[j], results[i][j]);
            }
            operatorDelimiter.put(operators[i], map);
        }
    }

    /**
     * 进行表达式求值
     *
     * @param expression 表达式
     * @throws Exception <br>
     */
    private static void initStack(String expression) throws Exception {
        // 去除表达式中所有的空格
        expression = expression.replaceAll(" ", "");
        if (!expression.endsWith("#")) {
            throw new Exception("表达式必须要以 '#' 结尾。");
        }
        // 运算符栈首先压入 #
        opTr.push("#");
        int len = expression.length();
        int i = 0;
        // 遍历表达式
        while (i < len) {
            String element = String.valueOf(expression.charAt(i));
            try {
                // 通过强转元素值位 Integer类型，判断是运算符还是运算数
                Integer.parseInt(element);
                // 未抛出异常，压入运算数栈
                opNd.push(element);
            } catch (NumberFormatException e) {
                // 抛异常，说明是运算符，与运算栈顶元素进场优先级对比
                String popElement = opTr.peek();
                Integer result = operatorDelimiter.get(popElement).get(element);
                switch (result) {
                    case -1 : // 左边的运算符优先级小于右边的运算符
                        opTr.push(element);
                        break;
                    case 0 : // 优先级相等
                        opTr.pop(); // 将左边对应的运算符移除（左括号）
                        break;
                    default:
                        String rightNum = opNd.pop();
                        String leftNum = opNd.pop();
                        String op = opTr.pop();
                        // 运算结果压入到 opNd栈中
                        opNd.push(dealExpression(leftNum, op, rightNum));
                        i--;
                }
            }
            i++;
        }
    }

    /**
     * 计算结果
     * @param leftNum  左边的计算数
     * @param op       操作符
     * @param rightNum 右边的计算数
     * @return 计算结果
     * @throws Exception <br>
     */
    private static String dealExpression(String leftNum, String op, String rightNum) throws Exception {
        Integer leftN = Integer.parseInt(leftNum);
        Integer rightN = Integer.parseInt(rightNum);
        Integer result = 0;
        switch (op) {
            case "+" :
                result = leftN + rightN;
                break;
            case "-" :
                result = leftN - rightN;
                break;
            case "*" :
                result = leftN * rightN;
                break;
            case "/" :
                result = leftN / rightN;
                break;
            default:
                break;
        }
        return String.valueOf(result.intValue());
    }

    public static void main(String[] args) throws Exception {
        // 初始化算符的优先关系
        initOperatorDelimiter();
        // 定义表达式： 3 * (7 - 2) + 5 - (1 + (1 * 3))#
        initStack("3 * (7 - 2) + 5 - (1 + (1 * 3))#");
        String result = opNd.peek();
        System.out.println(result);
    }

}
