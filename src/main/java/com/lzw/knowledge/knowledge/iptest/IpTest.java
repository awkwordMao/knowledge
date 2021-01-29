package com.lzw.knowledge.knowledge.iptest;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class IpTest {

    public static void main(String[] args) {
        getAddress();
    }

    public static void getAddress() {
        InetAddress inetAddress = null;
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                //过滤虚拟网卡、loopback设备
                if (ni.isVirtual() || !ni.isUp() || ni.isLoopback()) {
                    continue;
                }
                if(!ni.getDisplayName().contains("Intel") && !ni.getDisplayName().contains("Realtek")){
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress address = addresses.nextElement();
                    if (address instanceof Inet4Address) {

                        String hostName = address.getHostName();
                        String hostAddress = address.getHostAddress();

                        System.out.println("HostName：" + hostName + "_________HostAddress: " + hostAddress);
                    }

                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
}
