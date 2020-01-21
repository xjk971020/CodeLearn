package com.cathetine.designPattern.factory.factory;

import com.cathetine.designPattern.factory.Man;
import com.cathetine.designPattern.factory.People;

/**
 * @Author:cathetine
 * @Date:19-10-10
 * @Time:上午10:03
 */
public class ManFactory implements PeopleFactory {
    public People getPeople() {
        return new Man();
    }
}
