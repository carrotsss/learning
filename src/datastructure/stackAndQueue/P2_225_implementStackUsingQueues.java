package datastructure.stackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class P2_225_implementStackUsingQueues {
    static class MyStackOfficial<T> {
        Queue<T> queue1;
        Queue<T> queue2;

        public MyStackOfficial() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(T x) {
            queue2.offer(x);//先插入queue2
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());//把queue1的值插入queue2
            }
            Queue<T> queueTemp;
            queueTemp = queue1;
            queue1 = queue2;
            queue2 = queueTemp;
        }

        public T pop() {
            return queue1.poll();
        }

        public T top() {
            return queue1.peek();
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyStackOfficial<Integer> myStackOfficial = new MyStackOfficial<>();
        myStackOfficial.push(1);
        myStackOfficial.push(2);
        myStackOfficial.push(3);
        myStackOfficial.push(4);
        myStackOfficial.push(5);
    }
}
