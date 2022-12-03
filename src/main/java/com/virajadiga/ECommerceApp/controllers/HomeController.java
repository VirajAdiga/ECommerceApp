package com.virajadiga.ECommerceApp.controllers;

import com.virajadiga.ECommerceApp.services.CategoryService;
import com.virajadiga.ECommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @GetMapping("/shop")
    public String getShop(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("products", productService.getProducts());
        return "shop";
    }

    @GetMapping("/shop/viewproduct/{id}")
    public String getProduct(Model model, @PathVariable Long id){
        model.addAttribute("product", productService.getProduct(id).get());
        return "viewProduct";
    }

    @GetMapping("/shop/category/{id}")
    public String getShopByCategory(Model model, @PathVariable Long id){
        model.addAttribute("categories", categoryService.getCategories());
        model.addAttribute("products", productService.getAllProductsByCategoryId(id));
        return "shop";
    }
}
