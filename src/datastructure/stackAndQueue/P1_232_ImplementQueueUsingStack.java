package datastructure.stackAndQueue;

import java.util.Stack;

/**
 * 两个栈构建一个队列
 *
 * @param <T>
 */
public class P1_232_ImplementQueueUsingStack<T> {
    static class MyQueueSelf<T> {
        private Stack<T> input = new Stack<>();
        private Stack<T> out = new Stack<>();

        public void appendTail(T t) {
            input.push(t);
        }

        public T deleteHead(T t) {
            if (out.isEmpty()) {
                while (!input.isEmpty()) {
                    out.push(input.pop());
                }
            }
            return out.pop();
        }

        public void appendTail1(T t) {
            input.push(t);
        }

        public T popHead() {
            if (!out.isEmpty()) {
                return out.pop();
            }
            while (!input.isEmpty()) {
                out.push(input.pop());
            }
            return out.pop();
        }

        public int getSize() {
            return input.size() + out.size();
        }
    }

    class MyQueueOfficial<T> {
        Stack<T> stack1;
        Stack<T> stack2;

        public MyQueueOfficial() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(T X) {
            stack1.push(X);
        }

        public T pop() {
            dumpStack1();
            return stack2.pop();
        }

        public T peek() {
            dumpStack1();
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }

        private void dumpStack1() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
        }

}

}

