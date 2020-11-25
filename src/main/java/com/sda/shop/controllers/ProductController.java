package com.sda.shop.controllers;

import com.sda.shop.entity.ProductEntity;
import com.sda.shop.repository.ProductCategoryRepository;
import com.sda.shop.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;


@Controller
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductController() {
        logger.info(getClass().getSimpleName() + "created");
    }

    @GetMapping("/view-products/{id}")
    public ModelAndView getProducts(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("products");
        modelAndView.addObject("category", productCategoryRepository.findById(id).get());
        return modelAndView;
    }

    @GetMapping("/products/add/{categoryId}")
    public ModelAndView addProducts(@PathVariable Integer categoryId) {
        ModelAndView modelAndView = new ModelAndView("product-form");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductCategoryId(categoryId);
        modelAndView.addObject("product", productEntity);
        return modelAndView;
    }

    @PostMapping("/products/save")
    public ModelAndView saveProduct(@ModelAttribute ProductEntity product) {
        productRepository.save(product);
        ModelAndView modelAndView = new ModelAndView("redirect:/view-products/" + product.getProductCategoryId());
        return modelAndView;
    }

    @GetMapping("/products/edit/{productId}")
    public ModelAndView updateProduct(@PathVariable Integer productId) {
        ModelAndView modelAndView = new ModelAndView("product-form");
        modelAndView.addObject("product", productRepository.findById(productId).get());
        return modelAndView;
    }

    @GetMapping("/products/delete/{productId}")
    public ModelAndView deleteProduct(@PathVariable Integer productId) {
        ProductEntity productEntity = productRepository.findById(productId).get();
        ModelAndView modelAndView = new ModelAndView("redirect:/view-products/" + productEntity.getProductCategoryId());
        productRepository.delete(productEntity);
        return modelAndView;
    }
}