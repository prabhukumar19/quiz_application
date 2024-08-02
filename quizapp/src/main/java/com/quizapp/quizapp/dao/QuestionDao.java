package com.quizapp.quizapp.dao;

import com.quizapp.quizapp.entities.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {
    public List<Question> findByCategory(String categoryName);
}
