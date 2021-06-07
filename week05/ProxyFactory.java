package Advance.week05;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * @author 朱雨鹏
 * @create 2021-06-07 10:24
 */
public class ProxyFactory {
    Class c;
    InvocationHandler invocationHandler;

    public ProxyFactory(String name, InvocationHandler invocationHandler) {
        try {
            c=Class.forName(name);
            this.invocationHandler = invocationHandler;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Object createProxyObject(){
        return Proxy.newProxyInstance(c.getClassLoader(),c.getInterfaces(),invocationHandler);
    }
}
