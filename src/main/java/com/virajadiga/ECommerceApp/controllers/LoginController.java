package com.virajadiga.ECommerceApp.controllers;

import com.virajadiga.ECommerceApp.models.Role;
import com.virajadiga.ECommerceApp.models.User;
import com.virajadiga.ECommerceApp.repositories.RoleRepository;
import com.virajadiga.ECommerceApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, HttpServletRequest request) throws ServletException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2L).get());

        user.setRoles(roles);
        userRepository.save(user);

        request.login(user.getEmail(), user.getPassword());
        return "redirect:/";
    }
}
