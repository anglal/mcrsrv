package com.zlimbu.mcrsr.controller;

import com.zlimbu.mcrsr.model.User;
import com.zlimbu.mcrsr.repos.UserRepo;
import com.zlimbu.mcrsr.security.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

//For logging in
@Controller
public class UserController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SecurityService securityService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/showReg")
    public String showRegistrationPage(){
        return "registerUser";
    }
    @PostMapping("/registerUser")
    public String register(User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepo.save(user);
        return "login";
    }

    @GetMapping("/")
    public String showLoginPage(){
        return "login";
    }

    @PostMapping("/login") //@RequestParam can be used if different names
    public String login(String email, String password){
        boolean loginResponse = this.securityService.login(email, password);
        if(loginResponse){
            return "index";
        }
            return "login";
    }

}
