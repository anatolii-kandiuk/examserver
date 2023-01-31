package com.examserver.examserver.service;

import java.util.Set;
import java.util.List;

import com.examserver.examserver.model.exam.Category;
import com.examserver.examserver.model.exam.Quiz;

public interface QuizService {
    
    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId);

    public void deleteQuiz(Long quizId);

    public List<Quiz> getQuizzesOfCategory(Category category);

    public List<Quiz> getActiveQuizzes();

    public List<Quiz> getActiveQuizzesOfCategory(Category c);
}
