package com.anil.quizapp.repositary;

import com.anil.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepositary extends JpaRepository<Question, Integer>
{
    List<Question>findByCategory(String category);
    Question findById(int id);

    @Query(value = "SELECT * FROM question WHERE category=?1 ORDER BY RAND() LIMIT ?2",nativeQuery = true)
    List<Question> createRandomQuestionsByCategory(String category, int numQ);
}
