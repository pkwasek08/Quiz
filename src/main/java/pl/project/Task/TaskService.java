package pl.project.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.project.Answer.AnswerService;
import pl.project.Test.TestRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private AnswerService answerService;

    public List<Task> getAllTask() {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(taskList::add);
        return taskList;
    }

    public Task getTask(Integer id) {
        Task task = taskRepository.findById(id).get();
        return task;
    }

    public void addTask(TaskDTO taskDTO) {
        Task task = taskRepository.save(new Task(0, taskDTO.getQuestion(), taskDTO.getType(), taskDTO.getImage(), taskDTO.getPoints(), testRepository.findById(taskDTO.getTestId()).get()));
        taskDTO.getAnswerList().stream().forEach(answer -> {
            answer.setTaskId(task.getId());
            answerService.addAnswer(answer);
        });
    }


    public void updateTask(Integer id, TaskDTO taskDTO) {
        Task task = new Task(taskDTO.getId(), taskDTO.getQuestion(), taskDTO.getType(), taskDTO.getImage(), taskDTO.getPoints(), testRepository.findById(taskDTO.getTestId()).get());
        taskRepository.save(task);
        taskDTO.getAnswerList().stream().forEach(answer -> {
            answer.setTaskId(task.getId());
            answerService.updateAnswer(answer.getId(), answer);
        });
    }


    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
