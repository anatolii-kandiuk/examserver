package com.examserver.examserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.examserver.model.exam.Category;
import com.examserver.examserver.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findByCategory(Category category);
}
