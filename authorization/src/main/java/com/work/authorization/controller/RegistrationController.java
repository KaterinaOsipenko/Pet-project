package com.work.authorization.controller;

import com.work.authorization.model.OnRegistrationCompleteEvent;
import com.work.authorization.model.User;
import com.work.authorization.model.VerificationToken;
import com.work.authorization.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Calendar;
import java.util.Locale;

@Controller
@RequestMapping("/auth")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/registration")
    public String registrationPage(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "registration";
    }

    @PostMapping("/registrNewUser")
    public String newUser(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult bindingResult, HttpServletRequest request) {
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
        User user = userService.registerUser(userDTO);
        String appUrl = request.getContextPath();
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(appUrl, user, request.getLocale()));
        System.out.println("Verify!!!!!!!!!!!!!!!!!!!");
        return "index";
    }

    @GetMapping("/registrationConfirm")
    public String confirmRegistration(Model model, @RequestParam("token") String token, WebRequest webRequest) {
        Locale locale = webRequest.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = messageSource.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "index";
        }
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime()) <= 0) {
            String messageValue = messageSource.getMessage("auth.message.expired", null, locale);
            model.addAttribute("message", messageValue);
            return "index";
        }
        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "index";
    }


}
