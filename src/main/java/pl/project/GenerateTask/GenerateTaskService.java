package pl.project.GenerateTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.project.ChosenAnswer.ChosenAnswer;
import pl.project.GenerateTest.GenerateTest;
import pl.project.GenerateTest.GenerateTestRepository;
import pl.project.Task.TaskDTO;
import pl.project.Task.TaskRepository;
import pl.project.Test.TestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateTaskService {
    @Autowired
    private GenerateTaskRepository generateTaskRepository;

    @Autowired
    private GenerateTestRepository generateTestRepository;

    @Autowired
    private TaskRepository taskRepository;

    public List<GenerateTask> getAllGenerateTask() {
        List<GenerateTask> generateTaskList = new ArrayList<>();
        generateTaskRepository.findAll().forEach(generateTaskList::add);
        return generateTaskList;
    }

    public GenerateTask getGenerateTask(Integer id) {
        GenerateTask generateTask = generateTaskRepository.findById(id).get();
        return generateTask;
    }

    public GenerateTask addGenerateTask(GenerateTaskDTO generateTaskDTO) {
        GenerateTask generateTask = new GenerateTask(0, taskRepository.findById(generateTaskDTO.getTaskId()).get(), generateTestRepository.findById(generateTaskDTO.getGenerateTestId()).get());
        return generateTaskRepository.save(generateTask);
    }


    public void updateGenerateTask(Integer id, GenerateTaskDTO generateTaskDTO) {
        GenerateTask generateTask = new GenerateTask(generateTaskDTO.getId(), taskRepository.findById(generateTaskDTO.getTaskId()).get(), generateTestRepository.findById(generateTaskDTO.getGenerateTestId()).get());
        generateTaskRepository.save(generateTask);
    }

    public List<GenerateTaskAndAnswerDTO> getGenerateTasksByGenerateTestId(Integer id){
        List<GenerateTask> generateTasks = generateTaskRepository.findAllByGenerateTestsByGenerateTest_Id(id);
        List<GenerateTaskAndAnswerDTO> generateTaskAndAnswerDTO = new ArrayList<>();
        for(GenerateTask generateTask: generateTasks){
            generateTaskAndAnswerDTO.add(new GenerateTaskAndAnswerDTO(generateTask.getChosenAnswers(), new TaskDTO(generateTask.getTasksByTaskId())));
        }
        return  generateTaskAndAnswerDTO;
    }


    public void deleteGenerateTask(Integer id) {
        generateTaskRepository.deleteById(id);
    }
}
