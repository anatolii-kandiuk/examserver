package com.examserver.examserver.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examserver.examserver.model.exam.Question;
import com.examserver.examserver.model.exam.Quiz;
import com.examserver.examserver.service.QuestionService;
import com.examserver.examserver.service.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {
    
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    @GetMapping("/")
    public ResponseEntity<?> questions() {
        return ResponseEntity.ok(this.questionService.getQuestions());
    }

    @GetMapping("/{questionId}")
    public ResponseEntity<Question> question(@PathVariable("questionId") Long questionId) {
        return ResponseEntity.ok(this.questionService.getQuestion(questionId));
    }

    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("quizId") Long quizId) {
        Quiz quiz = this.quizService.getQuiz(quizId);
        
        Set<Question> questions = quiz.getQuestions();

        List<Question> list = new ArrayList<Question>(questions);

        if(list.size() > Integer.parseInt(quiz.getNumberOfQuestion())) {
            list = list.subList(0, Integer.parseInt(quiz.getNumberOfQuestion()+1));
        }

        list.forEach((q) -> {
            q.setAnswer("");
        });

        Collections.shuffle(list);
        
        return ResponseEntity.ok(list);
    }

    @GetMapping("/quiz/all/{quizId}")
    public ResponseEntity<?> getQuestionsOfQuizAdmin(@PathVariable("quizId") Long quizId) {
        Quiz quiz = new Quiz();
        
        quiz.setQuizId(quizId);
        
        Set<Question> questionsOfQuiz = this.questionService.getQuestionsOfQuiz(quiz);
        
        return ResponseEntity.ok(questionsOfQuiz);
    }

    @DeleteMapping("/{questionId}")
    public void delete(@PathVariable("questionId") Long questionId) {
        this.questionService.deleteQuestion(questionId);
    }

    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
                  
        double marksGot = 0;
        int correctAnswers = 0;
        int attempted = 0;

        for(Question q: questions){
           Question question = this.questionService.get(q.getQuestionId());

           if(question.getAnswer().equals(q.getGivenAnswer())) {
            correctAnswers++;

            double markSingle = Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();

            marksGot += markSingle;

           } 
           if(q.getGivenAnswer() != null) {
            attempted++;
           }
        
        }
        Map<String, Object> map = Map.of(
            "marksGot", marksGot,
            "correctAnswers", correctAnswers,
            "attempted", attempted
        );

        return ResponseEntity.ok(map);
    }
}
