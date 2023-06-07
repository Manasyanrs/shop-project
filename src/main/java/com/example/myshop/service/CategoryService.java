package com.example.myshop.service;

import com.example.myshop.entity.Category;

import java.util.List;

public interface CategoryService {
    void deleteCategoryById(int id);

    List<Category> getCategories();

    void addCategory(Category category);
}
