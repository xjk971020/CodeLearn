package com.cathetine.designPattern.builder;

import com.cathetine.designPattern.builder.food.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:cathetine
 * @Date:19-12-9
 * @Time:下午9:16
 */
public class Meal {
    private List<Item> items;

    public Meal() {
        this.items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public float getPrice() {
        float total = 0.0f;
        for (Item item : items) {
            total += item.price();
        }
        return total;
    }

    public void showItems() {
        for (Item item : items) {
            System.out.print("name:" + item.name() + ", ");
            System.out.print("packing: " + item.packing().pack() + ", ");
            System.out.println("price: " + item.price());
        }
    }
}
