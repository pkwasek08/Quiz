package pl.project.Result;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.project.Answer.AnswerDTO;

import java.util.List;

@RestController
@RequestMapping(value = "/result")
public class ResultController {
    Logger log = LogManager.getLogger(this.getClass());
    @Autowired
    private ResultService resultService;

    @GetMapping()
    @CrossOrigin(origins = "*")
    public List<Result> getResult() {
        return resultService.getAllResult();
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins = "*")
    public Result getResult(@PathVariable Integer id) {
        return resultService.getResult(id);
    }

    @GetMapping("/user/answerList")
    @CrossOrigin(origins = "*")
    public Result getResultByUserIdAndGenerateTestIdAndAnswerList(@PathVariable Integer userId, @PathVariable Integer generateTestId, @PathVariable List<AnswerDTO> answerList) {
        return resultService.getResultByUserIdAndGenerateTestIdAndAnswerList(generateTestId, answerList, userId);
    }
    @GetMapping("/user/answerList/nextTerm")
    @CrossOrigin(origins = "*")
    public Result getNextTermResultByResultIdAndAnswerList(@PathVariable Integer resultId, @PathVariable List<AnswerDTO> answerList) {
        return resultService.getNextTermResultByResultIdAndAnswerList(resultId, answerList);
    }

    @PostMapping()
    @CrossOrigin(origins = "*")
    public void addResult(@RequestBody ResultDTO result) {
        resultService.addResult(result);
    }

    @PutMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void updateResult(@RequestBody Result result, @PathVariable Integer id) {
        resultService.updateResult(id, result);
    }

    @DeleteMapping(value = "/{id}")
    @CrossOrigin(origins = "*")
    public void deleteResult(@PathVariable Integer id) {
        resultService.deleteResult(id);
    }
}
