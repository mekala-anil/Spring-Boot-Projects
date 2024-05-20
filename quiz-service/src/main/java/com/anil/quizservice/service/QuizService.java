package com.anil.quizservice.service;


import com.anil.quizservice.entity.Quiz;
import com.anil.quizservice.entity.QuizTest;
import com.anil.quizservice.entity.Response;
import com.anil.quizservice.feign.QuizInterface;
import com.anil.quizservice.repositary.QuizRepositary;
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

//    @Autowired
//    QuestionRepositary questionRepositary;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String quizTitle) {

        List<Integer>questionIds=quizInterface.generateQuestionsForQuiz(category, numQ).getBody();
        System.out.println("Quiz-Service Qn Ids :"+questionIds);

        Quiz quiz=new Quiz();
        quiz.setQuizTitle(quizTitle);
        quiz.setCategory(category);
        quiz.setQuestions(questionIds);

        quizRepositary.save(quiz);

//        List<Question> questions=questionRepositary.createRandomQuestionsByCategory(category, numQ);
//
//        System.out.println("Quiz Questions :"+questions);
//        System.out.println("Questions Length :"+questions.size());
//
//        Quiz quiz=new Quiz();
//        quiz.setQuizTitle(quizTitle);
//        quiz.setCategory(category);
//        quiz.setQuestions(questions);
//
//        quizRepositary.save(quiz);

        return new ResponseEntity<>("quiz created", HttpStatus.CREATED);
    }

    public ResponseEntity<Quiz> getQuizByTitle(String quizTitle) {

        return new ResponseEntity<>(quizRepositary.findByquizTitle(quizTitle),HttpStatus.OK);
    }

    public ResponseEntity<List<QuizTest>> getQuizTestById(int id) {
        Quiz quiz=quizRepositary.findById(id);

        List<Integer>qnIds=quiz.getQuestions();

        ResponseEntity<List<QuizTest>> quizTestList=quizInterface.getQuizTestFormatByIds(qnIds);

        System.out.println(quizTestList);

        //List<QuizTest>quizTestList=new ArrayList<>();
//        for(Question q:quiz.getQuestions()){
//            QuizTest qt=new QuizTest(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
//            quizTestList.add(qt);
//        }
        return quizTestList;
    }

    public ResponseEntity<Integer> submitQuiz(int id, List<Response>responses) {



//        Quiz quiz=quizRepositary.findById(id);
//        List<Question>correctQuestions=quiz.getQuestions();
//        int i=0;
        int correct=quizInterface.getScore(responses).getBody();
//        for(Response response:responses){
//            if(response.getAnswer().equals(correctQuestions.get(i).getRightAnswer()))
//                correct++;
//            i++;
//        }

        return new ResponseEntity<>(correct,HttpStatus.OK);
    }
}
