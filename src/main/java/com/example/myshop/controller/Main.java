package com.example.myshop.controller;

import com.example.myshop.entity.Cart;
import com.example.myshop.entity.Product;
import com.example.myshop.security.CurrentUser;
import com.example.myshop.service.CartService;
import com.example.myshop.service.CategoryService;
import com.example.myshop.service.ProductService;
import com.example.myshop.service.utils.LoadAndUploadImgService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class Main {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final CartService cartService;
    private final LoadAndUploadImgService loadAndUploadImgService;

    @Value("${my.shop.product.img.path}")
    private String productImgPath;


    @GetMapping("/")
    public String getHomePage(ModelMap modelMap,
                              @RequestParam("page") Optional<Integer> page,
                              @RequestParam("size") Optional<Integer> size,
                              @AuthenticationPrincipal CurrentUser currentUser) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(1);

        Sort sort = Sort.by(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(currentPage - 1, pageSize, sort);


        if (currentUser != null) {
            Optional<Cart> cartByUserId = cartService.getCartByUserId(currentUser.getUser().getId());
            cartByUserId.ifPresent(cart -> modelMap.addAttribute("orderCart", cart));
        }

        Page<Product> products = productService.getProducts(pageable);
        if (products.getTotalPages() > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, products.getTotalPages())
                    .boxed()
                    .collect(Collectors.toList());
            modelMap.addAttribute("pageNumbers", pageNumbers);
        }
        modelMap.addAttribute("products", products);
        modelMap.addAttribute("categories", categoryService.getCategories());
        modelMap.addAttribute("currentUser", currentUser);

        return "index";
    }


    @GetMapping("/admin/indexAdmin")
    public String getAdminPage(ModelMap modelMap, @AuthenticationPrincipal CurrentUser currentUser) {
        modelMap.addAttribute("products", productService.getProductList());
        modelMap.addAttribute("currentUser", currentUser);

        return "indexAdmin";
    }

    @GetMapping(value = "/productImg",
            produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("image") String imgName) throws IOException {

        return loadAndUploadImgService.getBytes(productImgPath, imgName);
    }

}
