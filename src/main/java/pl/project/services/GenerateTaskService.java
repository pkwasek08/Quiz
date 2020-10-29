package pl.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.entities.GenerateTask;
import pl.project.repositories.GenerateTaskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateTaskService {
    @Autowired
    private GenerateTaskRepository generateTaskRepository;

    public List<GenerateTask> getAllGenerateTask() {
        List<GenerateTask> generateTaskList = new ArrayList<>();
        generateTaskRepository.findAll().forEach(generateTaskList::add);
        return generateTaskList;
    }

    public GenerateTask getGenerateTask(Integer id) {
        GenerateTask generateTask = generateTaskRepository.findById(id).get();
        return generateTask;
    }

    public void addGenerateTask(GenerateTask generateTask) {
        generateTaskRepository.save(generateTask);
    }


    public void updateGenerateTask(Integer id, GenerateTask generateTask) {
        generateTaskRepository.save(generateTask);
    }


    public void deleteGenerateTask(Integer id) {
        generateTaskRepository.deleteById(id);
    }
}
