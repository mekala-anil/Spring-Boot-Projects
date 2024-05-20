package com.anil.quizapp.service;

import com.anil.quizapp.entity.Question;
import com.anil.quizapp.entity.Quiz;
import com.anil.quizapp.entity.QuizTest;
import com.anil.quizapp.entity.Response;
import com.anil.quizapp.repositary.QuestionRepositary;
import com.anil.quizapp.repositary.QuizRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizRepositary quizRepositary;

    @Autowired
    QuestionRepositary questionRepositary;

    public ResponseEntity<String> createQuiz(String category, int numQ, String quizTitle) {

        List<Question> questions=questionRepositary.createRandomQuestionsByCategory(category, numQ);

        System.out.println("Quiz Questions :"+questions);
        System.out.println("Questions Length :"+questions.size());

        Quiz quiz=new Quiz();
        quiz.setQuizTitle(quizTitle);
        quiz.setCategory(category);
        quiz.setQuestions(questions);

        quizRepositary.save(quiz);

        return new ResponseEntity<>("quiz creating", HttpStatus.CREATED);
    }

    public ResponseEntity<Quiz> getQuizByTitle(String quizTitle) {

        return new ResponseEntity<>(quizRepositary.findByquizTitle(quizTitle),HttpStatus.OK);
    }

    public ResponseEntity<List<QuizTest>> getQuizTestById(int id) {
        Quiz quiz=quizRepositary.findById(id);

        List<QuizTest>quizTestList=new ArrayList<>();
        for(Question q:quiz.getQuestions()){
            QuizTest qt=new QuizTest(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            quizTestList.add(qt);
        }
        return new ResponseEntity<>(quizTestList,HttpStatus.CREATED);
    }

    public ResponseEntity<Integer> submitQuiz(int id, List<Response>responses) {
        Quiz quiz=quizRepositary.findById(id);
        List<Question>correctQuestions=quiz.getQuestions();
        int i=0;
        int correct=0;
        for(Response response:responses){
            if(response.getAnswer().equals(correctQuestions.get(i).getRightAnswer()))
                correct++;
            i++;
        }

        return new ResponseEntity<>(correct,HttpStatus.OK);
    }
}
