package com.example.shopping.test.controller;

import com.example.shopping.test.entity.CategoryQuestions;
import com.example.shopping.test.entity.QuestionAnswer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/questions")
public class QuestionController {
    private final String[][] questions = {
            {
                    "****친구들과 노는 중 시간은 벌써 저녁 10시다! m1****\n1. 오늘 밤을 불태워야 함 아무도 집에 못 감\n2. 이제 슬슬 집에 가고 싶어서 눈치 보는 중",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? m2****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? m3****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
            },
            {
                    "****친구들과 노는 중 시간은 벌써 저녁 11시다! b1****\n1. 오늘 밤을 불태워야 함 아무도 집에 못 감\n2. 이제 슬슬 집에 가고 싶어서 눈치 보는 중",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? b2****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? b3****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
            },
            {
                    "****친구들과 노는 중 시간은 벌써 저녁 12시다! t1****\n1. 오늘 밤을 불태워야 함 아무도 집에 못 감\n2. 이제 슬슬 집에 가고 싶어서 눈치 보는 중",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? t2****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? t3****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
            },
            {
                    "****친구들과 노는 중 시간은 벌써 저녁 14시다! i1****\n1. 오늘 밤을 불태워야 함 아무도 집에 못 감\n2. 이제 슬슬 집에 가고 싶어서 눈치 보는 중",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? i2****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
                    "****친구들과의 모임이 끝난 후 집에 갈 때 나는? i3****\n1. 혼자 조용히 가고 싶음\n2. 함께 떠들면서 가고 싶음",
            }
    };
    private CategoryQuestions[] categories;

    public QuestionController() {
        // Initialize your categories and questions here
        // For example:
        categories = new CategoryQuestions[4];
        for (int i = 0; i < categories.length; i++) {
            categories[i] = new CategoryQuestions();
            for (String question : questions[i]) {
                categories[i].addQuestion(question);
            }
        }
    }
    @GetMapping("/{category}/{questionIndex}")
    public String getQuestion(
            @PathVariable int category,
            @PathVariable int questionIndex) {
        return categories[category].getQuestions().get(questionIndex).getQuestion();
    }

    @PostMapping("/{category}/{questionIndex}/{choice}")
    public void voteForChoice(
            @PathVariable int category,
            @PathVariable int questionIndex,
            @PathVariable int choice) {
        categories[category].incrementChoiceCount(questionIndex, choice);
    }

    @GetMapping("/results")
    public CategoryQuestions[] getPersonalityType() {
        StringBuilder personalityType = new StringBuilder();


        for (CategoryQuestions category : categories) {
            int choice1Count = category.getQuestions().stream().mapToInt(QuestionAnswer::getChoice1Count).sum();
            int choice2Count = category.getQuestions().stream().mapToInt(QuestionAnswer::getChoice2Count).sum();

            if (choice1Count > choice2Count) {
                personalityType.append(getPersonalityCode(category));
            } else {
                personalityType.append(getOppositePersonalityCode(category));
            }
        }

        System.out.println(personalityType.toString());

        return categories;
    }

    private String getPersonalityCode(CategoryQuestions category) {
        if (category.equals(categories[0])) {
            return "I";
        } else if (category.equals(categories[1])) {
            return "S";
        } else if (category.equals(categories[2])) {
            return "T";
        } else if (category.equals(categories[3])) {
            return "J";
        }
        return "";
    }

    private String getOppositePersonalityCode(CategoryQuestions category) {
        if (category.equals(categories[0])) {
            return "E";
        } else if (category.equals(categories[1])) {
            return "N";
        } else if (category.equals(categories[2])) {
            return "F";
        } else if (category.equals(categories[3])) {
            return "P";
        }
        return "";
    }
}