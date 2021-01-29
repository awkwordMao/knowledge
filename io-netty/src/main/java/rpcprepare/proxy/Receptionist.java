package rpcprepare.proxy;

public class Receptionist implements IGetServant{
    @Override
    public void choice(String desc) {
        System.out.println("已经为您选好了。。。。【" + desc + "】");
    }
}
