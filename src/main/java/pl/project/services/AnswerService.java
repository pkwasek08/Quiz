package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.Answer;
import pl.project.repositories.AnswerRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    public List<Answer> getAllAnswer() {
        List<Answer> answerList = new ArrayList<>();
        answerRepository.findAll().forEach(answerList::add);
        return answerList;
    }

    public Answer getAnswer(Integer id) {
        Answer answer = answerRepository.findById(id).get();
        return answer;
    }

    public void addAnswer(Answer answer) {
        answerRepository.save(answer);
    }


    public void updateAnswer(Integer id, Answer answer) {
        answerRepository.save(answer);
    }


    public void deleteAnswer(Integer id) {
        answerRepository.deleteById(id);
    }
}
