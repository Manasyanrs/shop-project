package com.example.myshop.controller;

import com.example.myshop.entity.Category;
import com.example.myshop.entity.Product;
import com.example.myshop.service.CategoryService;
import com.example.myshop.service.ProductService;
import com.example.myshop.service.utils.LoadAndUploadImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final LoadAndUploadImgService loadAndUploadImgService;

    @Value("${my.shop.product.img.path}")
    private String productImg;

    @GetMapping("/productDetailPage/{id}")
    public String getProductDetailPage(@PathVariable("id") int id,
                                       ModelMap modelMap) {
        modelMap.addAttribute("product", productService.getProductById(id));

        return "productDetailPage";
    }

    @GetMapping("/admin/addProduct")
    public String getAddProduct(ModelMap modelMap) {
        List<Category> categories = categoryService.getCategories();
        modelMap.addAttribute("categories", categories);
        return "addProduct";
    }

    @PostMapping("/admin/addProduct")
    public String postAddProduct(@ModelAttribute Product product,
                                 @RequestParam("uploadImg") MultipartFile multipartFile) throws IOException {
        String productImg = loadAndUploadImgService.uploadImg(this.productImg, multipartFile);
        product.setImgPath(productImg);

        productService.saveProduct(product);
        return "redirect:/admin/indexAdmin";

    }

    @PostMapping("/admin/deleteProduct")
    public String deleteProduct(@RequestParam("id") int id) {
        productService.deleteById(id);
        return "redirect:/admin/indexAdmin";
    }
}
