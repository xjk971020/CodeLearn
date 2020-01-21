package com.cathetine.designPattern.factory.factory;

import com.cathetine.designPattern.factory.People;
import com.cathetine.designPattern.factory.Woman;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:04
 */
public class WomanFactory implements PeopleFactory{
    public People getPeople() {
        return new Woman();
    }
}
