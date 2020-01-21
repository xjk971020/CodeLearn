package com.cathetine.designPattern.builder;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:33
 */
public class Demo {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        vegMeal.showItems();
        System.out.println("veg meal price: " + vegMeal.getPrice());

        System.out.println();

        Meal nonVegMeal = mealBuilder.prepareNonVegMeal();
        nonVegMeal.showItems();
        System.out.println("non veg meal price: " + nonVegMeal.getPrice());
    }
}
