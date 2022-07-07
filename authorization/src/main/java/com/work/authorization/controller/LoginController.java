package com.work.authorization.controller;

import com.work.authorization.model.User;
import com.work.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "index";
    }

    @GetMapping ("/login-success")
    public String loginSuccess(@ModelAttribute("user") User user) {
        if (!user.isEnabled()) {
            return "index";
        } else {
            return "confirmPage";
        }
    }

    @GetMapping("/logout-success")
    public String logoutSuccess() {
        return "index";
    }

    @GetMapping("/admin")
    public String settings(Model model) {
        List<User> usersList = (List<User>) userService.findAll();
        model.addAttribute("users", usersList);
        return "admin";
    }
}
