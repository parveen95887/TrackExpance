package com.example.demo.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.SavingGoal;
import com.example.demo.repository.SavingGoalRepository;

@Service
public class SavingGoalService {

    @Autowired
    private SavingGoalRepository savingGoalRepository;

    public SavingGoal saveSavingGoal(SavingGoal savingGoal) {
        return savingGoalRepository.save(savingGoal);
    }

    public List<SavingGoal> getAllSavingGoals() {
        return savingGoalRepository.findAll();
    }

    public Optional<SavingGoal> getSavingGoalById(Long id) {
        return savingGoalRepository.findById(id);
    }

    public void deleteSavingGoal(Long id) {
        savingGoalRepository.deleteById(id);
    }
}
