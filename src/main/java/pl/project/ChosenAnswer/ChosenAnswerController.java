package pl.project.ChosenAnswer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/chosenAnswer")
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
    public void addChosenAnswer(@RequestBody ChosenAnswerDTO chosenAnswerDTO) {
        chosenAnswerService.addChosenAnswer(chosenAnswerDTO);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateChosenAnswer(@RequestBody ChosenAnswerDTO chosenAnswerDTO, @PathVariable Integer id) {
        chosenAnswerService.updateChosenAnswer(id, chosenAnswerDTO);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteChosenAnswer(@PathVariable Integer id) {
        chosenAnswerService.deleteChosenAnswer(id);
    }
}
