package pl.project.ChosenAnswer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChosenAnswerService {
    @Autowired
    private ChosenAnswerRepository chosenAnswerRepository;

    public List<ChosenAnswer> getAllChosenAnswer() {
        List<ChosenAnswer> chosenAnswerList = new ArrayList<>();
        chosenAnswerRepository.findAll().forEach(chosenAnswerList::add);
        return chosenAnswerList;
    }

    public ChosenAnswer getChosenAnswer(Integer id) {
        ChosenAnswer chosenAnswer = chosenAnswerRepository.findById(id).get();
        return chosenAnswer;
    }

    public void addChosenAnswer(ChosenAnswer chosenAnswer) {
        chosenAnswerRepository.save(chosenAnswer);
    }


    public void updateChosenAnswer(Integer id, ChosenAnswer chosenAnswer) {
        chosenAnswerRepository.save(chosenAnswer);
    }


    public void deleteChosenAnswer(Integer id) {
        chosenAnswerRepository.deleteById(id);
    }
}
