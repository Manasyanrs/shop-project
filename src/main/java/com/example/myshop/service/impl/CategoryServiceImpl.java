package com.example.myshop.service.impl;

import com.example.myshop.entity.Category;
import com.example.myshop.repository.CategoryRepository;
import com.example.myshop.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public void deleteCategoryById(int id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        categoryRepository.save(category);
    }
}
