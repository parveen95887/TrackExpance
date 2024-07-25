package com.example.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Entity.SavingGoal;
import com.example.demo.Services.SavingGoalService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/savinggoals")
public class SavingGoalController {

    @Autowired
    private SavingGoalService savingGoalService;

    // Show create saving goal form
    @GetMapping("/create")
    public String showCreateSavingGoalForm(Model model) {
        model.addAttribute("savingGoal", new SavingGoal());
        return "addSavinggaols";
    }

    // Save new saving goal
    @PostMapping("/create")
    public String createSavingGoal(@Valid @ModelAttribute SavingGoal savingGoal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addSavingGaols";
        }
        savingGoalService.saveSavingGoal(savingGoal);
        return "redirect:/savinggoals/getall";
    }

    // List all saving goals
    @GetMapping("/getall")
    public String getAllSavingGoals(Model model) {
        List<SavingGoal> savingGoals = savingGoalService.getAllSavingGoals();
        model.addAttribute("savingGoals", savingGoals);
        return "/showSavinggGoals";
    }

    // Show edit saving goal form
    @GetMapping("/edit/{id}")
    public String showEditSavingGoalForm(@PathVariable Long id, Model model) {
        SavingGoal savingGoal = savingGoalService.getSavingGoalById(id).orElseThrow(() -> new IllegalArgumentException("Invalid saving goal Id:" + id));
        model.addAttribute("savingGoal", savingGoal);
        return "editSavingGoal";
    }

    // Update saving goal
    @PostMapping("/update/{id}")
    public String updateSavingGoal(@PathVariable Long id, @Valid @ModelAttribute SavingGoal savingGoal, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editSavingGoal";
        }
        savingGoal.setId(id);
        savingGoalService.saveSavingGoal(savingGoal);
        return "redirect:/savinggoals/getall";
    }

    // Delete saving goal
    @GetMapping("/delete/{id}")
    public String deleteSavingGoal(@PathVariable Long id) {
        savingGoalService.deleteSavingGoal(id);
        return "redirect:/savinggoals/getall";
    }
}