package com.anil.quizapp.repositary;

import com.anil.quizapp.entity.Question;
import com.anil.quizapp.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepositary extends JpaRepository<Quiz, Integer>
{
    Quiz findByquizTitle(String quizTitle);
    Quiz findById(int id);
}
