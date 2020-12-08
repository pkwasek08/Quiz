package pl.project.Task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<Task> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/test")
    @CrossOrigin(origins = "*")
    public List<Task> getAllTaskByTestId(@RequestParam Integer testId) {
        return taskService.getAllTaskByTestId(testId);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Task getTask(@PathVariable Integer id) {
        return taskService.getTask(id);
    }

    @GetMapping("/generateTest")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Object> getGenerateTaskAndAnswersAndUserId(@RequestParam Integer testId, @RequestParam Integer amountTasks, Integer userId) {
        List<TaskDTO> listTask = taskService.getGenerateTaskAndAnswers(testId, amountTasks, userId);
        if (!listTask.isEmpty()) {
            return ResponseEntity
                    .ok()
                    .body(listTask);
        } else {
           return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED).body("The user has already completed the test.");
        }
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addTaskAndAnswers(@RequestBody TaskDTO taskDTO) {
        taskService.addTask(taskDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateTask(@RequestBody TaskDTO taskDTO, @PathVariable Integer id) {
        taskService.updateTask(id, taskDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteTask(@PathVariable Integer id) {
        taskService.deleteTask(id);
    }
}
