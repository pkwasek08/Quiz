package pl.project.Task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private TaskService taskService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Task> getTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Task getTask(@PathVariable Integer id) {
        return taskService.getTask(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addTask(@RequestBody Task task) {
        taskService.addTask(task);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateTask(@RequestBody Task task, @PathVariable Integer id) {
        taskService.updateTask(id, task);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
