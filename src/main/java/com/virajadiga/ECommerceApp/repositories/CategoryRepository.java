package com.virajadiga.ECommerceApp.repositories;

import com.virajadiga.ECommerceApp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
