package cn.bcw_05_proxy.proxyclass;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by carrots on 2019/1/30.
 */
public class ProxyBoss2 {

    public static <T> T getProxy(final int discountCoupon ,final Class<?> interfaceClass, final Class<?> implementsClass){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Integer returnValue = (Integer) method.invoke(implementsClass.newInstance(),args);
                return returnValue - discountCoupon;
            }
        });
    }
}
