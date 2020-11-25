package com.sda.shop.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class AddCategoryEventsListener1 implements ApplicationListener<AddCategoryEvent> {


    @Override
    public void onApplicationEvent(AddCategoryEvent addCategoryEvent){
        System.out.println(getClass().getSimpleName()+addCategoryEvent);
    }

}
