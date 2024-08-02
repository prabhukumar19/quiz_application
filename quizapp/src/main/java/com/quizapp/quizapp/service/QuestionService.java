package com.quizapp.quizapp.service;

import com.quizapp.quizapp.dao.QuestionDao;
import com.quizapp.quizapp.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions(){
       try{
           return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
       return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<List<Question>>getQuestionByCategory(String categoryName) {
       try {
           return new ResponseEntity<>(questionDao.findByCategory(categoryName), HttpStatus.OK);
       }catch (Exception e){
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
//        Question q = new Question();
//        q.setTitle(question.getTitle());
//        q.setCategory(question.getCategory());
//        q.setDifficultyLevel(question.getDifficultyLevel());
//        q.setOption1(question.getOption1());
//        q.setOption2(question.getOption2());
//        q.setOption3(question.getOption3());
//        q.setRightAnswer(question.getRightAnswer());

       try {
           questionDao.save(question);
           return new ResponseEntity<>("Question Added Successfully!", HttpStatus.CREATED);
       }catch (Exception e){
           e.printStackTrace();
           return new ResponseEntity<>("Something went wrong!", HttpStatus.BAD_REQUEST);
       }

    }
}
