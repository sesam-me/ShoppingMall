package com.example.shopping.test.entity;

import java.util.ArrayList;
import java.util.List;

public class CategoryQuestions {
    private List<QuestionAnswer> questions;

    public CategoryQuestions() {
        this.questions = new ArrayList<>();
    }

    public void addQuestion(String question) {
        this.questions.add(new QuestionAnswer(question));
    }

    public void incrementChoiceCount(int questionIndex, int choice) {
        if (choice == 1) {
            questions.get(questionIndex).incrementChoice1Count(1);
        } else if (choice == 2) {
            questions.get(questionIndex).incrementChoice2Count(1);
        }
    }

    public List<QuestionAnswer> getQuestions() {
        return questions;
    }
}