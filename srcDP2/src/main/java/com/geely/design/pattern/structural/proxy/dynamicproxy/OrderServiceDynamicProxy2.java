package com.geely.design.pattern.structural.proxy.dynamicproxy;

import com.geely.design.pattern.structural.proxy.IOrderService;
import com.geely.design.pattern.structural.proxy.Order;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by carrots on 2019/1/25.
 */
public class OrderServiceDynamicProxy2 implements InvocationHandler{

    private Object target;

    public OrderServiceDynamicProxy2(Object target){
        this.target = target;
    }

    public Object bind(){
        Class cls = target.getClass();
        return Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object argObject = args[0];
        beforeMethod(argObject);
        Object object = method.invoke(target, args);
        afterMethod();
        return object;
    }

    public  void beforeMethod(Object obj){
        int userId  = 0;
        System.out.println("动态代理 before code");
        if(obj instanceof Order){
            Order order = (Order) obj;
            userId = order.getUserId();
        }
        int dbRouter = userId%2;
        System.out.println("动态代理分配到{db}");
    }

    private void afterMethod(){
        System.out.println("动态代理 after code");
    }
}
