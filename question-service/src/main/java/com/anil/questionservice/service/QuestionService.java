package com.anil.questionservice.service;

import com.anil.questionservice.entity.Question;
import com.anil.questionservice.entity.QuizTest;
import com.anil.questionservice.entity.Response;
import com.anil.questionservice.repositary.QuestionRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionRepositary questionRepositary;
    public ResponseEntity<List<Question>>allQuestions(){
        try{
            return new ResponseEntity<>(questionRepositary.findAll(), HttpStatus.FOUND);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
        try{
            return new ResponseEntity<>(questionRepositary.findByCategory(category), HttpStatus.FOUND);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<Question>(), HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        System.out.println(questionRepositary.save(question));
        //questionRepositary.save(question);
        return new ResponseEntity<>("Added Question", HttpStatus.CREATED);
    }

    public ResponseEntity<String> addQuestionsByList(List<Question> questions) {
        try{
            questionRepositary.saveAll(questions);
            return new ResponseEntity<>("Added all Questions",HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't add all Questions",HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<String> deleteById(int id) {
        try{
            if(questionRepositary.findById(id)!=null){
                questionRepositary.deleteById(id);
                return new ResponseEntity<>("Deleted By Id :"+id+" ",HttpStatus.OK);
            }else{
                throw new Exception();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>("Couldn't delete",HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<String> updateQuestion(int id, Question question) {
        try{
            Question old=questionRepositary.findById(id);
            if(question.getCategory()!=null)
                old.setCategory(question.getCategory());
            if(question.getDifficultyLevel()!=null)
                old.setDifficultyLevel(question.getDifficultyLevel());
            if(question.getOption1()!=null)
                old.setOption1(question.getOption1());
            if(question.getOption2()!=null)
                old.setOption2(question.getOption2());
            if(question.getOption3()!=null)
                old.setOption3(question.getOption3());
            if(question.getOption4()!=null)
                old.setOption4(question.getOption4());
            if(question.getQuestionTitle()!=null)
                old.setQuestionTitle(question.getQuestionTitle());
            if(question.getRightAnswer()!=null)
                old.setRightAnswer(question.getRightAnswer());
            questionRepositary.save(old);

            return new ResponseEntity<>("Updated :"+id+"",HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>("Couldn't Update",HttpStatus.NOT_ACCEPTABLE);
    }

    public ResponseEntity<List<Integer>> generateQuestionsForQuiz(String category, int numQ) {
        List<Integer> questions=questionRepositary.createRandomQuestionsByCategory(category, numQ);

        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

    public ResponseEntity<List<QuizTest>> getQuizTestFormatByIds(List<Integer> ids) {

        List<QuizTest>quizTestList=new ArrayList<>();
        List<Question>questions=new ArrayList<>();

        questions=questionRepositary.findQuestionsByIds(ids);

        for(Question question:questions){
            QuizTest tmp=new QuizTest(question.getId(),question.getQuestionTitle(),question.getOption1(),question.getOption2(),question.getOption3(),question.getOption4());
            quizTestList.add(tmp);
        }

        //System.out.println("Hitted :"+quizTestList);
        return new ResponseEntity<>(quizTestList, HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        int correct=0;
        for(Response response:responses){
            Question question=questionRepositary.findById(response.getId());
            if(response.getAnswer().equalsIgnoreCase(question.getRightAnswer()))
                correct++;
        }
        return new ResponseEntity<>(correct, HttpStatus.OK);
    }
}
