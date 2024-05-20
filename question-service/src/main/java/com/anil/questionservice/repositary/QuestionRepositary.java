package com.anil.questionservice.repositary;


import com.anil.questionservice.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepositary extends JpaRepository<Question, Integer>
{
    List<Question>findByCategory(String category);
    Question findById(int id);

    @Query(value = "SELECT id FROM question WHERE category=?1 ORDER BY RAND() LIMIT ?2",nativeQuery = true)
    List<Integer> createRandomQuestionsByCategory(String category, int numQ);

    @Query(value = "SElECT * FROM question WHERE id IN(?1)", nativeQuery = true)
    List<Question> findQuestionsByIds(List<Integer> ids);
}
