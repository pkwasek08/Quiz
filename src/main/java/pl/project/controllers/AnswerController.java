package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.Answer;
import pl.project.services.AnswerService;

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

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Answer getAnswer(@PathVariable Integer id) {
        return AnswerService.getAnswer(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addAnswer(@RequestBody Answer answer) {
        AnswerService.addAnswer(answer);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateAnswer(@RequestBody Answer answer, @PathVariable Integer id) {
        AnswerService.updateAnswer(id, answer);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteAnswer(@PathVariable Integer id) {
        AnswerService.deleteAnswer(id);
    }
}
