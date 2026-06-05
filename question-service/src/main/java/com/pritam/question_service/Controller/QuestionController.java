package com.pritam.question_service.Controller;


import com.pritam.question_service.Model.QuestionWrapper;
import com.pritam.question_service.Model.Questions;
import com.pritam.question_service.Model.Response;
import com.pritam.question_service.Service.QuestionService;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    private Environment environment;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Questions>> allQuestions()
    {
        return questionService.getAllQuestions();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category)
    {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addQuestion(@RequestBody Questions question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> generateQuestionforQuiz(@RequestParam String category, @RequestParam Integer numQuestions)
    {
        return questionService.getQuestionsForQuiz(category,numQuestions);
    }

    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromID(@RequestBody List<Integer> questionsids)
    {
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionFromID(questionsids);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses)
    {
        return questionService.getScore(responses);
    }


}
