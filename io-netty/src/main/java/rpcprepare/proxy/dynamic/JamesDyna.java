package rpcprepare.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 代理类
 */
public class JamesDyna implements InvocationHandler{

    Object receptionist;

    public JamesDyna(Object receptionist) {
        this.receptionist = receptionist;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("James find recp....");
        Object result = method.invoke(receptionist, args);
        System.out.println("half price....");
        return result;
    }
}
