package com.examserver.examserver.model.exam;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "question")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long questionId;

    @Column(length = 5000)
    private String content;

    private String image;

    private String optionOne;

    private String optionTwo;

    private String optionThree;

    private String optionFour;

    private String answer;

    @ManyToOne(fetch = FetchType.EAGER)
    private Quiz quiz;
    

}
