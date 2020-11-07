package pl.project.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTask() {
        List<Task> taskList = new ArrayList<>();
        taskRepository.findAll().forEach(taskList::add);
        return taskList;
    }

    public Task getTask(Integer id) {
        Task task = taskRepository.findById(id).get();
        return task;
    }

    public void addTask(Task task) {
        taskRepository.save(task);
    }


    public void updateTask(Integer id, Task task) {
        taskRepository.save(task);
    }


    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }
}
