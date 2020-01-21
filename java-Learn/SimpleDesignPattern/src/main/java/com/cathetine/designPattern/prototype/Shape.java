package com.cathetine.designPattern.prototype;

/**
 * @Author:cathetine
 * @Date:19-9-23
 * @Time:下午9:55
 * 原型抽象类
 */
public abstract class Shape implements Cloneable{
    private String shapeId;
    protected String type;

    abstract void draw();

    public String getShapeId() {
        return shapeId;
    }

    public void setShapeId(String shapeId) {
        this.shapeId = shapeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object clone() {
        Object object = null;
        try {
            object = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return object;
    }
}
