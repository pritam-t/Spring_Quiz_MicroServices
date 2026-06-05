package com.pritam.question_service.Service;


import com.pritam.question_service.DAO.QuestionsDAO;
import com.pritam.question_service.Model.QuestionWrapper;
import com.pritam.question_service.Model.Questions;
import com.pritam.question_service.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService
{
    @Autowired
    QuestionsDAO questionsDAO;

    public ResponseEntity<List<Questions>> getAllQuestions()
    {
        try{
            return new ResponseEntity<>(questionsDAO.findAll(), HttpStatus.OK);
        }
       catch(Exception e){
            e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }


    public ResponseEntity<List<Questions>> getQuestionsByCategory(String category)
    {
        try{
            return new ResponseEntity<>(questionsDAO.findByCategoryIgnoreCase(category),HttpStatus.OK);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Questions question)
    {
        try{
            questionsDAO.save(question);
            return new ResponseEntity<>("Question added successfully",HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<List<Integer>> getQuestionsForQuiz(String category, Integer numQuestions)
    {
        List<Integer> questions=  questionsDAO.findRandomQuestionByCategory(category,numQuestions);
        return new ResponseEntity<>(questions,HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionFromID(List<Integer> questionsids)
    {
        List<QuestionWrapper> wrappers= new ArrayList<>();
        List<Questions> questions= new ArrayList<>();

        for(Integer id:questionsids)
        {
            questions.add(questionsDAO.findById(id).get());
        }

        for(Questions question:questions)
        {
            QuestionWrapper wrapper = new QuestionWrapper();
            wrapper.setId(question.getId());
            wrapper.setQuestion_title(question.getQuestion_title());
            wrapper.setOption1(question.getOption1());
            wrapper.setOption2(question.getOption2());
            wrapper.setOption3(question.getOption3());
            wrappers.add(wrapper);
        }
        return new ResponseEntity<>(wrappers,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses)
    {
        int right=0;
        for(Response r:responses)
        {
            Questions question = questionsDAO.findById(r.getId()).get();
            if(r.getResponse().equals(question.getRight_answer()))
                right++;
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
