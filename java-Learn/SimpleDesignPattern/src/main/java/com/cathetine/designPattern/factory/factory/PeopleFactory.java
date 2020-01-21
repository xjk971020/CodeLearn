package com.cathetine.designPattern.factory.factory;

import com.cathetine.designPattern.factory.People;

/**
 * 生成人类这种产品的抽象工厂
 */
public interface PeopleFactory {
    People getPeople();
}
