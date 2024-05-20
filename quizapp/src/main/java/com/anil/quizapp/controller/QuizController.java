package com.anil.quizapp.controller;

import com.anil.quizapp.entity.Quiz;
import com.anil.quizapp.entity.QuizTest;
import com.anil.quizapp.entity.Response;
import com.anil.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestParam String category, @RequestParam int numQ, @RequestParam String quizTitle){

        return quizService.createQuiz(category, numQ, quizTitle);
    }

    @GetMapping("quizByTitle/{quizTitle}")
    public ResponseEntity<Quiz>getQuizByTitle(@PathVariable String quizTitle){
        return quizService.getQuizByTitle(quizTitle);
    }

    @GetMapping("quizTestById/{id}")
    public ResponseEntity<List<QuizTest>>getQuizTestById(@PathVariable int id){

        return quizService.getQuizTestById(id);
    }

    @PostMapping("submitQuiz/{id}")
    public ResponseEntity<Integer>submitQuiz(@PathVariable int id, @RequestBody List<Response>responses){
        return quizService.submitQuiz(id, responses);
    }
}
