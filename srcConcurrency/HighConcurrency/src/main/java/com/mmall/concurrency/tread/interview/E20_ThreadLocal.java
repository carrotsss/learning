package com.mmall.concurrency.tread.interview;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class E20_ThreadLocal<T> {
    class A{
        private int i;

        public int get() {
            return i;
        }
    }

    class B<E> {
        public void methodB(A a) {
            a.get();
        }
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        add(list, new Date());
        System.out.println(list.get(0));
    }

    public static void add(List list, Object ele) {
        list.add(ele);
    }

}
