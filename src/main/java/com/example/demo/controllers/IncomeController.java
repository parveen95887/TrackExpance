
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

import com.example.demo.Entity.Income;
import com.example.demo.Services.IncomeService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/incomes")
public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    // Show create income form
    @GetMapping("/create")
    public String showCreateIncomeForm(Model model) {
        model.addAttribute("income", new Income());
        return "addincome";
    }

    // Save new income
    @PostMapping("/create")
    public String createIncome(@Valid @ModelAttribute Income income, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addincome";
        }
        incomeService.saveIncome(income);
        return "redirect:/incomes/getincome";
    }

    // List all incomes
    @GetMapping("/getincome")
    public String getAllIncomes(Model model) {
        List<Income> incomes = incomeService.getAllIncomes();
        model.addAttribute("incomes", incomes);
        return "incomeList";
    }

    // Show edit income form
    @GetMapping("/edit/{id}")
    public String showEditIncomeForm(@PathVariable Long id, Model model) {
        Income income = incomeService.getIncomeById(id).orElseThrow(() -> new IllegalArgumentException("Invalid income Id:" + id));
        model.addAttribute("income", income);
        return "editincome";
    }

    // Update income
    @PostMapping("/update/{id}")
    public String updateIncome(@PathVariable Long id, @Valid @ModelAttribute Income income, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editincome";
        }
        income.setId(id);
        incomeService.saveIncome(income);
        return "redirect:/incomes/getincome";
    }

    // Delete income
    @GetMapping("/delete/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return "redirect:/incomes/getincome";
    }
}

