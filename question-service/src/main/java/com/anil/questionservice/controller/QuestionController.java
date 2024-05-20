package com.anil.questionservice.controller;


import com.anil.questionservice.entity.Question;
import com.anil.questionservice.entity.QuizTest;
import com.anil.questionservice.entity.Response;
import com.anil.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question-service")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    int hits=0;

    @GetMapping("allQuestions")
    public ResponseEntity<List<Question>> getAllQuestions(){

        return questionService.allQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>>getQuestionsByCategory(@PathVariable String category){
        System.out.println("Category :"+category);
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question){
        System.out.println("Question :"+question);
        return questionService.addQuestion(question);
    }

    @PostMapping("addQuestionsByList")
    public ResponseEntity<String> addQuestionsByList(@RequestBody List<Question>questions){
        System.out.println("Questions List :"+questions);
        return questionService.addQuestionsByList(questions);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id){
        System.out.println("Id :"+id);
        return questionService.deleteById(id);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>>generateQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ){
        return questionService.generateQuestionsForQuiz(category, numQ);
    }

    @PostMapping("getQuizTestFormatQuestions")
    public ResponseEntity<List<QuizTest>>getQuizTestFormatByIds(@RequestBody List<Integer>ids){
        hits++;
        System.out.println("Hitted :"+hits+", Port :"+environment.getProperty("local.server.port"));

        return questionService.getQuizTestFormatByIds(ids);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response>responses){
        return questionService.getScore(responses);
    }
}
