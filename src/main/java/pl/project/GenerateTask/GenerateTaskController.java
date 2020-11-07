package pl.project.GenerateTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/generateTask")
public class GenerateTaskController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private GenerateTaskService generateTaskService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<GenerateTask> getGenerateTask() {
        return generateTaskService.getAllGenerateTask();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public GenerateTask getGenerateTask(@PathVariable Integer id) {
        return generateTaskService.getGenerateTask(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addGenerateTask(@RequestBody GenerateTask generateTask) {
        generateTaskService.addGenerateTask(generateTask);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateGenerateTask(@RequestBody GenerateTask generateTask, @PathVariable Integer id) {
        generateTaskService.updateGenerateTask(id, generateTask);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteGenerateTask(@PathVariable Integer id) {
        generateTaskService.deleteGenerateTask(id);
    }
}
