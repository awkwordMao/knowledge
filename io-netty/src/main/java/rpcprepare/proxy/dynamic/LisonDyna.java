package rpcprepare.proxy.dynamic;

import rpcprepare.proxy.IGetServant;
import rpcprepare.proxy.Receptionist;

import java.lang.reflect.Proxy;

public class LisonDyna {
    public static void main(String[] args) {

        IGetServant james = (IGetServant)Proxy.newProxyInstance(IGetServant.class.getClassLoader(),
                new Class[]{IGetServant.class}, new JamesDyna(new Receptionist()));
        james.choice("御姐范风格");
    }
}