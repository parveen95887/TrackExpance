package com.example.demo.Services;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Expense;
import com.example.demo.repository.ExpenseRepository;
@Service
public class ExpenseService {
    
	@Autowired
	private ExpenseRepository expenseRepository;
	
	public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
    
    public Double getTotalExpensesBetweenDates(LocalDate startDate, LocalDate endDate) {
        return expenseRepository.findTotalExpensesBetweenDates(startDate, endDate);    
    }

    
    
}
