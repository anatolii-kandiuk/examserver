package com.examserver.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.examserver.model.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

    
}
