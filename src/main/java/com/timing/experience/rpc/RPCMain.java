package com.timing.experience.rpc;

public class RPCMain {
    public static void main(String[] args) throws Exception{
        RPCServer server = new RPCServer();
        server.register(HelloService.class, HelloServiceImpl.class);
        server.start(9000);
    }
}
