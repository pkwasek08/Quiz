package pl.project.GenerateTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.GenerateTest.GenerateTestRepository;
import pl.project.Task.Task;
import pl.project.Task.TaskDTO;
import pl.project.Task.TaskService;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenerateTaskService {
    @Autowired
    private GenerateTaskRepository generateTaskRepository;
    @Autowired
    private GenerateTestRepository generateTestRepository;
    @Autowired
    private TaskService taskService;

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
        GenerateTask generateTask = new GenerateTask(0, taskService.getTask(generateTaskDTO.getTaskId()), generateTestRepository.findById(generateTaskDTO.getGenerateTestId()).get());
        return generateTaskRepository.save(generateTask);
    }


    public void updateGenerateTask(Integer id, GenerateTaskDTO generateTaskDTO) {
        GenerateTask generateTask = new GenerateTask(generateTaskDTO.getId(), taskService.getTask(generateTaskDTO.getTaskId()), generateTestRepository.findById(generateTaskDTO.getGenerateTestId()).get());
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

    public Integer getPointsByGenerateTaskId(Integer taskId) {
        Task task = getGenerateTask(taskId).getTasksByTaskId();
        return taskService.getPointsByTaskId(task.getId());
    }


    public void deleteGenerateTask(Integer id) {
        generateTaskRepository.deleteById(id);
    }
}
