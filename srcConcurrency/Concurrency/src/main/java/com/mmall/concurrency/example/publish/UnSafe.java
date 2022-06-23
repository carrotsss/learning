//package com.mmall.concurrency.example.publish;
//
///**
// * Created by carrots on 2018/12/24.
// */
//public class UnSafe {
//    private int value;
//    public UnSafe(EventSource source){
//        source.registerListener{
//            new EventListener(){
//                public void onEvent(Event e){
//                    doSomething(e);
//                }
//            }
//        }
//        //一些初始化工作
//        value = 7;
//    }
//
//    public void doSomething(Event e){
//        System.out.println(value);
//    }
//}
