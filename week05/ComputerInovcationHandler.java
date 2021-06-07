package Advance.week05;

import javax.xml.ws.soap.MTOM;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author 朱雨鹏
 * @create 2021-06-07 10:20
 */
public class ComputerInovcationHandler implements InvocationHandler {
    public Object targer;

    public ComputerInovcationHandler(Object targer) {
        this.targer = targer;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoked"+method.getName());
        Object res = method.invoke(targer, args);
        System.out.println("after invoked"+method.getName());
        return res;
    }
}
