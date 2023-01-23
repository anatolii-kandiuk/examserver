package com.examserver.examserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.examserver.model.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
    
}
