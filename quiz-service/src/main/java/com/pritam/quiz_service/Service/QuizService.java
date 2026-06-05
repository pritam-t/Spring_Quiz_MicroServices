package com.pritam.quiz_service.Service;


import com.pritam.quiz_service.DAO.QuizDAO;
import com.pritam.quiz_service.Feign.QuizInterface;
import com.pritam.quiz_service.Model.QuestionWrapper;
import com.pritam.quiz_service.Model.Quiz;
import com.pritam.quiz_service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizdao;
    @Autowired
    QuizInterface quizInterface;



    public ResponseEntity<String> createQuiz(String category, int nofqts, String title)
    {

        List<Integer> questions = quizInterface.generateQuestionforQuiz(category,nofqts).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestions(questions);
        quizdao.save(quiz);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id)
    {
        Optional<Quiz> quiz= quizdao.findById(id);
        List<Integer> questionIds= quiz.get().getQuestions();
        quizInterface.getQuestionsFromID(questionIds);
        ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromID(questionIds);
        return questions;
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses)
    {
        ResponseEntity<Integer> score= quizInterface.getScore(responses);
        return score;
    }
}
