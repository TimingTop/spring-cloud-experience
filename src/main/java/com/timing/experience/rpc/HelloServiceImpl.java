package com.timing.experience.rpc;

public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        System.out.println("receive message : " + name);
        return "Hello, " + name;
    }
}
