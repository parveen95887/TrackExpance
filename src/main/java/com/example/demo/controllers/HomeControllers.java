package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.Entity.user;

import ch.qos.logback.core.model.Model;

@Controller
public class HomeControllers {

	
	    @GetMapping("/")
	    public String login(Model model) {
	        return "login";
	    }
	   
	    
}

