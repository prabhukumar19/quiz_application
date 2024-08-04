package com.quizapp.controller;

import com.quizapp.entities.Question;
import com.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<List<Question>> getQuestions(){
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{categoryName}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String categoryName){
        return questionService.getQuestionByCategory(categoryName);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        return questionService.addQuestion(question);
    }

}
