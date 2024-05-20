package com.anil.quizservice.feign;

import com.anil.quizservice.entity.QuizTest;
import com.anil.quizservice.entity.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("question-service/generate")
    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(@RequestParam String category, @RequestParam int numQ);

    @PostMapping("question-service/getQuizTestFormatQuestions")
    public ResponseEntity<List<QuizTest>>getQuizTestFormatByIds(@RequestBody List<Integer>ids);

    @PostMapping("question-service/getScore")
    public ResponseEntity<Integer>getScore(@RequestBody List<Response>responses);

}
