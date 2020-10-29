package pl.project.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.entities.ChosenAnswer;
import pl.project.services.ChosenAnswerService;

import java.util.List;

@RestController
@RequestMapping(value = "/v")
public class ChosenAnswerController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ChosenAnswerService chosenAnswerService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<ChosenAnswer> getChosenAnswer() {
        return chosenAnswerService.getAllChosenAnswer();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public ChosenAnswer getChosenAnswer(@PathVariable Integer id) {
        return chosenAnswerService.getChosenAnswer(id);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addChosenAnswer(@RequestBody ChosenAnswer chosenAnswer) {
        chosenAnswerService.addChosenAnswer(chosenAnswer);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateChosenAnswer(@RequestBody ChosenAnswer chosenAnswer, @PathVariable Integer id) {
        chosenAnswerService.updateChosenAnswer(id, chosenAnswer);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteChosenAnswer(@PathVariable Integer id) {
        chosenAnswerService.deleteChosenAnswer(id);
    }
}
