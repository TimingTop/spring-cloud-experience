package com.timing.experience.rpc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RPCServer {
    private static ExecutorService executor = Executors.newFixedThreadPool(10);
    private static final HashMap<String, Class> serviceRegistry = new HashMap<>();

    public void register(Class serviceInterface, Class impl) {
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    public void start(int port) throws IOException {
        final ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        while(true) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    Socket socket = null;
                    ObjectInputStream input = null;
                    ObjectOutputStream output = null;
                    try {
                        socket = server.accept();
                        input = new ObjectInputStream(socket.getInputStream());
                        String serviceName = input.readUTF();
                        String methodName = input.readUTF();
                        Class<?>[] parameterTypes = (Class<?> []) input.readObject();
                        Object[] arguments = (Object[]) input.readObject();

                        Class serviceClass = serviceRegistry.get(serviceName);
                        if (serviceClass == null) {
                            throw new ClassNotFoundException(serviceClass + " not found");
                        }
                        Method method = serviceClass.getMethod(methodName, parameterTypes);
                        Object result = method.invoke(serviceClass.newInstance(), arguments);

                        output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (socket != null)
                                socket.close();
                            if (input != null)
                                input.close();
                            if (output != null)
                                output.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
