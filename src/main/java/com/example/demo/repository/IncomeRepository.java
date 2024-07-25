package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Income;
@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {

}
