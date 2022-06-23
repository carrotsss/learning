package datastructure.stackAndQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * 给出有小写字母组成的字符串s，重复项删除操作会选择两个相邻且相同的字母，并删除他们。
 * 在s上反复执行重复项删除操作，直到无法继续删除
 * 在完成所有重复项删除操作后，返回最终的字符串，答案保证唯一。
 * 输入：“abbaca”
 * 输出：“ca”
 * 先删除bb，剩下"aaca"，删除aa，剩下"ca"
 */
public class P4_1047_RemoveAllAdjacentDuplicatesInString {
    public static String removeDuplicatesSelf(String s) {
        Stack<Character> stack = new Stack<>();
        stack.push(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (stack.peek() == s.charAt(i)) {
                stack.pop();
            }else{
                stack.push(s.charAt(i));
            }
        }
        String result = "";
        for (char c : stack) {
            result = result + c;
        }
        return result;
    }

    public static String removeDuplicatesOfficial(String s) {
        Deque<Character> deque = new LinkedList<>();
        char ch;
        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            if (deque.isEmpty() || deque.peek() != ch) {
                deque.push(ch);
            }else{
                deque.pop();
            }
        }
        String string = "";
        while (!deque.isEmpty()) {
            string = deque.pop() + string;
        }
        return string;
    }

    public static void main(String[] args) {
        String s = "abbaca";
        System.out.println(removeDuplicatesOfficial(s));
    }
}
