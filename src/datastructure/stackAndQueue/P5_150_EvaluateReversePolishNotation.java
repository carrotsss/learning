package datastructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 根据逆波兰表示法，求表达式的值
 * 有效的算符包括+、-、*、/ 每个运算对象可以使整数，也可以是另一个逆波兰是表达式
 *
 */
public class P5_150_EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            char c = token.charAt(0);
            if (!isOpe(token)) {
                stack.addFirst(stoi(token));
            } else if (c == '+') {
                stack.push(stack.pop() + stack.pop());
            } else if (c == '-') {
                stack.push(- stack.pop() + stack.pop());
            } else if (c == '*') {
                stack.push( stack.pop() * stack.pop());
            } else {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push( num2/num1);
            }
        }
        return stack.pop();
    }
    private static boolean isOpe(String s) {
        return s.length() == 1 && s.charAt(0) <'0' || s.charAt(0) >'9';
    }
    private static int stoi(String s) {
        return Integer.valueOf(s);
    }

    public static void main(String[] args) {
        evalRPN(new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"});
    }
}
