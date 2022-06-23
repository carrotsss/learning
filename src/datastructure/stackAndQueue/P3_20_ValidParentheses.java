package datastructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 有效括号
 * 左括号必须用相同类型的右括号闭合
 * 左括号必须以正确的顺序闭合
 */
public class P3_20_ValidParentheses {
    public static boolean isValidOfficial(String string) {
        Deque<Character> queue = new LinkedList<>();
        char ch;
        for (int i = 0; i < string.length(); i++) {
            ch = string.charAt(i);
            //碰到左括号，就把相应的右括号入栈
            if (ch == '(') {
                queue.push(')');
            } else if (ch == '{') {
                queue.push('}');
            } else if (ch == '[') {
                queue.push(']');
            } else if (queue.isEmpty() || queue.peek() != ch) {//如果是右括号判断是否和栈顶元素匹配
                return false;
            }else{
                //queue.peek() == ch
                queue.pop();
            }
        }
        return queue.isEmpty();
    }

    public static void main(String[] args) {
        String string = "{(())}";
        if(isValidOfficial(string)){
            System.out.println("是合法的");
        }
    }
}
