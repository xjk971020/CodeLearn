package com.cathetine.designPattern.proxy.dynamic;

/**
 * @Author:cathetine
 * @Date:19-9-9
 * @Time:下午9:08
 */
public class SoftwareEngineer implements Person {
    public SoftwareEngineer() {
    }

    public SoftwareEngineer(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void goWorking(String name, String dst) {
        System.out.println("name =" + name + " ， 去 " + dst + " 工作");
    }
}
