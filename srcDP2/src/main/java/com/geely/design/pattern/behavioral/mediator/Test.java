package com.geely.design.pattern.behavioral.mediator;

/**
 * Created by carrots on 2019/1/26.
 */
public class Test {
    public static void main(String[] args) {
        User ldc = new User("ldc");
        User ghl = new User("ghl");

        ldc.sendMessage("hey!ghl");
        ghl.sendMessage("hey!ldc");
    }
}
