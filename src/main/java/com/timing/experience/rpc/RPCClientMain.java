package com.timing.experience.rpc;

import java.net.InetSocketAddress;

public class RPCClientMain {
    public static void main(String[] args) {
        HelloService service = RPCClient.get(HelloService.class, new InetSocketAddress( 9000));
        System.out.println(service.hello("Timing"));
    }
}
