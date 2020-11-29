package pl.project.ChosenAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.project.Answer.AnswerRepository;
import pl.project.GenerateTask.GenerateTask;
import pl.project.GenerateTask.GenerateTaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChosenAnswerService {
    @Autowired
    private ChosenAnswerRepository chosenAnswerRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private GenerateTaskRepository generateTaskRepository;

    public List<ChosenAnswer> getAllChosenAnswer() {
        List<ChosenAnswer> chosenAnswerList = new ArrayList<>();
        chosenAnswerRepository.findAll().forEach(chosenAnswerList::add);
        return chosenAnswerList;
    }

    public ChosenAnswer getChosenAnswer(Integer id) {
        ChosenAnswer chosenAnswer = chosenAnswerRepository.findById(id).get();
        return chosenAnswer;
    }

    public void addChosenAnswer(ChosenAnswerDTO chosenAnswerDTO) {
        ChosenAnswer chosenAnswer = new ChosenAnswer(0, chosenAnswerDTO.getDescriptedAnswer(), generateTaskRepository.findById(chosenAnswerDTO.getGenerateTaskId()).get(), answerRepository.findById(chosenAnswerDTO.getAnswerId()).get());
        chosenAnswerRepository.save(chosenAnswer);
    }


    public void updateChosenAnswer(Integer id, ChosenAnswerDTO chosenAnswerDTO) {
        ChosenAnswer chosenAnswer = new ChosenAnswer(chosenAnswerDTO.getId(), chosenAnswerDTO.getDescriptedAnswer(), generateTaskRepository.findById(chosenAnswerDTO.getGenerateTaskId()).get(), answerRepository.findById(chosenAnswerDTO.getAnswerId()).get());
        chosenAnswerRepository.save(chosenAnswer);
    }

    public void deleteChosenAnswer(Integer id) {
        chosenAnswerRepository.deleteById(id);
    }
}
