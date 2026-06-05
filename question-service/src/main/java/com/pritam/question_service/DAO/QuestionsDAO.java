package com.pritam.question_service.DAO;

import com.pritam.question_service.Model.Questions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsDAO extends JpaRepository<Questions, Integer>
{
    List<Questions> findByCategoryIgnoreCase(String category);

    @Query(
            value = "SELECT q.id FROM questions q WHERE q.category = :category ORDER BY RANDOM() LIMIT :nofqts",
            nativeQuery = true
    )
    List<Integer> findRandomQuestionByCategory(
            String category,
            int nofqts
    );
}
