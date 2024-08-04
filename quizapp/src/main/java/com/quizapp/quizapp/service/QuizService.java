package com.quizapp.quizapp.service;

import com.quizapp.quizapp.dao.QuestionDao;
import com.quizapp.quizapp.dao.QuizDao;
import com.quizapp.quizapp.entities.Question;
import com.quizapp.quizapp.entities.QuestionWrapper;
import com.quizapp.quizapp.entities.Quiz;
import com.quizapp.quizapp.entities.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;
    public ResponseEntity<String> createQuiz(String category, Integer numberOfQuestions, String name) {
        List<Question> questions = questionDao.findRandomQuestionsByCategory(category,numberOfQuestions);
        Quiz quiz = new Quiz();
        quiz.setName(name);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz has been created", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuiz(Integer id){
        Optional<Quiz> quiz =quizDao.findById(id);
        List<Question> questions = quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();
        for(Question q : questions){
            QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionName(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qw);
        }
        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Optional<Quiz> quiz =quizDao.findById(id);
        List<Question> questions=quiz.get().getQuestions();
//        System.out.println("Quiz "+quiz);
//        System.out.println("Questions "+questions);
        int i =0;
        int ans=0;
        for(Response res :responses){
//            System.out.println("Response :- "+res.getResponse());
//            System.out.println("Right Answer :- "+questions.get(i).getRightAnswer());
            if(res.getResponse().equals(questions.get(i).getRightAnswer())){
                ans++;
            }
            i++;
        }
        return new ResponseEntity<>(ans, HttpStatus.OK);

    }
}
