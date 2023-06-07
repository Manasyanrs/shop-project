package com.example.myshop.controller;

import com.example.myshop.entity.Category;
import com.example.myshop.security.CurrentUser;
import com.example.myshop.service.CategoryService;
import com.example.myshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final ProductService productService;


    @GetMapping("/admin/addCategory")
    public String getAddCategory(ModelMap modelMap) {
        modelMap.addAttribute("categories", categoryService.getCategories());
        return "addCategory";
    }

    @PostMapping("/admin/addCategory")
    public String postAddCategory(@ModelAttribute Category category) {
        categoryService.addCategory(category);
        return "redirect:/admin/indexAdmin";
    }


    @GetMapping("/admin/deleteCategory")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryService.deleteCategoryById(id);
        return "redirect:/admin/addCategory";
    }

    @GetMapping("/chosenCategory")
    public String chosenCategory(ModelMap modelMap, @RequestParam("id") int chosenCategory,
                                 @AuthenticationPrincipal CurrentUser currentUser) {

        modelMap.addAttribute("categories", categoryService.getCategories());
        modelMap.addAttribute("currentUser", currentUser);
        modelMap.addAttribute("products", productService.getProductByCategories(chosenCategory));
        return "index";
    }
}
