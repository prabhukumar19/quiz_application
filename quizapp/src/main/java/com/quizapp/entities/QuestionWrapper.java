package com.quizapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class QuestionWrapper {
    private Integer id;
    private String questionName;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
}
