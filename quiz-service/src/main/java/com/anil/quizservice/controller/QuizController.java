package com.anil.quizservice.controller;


import com.anil.quizservice.entity.Quiz;
import com.anil.quizservice.entity.QuizDto;
import com.anil.quizservice.entity.QuizTest;
import com.anil.quizservice.entity.Response;
import com.anil.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz-service")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String>createQuiz(@RequestBody QuizDto quizDto){

        return quizService.createQuiz(quizDto.getCategory(),quizDto.getNumQ(),quizDto.getQuizTitle());
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
