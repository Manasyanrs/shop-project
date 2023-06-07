package com.example.myshop.controller;

import com.example.myshop.entity.User;
import com.example.myshop.entity.UserRole;
import com.example.myshop.security.CurrentUser;
import com.example.myshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/customRegister")
    public String getRegisterUserPage() {
        return "customRegister";
    }

    @PostMapping("/customRegister")
    public String postUserRegisterPage(@ModelAttribute User user,
                                       RedirectAttributes redirectAttributes) {
        Optional<User> userByEmail = userService.getUserByEmail(user.getEmail());

        if (userByEmail.isEmpty()) {
            user.setUserRole(UserRole.USER);

            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("msg", "User name or password is exist!");
        return "redirect:/customRegister";
    }

    @GetMapping("/customLoginPage")
    public String getLoginPage() {
        return "customLogin";
    }

    @GetMapping("/customSuccessLogin")
    public String postLoginUser(@AuthenticationPrincipal CurrentUser currentUser, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("msg", "User name or password incorrect.");
        if (currentUser != null) {
            User user = currentUser.getUser();
            if (user.getUserRole() == UserRole.ADMIN) {
                return "redirect:/admin/indexAdmin";
            } else if (user.getUserRole() == UserRole.USER) {
                return "redirect:/";
            }
        }
        return "redirect:/customLoginPage";
    }

}
