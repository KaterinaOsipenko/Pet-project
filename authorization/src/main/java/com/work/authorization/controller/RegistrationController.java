package com.work.authorization.controller;

import com.work.authorization.model.User;
import com.work.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/registrNewUser")
    public String newUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult) {
        User existing = userService.findByEmail(userDTO.getEmail());
        User checkUsername = userService.findByUsername(userDTO.getUsername());
        if (existing != null) {
            bindingResult.rejectValue("email", "404", "There is already an account registered with that email");
        }
        if (checkUsername != null) {
            bindingResult.rejectValue("username", "404", "There is already an account registered with that username! Please, chose another one.");

        }
        if (!userDTO.getPassword().equals(userDTO.getMatchingPassword())) {
            bindingResult.rejectValue("password", "404", "Error confirmation isn`t true!");
        }
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        userService.save(userDTO);
        return "index";
    }


}
