package com.sda.shop.controllers;

import com.sda.shop.entity.ProductCategoryEntity;
import com.sda.shop.repository.ProductCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    public MainController() {
        logger.info(getClass().getSimpleName()+"created");
    }
    @GetMapping("/")
    public ModelAndView getIndex(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories",productCategoryRepository.findAll());
        return modelAndView;
    }
}
