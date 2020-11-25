package com.sda.shop.events;

import org.springframework.context.ApplicationEvent;

public class AddCategoryEvent extends ApplicationEvent {
    private String categoryDescription;


    public AddCategoryEvent(Object source,String categoryDescription) {
        super(source);
        this.categoryDescription = categoryDescription;

        }

    @Override
    public String toString() {
        return "AddCategoryEvent{" +
                "categoryDescription='" + categoryDescription + '\'' +
                '}';
    }
}

