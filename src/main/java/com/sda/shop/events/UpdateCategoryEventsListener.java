package com.sda.shop.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class UpdateCategoryEventsListener implements ApplicationListener<EditCategoryEvent> {

    @Override
    public void onApplicationEvent(EditCategoryEvent editCategoryEvent){
        System.out.println(getClass().getSimpleName()+editCategoryEvent);
    }
}
