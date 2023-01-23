package com.examserver.examserver.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examserver.examserver.model.exam.Question;
import com.examserver.examserver.model.exam.Quiz;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    Set<Question> findByQuiz(Quiz quiz);
    
}
