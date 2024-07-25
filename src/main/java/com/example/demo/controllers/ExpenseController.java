package com.example.demo.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.Entity.Expense;
import com.example.demo.Services.ExpenseService;

import jakarta.validation.Valid;
@Controller
@RequestMapping("/expenses")
public class ExpenseController {

	@Autowired
    private ExpenseService expenseService;

	
	
    // Show create expense form
    @GetMapping("/expanceAdd")
    public String showCreateExpenseForm(Model model) {
        model.addAttribute("expense", new Expense());
        return "addexpance";
    }
    
  
    @PostMapping("/create")
    public String createExpense(@Valid @ModelAttribute Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addexpance";
        }
        expenseService.saveExpense(expense);
        return "redirect:/expenses/getall";
    }
    @GetMapping("/getall")
    public String getAllExpenses(Model model) {
        List<Expense> expenses = expenseService.getAllExpenses();
        model.addAttribute("expenses", expenses);
        return "index";
    }
    @GetMapping("/edit/{id}")
    public String showEditExpenseForm(@PathVariable Long id, Model model) {
        Expense expense = expenseService.getExpenseById(id).orElseThrow(() -> new IllegalArgumentException("Invalid expense Id:" + id));
        model.addAttribute("expense", expense);
        return "editExpance";
    }
    

    // Update expense
    @PostMapping("/update/{id}")
    public String updateExpense(@PathVariable Long id, @Valid @ModelAttribute Expense expense, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "editexpense";
        }
        expense.setExpenseId(id);
        expenseService.saveExpense(expense);
        return "redirect:/expenses/getall";
    }
    
    // Delete expense
    @GetMapping("/delete/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "redirect:/expenses/getall";
    }

   
    @GetMapping("/getreport")
    public String getTotalExpensesBetweenDates(@RequestParam(required = false) String startDate, 
                                               @RequestParam(required = false) String endDate, 
                                               Model model) {
        if (startDate != null && endDate != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);
            Double totalExpenses = expenseService.getTotalExpensesBetweenDates(start, end);
            
            model.addAttribute("totalExpenses", totalExpenses);
            model.addAttribute("startDate", startDate);
            model.addAttribute("endDate", endDate);
        }
        return "report";
    }
    
    
}
