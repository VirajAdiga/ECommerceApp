package com.virajadiga.ECommerceApp.controllers;

import com.virajadiga.ECommerceApp.dto.ProductDTO;
import com.virajadiga.ECommerceApp.models.Category;
import com.virajadiga.ECommerceApp.models.Product;
import com.virajadiga.ECommerceApp.services.CategoryService;
import com.virajadiga.ECommerceApp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    private final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @GetMapping("/admin")
    public String getAdminHome(){
        return "adminHome";
    }


    // CATEGORIES

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories", categoryService.getCategories());
        return "categories";
    }

    @GetMapping("/admin/categories/add")
    public String addCategory(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }

    @PostMapping("/admin/categories/add")
    public String addCategory(Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/update/{id}")
    public String updateCategory(@PathVariable Long id, Model model){
        Optional<Category> category = categoryService.getCategory(id);
        if(category.isPresent()){
            model.addAttribute("category", category);
            return "categoriesAdd";
        }
        else{
            // 404 page is not implemented
            return "404";
        }
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }


    // PRODUCTS

    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products", productService.getProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getCategories());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String addProduct(@ModelAttribute("productDTO") ProductDTO productDTO, @RequestParam("productImage")MultipartFile multipartFile, @RequestParam("imgName") String imgName) throws IOException {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setCategory(categoryService.getCategory(productDTO.getCategoryId()).get());
        product.setPrice(productDTO.getPrice());
        product.setWeight(productDTO.getWeight());
        product.setDescription(productDTO.getDescription());

        String imageUUID;
        if(!multipartFile.isEmpty()){
            imageUUID = multipartFile.getOriginalFilename();
            Path path = Paths.get(uploadDirectory, imageUUID);
            Files.write(path, multipartFile.getBytes());
        }
        else{
            imageUUID = imgName;
        }
        product.setImageName(imageUUID);
        productService.addProduct(product);

        return "redirect:/admin/products";
    }

    @GetMapping("/admin/products/update/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Optional<Product> product = productService.getProduct(id);
        if(product.isPresent()){

            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(product.get().getId());
            productDTO.setName(product.get().getName());
            productDTO.setCategoryId(product.get().getCategory().getId());
            productDTO.setPrice(product.get().getPrice());
            productDTO.setWeight(product.get().getWeight());
            productDTO.setDescription(product.get().getDescription());

            model.addAttribute("categories", categoryService.getCategories());
            model.addAttribute("productDTO", productDTO);
            return "productsAdd";
        }
        else{
            // 404 page is not implemented
            return "404";
        }
    }

    @GetMapping("/admin/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
