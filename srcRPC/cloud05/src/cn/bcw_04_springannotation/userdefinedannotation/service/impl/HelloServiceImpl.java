package cn.bcw_04_springannotation.userdefinedannotation.service.impl;

import cn.bcw_04_springannotation.userdefinedannotation.annotation.RpcService;
import cn.bcw_04_springannotation.userdefinedannotation.service.HelloService;


@RpcService("HelloService")
public class HelloServiceImpl implements HelloService {
    public String hello(String name) {
        return "Hello! " + name;
    }
}
