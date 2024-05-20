package com.anil.quizapp.controller;

import com.anil.quizapp.entity.Question;
import com.anil.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

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
}
