package pl.project.Answer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/answer")
public class AnswerController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private AnswerService AnswerService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Answer> getAnswer() {
        return AnswerService.getAllAnswer();
    }

    @GetMapping("/task/{taskId}")
    @CrossOrigin(origins = "*")
    public List<Answer> getAnswersByTask(@PathVariable int taskId) {
        return AnswerService.getAllAnswerByTask(taskId);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Answer getAnswer(@PathVariable Integer id) {
        return AnswerService.getAnswer(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addAnswer(@RequestBody AnswerDTO answerDTO) {
        AnswerService.addAnswer(answerDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateAnswer(@RequestBody AnswerDTO answerDTO, @PathVariable Integer id) {
        AnswerService.updateAnswer(id, answerDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteAnswer(@PathVariable Integer id) {
        AnswerService.deleteAnswer(id);
    }
}
