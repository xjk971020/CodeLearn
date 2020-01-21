package com.cathetine.spirder.proxy;

import com.sun.istack.internal.NotNull;

/**
 * @Author:xjk
 * @Date 2019/12/3 11:11
 */
public class IPBean {
    private String ip;
    private String port;
    private String address;
    /**
     * 是否匿名
     */
    private String isHide;
    @NotNull
    private String type;
    /**
     * 存活时间
     */
    private String existTime;

    public String getExistTime() {
        return existTime;
    }

    public void setExistTime(String existTime) {
        this.existTime = existTime;
    }



    public IPBean() {
    }

    public IPBean(String ip, String port, String address, String isHide, String type, String existTime) {
        this.ip = ip;
        this.port = port;
        this.address = address;
        this.isHide = isHide;
        this.type = type;
        this.existTime = existTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsHide() {
        return isHide;
    }

    public void setIsHide(String isHide) {
        this.isHide = isHide;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "IPBean{" +
                "ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", address='" + address + '\'' +
                ", isHide='" + isHide + '\'' +
                ", type='" + type + '\'' +
                ", existTime='" + existTime + '\'' +
                '}';
    }
}
