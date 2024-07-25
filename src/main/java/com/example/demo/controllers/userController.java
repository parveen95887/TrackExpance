package com.example.demo.controllers;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.user;
import com.example.demo.Services.userService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users")
public class userController {
	
    @Autowired
    private userService userService;
    
    @GetMapping("/index")
    public String getindex(Model model) {
        
        model.addAttribute("user", new user());
        return "redirect:/expenses/getall";
    }
    
    @GetMapping("/profile")
    public String showUserProfile(HttpSession session, Model model) {
        user currentUser = (user) session.getAttribute("currentUser");
        if (currentUser != null) {
            model.addAttribute("user", currentUser);
            return "profile";
        } else {
            return "login";
        }
    }
  
    @PostMapping("/updateProfile/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid @ModelAttribute("user") user updatedUser, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("user", updatedUser);
            return "editProfile";
        }
        userService.updateUser(id, updatedUser);
        return "redirect:/users/profile";
    }
    
    @GetMapping("/updateProfile/{id}")
    public String showEditUserForm(@PathVariable("id") long id, Model model) {
        Optional<user> user = userService.getUserById(id);
        if (user.isEmpty()) {
            return "redirect:/users/index";
        }
        model.addAttribute("user", user.get());
        return "editProfile";
    }
  
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        
        model.addAttribute("user", new user());
        return "register";
    }
    
    
    //saveuser
    @PostMapping("/registerUser")
    public 	String registerUser(@Valid @ModelAttribute("user") user user, HttpSession session, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        session.setAttribute("currentUser", user);
        userService.saveUser(user);
         
        model.addAttribute("user", user);
        return "login";
    }
    
    @PostMapping("/login")
    public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        if (userService.validateUser(email, password)) {
            user currentUser = userService.getByEmail(email);
            session.setAttribute("currentUser", currentUser);
            return "redirect:/expenses/getall";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }

   // logout
    @GetMapping("/logout")
    public String logoutUser(HttpSession session) {
        session.invalidate();
        return "login";
    }
    
    
}
