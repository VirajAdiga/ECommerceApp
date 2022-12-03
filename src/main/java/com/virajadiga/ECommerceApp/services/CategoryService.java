package com.virajadiga.ECommerceApp.services;

import com.virajadiga.ECommerceApp.models.Category;
import com.virajadiga.ECommerceApp.repositories.CategoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }

    public Optional<Category> getCategory(Long id){
        return categoryRepository.findById(id);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
