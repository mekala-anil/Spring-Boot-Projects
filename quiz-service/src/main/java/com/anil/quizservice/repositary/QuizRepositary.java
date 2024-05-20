package com.anil.quizservice.repositary;


import com.anil.quizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepositary extends JpaRepository<Quiz, Integer>
{
    Quiz findByquizTitle(String quizTitle);
    Quiz findById(int id);
}
