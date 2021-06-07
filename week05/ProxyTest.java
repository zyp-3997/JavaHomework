package Advance.week05;

import java.lang.reflect.InvocationHandler;

/**
 * @author 朱雨鹏
 * @create 2021-06-07 10:27
 */
public class ProxyTest {
    public static void main(String[] args) {
        Computer computer=new ComputerImpl();
        InvocationHandler invocationHandler=new ComputerInovcationHandler(computer);
        ProxyFactory proxyFactory=new ProxyFactory(ComputerImpl.class.getName(),invocationHandler);
        Computer computerProxy = (Computer) proxyFactory.createProxyObject();
        computerProxy.method();
    }
}
