package com.sda.shop.controllers;

import com.sda.shop.entity.ProductCategoryEntity;
import com.sda.shop.events.AddCategoryEvent;
import com.sda.shop.repository.ProductCategoryRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class CategoriesController {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    private static final Logger logger = LoggerFactory.getLogger(CategoriesController.class);

    public CategoriesController() {
        logger.info(getClass().getSimpleName() + " created");
    }

    @GetMapping("/categories")
    public ModelAndView getCategories() {
        ModelAndView modelAndView = new ModelAndView("categories");
        modelAndView.addObject("categoryList", productCategoryRepository.findAll());
        return modelAndView;
    }

    @GetMapping("/categories/add")
    public ModelAndView addCategory() {
        ModelAndView modelAndView = new ModelAndView("category-form");
        modelAndView.addObject("category", new ProductCategoryEntity());
        return modelAndView;

    }

    @PostMapping("/categories/save")
    public ModelAndView saveCategory(@Valid @ModelAttribute("category") ProductCategoryEntity productCategoryEntity, BindingResult bindingResult) {


        ModelAndView modelAndView = new ModelAndView("redirect:/categories");

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("category-form");
            modelAndView.addObject("category", productCategoryEntity);
            return modelAndView;
        }
        productCategoryRepository.save(productCategoryEntity);
        applicationEventPublisher.publishEvent(new AddCategoryEvent(this,productCategoryEntity.getDescription()));
        return modelAndView;
    }

    @GetMapping("/categories/edit/{id}")
    public ModelAndView editCategory(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("category-form");
        modelAndView.addObject("category", productCategoryRepository.findById(id).get());
        return modelAndView;

    }
    @GetMapping("/categories/delete/{id}")
    public ModelAndView deleteCategory(@PathVariable(name="id") Integer categoryId){
        ModelAndView modelAndView = new ModelAndView("redirect:/categories");
        productCategoryRepository.deleteById(categoryId);
        return modelAndView;
    }
}