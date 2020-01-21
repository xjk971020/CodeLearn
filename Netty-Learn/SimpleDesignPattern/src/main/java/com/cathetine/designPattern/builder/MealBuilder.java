package com.cathetine.designPattern.builder;

import com.cathetine.designPattern.builder.impl.ChickenBurger;
import com.cathetine.designPattern.builder.impl.Coke;
import com.cathetine.designPattern.builder.impl.Pepsi;
import com.cathetine.designPattern.builder.impl.VegetableBurger;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:30
 */
public class MealBuilder {
    public Meal prepareVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new VegetableBurger());
        meal.addItem(new Pepsi());
        return meal;
    }

    public Meal prepareNonVegMeal() {
        Meal meal = new Meal();
        meal.addItem(new ChickenBurger());
        meal.addItem(new Coke());
        return meal;
    }
}
