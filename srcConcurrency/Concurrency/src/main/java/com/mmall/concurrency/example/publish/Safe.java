//package com.mmall.concurrency.example.publish;
//
//import javafx.event.Event;
//
//import java.util.EventListener;
//
///**
// * Created by carrots on 2018/12/24.
// */
//public class Safe {
//    private final EventListener listener;
//
//    private Safe(){
//        listener = new EventListener(){
//            public void onEvent(Event e){
//                doSomething(e);
//            }
//        };
//    }
//
//    public static Safe newInstance(EventSource source){
//        Safe safeListener = new Safe();
//        safeListener.registerListener(safeListener.listener);
//
//        return safeListener;
//    }
//}
